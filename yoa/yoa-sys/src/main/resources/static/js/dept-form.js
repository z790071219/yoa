var deptForm={
    submitForm(method){
        var id=$("#id").val();
        var name=$("#name").val();
        var description=$("#description").val();
        var data={
            "id":id,
            "name":name,
            "description":description
        };
        $.ajax({
            url:'/dept',
            method:method,
            data:JSON.stringify(data),
            dataType: "json",
            contentType:"application/json",
            success:function (data) {
                if (data.code==0){
                    alert("success");
                    window.location.href="/dept/"+data.message.id;
                }
                else
                    alert(data.error);
            }
        })
    }

}