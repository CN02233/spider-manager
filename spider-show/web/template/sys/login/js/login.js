        var ajax_support = ajax_support.createNew();
        var page_support = page_support.createNew();

        function doLogin(){
            console.info("do login function is running.....");
            var user_name = $("#user_name").val();
            var user_pwd = $("#user_pwd").val();

            var param = new Object();
            param["user_name"] = user_name;
            param["user_pwd"] = user_pwd;
            ajax_support.sendAjaxRequest("/sys/login/doLogin.do",param,"callBackFunction_login");
        }

        function callBackFunction_login(valdiateData){
            var result_code = valdiateData.result_code;
            var validate_result = valdiateData.validate_result;
            console.log("here we go...."+result_code+"||"+validate_result);
            if(result_code!='SUCCESS'){
                alert(validate_result);
            }else{
//                page_support.forward_new_page("/sys/user/userMenuList.do");
                page_support.forward_new_page("/template/sys/main/main_page.html");
            }
        }