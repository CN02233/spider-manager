
// 调用后台接口获取数据的公用方法

var ajax_support = {

    createNew: function(){
        var ajax_support = {};

        ajax_support.sendAjaxRequest = function(url,params,callBackFunction){
            var realUrl = url + "?web_call_back=" + callBackFunction;
            $.ajax({
                url:SERVICE_HOST+realUrl,
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

        ajax_support.ajax_result_success = function(result_json){
            if(result_json.result=="SUCCESS"){
                return true;
            }else{
                return false;
            }
        };

        return ajax_support;
    }

}
