
// 调用后台接口获取数据的公用方法

var ajax_support = {

    createNew: function(){
        var ajax_support = {};

        ajax_support.DEFUALT_PAGE_SIZE = 10;//默认每页显示条数

        ajax_support.json_data_list = new Array();

        ajax_support.sendAjaxRequestSimple = function(url,params){
            $.ajax({
                url:SERVICE_HOST+url,
                type:'post',
                data:params,
                xhrFields:{withCredentials:true},
                dataType:'jsonp',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
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

        // ajax_support.sendJsonAjaxRequest = function(url,params,callBackFunction){
        //     var jsonStr = JSON.stringify(params);
        //     var realUrl = url + "?web_call_back=" + callBackFunction+"&isJson=Y";
        //     this.sendAjaxRequestSimple(realUrl,JSON.parse(JSON.stringify(params)));
        // };

        ajax_support.sendJsonAjaxRequest = function(url,callBackFunction){
            var realUrl = url + "?web_call_back=" + callBackFunction+"&isJson=Y";
            var sendParam = new Object();
            if(this.json_data_list.length>0){
                $.each(this.json_data_list,function(i,json_data){
                    var jsonName = json_data["jsonName"];
                    var jsonData = json_data["jsonData"];
                    var jsonStr = JSON.stringify(jsonData);
                    sendParam[jsonName] = jsonStr;
                });
                this.sendAjaxRequestSimple(realUrl,sendParam);

            }
        };


        ajax_support.addJsonData = function(jsonName,jsonData){
            this.json_data_list.push({"jsonName":jsonName,"jsonData":jsonData});
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
                pageSize = this.DEFUALT_PAGE_SIZE;
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
                if(result_json.faild_reason == "USER_NOT_LOGIN"){
                    modal_support.make_alter(result_json.result_msg,auth_failed);
                }else if(result_json.faild_reason == "USERNM_NOT_FOUND"){
                    modal_support.make_alter(result_json.result_msg,auth_failed);
                }

                function auth_failed(){
                    page_support.forward_new_page("/template/sys/login/login.html");
                }

                return false;
            }
        };

        ajax_support.get_result_data = function(result_json){
            return result_json.resultData;
        };

        ajax_support.isRoot = function(){
            return false;
        }

        return ajax_support;
    },

    isRoot : function(){
        return true;
    }

}
