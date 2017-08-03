/**
 * Created by SongCQ on 2017/7/31.
 */

$(document).ready(function(){
    var paging_data_support = paging_data.crateNew();

    var tableSearchCreater = workbench_table.tableSearchCreater();

    var optionArray = new Array();
    optionArray.push(tableSearchCreater.selectOptionObject('',"请输入是否有效"));
    optionArray.push(tableSearchCreater.selectOptionObject(0,"无效"));
    optionArray.push(tableSearchCreater.selectOptionObject(1,"有效"));

    var jobJdElement = tableSearchCreater.searchElementObject("job_id","请输入采集编号");
    var jobNameElement = tableSearchCreater.searchElementObject("job_name","请输入采集名称");
    var isValidElement = tableSearchCreater.searchElementObject("is_valid","请输入是否有效","select",optionArray);
    var searchElements = new Array();
    searchElements.push(jobJdElement);
    searchElements.push(jobNameElement);
    searchElements.push(isValidElement);

    var searchInfoObj = tableSearchCreater.searchInfoObject("/crawler/jobMg/pagingList.do",searchElements,true,true,"add_job()");
    tableSearchCreater.createSearch(searchInfoObj);

    paging_data_support.make_paging_data("/crawler/jobMg/pagingList.do");
});

function page_callback(dataList){
    console.info("dataList "+JSON.stringify(dataList));

    var $user_table = $("#job_list_table").find("tbody");
    $user_table.empty();
    var columnNames = ["job_id","job_name","entry_page_id","user.user_name","is_valid_cn","jobStatus.run_status_cn"];

    var table_support = workbench_table.createNew();

    var operationArray = new Array();
    var view_operation = table_support.operationsByName("查看", ["job_id"], "viewJob(this)");
    var edit_operation = table_support.operationsByName("编辑", ["job_id"], "editJob(this)");
    var delete_operation = table_support.operationsByName("删除", ["job_id"], "deleteJob(this)");
    operationArray.push(view_operation);
    operationArray.push(edit_operation);
    operationArray.push(delete_operation);

    table_support.makeTable("job_list_table",columnNames,dataList,operationArray);

}

function add_job(){
    page_support.createNew().forward_new_page("/template/crawler/job_manage/edit_job.html")
}

function viewJob(viewObj){
    var job_id = $(viewObj).attr("job_id");
    page_support.createNew().forward_new_page("/template/crawler/job_manage/edit_job.html?job_id="+job_id+"&type=view")
}

function editJob(viewObj){
    var job_id = $(viewObj).attr("job_id");
    page_support.createNew().forward_new_page("/template/crawler/job_manage/edit_job.html?job_id="+job_id+"&type=edit")
}

function deleteJob(viewObj){
    var job_id = $(viewObj).attr("job_id");
    modal_support.createNew().make_alter("确定删除当前采集任务？该操作会删除所有与该任务关联的内容","confirmDel",{"job_id":job_id});
}

function confirmDel(jobParam){
    ajax_support.sendAjaxRequest("/crawler/jobMg/deleJob.do",jobParam,"delCallBack")
}

function delCallBack(delResult){
    modal_support.createNew().make_alter(delResult["result_msg"]);
    paging_data.make_paging_data("/crawler/jobMg/pagingList.do");
}