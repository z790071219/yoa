var documentList={
    delete:function (id) {
        if(!confirm("删除后不可恢复，确定删除？"))
            return;
        $.ajax({
            url:'/document/'+id,
            type:'delete',
            success:function (data) {
                if (data.code==0)
                {
                    $("#li_document"+id).remove();
                    alert(data.message);
                }
                else alert(data.error);
            }
        })
    }
}