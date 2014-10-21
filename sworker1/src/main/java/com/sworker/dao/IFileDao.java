package com.sworker.dao;

import com.sworker.entity.SwFileAuthorityEntity;
import com.sworker.entity.SwFileDirectoryEntity;
import com.sworker.entity.SwFileVersionEntity;
import com.sworker.entity.SwFileinfoEntity;
import com.sworker.model.FileModel;

import java.util.List;

/**
 * Created by zhangjin on 2014/8/28.
 */
public interface IFileDao {

    /**
     * 创建路径
     * @param swFileDirectoryEntity 路径实体
     * @return 主键
     */
    public Long createDirectory(SwFileDirectoryEntity swFileDirectoryEntity);

    /**
     * 重命名目录
     * @param swFileDirectoryEntity 路径实体
     * @return 标志位
     */
    public Boolean updateDirectory(SwFileDirectoryEntity swFileDirectoryEntity);


    /**
     * 查询目录列表
     * @param companyId 企业ID
     * @param baseDirectory 父目录ID
     * @param fileName 当前目录名称
     * @return 标志位
     */
    public List<SwFileDirectoryEntity> queryDirectoryList(Long companyId, Long baseDirectory, String fileName);

    /**
     * test
     */
    public List<SwFileAuthorityEntity> queryGroupAuthorityList(Long companyId, Long directoryId, Long groupId);


    /**
     * 查询群组权限列表
     * @param companyId 企业ID
     * @param directoryId 目录ID
     * @param groupId 群组ID
     * @return 权限列表
     */
    public List<SwFileAuthorityEntity> groupAuthorityList(Long companyId, Long directoryId, Long groupId);

    /**
     * 查询用户权限列表
     * @param companyId 企业ID
     * @param directoryId 目录ID
     * @param userId 用户id
     * @return 权限列表
     */
    public List<SwFileAuthorityEntity> userAuthorityList(Long companyId, Long directoryId, Long userId);

    /**
     * 后台角色权限列表
     * @param companyId 企业ID
     * @param directoryId 目录ID
     * @param userId 用户id
     * @return 权限列表
     */
    public List<SwFileAuthorityEntity> backRoleAuthorityList(Long companyId, Long directoryId, Long userId);

    /**
     * 获得目录映射实体
     * @param directoryId 路径
     * @return SwFileDirectoryEntity
     */
    public SwFileDirectoryEntity getDirectory(Long directoryId);

    /**
     * 获取当前用户所在群组对子目录的权限列表
     * @param companyId 企业ID
     * @param baseDirectoryId 父目录ID
     * @param groupId 群组ID
     * @return 权限列表
     */
    public List<SwFileAuthorityEntity> querySubGroupAuList(Long companyId, Long baseDirectoryId, Long groupId);


    /**
     *
     * @param companyId 企业ID
     * @param baseDirectoryId 目录ID
     * @param userId 用户id
     * @return 权限列表
     */
    public List<SwFileAuthorityEntity> querySubUserAuList(Long companyId, Long baseDirectoryId, Long userId);

    /**
     * 查询子目录列表
     * @param baseDirectoryId 父目录ID
     * @return 子目录列表
     */
    public List<SwFileDirectoryEntity> querySubFileDirectoryList(Long baseDirectoryId);

    /**
     * 删除目录
     * @param companyId 企业ID
     * @param baseDirectoryId 父目录ID
     * @return flag
     */
    public Boolean deleteDirectory(Long companyId, Long baseDirectoryId);


    /**
     * 删除目录或文件权限
     * @param companyId 企业ID
     * @param sourceId 资源ID
     * @return flag
     */
    public Boolean deleteObjectAuth(Long companyId, Long sourceId);

    /**
     * 批量保存文件权限
     * @param entities 文件权限实体
     * @return flag
     */
    public Boolean createFileAuList(List<SwFileAuthorityEntity> entities);

    /**
     *
     * @param companyId 企业ID
     * @param sourceId 资源ID
     * @param auType 权限类型
     * @return 权限列表
     */
    public List<SwFileAuthorityEntity> querySourceGroupAuList(Long companyId, Long sourceId, Integer auType);

    /**
     * 根据关键字模糊查询
     * @param companyId 企业ID
     * @param keyWord 关键字
     * @param pageNum 页码
     * @param pageSize 记录数
     * @return 目录实体列表
     */
    public List<SwFileDirectoryEntity> queryDirectoryList(Long companyId, String keyWord, Integer pageNum, Integer pageSize);

    /**
     * 批量创建文件实体
     * @param fileinfoList 文件信息列表
     * @return 标志位
     */
    public Boolean bachCreateFiles(List<SwFileinfoEntity> fileinfoList);

    /**
     * 创建文件实体
     * @param fileinfoEntity 文件实体
     * @return 主键
     */
    public Long createFile(SwFileinfoEntity fileinfoEntity);

    /**
     * 创建文件版本实体
     * @param fileVersionEntity 文件版本实体
     * @return 主键
     */
    public Long createFileVersion(SwFileVersionEntity fileVersionEntity);

    /**
     * 查询文件信息实体
     * @param id 文件主键
     * @return 文件实体
     */
    public SwFileinfoEntity queryFileInfo(Long id);

    /**
     * 更新文件表
     * @param entity 文件实体
     * @return 标志
     */
    public Boolean updateFileInfo(SwFileinfoEntity entity);

    /**
     * 查询文件信息列表
     * @param companyId 企业ID
     * @param keyWord 关键字
     * @param pageNum 页码
     * @param pageSize 行数
     * @return 文件列表
     */
    public List<SwFileinfoEntity> queryFileInfoList(Long companyId, String keyWord, Integer pageNum, Integer pageSize);

    /**
     * 获取文件权限或目录权限
     *
     * @param companyId  企业ID
     * @param sourceId   资源ID
     * @param sourceType 资源类型
     * @return 权限列表
     */
    public List<SwFileAuthorityEntity> queryFileAuthorityList(Long companyId, Long sourceId, Integer sourceType);

    /**
     * 查询文件实体
     * @param companyId  企业ID
     * @param fileId 文件ID
     * @return 文件实体
     */
    public FileModel getFileModel(Long companyId, Long fileId);

    /**
     * 获取文件版本实体
     * @param versionId 版本ID
     * @return 文件版本实体
     */
    public SwFileVersionEntity getFileVersion(Long versionId);

    /**
     * 更新文件表状态
     * @param fileId 文件主键
     * @param status 状态
     * @return flag
     */
    public Boolean updateFileStatus(Long fileId, Integer status);

    /**
     * 更新文件浏览次数
     * @param fileId 文件id
     * @return flag
     */
    public Boolean updateViewCount(Long fileId);

    /**
     * 更新下载次数
     * @param fileId 文件id
     * @return flag
     */
    public Boolean updateDownloadCount(Long fileId);

    /**
     * 获取字文件列表
     * @param baseDirectoryId 父目录id
     * @return 子文件列表
     */
    public List<SwFileinfoEntity> queryFileInfoList(Long baseDirectoryId);

    /**
     * 获取路径列表
     * @param baseDirectoryId 父目录id
     * @return 子文件列表
     */
    public List<SwFileDirectoryEntity> queryFileDirectoryList(Long baseDirectoryId);
}
