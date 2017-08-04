/**
 * 公用弹出框 基于bootstrap modal弹窗实现
 */
var modal_support = {
    createNew: function(){
        var modal_support_alter = {};
        // console.log("modal support init function has running....");
        /**
         * 弹出窗方法
         * @param showMsg 需要显示在弹窗中的信息，支持HTML
         * @param callBack 弹窗点击确认后的回调方法
         * @param callBackParam 弹窗点击确认后回调方法中的参数
         * @param needCancel 是否需要取消按钮，默认false不需要
         */
        modal_support_alter.make_alter = function(showMsg,callBack,callBackParam,needCancel){
            // console.log("make alter is running...show message is "+showMsg);

            var modal_html_code =
                "<div class='modal fade modal_top' id='modal_alter' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>"+
                "<div class='modal-dialog modal_root'>"+
                "<div class='modal-content'>"+
                "<div class=' modal_wrapper modal-body'>"+
                "<div class=' modal_cell'>"+
                showMsg+
                "</div>"+
                "</div>"+
                "<div class='modal-footer'>"+
                "<button type='button' class='btn btn-primary' onclick='modal_alter_confirm_click("+callBack+","+JSON.stringify(callBackParam)+")'>"+
                "确定"+
                "</button>"+
                "</div>"+
                "</div><!-- /.modal-content -->"+
                "</div><!-- /.modal -->"+
                "</div>'";
            $("body").append(modal_html_code);
            $("#modal_alter").modal({backdrop:false,show:true});
        };

        modal_support_alter.makeEditModalByColumn = function(alter_title,columns,callBack){
            var modal_name = "edit_modal";

            $("#edit_modal").remove();
            var modal_html_code =
                "<div class='modal fade modal_top' id='"+modal_name+"' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>"+
                "<div class='modal-dialog'>"+
                " <div class='modal-content'>"+
                " <div class='modal-header'>"+
                "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>"+
                "<h4 class='modal-title' id='myModalLabel'>"+alter_title+"</h4>"+
                "</div>"+
                "<div class='modal-body '>"+
                "   <table id='modal_edit_table'></table>"+
                "   </div>"+
                "   <div class='modal-footer'>"+
                "   <button type='button' class='btn btn-default' onclick='removeEditModal()'>关闭</button>"+
                "   <button type='button' id='commitEditModal' class='btn btn-primary'>提交</button>"+
                "   </div>"+
                "   </div></div></div>";

            $("body").append(modal_html_code);

            $.each(columns,function(i,edit_columns){
                makeEditTr(edit_columns);
            });

            if(callBack!=null){
                $("#commitEditModal").click(function(){
                    // console.log("commit edit modal click function has running....");
                    var inputValues = new Array();
                    $.each(columns,function(i,edit_columns){
                        var column_id = edit_columns.column_id;
                        var column_val = $("#modal_edit_table").find("#"+column_id).val();
                        // var column_val = $("#"+column_id).val();
                        inputValues.push({"column_id":column_id,"column_val":column_val});
                    });
                    callBack(inputValues);
                });
            }else{
                $("#commitEditModal").remove();
            }   

            $("#edit_modal").modal({backdrop:false,show:true});
            // $('#edit_modal').modal('show');
            $('#edit_modal').on('hidden.bs.modal', function (e) {
                // console.log("edit_modal has fired.....");
                // $('#edit_modal').modal('hide');
                removeEditModal();
            });

            function makeEditTr(edit_columns){
                // console.log("makeEditTr is running...."+JSON.stringify(edit_columns));
                var column_id = edit_columns.column_id;
                var column_name = edit_columns.column_name;
                var column_type = edit_columns.column_type;
                var column_value = edit_columns.column_value!=null?edit_columns.column_value:"";
                var column_readyOnly = edit_columns.readyOnly;
                var column_list = edit_columns.column_list;
                var $tr = $("<tr></tr>");

                $("#modal_edit_table").append($tr);

                $tr.append("<td class=' input_name_div'>"+column_name+"：</td>");

                if(column_type=='text'){
                    var readOnlyAttr = "";
                    if(column_readyOnly){
                        readOnlyAttr = "readonly = true";
                    }

                    $tr.append("<td class='input_area_div'>" +
                        "<input type='text' id='"+column_id+"' "+readOnlyAttr+" value='"+column_value+"' class='form-control modal_input'/>" +
                        "</td>");

                }else if(column_type=='select'){
                    var readOnlyAttr = "";
                    if(column_readyOnly){
                        readOnlyAttr = "disabled=disabled";
                    }


                    $tr.append("<td class='input_area_div'>" +
                        "<select id='"+column_id+"' "+readOnlyAttr+" class='form-control modal_input'></select>" +
                        "</td>");
                    $("#"+column_id).append("<option value=''>请选择</option>");
                    if(column_list!=null){
                        $.each(column_list,function(i,options){
                            var options_value = options["options_value"];
                            var options_text = options["options_text"];
                            $("#"+column_id).append("<option value='"+options_value+"'>"+options_text+"</option>");

                        });
                    }
                }

            }

            return modal_name;
        };

        modal_support_alter.makeTableEdit = function(edit_title,callBack,titleNames,paramNames,dataList,operations){
            this.makeEditModalByColumn(edit_title,new Array(),callBack);
            console.log("modal edit table has inited ...."+$("#modal_edit_table").length);
            $("#modal_edit_table").addClass("workbench_table");
            var titleThead = $("<thead><tr></tr>tr></thead>");
            $.each(titleNames,function(i,titleName){
                titleThead.append("<th>"+titleName+"</th>");
            });

            $("#modal_edit_table").append(titleThead);
            $("#modal_edit_table").append("<tbody></tbody>");

            var workbench_table_support_obj = workbench_table.createNew();
            workbench_table_support_obj.makeTable("modal_edit_table",paramNames,dataList,operations);
        };

        modal_support_alter.closeEditModalByColumn = function(){
            removeEditModal();
        };

        return modal_support_alter;
    },
    getModalEditObject:function(column_id,column_name,column_type,not_null,column_value,readOnly,column_list){
        var modal_edit_object = {};
        if(readOnly!=null&&readOnly){
            modal_edit_object.readyOnly = true;
        }else
            modal_edit_object.readyOnly = false;
        modal_edit_object.column_id = column_id;
        modal_edit_object.column_name = column_name;

        if(column_type!=null)
            modal_edit_object.column_type = column_type;
        else{
            modal_edit_object.column_type = "text";
        }
        modal_edit_object.column_list = column_list;

        modal_edit_object.column_value = column_value;
        modal_edit_object.not_null = not_null!=null?not_null:false;
        return modal_edit_object;
    }
}


function modal_alter_confirm_click(call_back_function,callBackParam){
    // console.log(callBackParam);
    // console.log(call_back_function!=null);
    // $('#modal_alter').modal('hide');
    $('#modal_alter').remove();
    if(call_back_function!=null)
        call_back_function(callBackParam);
    // $("#modal_alter").remove();
}

function removeEditModal(){
    $("#edit_modal").remove();
    $("body").removeClass("modal-open");
}
