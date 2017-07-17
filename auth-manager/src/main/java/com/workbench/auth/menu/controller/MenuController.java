package com.workbench.auth.menu.controller;

import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.jsonp.JsonpSupport;
import com.webapp.support.page.PageResult;
import com.workbench.auth.menu.entity.Menu;
import com.workbench.auth.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SongCQ on 2017/7/7.
 */
@Controller
@RequestMapping("sys/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("listMenuByPage")
    @ResponseBody
    public String listMenuByPage(int currPage, int pageSize, HttpServletRequest request){
        PageResult menuList = menuService.listMenuByPage(currPage, pageSize);

        String jsonpResponse = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS, "获取成功", null, menuList, request);

        return jsonpResponse;
    }

    @RequestMapping("getMenuById")
    @ResponseBody
    public String getMenuById(int module_id, HttpServletRequest request){
        Menu menu = menuService.getMenu(module_id);
        String jsonpResponse = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS, "获取成功", null, menu, request);
        return jsonpResponse;
    }

    @RequestMapping("saveNewMenu")
    @ResponseBody
    public String  saveNewMenu(Menu menu, HttpServletRequest request){

        menuService.saveNewMenu(menu);

        String jsonpResponse = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS, "保存成功", null, null, request);

        return jsonpResponse;
    }

    @RequestMapping("updateMenu")
    @ResponseBody
    public String  updateMenu(Menu menu, HttpServletRequest request){
        menuService.updateMenu(menu);
        String jsonpResponse = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS, "保存成功", null, null, request);
        return jsonpResponse;
    }

    @RequestMapping("delMenuById")
    @ResponseBody
    public String  delMenuById(int module_id, HttpServletRequest request){
        menuService.delMenuById(module_id);
        String jsonpResponse = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS, "删除成功", null, null, request);
        return jsonpResponse;
    }

}
