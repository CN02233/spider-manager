/**
 * 公用弹出框 基于bootstrap modal弹窗实现
 */
var modal_support = {
    createNew: function(){
        var modal_support = {};

        /**
         * 弹出窗方法
         * @param showMsg 需要显示在弹窗中的信息，支持HTML
         * @param callBack 弹窗点击确认后的回调方法
         */
        modal_support.make_alter = function(showMsg,callBack){
            $("body").append();
        };
    }
}

function modal_alter_confirm_click(){
    $('#modal_alter').modal('hide');
    // $("#modal_alter").remove();
}