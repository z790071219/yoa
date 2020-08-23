package com.yoa.config.shiro;

import com.yoa.model.Permissions;
import com.yoa.model.Role;
import com.yoa.service.RoleService;
import com.yoa.shiro.ShiroUser;
import com.yoa.util.ApplicationContextRegister;
import com.yoa.util.Global;
import com.yoa.model.User;
import com.yoa.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component(value = "authRealm")
public class YoaShiroRealm extends AuthorizingRealm {

    private Logger logger= LoggerFactory.getLogger(getClass());
    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Global.HASH_ALGORITHM);
        matcher.setHashIterations(Global.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
        logger.info("---------密码校验器  -matcher----------------");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginName=((UsernamePasswordToken) token).getUsername();
        UserService userService = ApplicationContextRegister.getBean(UserService.class);
        User user = userService.selectUserByUserName(loginName);
        if (user != null) {
            logger.info(user.getUserName()+"登录");
            if (user.getDisable()) {
                throw new DisabledAccountException();
            }
            return new SimpleAuthenticationInfo(new ShiroUser(user.getId(),user.getUserName(),user.getFullName()), user.getHashPwd(),ByteSource.Util.bytes(user.getUserName()),
                    getName());
        } else {
            logger.info(loginName+"登录失败");
            throw new UnknownAccountException();
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser user = (ShiroUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        RoleService sysRoleService = ApplicationContextRegister.getBean(RoleService.class);

        for (Role role : sysRoleService.selectRolesByUserId(user.getId())) {
            // 基于Role的权限信息
            info.addRole(role.getId().toString());
            // 基于Permission的权限信息
            List<String> permissionIds=new ArrayList<>();
            for (Permissions permissions: role.getPermissions()){
                permissionIds.add(permissions.getId().toString());
            }
            info.addStringPermissions(permissionIds);
        }
        return info;
    }
}
