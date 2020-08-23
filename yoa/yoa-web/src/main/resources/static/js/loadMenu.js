$(function () {
    $.ajax({
        type: "get",
        url: "/menu/getMenuByUser",
        dataType: "json",
        contentType:"application/json",
        success:function (data) {
            loadMenu(data.message);
        }
    })
})
function loadMenu(menus){
    var $ul=$('<ul class="tpl-left-nav-menu"></ul>');
    for (var i=0;i<menus.length;i++){
        var menu=menus[i];
        if (menu.type==1)
            $ul.append(getParend(menu));
        // if (menu.type==2)
        //     $ul.append(getChild(menu));
    }
    $('#menu_d').append($ul);
    $('.tpl-left-nav-link-list').on('click', function() {
        $(this).siblings('.tpl-left-nav-sub-menu').slideToggle(80)
            .end()
            .find('.tpl-left-nav-more-ico').toggleClass('tpl-left-nav-more-ico-rotate');
    })
}

function getParend(menu) {
    var href=menu.url?menu.url:'javascript:;';
    var icon=menu.icon?menu.icon:'am-icon-wpforms';
    var hasChild=menu.childList?menu.childList.length:0;
    var name=menu.description;

    var i='<i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>';
    var $pMenu=$('<li class="tpl-left-nav-item">\n' +
        '    <a href="'+href+'" class="nav-link tpl-left-nav-link-list">\n' +
        '    <i class="'+icon+'"></i>\n' +
        '    <span>'+name+'</span>\n' +
        '    </a>\n' +
        '    </li>');
    if (hasChild>0){
        $pMenu.find('a').append(i);
        $pMenu.append('<ul class="tpl-left-nav-sub-menu"><li></li></ul>');
        for (var i=0;i < hasChild;i++){
            $pMenu.find('li').append(getChild(menu.childList[i]));
        }
    }
    return $pMenu;

}

function getChild(menu){
    var href=menu.url?menu.url:'javascript:;';
    var icon=menu.icon?menu.icon:'am-icon-angle-right';
    var name=menu.description;
    var $cMenu=$('<a href="'+href+'">\n' +
        '<i class="'+icon+'"></i>\n' +
        '<span>'+name+'</span>\n' +
        '</a>');
    return $cMenu;
}
