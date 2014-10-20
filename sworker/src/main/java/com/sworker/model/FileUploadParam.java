package com.sworker.model;

import com.sworker.entity.SwAccountInfoEntity;
import com.sworker.entity.SwFileAuthorityEntity;

import java.io.File;
import java.util.List;

/**
 * Created by zhangjin on 2014/8/28.
 */
public class FileUploadParam {
    //文件数组
    private List<File> fileList;

    //bucket
    private String bucket;

    //企业Id
    private Long companyId;

    //群组ID
    private Long groupId;

    //父目录id
    private Long  baseDirectoryId;

    //是否继承上层文件夹
    private Integer isExtendsUpFile;

    //管理权限用户列表
    private List<Integer> authorityUserList;

    //可见范围用户列表
    private List<Integer> visiableUserList;

    //创建人
    private Long creatorId;

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getBaseDirectoryId() {
        return baseDirectoryId;
    }

    public void setBaseDirectoryId(Long baseDirectoryId) {
        this.baseDirectoryId = baseDirectoryId;
    }

    public Integer getIsExtendsUpFile() {
        return isExtendsUpFile;
    }

    public void setIsExtendsUpFile(Integer isExtendsUpFile) {
        this.isExtendsUpFile = isExtendsUpFile;
    }

    public List<Integer> getAuthorityUserList() {
        return authorityUserList;
    }

    public void setAuthorityUserList(List<Integer> authorityUserList) {
        this.authorityUserList = authorityUserList;
    }

    public List<Integer> getVisiableUserList() {
        return visiableUserList;
    }

    public void setVisiableUserList(List<Integer> visiableUserList) {
        this.visiableUserList = visiableUserList;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
