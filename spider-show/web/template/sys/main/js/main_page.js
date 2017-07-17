/**
 * Created by SongCQ on 2017/7/7.
 */
var ajax_support = ajax_support.createNew();
var page_support = page_support.createNew();
var modal_support = modal_support.createNew();

ajax_support.sendAjaxRequest("/sys/user/userMenuList.do",null,"userMenuList");

function userMenuList(menuDataResult){
    // console.info("menu list data :"+JSON.stringify(menuDataResult));
    if(ajax_support.ajax_result_success(menuDataResult)){
        make_menu(menuDataResult);
    }else{
        alert(menuDataResult.result_msg);
        modal_support.make_alter(menuDataResult.result_msg,null);
        if(menuDataResult.faild_reason == "USER_NOT_LOGIN"){
            page_support.forward_new_page("/template/sys/login/login.html");
        }
    }
}

$(document).ready(function(){
    menu_show();//菜单样式、逻辑
});