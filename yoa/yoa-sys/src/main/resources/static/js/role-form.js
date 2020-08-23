var roleForm={
    getPermissions:function(){
        var permissionsIds=$("#permissionsIds").val().split(' ');
        var permissions=$("#permissions").val().split(' ');
        for (var i=0 ;i< permissionsIds.length-1;i++){
            var permissionsId=permissionsIds[i];
            var permission=permissions[i];
            $("input[name='permissionsBox'][value='"+permissionsId+'|'+permission+"']").prop("checked",true);
        }
        $("#permissions-modal").modal();
    },
    selectRole:function () {
        var permissionsIds='';
        var permissions='';
        var $checked=$("input[name='permissionsBox']:checked");
        for (var i=0;i<$checked.length;i++)
        {
            var v=($checked[i].value).split('|');
            permissionsIds+=v[0]+' ';
            permissions+=v[1]+' ';
        }
        $("#permissionsIds").val(permissionsIds);
        $("#permissions").val(permissions);
        $("#permissions-modal").modal('close');
    },
    checkAllPermissions:function(){
        debugger
        if ($("#checkAllPermissions").prop("checked")){
            $("input[name='permissionsBox']").prop("checked",true);
        }else
            $("input[name='permissionsBox']").prop("checked",false);
    },
    submitForm(method){
        var id=$("#id").val();
        var name=$("#name").val();
        var description=$("#description").val();
        var permissionsIds=$("#permissionsIds").val().split(" ");
        var data={
            "id":id,
            "name":name,
            "description":description,
            "permissions":[]
        };
        for (var i=0;i<permissionsIds.length-1;i++)
        {
            var permission={};
            permission.id=permissionsIds[i];
            data.permissions.push(permission);
        }
        $.ajax({
            url:'/role',
            method:method,
            data:JSON.stringify(data),
            dataType: "json",
            contentType:"application/json",
            success:function (data) {
                if (data.code==0){
                    alert("success");
                    window.location.href="/role/"+data.message.id;
                }
                else
                    alert(data.error);
            }
        })
    }

}