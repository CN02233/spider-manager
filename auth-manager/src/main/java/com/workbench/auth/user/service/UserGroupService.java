package com.workbench.auth.user.service;

import com.workbench.auth.group.entity.Group;

import java.util.List;

/**
 * Created by SongCQ on 2017/7/7.
 */
public interface UserGroupService {

    List<Group> listGroupByUserId(int user_id);

    void saveUserGroup(int user_id,int user_group_id);

    void delUserGroup(int user_id,int user_group_id);
}
