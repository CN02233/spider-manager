package com.workbench.auth.user.imp;

import com.workbench.auth.user.entity.User;
import com.workbench.auth.user.UserService;
import com.workbench.auth.user.dao.IUserServiceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc on 2017/6/29.
 */
@Service("userService")
public class UserServiceImp implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private IUserServiceDao userServiceDao;

    public User checkUser(String userNm, String password) {
        User resultUser = userServiceDao.checkUserByUserNm(userNm);
        logger.debug("check user result {}",resultUser.toString());
        return resultUser;
    }

    public List<User> listAllUser(){
        List<User> allUser = userServiceDao.listAllUser();
        logger.debug("check user result {}",allUser.toString());
        return allUser;
    }

    public List<User> listUsersForPage(int currPage,int pageSize){
        List<User> allUser = userServiceDao.listUsersForPage(currPage,pageSize);
        logger.debug("check user result {}",allUser.toString());
        return allUser;
    }

    public void createUser(User user){

    }

    public void updateUser(User user){

    }

    public void delUser(User user){

    }

    public User getUserByUserId(Integer userId){
        User resultUser = userServiceDao.getUserByUserId(userId);
        logger.debug(resultUser.toString());
        return resultUser;
    }

    public User getUserByUserNm(String userName){
        User resultUser = userServiceDao.checkUserByUserNm(userName);
        logger.debug(resultUser.toString());
        return resultUser;
    }
}
