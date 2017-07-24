package com.workbench.auth.user.controller;

import com.github.pagehelper.Page;
import com.webapp.support.jsonp.JsonpSupport;
import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.page.PageResult;
import com.workbench.auth.group.entity.Group;
import com.workbench.auth.user.entity.User;
import com.workbench.auth.user.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by SongCQ on 2017/7/7.
 */
@Controller
@RequestMapping("sys/userGroup")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @RequestMapping("listGroupByUserId")
    @ResponseBody
    public String listGroupByUserId(int user_id, HttpServletRequest request){
        List<Group> userGroupList = userGroupService.listGroupByUserId(user_id);

        return JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"获取成功",null,userGroupList,request);
    }

    @RequestMapping("listUsersByGroupId")
    @ResponseBody
    public String listUsersByGroupId(int user_group_id,int currPage, int pageSize, HttpServletRequest request){
        Page<User> groupPage = userGroupService.listUsersByGroupId(user_group_id,currPage,pageSize);

        PageResult pageResult = PageResult.pageHelperList2PageResult(groupPage);;
        String resultJson = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"获取成功",null,pageResult,request);
//        logger.debug("jsonResult information after delete :{}",resultJson);
        return resultJson;
    }

    @RequestMapping("saveUserGroup")
    @ResponseBody
    public String saveUserGroup(int user_id,int user_group_id,HttpServletRequest request){
        userGroupService.saveUserGroup(user_id,user_group_id);
        return JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"保存成功",null,null,request);
    }

    @RequestMapping("delUserGroup")
    @ResponseBody
    public String delUserGroup(int user_id,int user_group_id,HttpServletRequest request){
        userGroupService.delUserGroup(user_id,user_group_id);
        return JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"删除成功",null,null,request);

    }
}
