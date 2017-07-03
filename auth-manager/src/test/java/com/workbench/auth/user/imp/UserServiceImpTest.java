package com.workbench.auth.user.imp;

import com.AbstractTestService;
import com.workbench.auth.user.UserService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by pc on 2017/6/30.
 */
public class UserServiceImpTest extends AbstractTestService {

    @Resource
    private UserService userService;

    @Test
    public void checkUser() throws Exception {

    }

    @Test
    public void listAllUser() throws Exception {
        userService.listAllUser();
    }

    @Test
    public void createUser() throws Exception {

    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void delUser() throws Exception {

    }

    @Test
    public void getUserByUserId() throws Exception {

    }

    @Test
    public void getUserByUserNm() throws Exception {

    }

}