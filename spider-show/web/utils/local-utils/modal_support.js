/**
 * 公用弹出框 基于bootstrap modal弹窗实现
 */
var modal_support = {
    createNew: function(){
        var modal_support = {};
        // console.log("modal support init function has running....");
        /**
         * 弹出窗方法
         * @param showMsg 需要显示在弹窗中的信息，支持HTML
         * @param callBack 弹窗点击确认后的回调方法
         */
        modal_support.make_alter = function(showMsg,callBack){
            // console.log("make alter is running...show message is "+showMsg);
            var modal_html_code =
                "<div class='modal fade ' id='modal_alter' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>"+
                "<div class='modal-dialog modal_root'>"+
                "<div class='modal-content'>"+
                "<div class=' modal_wrapper modal-body'>"+
                "<div class=' modal_cell'>"+
                showMsg+
                "</div>"+
                "</div>"+
                "<div class='modal-footer'>"+
                "<button type='button' class='btn btn-primary' onclick='modal_alter_confirm_click("+callBack+")'>"+
                "确定"+
                "</button>"+
                "</div>"+
                "</div><!-- /.modal-content -->"+
                "</div><!-- /.modal -->"+
                "</div>'";
            $("body").append(modal_html_code);
            $("#modal_alter").modal({backdrop:false,show:true});
        };

        return modal_support;
    }
}

function modal_alter_confirm_click(call_back_function){
    $('#modal_alter').modal('hide');
    if(call_back_function!=null)
        call_back_function();
    // $("#modal_alter").remove();
}