package com.workbench.auth.role.controller;

import com.webapp.support.JsonpSupport;
import com.webapp.support.jsonp.JsonResult;
import com.workbench.auth.menu.entity.Menu;
import com.workbench.auth.role.dao.IRoleManageDao;
import com.workbench.auth.role.dao.IRoleMenuDao;
import com.workbench.auth.role.entity.RoleMenu;
import com.workbench.auth.role.service.RoleMenuManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by SongCQ on 2017/7/6.
 */

@Controller
@RequestMapping("sys/roleMenu")
public class RoleMenuManageController {

    @Autowired
    private RoleMenuManageService roleMenuManageService;

    @RequestMapping("getMenuByRole")
    @ResponseBody
    public String getMenuByRole(int user_role_id, HttpServletRequest request){
        List<Menu> roleMenuList = roleMenuManageService.getMenuByRole(user_role_id);

        String jsonpResult = JsonpSupport.makeJsonpResponse(
                JsonResult.RESULT.SUCCESS, "获取成功", null, roleMenuList, request);

        return jsonpResult;
    }

    @RequestMapping("saveMenuForRole")
    @ResponseBody
    public String  saveMenuForRole(RoleMenu roleMenu, HttpServletRequest request){
        roleMenuManageService.saveMenuForRole(roleMenu);

        String jsonpResult = JsonpSupport.makeJsonpResponse(
                JsonResult.RESULT.SUCCESS, "删除成功", null, null, request);
        return jsonpResult;
    }

    @RequestMapping("delMenuFromRole")
    @ResponseBody
    public String  delMenuFromRole(RoleMenu roleMenu, HttpServletRequest request){
        roleMenuManageService.delMenuFromRole(roleMenu);

        String jsonpResult = JsonpSupport.makeJsonpResponse(
                JsonResult.RESULT.SUCCESS, "删除成功", null, null, request);
        return jsonpResult;
    }

//    @RequestMapping("modifyMenuForRole")
//    @ResponseBody
//    @Transactional
//    public String modifyMenuForRole(int user_role_id,List<Integer> moduleList,HttpServletRequest request){
//        roleMenuManageService.delMenuByRoleId(user_role_id);
//        for(Integer model_id:moduleList){
//            RoleMenu roleMenu = new RoleMenu();
//            roleMenu.setModule_id(model_id);
//            roleMenu.setUser_role_id(user_role_id);
//            roleMenuManageService.saveMenuForRole(roleMenu);
//        }
//        String jsonpResult = JsonpSupport.makeJsonpResponse(
//                JsonResult.RESULT.SUCCESS, "保存成功", null, null, request);
//        return jsonpResult;
//    }

}
