package com.workbench.auth.role.controller;

import com.github.pagehelper.Page;
import com.webapp.support.JsonpSupport;
import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.page.PageResult;
import com.workbench.auth.role.entity.Role;
import com.workbench.auth.role.service.RoleManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SongCQ on 2017/7/6.
 */
@Controller
@RequestMapping("sys/role")
public class RoleManageController {

    Logger logger = LoggerFactory.getLogger(RoleManageController.class);

    @Autowired
    private RoleManageService roleManageService;

    @RequestMapping("rolePageData")
    @ResponseBody
    public String rolePageData(int currPage, int pageSize, HttpServletRequest request){
        Page<Role> pageList = roleManageService.rolePageData(currPage, pageSize);
        PageResult pageResult = PageResult.pageHelperList2PageResult(pageList);

        String jsonpResponse = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS, "获取成功", null, pageResult, request);

        logger.debug("rolePageData value is {}",jsonpResponse);

        return jsonpResponse;
    }

    @RequestMapping("getRoleById")
    @ResponseBody
    public String getRoleById(int user_role_id, HttpServletRequest request){
        Role roleData = roleManageService.getRoleById(user_role_id);
        String jsonpResponse = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS, "获取成功", null, roleData, request);
        logger.debug("getRoleById value is {}",jsonpResponse);

        return jsonpResponse;
    }

    @RequestMapping("saveNewRole")
    @ResponseBody
    public String saveNewRole(Role role, HttpServletRequest request){
        roleManageService.saveNewRole(role);
        String jsonpResponse = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS, "保存成功", null, null, request);
        logger.debug("saveNewRole value is {}",jsonpResponse);

        return jsonpResponse;
    }

    @RequestMapping("updateSaveRole")
    @ResponseBody
    public String updateSaveRole(Role role, HttpServletRequest request){
        roleManageService.updateSaveRole(role);
        String jsonpResponse = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS, "保存成功", null, null, request);
        logger.debug("updateSaveRole value is {}",jsonpResponse);

        return jsonpResponse;
    }
}
