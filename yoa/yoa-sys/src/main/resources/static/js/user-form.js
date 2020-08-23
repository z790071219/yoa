var userForm={
    getDepartment:function(){
        $("#department-modal").modal();
    },
    selectDepartment:function (id,name) {
        $("#departmentId").val(id);
        $("#department").val(name);
        $("#department-modal").modal('close');
    },
    getRole:function(){
        var roleIds=$("#roleIds").val().split(' ');
        var roles=$("#roles").val().split(' ');
        for (var i=0 ;i< roleIds.length-1;i++){
            var roleId=roleIds[i];
            var role=roles[i];
            $("input[name='roleBox'][value='"+roleId+'|'+role+"']").prop("checked",true);
        }
        $("#role-modal").modal();
    },
    selectRole:function () {
        var roleIds='';
        var roles='';
        var $checked=$("input[name='roleBox']:checked");
        for (var i=0;i<$checked.length;i++)
        {
            var v=($checked[i].value).split('|');
            roleIds+=v[0]+' ';
            roles+=v[1]+' ';
        }
        $("#roleIds").val(roleIds);
        $("#roles").val(roles);
        $("#role-modal").modal('close');
    },
    checkAllRole:function(){
        if ($("#checkAllRole").prop("checked")){
            $("input[name='roleBox']").prop("checked",true);
        }else
            $("input[name='roleBox']").prop("checked",false);
    },
    submitForm(method){
        var id=$("#id").val();
        var userName=$("#userName").val();
        var hashPwd=$("#hashPwd").val();
        var fullName=$("#fullName").val();
        var sex=$("#sex option:selected").val();
        var mail=$("#mail").val();
        var tel=$("#tel").val();
        var address=$("#address").val();
        var departmentId=$("#departmentId").val();
        var roleIds=$("#roleIds").val().split(" ");
        var data={
            "id":id,
            "userName":userName,
            "hashPwd":hashPwd,
            "fullName":fullName,
            "sex":sex,
            "mail":mail,
            "tel":tel,
            "address":address,
            "departmentId":departmentId,
            "roles":[]
        };
        for (var i=0;i<roleIds.length-1;i++)
        {
            var role={};
            role.id=roleIds[i];
            data.roles.push(role);
        }
        $.ajax({
            url:'/user',
            method:method,
            data:JSON.stringify(data),
            dataType: "json",
            contentType:"application/json",
            success:function (data) {
                if (data.code==0){
                    alert("success");
                    window.location.href="/user/"+data.message.id;
                }
                else
                    alert(data.error);
            }
        })
    }

}