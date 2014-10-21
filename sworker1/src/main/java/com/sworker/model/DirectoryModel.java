package com.sworker.model;

import com.sworker.entity.SwFileDirectoryEntity;

/**
 * Created by zhangjin on 2014/9/3.
 */
public class DirectoryModel extends SwFileDirectoryEntity {

    //管理权限
    private Boolean canManage;

    //可见范围
    private Boolean canScan;

    public Boolean getCanManage() {
        return canManage;
    }

    public void setCanManage(Boolean canManage) {
        this.canManage = canManage;
    }

    public Boolean getCanScan() {
        return canScan;
    }

    public void setCanScan(Boolean canScan) {
        this.canScan = canScan;
    }
}
