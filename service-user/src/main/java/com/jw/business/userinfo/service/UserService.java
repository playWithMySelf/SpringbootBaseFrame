package com.jw.business.userinfo.service;

import com.jw.business.userinfo.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/16.
 */
public interface UserService {

    Map addUser(User user);

    Map findAllUser(Map param);

    Map updateUser(User user);
}
