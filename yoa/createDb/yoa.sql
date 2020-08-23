/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : yoa

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-05-27 01:11:14
*/
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `yoa_approve_flow`
-- ----------------------------
DROP TABLE IF EXISTS `yoa_approve_flow`;
CREATE TABLE `yoa_approve_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_id` int(11) DEFAULT NULL COMMENT '公文类型id',
  `has_dept_limit` tinyint(1) DEFAULT NULL COMMENT '部门限制',
  `role_id` int(11) DEFAULT NULL COMMENT '批核人角色id',
  `tier` int(11) DEFAULT NULL COMMENT '当前层级',
  `is_last` tinyint(1) DEFAULT NULL COMMENT '最后批核',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_person` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_8` (`type_id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`type_id`) REFERENCES `yoa_document_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='公文审批流程表';

-- ----------------------------
-- Records of yoa_approve_flow
-- ----------------------------
INSERT INTO `yoa_approve_flow` VALUES ('8', '4', '1', '8', '1', '1', '2019-05-16', 'zhangsan');
INSERT INTO `yoa_approve_flow` VALUES ('9', '1', '1', '9', '1', '0', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_approve_flow` VALUES ('10', '1', '0', '8', '2', '0', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_approve_flow` VALUES ('11', '1', '1', '8', '3', '1', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_approve_flow` VALUES ('12', '5', '1', '8', '1', '1', '2019-05-20', 'zhangsan');

-- ----------------------------
-- Table structure for `yoa_department`
-- ----------------------------
DROP TABLE IF EXISTS `yoa_department`;
CREATE TABLE `yoa_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '部门名称',
  `description` varchar(50) DEFAULT NULL COMMENT '部门描述',
  `node` int(11) DEFAULT NULL COMMENT '所属节点',
  `parent_node` int(11) DEFAULT NULL COMMENT '父级节点',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_person` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='部门信息表';

-- ----------------------------
-- Records of yoa_department
-- ----------------------------
INSERT INTO `yoa_department` VALUES ('1', 'market', '市场部1', '22222', null, '2019-05-07', '张三');
INSERT INTO `yoa_department` VALUES ('9', 'sale', '销售部', '0', '-1', '2019-05-01', '9');
INSERT INTO `yoa_department` VALUES ('10', 'sale2', '销售', '0', '-1', '2019-05-17', 'zhangsan');
INSERT INTO `yoa_department` VALUES ('11', 'sale1', '销售', '0', '-1', '2019-05-01', '9');

-- ----------------------------
-- Table structure for `yoa_document`
-- ----------------------------
DROP TABLE IF EXISTS `yoa_document`;
CREATE TABLE `yoa_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `type_id` int(11) DEFAULT NULL COMMENT '公文类型',
  `status` varchar(32) DEFAULT NULL COMMENT '批核状态',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  `role_id` int(11) DEFAULT NULL COMMENT '批核人角色id',
  `tier` int(11) DEFAULT NULL COMMENT '当前层级',
  `is_last` tinyint(1) DEFAULT NULL COMMENT '最后层级',
  `approver` varchar(50) DEFAULT NULL COMMENT '最后审批人',
  `comment` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_person` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` date DEFAULT NULL COMMENT 'date',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_person` varchar(50) DEFAULT NULL COMMENT '修改人',
  `has_dept_limit` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_7` (`type_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`type_id`) REFERENCES `yoa_document_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='公文信息表';

-- ----------------------------
-- Records of yoa_document
-- ----------------------------
INSERT INTO `yoa_document` VALUES ('2', 'tit123', 'con123', '1', 'refuse', '10', '6', '1', '1', '张三', 'con', 'zhangsan', '2015-01-01', '2019-05-16', '张三', '1');
INSERT INTO `yoa_document` VALUES ('6', '324', '2342342345如同仁堂', '1', 'active', '1', '6', '1', null, '', '', '孙二', '2019-05-16', '2019-05-16', '孙二', '1');
INSERT INTO `yoa_document` VALUES ('7', 'test222', '232分飞', '4', 'approved', '1', '8', '1', '1', 'zhaoliu', '', '孙二', '2019-05-16', '2019-05-16', 'zhaoliu', '1');
INSERT INTO `yoa_document` VALUES ('9', '123123', '123123', '4', 'active', '1', '8', '1', '1', '', '', 'zhaoliu', '2019-05-17', '2019-05-17', 'zhaoliu', '1');
INSERT INTO `yoa_document` VALUES ('10', '111', '11231', '1', 'active', '1', '6', '1', null, '', '', 'zhangsan', '2019-05-18', '2019-05-18', 'zhangsan', '1');
INSERT INTO `yoa_document` VALUES ('11', '1232312312312312312', '31231232', '1', 'approved', '1', '8', '3', '1', 'zhangsan', '', 'zhangsan', '2019-05-18', '2019-05-18', 'zhangsan', '1');
INSERT INTO `yoa_document` VALUES ('12', 'test111', 'rwerwerwerwe', '5', 'new', null, null, null, null, '', '', 'zhangsan', '2019-05-20', '2019-05-20', 'zhangsan', null);

-- ----------------------------
-- Table structure for `yoa_document_type`
-- ----------------------------
DROP TABLE IF EXISTS `yoa_document_type`;
CREATE TABLE `yoa_document_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_name` varchar(32) DEFAULT NULL COMMENT '类型名',
  `comment` varchar(100) DEFAULT NULL COMMENT '备注',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_person` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='公文类型表';

-- ----------------------------
-- Records of yoa_document_type
-- ----------------------------
INSERT INTO `yoa_document_type` VALUES ('1', 'type1', 'qwe', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_document_type` VALUES ('4', 'typeB', null, '2019-05-16', 'zhangsan');
INSERT INTO `yoa_document_type` VALUES ('5', 'typeC', null, '2019-05-20', 'zhangsan');

-- ----------------------------
-- Table structure for `yoa_inform`
-- ----------------------------
DROP TABLE IF EXISTS `yoa_inform`;
CREATE TABLE `yoa_inform` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `level` int(11) DEFAULT NULL COMMENT '紧急等级',
  `create_person` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_person` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='公告信息表';

-- ----------------------------
-- Records of yoa_inform
-- ----------------------------
INSERT INTO `yoa_inform` VALUES ('1', null, '23232', '323232', '0', null, null, '2019-05-17', 'zhangsan');
INSERT INTO `yoa_inform` VALUES ('3', null, 'sdf', 'sdf', '0', 'zhangsan', '2019-05-17', '2019-05-17', 'zhangsan');
INSERT INTO `yoa_inform` VALUES ('4', null, '234sfd', '2342323', '0', 'zhangsan', '2019-05-17', '2019-05-17', 'zhangsan');
INSERT INTO `yoa_inform` VALUES ('5', null, '234', '234', '0', 'zhangsan', '2019-05-17', '2019-05-17', 'zhangsan');
INSERT INTO `yoa_inform` VALUES ('7', null, '234234234234', '234', '0', 'zhangsan', '2019-05-17', '2019-05-17', 'zhangsan');
INSERT INTO `yoa_inform` VALUES ('8', null, '23423', '4234234', '0', 'zhangsan', '2019-05-17', '2019-05-17', 'zhangsan');
INSERT INTO `yoa_inform` VALUES ('9', null, '123', '123', '0', 'zhangsan', '2019-05-17', '2019-05-17', 'zhangsan');
INSERT INTO `yoa_inform` VALUES ('11', null, '第1集', '1', '0', 'zhangsan', '2019-05-18', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_inform` VALUES ('13', null, 'test1', '测试公告1', '0', 'zhangsan', '2019-05-18', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_inform` VALUES ('14', null, '323', '2323', '0', 'wangwu', '2019-05-20', '2019-05-20', 'wangwu');
INSERT INTO `yoa_inform` VALUES ('15', null, '测试公告1', '测试内容测试内容测试内容测试内容测试内容测试内容', '0', 'wangwu', '2019-05-22', '2019-05-22', 'wangwu');
INSERT INTO `yoa_inform` VALUES ('16', null, '测试公告2', '测试内容测试内容测试内容测试内容测试内容测试内容', '0', 'wangwu', '2019-05-22', '2019-05-22', 'wangwu');
INSERT INTO `yoa_inform` VALUES ('17', null, '测试公告3', '测试内容测试内容测试内容测试内容测试内容测试内容', '0', 'wangwu', '2019-05-22', '2019-05-22', 'wangwu');

-- ----------------------------
-- Table structure for `yoa_menu`
-- ----------------------------
DROP TABLE IF EXISTS `yoa_menu`;
CREATE TABLE `yoa_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '菜单名',
  `description` varchar(50) DEFAULT NULL COMMENT '菜单描述',
  `permissions_id` int(11) DEFAULT NULL COMMENT '访问所需权限',
  `url` varchar(100) DEFAULT NULL COMMENT '对应url',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `node` int(11) DEFAULT NULL COMMENT '所属节点',
  `parent_node` int(11) DEFAULT NULL COMMENT '父级节点',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_person` varchar(50) DEFAULT NULL COMMENT '修改人',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='菜单信息表';

-- ----------------------------
-- Records of yoa_menu
-- ----------------------------
INSERT INTO `yoa_menu` VALUES ('1', 'home', '系统', null, '', 'am-icon-cog', '1', '-1', '2019-05-04', '张三', '1');
INSERT INTO `yoa_menu` VALUES ('2', 'inform', '公告', null, '/inform/page', 'am-icon-wpforms', '2', '-1', '2019-05-04', '张三', '1');
INSERT INTO `yoa_menu` VALUES ('3', 'doc', '公文', null, '', 'am-icon-wpforms', '3', '-1', '2019-05-04', '张三', '1');
INSERT INTO `yoa_menu` VALUES ('4', 'menu', '菜单管理', '6', '/menu/page', 'am-icon-angle-right', '4', '1', '2019-05-18', 'wangwu', '2');
INSERT INTO `yoa_menu` VALUES ('5', 'user', '用户管理', '7', '/user/page', 'am-icon-angle-right', '5', '1', '2019-05-18', 'wangwu', '2');
INSERT INTO `yoa_menu` VALUES ('6', 'role', '角色管理', '8', '/role/page', 'am-icon-angle-right', '6', '1', '2019-05-18', 'wangwu', '2');
INSERT INTO `yoa_menu` VALUES ('22', 'dept', '部门管理', '9', '/dept/page', 'am-icon-angle-right', '22', '1', '2019-05-18', 'wangwu', '2');
INSERT INTO `yoa_menu` VALUES ('24', 'permissions', '权限管理', '10', '/permissions/page', 'am-icon-angle-right', '24', '1', '2019-05-18', 'wangwu', '2');
INSERT INTO `yoa_menu` VALUES ('25', 'document', '公文管理', null, '/document/all', 'am-icon-angle-right', '25', '3', null, null, '2');
INSERT INTO `yoa_menu` VALUES ('26', 'approve', '批核流程管理', null, '/approve/page', 'am-icon-angle-right', '26', '3', null, null, '2');
INSERT INTO `yoa_menu` VALUES ('28', 'activeToMe', '待审核', null, '/document/activeToMe', 'am-icon-angle-right', '28', '3', null, null, '2');
INSERT INTO `yoa_menu` VALUES ('29', 'myDoc', '我提交的', null, '/document/myDoc', 'am-icon-angle-right', '29', '3', null, null, '2');

-- ----------------------------
-- Table structure for `yoa_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `yoa_permissions`;
CREATE TABLE `yoa_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '权限名称',
  `description` varchar(50) DEFAULT NULL COMMENT '权限描述',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_person` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='权限信息表';

-- ----------------------------
-- Records of yoa_permissions
-- ----------------------------
INSERT INTO `yoa_permissions` VALUES ('1', 'XSOrder', '编辑销售订单22', '2019-05-08', '张三');
INSERT INTO `yoa_permissions` VALUES ('2', 'XSPer', '销售人员管理', null, null);
INSERT INTO `yoa_permissions` VALUES ('3', 'CGPer', '采购人员管理', null, null);
INSERT INTO `yoa_permissions` VALUES ('6', 'menuPermissions', '菜单管理', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_permissions` VALUES ('7', 'userPermissions', '用户管理', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_permissions` VALUES ('8', 'rolePermissions', '角色管理', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_permissions` VALUES ('9', 'deptPermissions', '部门管理', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_permissions` VALUES ('10', 'adminPermissions', '权限管理', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_permissions` VALUES ('11', 'documentPermissions', '公文管理', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_permissions` VALUES ('12', 'informPermissions', '公告管理', '2019-05-18', 'zhangsan');

-- ----------------------------
-- Table structure for `yoa_role`
-- ----------------------------
DROP TABLE IF EXISTS `yoa_role`;
CREATE TABLE `yoa_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(50) DEFAULT NULL COMMENT '角色描述',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_person` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of yoa_role
-- ----------------------------
INSERT INTO `yoa_role` VALUES ('6', 'admin', '管理员', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_role` VALUES ('7', 'staff', '职员', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_role` VALUES ('8', 'groupleader', '组长', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_role` VALUES ('9', 'section', '部长', '2019-05-18', 'zhangsan');

-- ----------------------------
-- Table structure for `yoa_role_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `yoa_role_permissions`;
CREATE TABLE `yoa_role_permissions` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permissions_id` int(11) NOT NULL COMMENT '权限id',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_person` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`role_id`,`permissions_id`),
  KEY `FK_Reference_3` (`permissions_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`role_id`) REFERENCES `yoa_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`permissions_id`) REFERENCES `yoa_permissions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

-- ----------------------------
-- Records of yoa_role_permissions
-- ----------------------------
INSERT INTO `yoa_role_permissions` VALUES ('6', '1', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('6', '2', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('6', '3', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('6', '6', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('6', '7', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('6', '8', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('6', '9', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('6', '10', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('7', '1', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('7', '2', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('7', '3', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('7', '6', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('7', '7', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('8', '1', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('8', '2', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('8', '3', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('8', '7', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('9', '1', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('9', '2', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('9', '3', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('9', '6', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('9', '7', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('9', '8', null, null);
INSERT INTO `yoa_role_permissions` VALUES ('9', '9', null, null);

-- ----------------------------
-- Table structure for `yoa_user`
-- ----------------------------
DROP TABLE IF EXISTS `yoa_user`;
CREATE TABLE `yoa_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `department_id` int(11) DEFAULT NULL COMMENT '所属部门',
  `type` varchar(10) DEFAULT NULL COMMENT '用户类型',
  `hash_pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `full_name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(3) DEFAULT NULL COMMENT '性别',
  `photo` blob COMMENT '用户头像',
  `mail` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `reg_time` date DEFAULT NULL COMMENT '注册时间',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_person` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`department_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`department_id`) REFERENCES `yoa_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of yoa_user
-- ----------------------------
INSERT INTO `yoa_user` VALUES ('12', 'lisi', null, null, null, null, '\'d\'', null, null, null, null, '1', null, '2019-05-02', '9');
INSERT INTO `yoa_user` VALUES ('15', 'zhangsan', '1', null, '92eb2ebd555db284186a07025cb00e1c', '张三', '1', null, '111', '222', '333', '0', '2019-05-06', '2019-05-18', 'zhangsan');
INSERT INTO `yoa_user` VALUES ('16', 'wangwu', '9', null, 'ba384ee7c3fd57501b01c64c81bb5536', '王五', '1', null, '', '', '', '0', '2019-05-16', '2019-05-16', 'zhangsan');
INSERT INTO `yoa_user` VALUES ('17', 'zhaoliu', '1', null, '6b4613210610f919cbd49ee33244250a', '', '1', null, '', '', '', '0', '2019-05-16', '2019-05-16', 'zhangsan');
INSERT INTO `yoa_user` VALUES ('18', '孙二', '1', null, '067b47720774c40ee4751a3d5a33c327', '', '1', null, '', '', '', '0', '2019-05-16', '2019-05-16', 'zhangsan');
INSERT INTO `yoa_user` VALUES ('19', '李七', '9', null, 'b5b6ccc4aab2a4be52909edce2d6e040', '', '1', null, '', '', '', '0', '2019-05-16', '2019-05-16', 'zhangsan');

-- ----------------------------
-- Table structure for `yoa_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `yoa_user_role`;
CREATE TABLE `yoa_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `modify_person` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_Reference_5` (`role_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`user_id`) REFERENCES `yoa_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`role_id`) REFERENCES `yoa_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of yoa_user_role
-- ----------------------------
INSERT INTO `yoa_user_role` VALUES ('15', '6', null, null);
INSERT INTO `yoa_user_role` VALUES ('15', '7', null, null);
INSERT INTO `yoa_user_role` VALUES ('15', '8', null, null);
INSERT INTO `yoa_user_role` VALUES ('15', '9', null, null);
INSERT INTO `yoa_user_role` VALUES ('16', '8', null, null);
INSERT INTO `yoa_user_role` VALUES ('17', '8', null, null);
INSERT INTO `yoa_user_role` VALUES ('18', '7', null, null);
INSERT INTO `yoa_user_role` VALUES ('19', '7', null, null);
