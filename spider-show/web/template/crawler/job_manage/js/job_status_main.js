/**
 * Created by SongCQ on 2017/7/31.
 */

$(document).ready(function(){
    var paging_data_support = paging_data.crateNew();

    var tableSearchCreater = workbench_table.tableSearchCreater();


    var jobNameElement = tableSearchCreater.searchElementObject("job_name","请输入采集名称");
    var searchElements = new Array();
    searchElements.push(jobNameElement);

    var searchInfoObj = tableSearchCreater.searchInfoObject("/crawler/jobStatus/pagingList.do",searchElements,true,false);
    tableSearchCreater.createSearch(searchInfoObj);

    paging_data_support.make_paging_data("/crawler/jobStatus/pagingList.do");
});

function page_callback(dataList){
    console.info("dataList "+JSON.stringify(dataList));

    var $user_table = $("#job_list_table").find("tbody");
    $user_table.empty();
    var columnNames = ["job_id","job_name","start_time_str","run_status_cn","user.user_name","download_page_num","pending_page_num","error_page_num"];

    var table_support = workbench_table.createNew();

    table_support.makeTable("job_list_table",columnNames,dataList,null);

}