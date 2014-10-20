package com.sworker.logic;

import com.sworker.common.util.ObjectUtil;
import com.sworker.entity.SwFileAuthorityEntity;
import com.sworker.entity.SwFileDirectoryEntity;
import com.sworker.entity.SwFileVersionEntity;
import com.sworker.entity.SwFileinfoEntity;
import com.sworker.model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangjin on 2014/8/27.
 */
public interface IFileLogic {
    /**
     * 获取根目录实体
     * @param param 企业ID 目录ID 群组ID 用户ID 目录实体
     * @return 目录实体
     */
    public SwFileDirectoryEntity getCurrentDirectory(FileParam param) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    /**
     * 获取子目录实体
     * @param param 文件参数
     * @return 子目录实体
     */
    public List<DirectoryModel> getSubDirectorys(FileParam param) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    /**
     * 判断当前目录是否为空
     * @param baseDirectoryId 父目录id
     * @return boolean
     */
    public Boolean isDirectoryEmpty(Long baseDirectoryId);

    /**
     * 删除目录
     * @param companyId 公司id
     * @param baseDirectoryId 父目录id
     * @return boolean
     */
    public Boolean deleteDirectory(BigInteger companyId, BigInteger baseDirectoryId);

    /**
     * 删除目录或文件权限
     * @param companyId 公司id
     * @param sourceId 资源id
     * @return boolean
     */
    public Boolean deleteObjectAuth(Long companyId, Long sourceId);

    /**
     * 判断父目录下有无重名目录
     * @param param 企业ID 父目录ID 当前目录名称
     * @return boolean
     */
    public Boolean isDirectoryRepeat(FileParam param);

    /**
     * 重命名目录
     * @param param 企业ID 目录ID  目录名称  更新人
     * @return boolean
     */
    public Boolean updateDirectory(FileParam param);

    /**
     * 创建目录
     * @param param 企业ID 群组ID 父目录ID 目录名称 是否继承上层文件夹
     *              创建人
     * @return
     */
    public Boolean createDirectory(FileParam param);

    /**
     * 创建目录或文件权限
     * @param param 企业ID 父资源ID 资源ID 是否继承上层文件夹
     *              管理权限用户列表 可见范围用户列表
     * @return
     */
    public Boolean createObjectAuth(FileParam param);


    /**
     * 根据关键字模糊查询返回目录列表
     * @param param 企业ID 关键字 页码 记录数
     * @return 返回目录实体列表
     */
    public List<SwFileDirectoryEntity> findDirectoryByWord(FileParam param);

    /**
     * 根据关键字模糊查询返回文件列表
     * @param param 企业ID 关键字  页码 记录数
     * @return 文件实体列表
     */
    public List<SwFileinfoEntity> findFileByWord(FileParam param);

    /**
     * 判断父目录下有无重名文件
     * @param companyId 企业ID
     * @param baseDirectoryId 父目录ID
     * @param fileName 当前文件名称
     * @return boolean
     */
    public Boolean isFileRepeat(BigInteger companyId, BigInteger baseDirectoryId, String fileName);

    /**
     * 修改文件名
     * @param param 企业ID 文件ID 文件名称 更新人
     * @return 标志位
     */
    public Boolean updateFileName(FileParam param);

    /**
     * 上传文件
     * @param param 上传文件参数
     * @return 文件实体列表
     */
    public List<SwFileinfoEntity> putFile(FileUploadParam param) throws FileNotFoundException;

    /**
     * 获取文件权限或目录权限
     * @param companyId 企业ID
     * @param sourceId 资源ID
     * @param sourceType 资源类型
     * @return 权限键值对象
     */
    public Map<String, List> findObjectAuth(Long companyId, Long sourceId, Integer sourceType);

    /**
     * 上传文件新版本
     * @param param 企业ID 文件ID bucket 更新说明 文件对象 更新人
     * @return 标志位
     */
    public Boolean putFileVersion(FileParam param);

    /**
     * 获取文件信息
     * @param companyId 企业ID
     * @param fileId 文件ID
     * @return
     */
    public FileModel findFileInfo(Long companyId, Long fileId);

    /**
     * 获取OSS文件对象
     * @param bucketName OSSBucket
     * @param key OSS对象key
     * @return 文件流
     */
    public InputStream getFileObject(String bucketName, String key);


    /**
     * 获取文件历史版本
     * @param versionId 当前版本ID
     * @return 文件版本实体
     */
    public SwFileVersionEntity getHistoryFileList(Long versionId);

    /**
     * 修改文件描述
     * @param
     * @return
     */
    public Boolean updateFileDesc(Long fileId, String fileDes, Long updator);

    /**
     * 修改文件状态
     * @param param 文件ID 状态
     * @return 标志
     */
    public Boolean updateFileState(FileParam param);

    //deleteFile  TODO

    /**
     * 更新浏览次数
     * @param fileId 文件ID
     * @return boolean
     */
    public Boolean updateViewCount(Long fileId);

    /**
     * 更新下载次数
     * @param fileId 文件ID
     * @return 标志位
     */
    public Boolean updateDownloadCount(Long fileId);

    /**
     * 初始化群组文件空间
     * @param groupId 文件ID
     * @param companyId 企业ID
     * @return 标志位
     */
    public Boolean initGroupFileSpace(Long groupId, Long companyId);

    /**
     * 初始化用户文件空间
     * @param userId 文件ID
     * @param companyId 企业ID
     * @return 标志位
     */
    public Boolean initUserFileSpace(Long userId, Long companyId);

    /**
     * 删除阿里云上的文件
     * @param bucket bucket
     * @param key 文件key
     * @return 标志位
     */
    public Boolean deleteOssFile(String bucket, String key);

}
