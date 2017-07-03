
// 调用后台接口获取数据的公用方法

var ajax_support = {

    createNew: function(){
        var ajax_support = {};

        ajax_support.sendAjaxRequest = function(url,params,callBackFunction){
            var realUrl = url + "?web_call_back=" + callBackFunction;
            $.ajax({
                url:HOST+realUrl,
                type:'get',
                data:params,
                xhrFields:{withCredentials:true},
                dataType:'jsonp',
                // jsonp:'jsonpcallback ',
                // success:function(data){
                //     console.log("success is running...");
                //     console.log(data);
                //     if(callBackFunction!=null)
                //         callBackFunction();
                // },
                error:function(data){
                    console.log("brower get one error....."+data);
                }

            });
        };


        return ajax_support;
    }

}
