
var modal_support = modal_support.createNew();
var ajax_support = ajax_support.createNew();
var paging_data = paging_data.crateNew();

$(document).ready(function(){
    // init_modal_alter();
    paging_data.make_paging_data("/sys/user/listUserPage.do");
});

function page_callback(dataList){
    console.log("call back function is running...."+JSON.stringify(dataList));
    var $user_table = $("#user_table").find("tbody");
    $user_table.empty();
    $.each(dataList,function(i,dataObj){
        var user_id = dataObj["user_id"];
        var user_name = dataObj["user_name"];
        var user_type = dataObj["user_type"];
        var reg_date =  dataObj["reg_date"];
        var user_status =  dataObj["user_status"];
        var last_login_time = dataObj["last_login_time"];

        var $tr = $("<tr></tr>");
        $tr.append("<td>"+user_id+"</td>");
        $tr.append("<td>"+user_name+"</td>");
        $tr.append("<td>"+user_type+"</td>");
        $tr.append("<td>"+reg_date+"</td>");
        $tr.append("<td>"+user_status+"</td>");
        $tr.append("<td>"+last_login_time+"</td>");
        $tr.append("<td>操作栏</td>");

        $user_table.append($tr);
    });
}
