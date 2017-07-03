/**
 * 控制浏览器将当前页面跳转到其他页面
 */
var page_support = {

    createNew: function(){
        var page_support = {};

        page_support.forward_new_page = function(url){
            if(url!=null){
                window.location.href = projectName+url;
            }else{
                alert("跳转url未定义");
            }
        };

        /**
         * 控制浏览器打开新窗口
         */
        page_support.open_new_page = function(url){

        };

        page_support.alter_window = function(url){

        };

        return page_support;
    }

}