
var modal_support = modal_support.createNew();
var ajax_support = ajax_support.createNew();
var paging_data = paging_data.crateNew();

$(document).ready(function(){
    // init_modal_alter();
    paging_data.make_paging_data("/sys/user/listUserPage.do");
});

function page_callback(dataList){
    // console.log("call back function is running...."+JSON.stringify(dataList));
}
