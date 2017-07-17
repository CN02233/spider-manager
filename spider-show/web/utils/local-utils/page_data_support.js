
// 调用后台接口获取数据的公用方法

var ajax_support = {

    createNew: function(){
        var ajax_support = {};

        ajax_support.sendAjaxRequestSimple = function(url,params){
            $.ajax({
                url:SERVICE_HOST+url,
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

        ajax_support.sendAjaxRequest = function(url,params,callBackFunction){
            // console.log("sendAjaxRequest is running....");
            var realUrl = url + "?web_call_back=" + callBackFunction;
            this.sendAjaxRequestSimple(realUrl,params);
        };

        /**
         * 带分页数据
         * @param url 目标后台服务地址
         * @param params 请求参数
         * @param callBackFunction 回调函数
         * @param pageSize 每页数据量
         * @param currPage 当前页码
         */
        ajax_support.sendAjaxRequestByPage = function(url,param,pageSize,currPage,callBackFunction){
            // console.log("sendAjaxRequestByPage is running....");
            if(pageSize==null||pageSize==''){
                pageSize = 8;
            }
            if(currPage==null||currPage==''){
                currPage = 1;
            }
            var realUrl = url + "?web_call_back=" + callBackFunction+"&pageSize="+pageSize+"&currPage="+currPage;

            // this.sendAjaxRequest()
            this.sendAjaxRequestSimple(realUrl,param);
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
