package com.workbench.auth.menu.service.imp;

import com.workbench.auth.menu.dao.IMenuDao;
import com.workbench.auth.menu.entity.Menu;
import com.workbench.auth.menu.entity.RoleMenu;
import com.workbench.auth.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc on 2017/7/3.
 */
@Service("menuService")
public class MenuServiceImp implements MenuService{

    @Autowired
    private IMenuDao menuDao;

    public List<Menu> getMenuList4User(String user_nm) {

        List<Menu> allMenuList = menuDao.getMenuList4User(user_nm);

        return allMenuList;
    }

    public List<Menu> listMenuByPage(int currPage, int pageSize) {
        return null;
    }

    public List<Menu> getMenuList4Role(int role_id) {
        return null;
    }

    public void saveNewRoleMenu(RoleMenu roleMenu) {

    }

    public void deleteRoleMenu(RoleMenu roleMenu) {

    }

    public void deleteAllMenu4Role(int role_id) {

    }
}
