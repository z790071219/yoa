var permissionsForm={
    submitForm(method){
        var id=$("#id").val();
        var name=$("#name").val();
        var description=$("#description").val();
        var data={
            "id":id,
            "name":name,
            "description":description,
        };
        $.ajax({
            url:'/permissions',
            method:method,
            data:JSON.stringify(data),
            dataType: "json",
            contentType:"application/json",
            success:function (data) {
                if (data.code==0){
                    alert("success");
                    window.location.href="/permissions/"+data.message.id;
                }
                else
                    alert(data.error);
            }
        })
    }

}