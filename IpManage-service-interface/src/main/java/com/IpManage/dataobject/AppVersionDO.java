package com.IpManage.dataobject;


import java.io.Serializable;

/**
 * @author: yangfei
 * @Date: 2016/4/15 Time: 15:52
 * @desc 功能描述
 */
public class AppVersionDO implements Serializable {

    private static final long serialVersionUID = -8863024233793114159L;
    private Integer app_version_id;
    private String app_name;
    private String app_version;
    private String app_type;
    private String app_status;
    private String app_path;

    public Integer getApp_version_id() {
        return app_version_id;
    }

    public void setApp_version_id(Integer app_version_id) {
        this.app_version_id = app_version_id;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getApp_status() {
        return app_status;
    }

    public void setApp_status(String app_status) {
        this.app_status = app_status;
    }

    public String getApp_path() {
        return app_path;
    }

    public void setApp_path(String app_path) {
        this.app_path = app_path;
    }

    @Override
    public String toString() {
        return "AppVersionDO{" +
                "app_version_id=" + app_version_id +
                ", app_name='" + app_name + '\'' +
                ", app_version='" + app_version + '\'' +
                ", app_type='" + app_type + '\'' +
                ", app_status='" + app_status + '\'' +
                ", app_path='" + app_path + '\'' +
                '}';
    }
}