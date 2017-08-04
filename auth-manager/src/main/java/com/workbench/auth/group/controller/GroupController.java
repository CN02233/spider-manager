package com.workbench.auth.group.controller;

import com.github.pagehelper.Page;
import com.webapp.support.jsonp.JsonpSupport;
import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.page.PageResult;
import com.workbench.auth.group.entity.Group;
import com.workbench.auth.group.service.GroupService;
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
@RequestMapping("sys/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping("listUserGroupPage")
    @ResponseBody
    public String listUserGroupPage(int currPage, int pageSize, HttpServletRequest request){
        Page<Group> userGrpPage = groupService.listUserGroupPage(currPage, pageSize);
        PageResult pageResult = PageResult.pageHelperList2PageResult(userGrpPage);
        String resultJson = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"获取成功",null,pageResult,request);
//        logger.debug("jsonResult information after delete :{}",resultJson);
        return resultJson;
    }

    @RequestMapping("listUserGroup")
    @ResponseBody
    public String listUserGroupPage(HttpServletRequest request){
        Page<Group> userGrpPage = groupService.listUserGroupPage(1, 200);
        PageResult pageResult = PageResult.pageHelperList2PageResult(userGrpPage);
        String resultJson = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"获取成功",null,pageResult,request);
//        logger.debug("jsonResult information after delete :{}",resultJson);
        return resultJson;
    }

    @RequestMapping("listSuperGroup")
    @ResponseBody
    public String listSuperGroup(HttpServletRequest request){
        List<Group> list = groupService.listSuperGroup();
        String resultJson = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"获取成功",null,list,request);
//        logger.debug("jsonResult information after delete :{}",resultJson);
        return resultJson;
    }

    @RequestMapping("getUserGroup")
    @ResponseBody
    public String  getUserGroup(int user_group_id, HttpServletRequest request){
        Group group = groupService.getUserGroup(user_group_id);
        String resultJson = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"获取成功",null,group,request);
//        logger.debug("jsonResult information after delete :{}",resultJson);
        return resultJson;
    }

    @RequestMapping("saveNewGroup")
    @ResponseBody
    public String  saveNewUserGroup(Group group, HttpServletRequest request){
        groupService.saveNewUserGroup(group);
        String resultJson = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"保存成功",null,null,request);
//        logger.debug("jsonResult information after delete :{}",resultJson);
        return resultJson;
    }

    @RequestMapping("updateUserGroup")
    @ResponseBody
    public String  updateUserGroup(Group group, HttpServletRequest request){
        groupService.updateUserGroup(group);
        String resultJson = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"保存成功",null,null,request);
//        logger.debug("jsonResult information after delete :{}",resultJson);
        return resultJson;
    }

    @RequestMapping("delUserGroup")
    @ResponseBody
    public String  delUserGroup(int user_group_id,HttpServletRequest request){
        groupService.delUserGroup(user_group_id);
        String resultJson = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"删除成功",null,null,request);
//        logger.debug("jsonResult information after delete :{}",resultJson);
        return resultJson;
    }

}
