package com.jw.business.userinfo.mapper;

import com.jw.business.userinfo.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(String userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAllUser(Map param);

    String selectAllUserCount(Map param);
}