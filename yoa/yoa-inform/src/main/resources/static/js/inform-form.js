var informForm={

    submitForm(){
        var id=$("#id").val();
        var title=$("#title").val();
        var content=$("#content").val();
        var data={
            "id":id,
            "title":title,
            "content":content,
        };
        $.ajax({
            url:'/inform',
            method:'POST',
            data:JSON.stringify(data),
            dataType: "json",
            contentType:"application/json",
            success:function (data) {
                if (data.code==0){
                    alert("success");
                    window.location.href="/inform/"+data.message.id;
                }
                else
                    alert(data.error);
            }
        })
    }

}