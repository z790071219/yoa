setInterval(function() {
   $.ajax({
       url:"/getMessage",
       dataType: "json",
       contentType:"application/json",
       success:function (data) {
           var informs=data.message;
           $(".inform").remove();
           for (var i=0;i<informs.length;i++){
               var d='<li class="tpl-dropdown-list-bdbc inform"><a href="/inform/'+informs[i].id+'" class="tpl-dropdown-list-fl"><span class="am-icon-bell-o tpl-dropdown-ico-btn-size "></span> '+informs[i].title+'</a>\n' +
                   '                        <span class="tpl-dropdown-list-fr" >'+informs[i].createTime+'</span>\n' +
                   '                    </li>';
               //console.log(d);
               $("#informUl").append(d);
           }
          $(".informMessage")[0].innerHTML=data.message.length;
           $(".informMessage")[1].innerHTML=data.message.length;
       }
   })
},1000);