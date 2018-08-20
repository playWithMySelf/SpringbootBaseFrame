package com.jw.business.userinfo.service.impl;

import com.github.pagehelper.PageHelper;
import com.jw.business.userinfo.mapper.UserMapper;
import com.jw.business.userinfo.model.User;
import com.jw.business.userinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: inwei
 * @create: ${Date} ${time}
 * @description:
 * @param: ${params}
 * @return: ${returns}
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    /* 事务测试
    @Override
    @Transactional
    public int addUser(User user) {
        userMapper.insertSelective(user);
        return userMapper.insertSelective(null);
    }*/

    @Override
    public Map addUser(User user) {
        Map map = new HashMap();
        try{
            map.put("data",userMapper.insertSelective(user));
        }catch(Exception e){
            e.printStackTrace();
            map.put("code","EC01");
            map.put("msg","插入异常."+e.getMessage());
            return map;
        }
        map.put("code","EC00");
        map.put("msg","success");
        return map;
    }

    /*
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public Map findAllUser(Map param) {
        Map map = new HashMap();
        param.put("start",1);
        try{
            map.put("data",userMapper.selectAllUser(param));
            map.put("count",userMapper.selectAllUserCount(param));
        }catch(Exception e){
            e.printStackTrace();
            map.put("code","EC01");
            map.put("msg","查询异常");
            return map;
        }
        map.put("code","EC00");
        map.put("msg","success");
        return map;
    }

    @Override
    public Map updateUser(User user) {
        Map map = new HashMap();
        try{
            map.put("data",userMapper.updateByPrimaryKeySelective(user));
        }catch(Exception e){
            e.printStackTrace();
            map.put("code","EC01");
            map.put("msg","更新异常."+e.getMessage());
            return map;
        }
        map.put("code","EC00");
        map.put("msg","success");
        return map;
    }
}
