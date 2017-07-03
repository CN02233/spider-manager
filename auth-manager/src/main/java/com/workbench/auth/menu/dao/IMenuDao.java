package com.workbench.auth.menu.dao;

import com.workbench.auth.menu.entity.Menu;
import com.workbench.auth.menu.entity.RoleMenu;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by pc on 2017/7/3.
 */
public interface IMenuDao {

    @Select("select am.* from user u " +
            "inner join user_role_assign ura on u.user_id = ura.user_id and u.user_name=#{user_nm} " +
            "inner join user_role_privilege urp on ura.user_role_id = urp.user_role_id " +
            "inner join app_module am on urp.module_id = am.module_id")
    @Options(useCache = false)
    List<Menu> getMenuList4User(String user_nm);

    List<Menu> listMenuByPage(int currPage,int pageSize);

    List<Menu> getMenuList4Role(int role_id);

    void saveNewRoleMenu(RoleMenu roleMenu);

    void deleteRoleMenu(RoleMenu roleMenu);

    void deleteAllMenu4Role(int role_id);
}
