package com.workbench.auth.user.controller;

import com.webapp.support.jsonp.JsonpSupport;
import com.webapp.support.jsonp.JsonResult;
import com.workbench.auth.role.entity.Role;
import com.workbench.auth.user.entity.UserRole;
import com.workbench.auth.user.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by pc on 2017/7/6.
 */

@Controller
@RequestMapping("sys/userRole")
public class UserRoleController {

    private Logger logger = LoggerFactory.getLogger(UserRoleController.class);


    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("getRoleByUserId")
    @ResponseBody
    public String getRoleByUserId(int user_id, HttpServletRequest request){
        List<Role> role = userRoleService.getRoleByUserId(user_id);

        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(JsonResult.RESULT.SUCCESS);
        jsonResult.setResult_msg("获取成功");
        jsonResult.setResultData(role);
        logger.debug("jsonResult information after delete :{}",jsonResult.toString());
        String resultJson = JsonpSupport.objectToJsonp(JsonpSupport.jsonpCallbackFunctionName(request), jsonResult);

        return resultJson;
    }

    @RequestMapping("saveUserRole")
    @ResponseBody
    public String saveUserRole(UserRole userRole,HttpServletRequest request){
        userRoleService.saveUserRole(userRole);

        String resultJson = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"保存成功",null,null,request);

        logger.debug("jsonResult information after saveUserRole :{}",resultJson);


        return resultJson;
    }

    @RequestMapping("updateUserRole")
    @ResponseBody
    public String updateUserRole(UserRole userRole,int old_user_role_id,HttpServletRequest request){
        userRoleService.updateUserRole(userRole,old_user_role_id);
        String resultJson = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"保存成功",null,null,request);
        logger.debug("jsonResult information after delete :{}",resultJson);


        return resultJson;
    }

    @RequestMapping("delUserRole")
    @ResponseBody
    public String delUserRole(UserRole userRole,HttpServletRequest request){
        userRoleService.delUserRole(userRole);
        String resultJson = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"删除成功",null,null,request);
        logger.debug("jsonResult information after delete :{}",resultJson);


        return resultJson;
    }
}
