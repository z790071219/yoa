var documentForm={

    submitForm(method){
        var id=$("#id").val();
        var title=$("#title").val();
        var type=$("#type option:selected").val();
        var status=$("#status option:selected").val();
        var content=$("#content").val();
        var tier=$("#tier").val();
        var deptId=$("#deptId").val();
        var roleId=$("#roleId").val();
        var last=$("#last").val();
        var hasDeptLimit=$("#hasDeptLimit").val();
        var comment=$("#comment").val();
        var approver=$("#approver").val();
        var data={
            "id":id,
            "title":title,
            "typeId":type,
            "status":status,
            "content":content,
            "tier":tier,
            "deptId":deptId,
            "roleId":roleId,
            "last":last,
            "hasDeptLimit":hasDeptLimit,
            "comment":comment,
            "approver":approver,
        };
        $.ajax({
            url:'/document'+method,
            method:'PUT',
            data:JSON.stringify(data),
            dataType: "json",
            contentType:"application/json",
            success:function (data) {
                if (data.code==0){
                    alert("success");
                    window.location.href="/document/"+data.message.id;
                }
                else
                    alert(data.error);
            }
        })
    }

}