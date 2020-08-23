var approveForm={
    addTier(){
        $("#role-modal").modal();
    },
    addFlow(roleId,roleName){
        var hasDeptLimit=$("#role_"+roleId).prop("checked");
        var deptLimit=hasDeptLimit?'（限制部门）':'';
        var div='<div class="flowTier"><i style="margin-left: 250px" class="am-icon-arrow-down"></i>\n' +
                '<input name="roleId" type="hidden" value='+roleId+'|'+hasDeptLimit+'>'+
                '<input type="text"  readonly value="'+roleName+deptLimit+'"></div>';
        $(".flowTier:last").append(div);
        $("#role-modal").modal('close');
    },
    submitForm(method){
        var id=$("#id").val();
        var typeName=$("#typeName").val();
        var flowParam=$("input[name='roleId']");
        var flows=[];
        for (var i=0;i<flowParam.length;i++){
            var flow={
                "roleId":flowParam[i].value.split('|')[0],
                "tier":i+1,
                "hasDeptLimit":flowParam[i].value.split('|')[1],
                "last":(i+1)==flowParam.length
            };
            flows.push(flow);
        }
        var data={
            "id":id,
            "typeName":typeName,
            "approveFlows":flows
        };
        $.ajax({
            url:'/approve',
            method:method,
            data:JSON.stringify(data),
            dataType: "json",
            contentType:"application/json",
            success:function (data) {
                if (data.code==0){
                    alert("success");
                    window.location.href="/approve/"+data.message.id;
                }
                else
                    alert(data.error);
            }
        })
    }

}