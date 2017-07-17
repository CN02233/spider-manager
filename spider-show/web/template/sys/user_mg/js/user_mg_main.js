
var modal_support = modal_support.createNew();

$(document).ready(function(){
    init_modal_alter();
});

function init_modal_alter(){
    console.log("init modal alter.....");
    modal_support.make_alter("<p>This is user page.....</p>"+
        "<p>Isn't show????</p>",call_back_test);
    console.log("init modal alter end.....");

}

function call_back_test(){
    alert("call back is running....");
}