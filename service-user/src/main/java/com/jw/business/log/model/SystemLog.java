package com.jw.business.log.model;

import java.util.Date;

public class SystemLog {
    private Integer id;

    private String userid;

    private Date usedate;

    private String usemoudle;

    private String remark;

    private String userxm;

    private String sszzjg;

    private String usefunction;

    private String userip;

    private String optiontype;

    private String optiondata;

    private String domainid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Date getUsedate() {
        return usedate;
    }

    public void setUsedate(Date usedate) {
        this.usedate = usedate;
    }

    public String getUsemoudle() {
        return usemoudle;
    }

    public void setUsemoudle(String usemoudle) {
        this.usemoudle = usemoudle == null ? null : usemoudle.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUserxm() {
        return userxm;
    }

    public void setUserxm(String userxm) {
        this.userxm = userxm == null ? null : userxm.trim();
    }

    public String getSszzjg() {
        return sszzjg;
    }

    public void setSszzjg(String sszzjg) {
        this.sszzjg = sszzjg == null ? null : sszzjg.trim();
    }

    public String getUsefunction() {
        return usefunction;
    }

    public void setUsefunction(String usefunction) {
        this.usefunction = usefunction == null ? null : usefunction.trim();
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip == null ? null : userip.trim();
    }

    public String getOptiontype() {
        return optiontype;
    }

    public void setOptiontype(String optiontype) {
        this.optiontype = optiontype == null ? null : optiontype.trim();
    }

    public String getOptiondata() {
        return optiondata;
    }

    public void setOptiondata(String optiondata) {
        this.optiondata = optiondata == null ? null : optiondata.trim();
    }

    public String getDomainid() {
        return domainid;
    }

    public void setDomainid(String domainid) {
        this.domainid = domainid == null ? null : domainid.trim();
    }
}