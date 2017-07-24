/**
 * Created by SongCQ on 2017/7/24.
 */


$(document).ready(function(){
    var page_support_obj = page_support.createNew();
    var params = page_support_obj.check_param_from_url();
    console.log(JSON.stringify(params));
});