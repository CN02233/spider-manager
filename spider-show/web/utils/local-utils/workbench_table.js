/**
 * Created by SongCQ on 2017/7/19.
 */
document.write('<script type="text/javascript" src="'+projectName+'/utils/local-utils/paging_data.js"></script>');

var workbench_table = {
    createNew : function(){
        var table_support = {};

        table_support.makeTable = function(tableName,paramNames,dataList,operations){
            $table = $("#"+tableName);
            var $user_table = $table.find("tbody");
            $user_table.empty();
            $.each(dataList,function(i,dataObj){
                var $tr = $("<tr></tr>");
                $.each(paramNames,function(pi,paramName){
                   var paramValue = dataObj[paramName];
                   if(i%2==0){
                       $tr.attr("class","workbench_table_colorful");
                   }
                   $tr.append("<td>"+paramValue+"</td>");
               });
                if(operations!=null){
                    $opeTd = $("<td></td>");
                    $.each(operations,function(i,operationObj){
                        if(i>0){
                            $opeTd.append("<span style='margin-left:5px;'></span>");
                        }
                        if(operationObj.operationType=='function'){
                            $opeTd.append(createOperationByFunction(operationObj,dataObj));
                        }else if(operationObj.operationType=='html'){
                            $opeTd.append(operationObj.operationName);
                        }
                    });
                    console.log("operation for table html code is "+$opeTd.html());
                    $tr.append($opeTd);

                }
                $user_table.append($tr);
            });

            function createOperationByFunction(operationObj,rowData){
                var $operation = $("<span class='workbench_table_operation' onclick='"+operationObj.clickFunction+"'>"+operationObj.operationName+"</span>");
                var operationParams = operationObj.operationParams;
                $.each(operationParams,function(i,paramObj){
                    $operation.attr(paramObj,rowData[paramObj]);
                });
                return $operation;
            }
        };


        table_support.operationsByHtmlCode = function(operationHtmlCode){
            var operation = {};
            operation.operationName = operationHtmlCode;
            operation.operationType = "html";
            return operation;
        };

        table_support.operationsByName = function(operationName,operationParams,clickFunction){
            var operation = {};
            operation.operationName = operationName;
            operation.operationParams = operationParams;
            operation.clickFunction = clickFunction;
            operation.operationType = "function";
            return operation;
        };

        return table_support;
    }
}

/**
 * 初始化 搜索栏 列表 分页 的高度和宽度
 */
$(document).ready(function(){
    var $seach_area = $(".seach_area");
    var $table_area = $(".table_area");
    var paging_area = $(".paging_area");
});