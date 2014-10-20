package com.sworker.model;

import com.sworker.entity.SwAccountInfoEntity;
import com.sworker.entity.SwFileAuthorityEntity;
import com.sworker.entity.SwFileinfoEntity;

import java.util.List;


/**
 * Created by zhangjin on 2014/8/27.
 */
public class FileParam {

    //公司ID
    private Long companyId;

    //路径id
    private Long directoryId;

    //父目录ID
    private Long baseDirectoryId;

    //目录名称
    private String directoryName;

    //群组ID
    private Long groupId;

    //用户ID
    private Long userId;

    //更新人
    private Long updateUserId;



    //创建人ID
    private Long createUserId;

    //文件类型
    private String fileType;

    //文件名称
    private String fileName;

    //文件id
    private Long fileId;

    //bucket
    private String bucket;

    //是否继承上层文件夹
    private Integer isExtends;

    //资源id
    private Long sourceId;

    //父资源id
    private Long baseSourceId;

    //关键字
    private String keyWord;

    //页码
    private Integer pageNum;

    //行号
    private Integer rowNum;

    //记录数
    private Integer countNum;

    //更新说明
    private String updateDes;

    //版本Id
    private Long versionId;

    //资源类型
    private Integer sourceType;

    //文件状态
    private Integer status;

    //文件对象
    private SwFileinfoEntity fileinfoEntity;

    //管理权限用户列表
    private List<Integer> authorityUserList;

    //可见范围用户列表
    private List<Integer> visiableUserList;

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

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getUpdateDes() {
        return updateDes;
    }

    public void setUpdateDes(String updateDes) {
        this.updateDes = updateDes;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public SwFileinfoEntity getFileinfoEntity() {
        return fileinfoEntity;
    }

    public void setFileinfoEntity(SwFileinfoEntity fileinfoEntity) {
        this.fileinfoEntity = fileinfoEntity;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getBaseSourceId() {
        return baseSourceId;
    }

    public void setBaseSourceId(Long baseSourceId) {
        this.baseSourceId = baseSourceId;
    }

    public Integer getIsExtends() {
        return isExtends;
    }

    public void setIsExtends(Integer isExtends) {
        this.isExtends = isExtends;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Long directoryId) {
        this.directoryId = directoryId;
    }

    public Long getBaseDirectoryId() {
        return baseDirectoryId;
    }

    public void setBaseDirectoryId(Long baseDirectoryId) {
        this.baseDirectoryId = baseDirectoryId;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
