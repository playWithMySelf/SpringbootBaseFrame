package com.jw.business.websocket.service.impl;

import com.jw.business.websocket.server.SocketServer;
import com.jw.business.websocket.service.WebSocketService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: inwei
 * @create: ${Date} ${time}
 * @description:
 * @param: ${params}
 * @return: ${returns}
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {

     /**
      *@author: jinwei【jin_wei@founder.com.cn】
      *@description: 获取websocket目前的统计信息，人数、用户姓名列表
      *@create: 2018/7/24 11:09
      */
    public Map getTongji(Map param){
        Map resmap = new HashMap();
        try {
            resmap.put("num", SocketServer.getOnlineNum());
            resmap.put("users", SocketServer.getOnlineUsers());
        } catch (Exception e) {
            e.printStackTrace();
            resmap.put("code","EC01");
            resmap.put("msg","获取websocket统计信息失败");
        }
        resmap.put("code","EC00");
        resmap.put("msg","succes");
        return resmap;
    }

    /**
    *@author: jinwei【jin_wei@founder.com.cn】
    *@description: 给指定的用户发送消息
    *@create: 2018/7/24 11:09
    */
    public Map sendmsg(Map param){
        Map resmap = new HashMap();
        try {
            String username = (String) param.get("username");
            String msg = (String) param.get("msg");
            SocketServer.sendMessage(msg, username);
        } catch (Exception e) {
            e.printStackTrace();
            resmap.put("code","EC01");
            resmap.put("msg","websocket发送信息失败");
        }
        resmap.put("code","EC00");
        resmap.put("msg","succes");
        return resmap;
    }

    /**
     *@author: jinwei【jin_wei@founder.com.cn】
     *@description: 给所有的用户发送消息
     *@create: 2018/7/24 11:09
     */
    public Map sendAll(Map param){
        Map map2 = new HashMap();
        try {
            String msg = (String) param.get("msg");
            SocketServer.sendAll(msg);
        } catch (Exception e) {
            e.printStackTrace();
            map2.put("code","EC01");
            map2.put("msg","websocket发送信息失败");
        }
        map2.put("code","EC00");
        map2.put("msg","succes");
        return map2;
    }
}
