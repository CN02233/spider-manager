package com.workbench.auth.user;

import com.workbench.auth.user.entity.User;

import java.util.List;

/**
 * Created by pc on 2017/6/29.
 */
public interface UserService {

    User checkUser(String userNm, String password);

    List<User> listAllUser();

    List<User> listUsersForPage(int currPage,int pageSize);

    void createUser(User user);

    void updateUser(User user);

    void delUser(User user);

    User getUserByUserId(Integer userId);

    User getUserByUserNm(String userName);

}
