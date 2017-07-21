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
                    // console.log("operation for table html code is "+$opeTd.html());
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
    },

    createTableSearch:function(){
        var table_search_support = {};

        table_search_support.bodyHeight = $(document.body).height();
        table_search_support.$seach_area = $(".seach_area");
        table_search_support.$paging_area = $(".paging_area");
        table_search_support.$table_area = $(".table_area");

        table_search_support.initSearchArea = function(){
            var $seach_div = $(".seach_div");
            var $search_condition_area = $(".search_condition_area");

            console.log("search_condition_area val "+$search_condition_area.width());

            if($search_condition_area.height()>$seach_div.height()){
                var $search_condition_area_width = $search_condition_area.width();
                var search_width = $(".search_input").width();
                console.log($search_condition_area_width/(search_width+30));
                var maxInputCount_EachRow = Math.floor($search_condition_area_width/(search_width+30));

                var allInput = $(".search_input");
                $.each(allInput,function(i,inputObj){
                    if((i+1)>maxInputCount_EachRow){
                        $(inputObj).addClass("search_input_hide");
                    }
                    if((i+1)==maxInputCount_EachRow){
                        $(inputObj).after("<span id='showAllSearchInput' onclick='showAllSearchInput()' " +
                            "class='glyphicon glyphicon-resize-full'><span style='margin-left:10px'>展开</span></span>");

                        $(inputObj).after("<span id='hideSearchInput' onclick='hideSearchInput()' " +
                            "class='glyphicon glyphicon-resize-full'><span style='margin-left:10px'>收起</span></span>");

                        $("#hideSearchInput").hide();
                    }

                });
            }
        };

        table_search_support.initTableArea = function(){
            var $seach_area_height = this.$seach_area.outerHeight();

            var $paging_area_height = this.$paging_area.outerHeight();

            this.$table_area.css("margin-top",$seach_area_height);

            console.log("$seach_area_height "+$seach_area_height+"--$paging_area_height "+$paging_area_height);
            this.$table_area.css("height",this.bodyHeight-$seach_area_height-$paging_area_height-2);
        };

        table_search_support.initPagingArea = function(){
            this.$paging_area.css("margin-top",this.$seach_area.outerHeight()+this.$table_area.outerHeight());
        }

        return table_search_support;
    }
}

/**
 * 初始化 搜索栏 列表 分页 的高度和宽度
 */
$(document).ready(function(){
    var table_search_support = workbench_table.createTableSearch();

    table_search_support.initSearchArea();
    table_search_support.initTableArea();
    table_search_support.initPagingArea();
    //

});

function showAllSearchInput(){
    var $seach_area = $(".seach_area");
    var $search_condition_area = $(".search_condition_area");
    $(".seach_area").css("z-index","10086");
    $(".search_input_hide").removeClass("search_input_hide");

    $seach_area.height($search_condition_area.height());
    $("#showAllSearchInput").hide();
    $("#hideSearchInput").show();
}

function hideSearchInput(){
    $("#showAllSearchInput").remove();
    $("#hideSearchInput").remove();
    var table_search_support = workbench_table.createTableSearch();
    table_search_support.initSearchArea();
    $(".seach_area").removeAttr("style");

}