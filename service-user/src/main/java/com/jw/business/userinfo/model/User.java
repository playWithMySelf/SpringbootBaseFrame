package com.jw.business.userinfo.model;

public class User {
    private String userid;

    private String username;

    private String password;

    private String isenabled;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIsenabled() {
        return isenabled;
    }

    public void setIsenabled(String isenabled) {
        this.isenabled = isenabled == null ? null : isenabled.trim();
    }
}