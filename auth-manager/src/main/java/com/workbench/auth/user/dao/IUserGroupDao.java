package com.workbench.auth.user.dao;

import com.workbench.auth.group.entity.Group;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by SongCQ on 2017/7/7.
 */
public interface IUserGroupDao {

    @Select("select ug.* from user_group_member ugm inner join user_group ug on " +
            "ugm.user_group_id=ug.user_group_id and ugm.user_id=#{user_id}")
    @Options(useCache = false)
    List<Group> listGroupByUserId(int user_id);

    @Insert("insert into user_group_member (user_id,user_group_id) values (#{user_id},#{user_group_id})")
    @Options(useCache = false)
    void saveUserGroup(int user_id, int user_group_id);

    @Delete("delete from user_group_member where user_id=#{user_id} and user_group_id=#{user_group_id}")
    @Options(useCache = false)
    void delUserGroup(int user_id, int user_group_id);
}
