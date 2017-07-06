package com.workbench.auth.role.service;

import com.workbench.auth.menu.entity.Menu;
import com.workbench.auth.role.entity.RoleMenu;

import java.util.List;

/**
 * Created by SongCQ on 2017/7/6.
 */
public interface RoleMenuManageService {

    List<Menu> getMenuByRole(int role_id);

    void saveMenuForRole(RoleMenu roleMenu);

    void delMenuFromRole(RoleMenu roleMenu);

    void delMenuByRoleId(int role_id);

}
