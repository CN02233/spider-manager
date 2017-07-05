package com.workbench.auth.menu.service;

import com.workbench.auth.menu.entity.Menu;
import com.workbench.auth.menu.entity.RoleMenu;

import java.util.List;

/**
 * Created by pc on 2017/7/3.
 */
public interface MenuService {

    List<Menu> listMenuByPage(int currPage,int pageSize);

    List<Menu> getMenuList4Role(int role_id);

    void saveNewRoleMenu(RoleMenu roleMenu);

    void deleteRoleMenu(RoleMenu roleMenu);

    void deleteAllMenu4Role(int role_id);
}
