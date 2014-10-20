package com.sworker.dao;

import com.sworker.common.CodeBook;
import com.sworker.common.util.ObjectUtil;
import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwFileAuthorityEntity;
import com.sworker.entity.SwFileDirectoryEntity;
import com.sworker.entity.SwFileVersionEntity;
import com.sworker.entity.SwFileinfoEntity;
import com.sworker.model.FileModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangjin on 2014/8/28.
 */
@Repository
public class FileDao extends AbstractHibernateDao<SwFileinfoEntity> implements IFileDao{

    /**
     * constructor
     */
    public FileDao(){
        super();
        this.setClazz(SwFileinfoEntity.class);
    }


    /**
     * 创建路径
     * @param swFileDirectoryEntity 路径实体
     * @return 主键
     */
    @Transactional
    @Override
    public Long createDirectory(SwFileDirectoryEntity swFileDirectoryEntity) {
        Session session = getCurrentSession();
        session.save(swFileDirectoryEntity);
        return swFileDirectoryEntity.getDirectoryid();
    }

    /**
     * 重命名目录
     *
     * @param swFileDirectoryEntity 路径实体
     * @return 标志位
     */
    @Transactional
    @Override
    public Boolean updateDirectory(SwFileDirectoryEntity swFileDirectoryEntity) {
        Session session = getCurrentSession();
        session.update(swFileDirectoryEntity);
        return true;
    }

    /**
     * 查询目录列表
     *
     * @param companyId     企业ID
     * @param baseDirectory 父目录ID
     * @param fileName      当前目录名称
     * @return 标志位
     */
    @Transactional
    @Override
    public List<SwFileDirectoryEntity> queryDirectoryList(Long companyId, Long baseDirectory, String fileName) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from  SwFileDirectoryEntity where enterpriseid = :companyId and parentid = :baseDirectory" +
                "and dirname = :fileName");
        query.setParameter("companyId", companyId);
        query.setParameter("baseDirectory", baseDirectory);
        query.setParameter("fileName", fileName);
        List<SwFileDirectoryEntity> list = query.list();

        return list;
    }

    /**
     * test for inner join querys
     * test
     */
    @Transactional
    @Override
    public List<SwFileAuthorityEntity> queryGroupAuthorityList(Long companyId, Long directoryId, Long groupId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select file from SwFileinfoEntity as file " +
                "inner join file.swGroupInfoEntity entity");
        List<SwFileinfoEntity> list = query.list();
        return null;
    }

    /**
     * 查询群组权限列表
     *
     * @param companyId   企业ID
     * @param directoryId 目录ID
     * @param groupId     群组ID
     * @return 权限列表
     */
    @Transactional
    @Override
    public List<SwFileAuthorityEntity> groupAuthorityList(Long companyId, Long directoryId, Long groupId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select au from SwFileAuthorityEntity as au " +
                        "inner join au.drEntity dir " +
                        "inner join au.groupEntity grou " +
                        "where dir.enterpriseid = au.enterpriseid " +
                        "and dir.enterpriseid = :companyId " +
                        "and dir.directoryid = :directoryId " +
                        "and grou.id = :groupId " +
                        //群组权限
                        "and au.authtype = :types"
        );
        query.setParameter("companyId", companyId);
        query.setParameter("directoryId", directoryId);
        query.setParameter("groupId", groupId);
        query.setParameter("types", CodeBook.FILEAU_ROLE_GROUP);
        List<SwFileAuthorityEntity> list = query.list();

        return list;
    }

    /**
     * 查询用户权限列表
     *
     * @param companyId   企业ID
     * @param directoryId 目录ID
     * @return
     */
    @Transactional
    @Override
    public List<SwFileAuthorityEntity> userAuthorityList(Long companyId, Long directoryId, Long userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select au from SwFileAuthorityEntity as au " +
                        "inner join au.drEntity dir " +
                        "inner join au.userEntity user " +
                        "where dir.enterpriseid = au.enterpriseid " +
                        "and dir.enterpriseid = :companyId " +
                        "and user.id = :userId " +
                        "and dir.directoryid = :directoryId " +
                        "and au.authtype = :types"
        );
        query.setParameter("companyId", companyId);
        query.setParameter("userId", userId);
        query.setParameter("directoryId", directoryId);
        query.setParameter("types", CodeBook.FILEAU_ROLE_USER);
        List<SwFileAuthorityEntity> list = query.list();
        return list;
    }

    /**
     * 后台角色权限列表
     *
     * @param companyId   企业ID
     * @param directoryId 目录ID
     * @param userId      用户id
     * @return
     */
    @Transactional
    @Override
    public List<SwFileAuthorityEntity> backRoleAuthorityList(Long companyId, Long directoryId, Long userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from  SwFileAuthorityEntity as au " +
                "inner join au.drEntity dir " +
                "");
        return null;
    }

    /**
     * 获得目录映射实体
     *
     * @param directoryId 路径ID
     * @return SwFileDirectoryEntity 路径实体
     */
    @Transactional
    @Override
    public SwFileDirectoryEntity getDirectory(Long directoryId) {
        Session session = getCurrentSession();

        SwFileDirectoryEntity entity = (SwFileDirectoryEntity)session.get(SwFileDirectoryEntity.class, directoryId);

        return entity;
    }

    /**
     * 获取当前用户所在群组对子目录的权限列表
     *
     * @param companyId       企业ID
     * @param baseDirectoryId 父目录ID
     * @param groupId         群组ID
     * @return 权限列表
     */
    @Transactional
    @Override
    public List<SwFileAuthorityEntity> querySubGroupAuList(Long companyId, Long baseDirectoryId, Long groupId) {
        Session session = getCurrentSession();

        Query query = session.createQuery("select au from SwFileAuthorityEntity au " +
                "inner join au.drEntity dr " +
                "inner join au.groupEntity grou " +
                "where au.enterpriseid = dr.enterpriseid " +
                "and dr.enterpriseid = :companyId " +
                "and dr.parentid = :baseDirectoryId " +
                "and grou.id = :groupId " +
                "and au.authtype = :types");

        query.setParameter("companyId", companyId);
        query.setParameter("baseDirectoryId", baseDirectoryId);
        query.setParameter("groupId", groupId);
        query.setParameter("types", 1);

        List<SwFileAuthorityEntity> list = query.list();

        return list;
    }

    /**
     * @param companyId       企业ID
     * @param baseDirectoryId 目录ID
     * @param userId          用户id
     * @return 权限列表
     */
    @Transactional
    @Override
    public List<SwFileAuthorityEntity> querySubUserAuList(Long companyId, Long baseDirectoryId, Long userId) {
        Session session = getCurrentSession();

        Query query = session.createQuery("select au from SwFileAuthorityEntity as au " +
                "inner join au.drEntity dr " +
                "inner join au.userEntity user " +
                "where au.enterpriseid = dr.enterpriseid " +
                "and au.userroleid = :userId " +
                "and dr.enterpriseid = :companyId " +
                "and dr.parentid = :baseDirectoryId " +
                "and au.authtype = :types");

        query.setParameter("userId", userId);
        query.setParameter("baseDirectoryId", baseDirectoryId);
        query.setParameter("companyId", companyId);
        query.setParameter("types", 0);

        List<SwFileAuthorityEntity> list = query.list();

        return list;
    }

    /**
     * 查询子目录列表
     *
     * @param baseDirectoryId 父目录ID
     * @return 子目录列表
     */
    @Transactional
    @Override
    public List<SwFileDirectoryEntity> querySubFileDirectoryList(Long baseDirectoryId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwFileDirectoryEntity where parentid = :baseDirectoryId");
        query.setParameter("baseDirectoryId", baseDirectoryId);

        List<SwFileDirectoryEntity> list = query.list();

        return list;
    }

    /**
     * 删除目录
     *
     * @param companyId       企业ID
     * @param baseDirectoryId 父目录ID
     * @return flag
     */
    @Transactional
    @Override
    public Boolean deleteDirectory(Long companyId, Long baseDirectoryId) {
        Session session = getCurrentSession();

        Query query = session.createQuery("delete SwFileDirectoryEntity dr where dr.enterpriseid = :companyId " +
                "and dr.parentid = :baseDirectoryId");
        query.setParameter("companyId", companyId);
        query.setParameter("baseDirectoryId", baseDirectoryId);
        query.executeUpdate();

        return true;
    }

    /**
     * 删除目录或文件权限
     *
     * @param companyId 企业ID
     * @param sourceId  资源ID
     * @return flag
     */
    @Transactional
    @Override
    public Boolean deleteObjectAuth(Long companyId, Long sourceId) {
        Session session = getCurrentSession();

        Query query = session.createQuery("delete SwFileAuthorityEntity au where au.enterpriseid = :companyId " +
                "and au.resourceid = :sourceId");
        query.setParameter("companyId", companyId);
        query.setParameter("sourceId", sourceId);
        query.executeUpdate();
        return true;
    }

    /**
     * 批量保存文件权限
     *
     * @param entities 文件权限实体
     * @return flag
     */
    @Transactional
    @Override
    public Boolean createFileAuList(List<SwFileAuthorityEntity> entities) {
        Session session = getCurrentSession();

        for (int i = 0; i < entities.size(); i++) {
            session.save(entities.get(i));
            if(i % 30 == 0){
                session.flush();
                session.clear();
            }
        }
        return true;
    }


    /**
     * 查询权限列表
     * @param companyId 企业ID
     * @param sourceId  资源ID
     * @param auRole    权限类型
     * @return 权限列表
     */
    @Override
    @Transactional
    public List<SwFileAuthorityEntity> querySourceGroupAuList(Long companyId, Long sourceId, Integer auRole) {
        Session session = getCurrentSession();

        Query query = session.createQuery("from  SwFileAuthorityEntity au where au.enterpriseid = :companyId " +
                "and au.resourceid = :sourceId " +
                "and au.authrole = :auRole");
        query.setParameter("companyId", companyId);
        query.setParameter("sourceId", sourceId);
        query.setParameter("auRole", auRole);

        List<SwFileAuthorityEntity> list = query.list();

        return list;
    }

    /**
     * 根据关键字模糊查询
     *
     * @param companyId 企业ID
     * @param keyWord   关键字
     * @param pageNum   页码
     * @param pageSize  记录数
     * @return 目录实体列表
     */
    @Override
    @Transactional
    public List<SwFileDirectoryEntity> queryDirectoryList(Long companyId, String keyWord, Integer pageNum, Integer pageSize) {
        Session session = getCurrentSession();

        Query query = session.createQuery("from SwFileDirectoryEntity dir where dir.dirname like :keyWord " +
                "and dir.enterpriseid = :companyId");
        query.setParameter("keyWord", keyWord);
        query.setParameter("companyId", companyId);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);

        List<SwFileDirectoryEntity> list = query.list();
        return list;
    }

    /**
     * 批量创建文件实体
     *
     * @param fileinfoList 文件信息列表
     * @return 标志位
     */
    @Transactional
    @Override
    public Boolean bachCreateFiles(List<SwFileinfoEntity> fileinfoList) {
        Session session = getCurrentSession();

        for (int i = 0; i < fileinfoList.size(); i++) {
            session.save(fileinfoList.get(i));
            if(i % 30 == 0){
                session.flush();
                session.clear();
            }
        }
        return true;
    }

    /**
     * 创建文件实体
     *
     * @param fileinfoEntity 文件实体
     * @return 主键
     */
    @Transactional
    @Override
    public Long createFile(SwFileinfoEntity fileinfoEntity) {
        Session session = getCurrentSession();
        session.persist(fileinfoEntity);
        fileinfoEntity.setCurrversionid(fileinfoEntity.getFileVersionEntity().getVersionid());
        session.merge(fileinfoEntity);
        return fileinfoEntity.getFileid();
    }

    /**
     * 创建文件版本实体
     *
     * @param fileVersionEntity 文件版本实体
     * @return 主键
     */
    @Transactional
    @Override
    public Long createFileVersion(SwFileVersionEntity fileVersionEntity) {
        Session session = getCurrentSession();
        session.save(fileVersionEntity);
        return fileVersionEntity.getVersionid();
    }

    /**
     * 查询文件信息实体
     *
     * @param id 文件主键
     * @return 文件实体
     */
    @Transactional
    @Override
    public SwFileinfoEntity queryFileInfo(Long id) {
        return findById(id);
    }

    /**
     * 更新文件表
     *
     * @param entity 文件实体
     * @return 标志
     */
    @Transactional
    @Override
    public Boolean updateFileInfo(SwFileinfoEntity entity) {
        update(entity);
        return true;
    }

    /**
     * 查询文件信息列表
     *
     * @param companyId 企业ID
     * @param keyWord   关键字
     * @param pageNum   页码
     * @param pageSize  行数
     * @return 文件列表
     */
    @Transactional
    @Override
    public List<SwFileinfoEntity> queryFileInfoList(Long companyId, String keyWord, Integer pageNum, Integer pageSize) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwFileinfoEntity file where file.filename like :keyWord " +
                "and file.enterpriseid = :companyId");
        query.setParameter("keyWord", keyWord);
        query.setParameter("companyId", companyId);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);

        List<SwFileinfoEntity> list = query.list();
        return list;
    }

    /**
     * 获取文件权限或目录权限
     *
     * @param companyId  企业ID
     * @param sourceId   资源ID
     * @param sourceType 资源类型
     * @return 权限列表
     */
    @Transactional
    @Override
    public List<SwFileAuthorityEntity> queryFileAuthorityList(Long companyId, Long sourceId, Integer sourceType) {
        List<SwFileAuthorityEntity> list = null;
        Session session = getCurrentSession();

        Query dirQuery = session.createQuery("from SwFileAuthorityEntity auth where auth.enterpriseid = :companyId " +
                "and auth.resourceid = :sourceId");
        Query fileQuery = session.createQuery("from SwFileAuthorityEntity auth where auth.enterpriseid = :companyId " +
                "and auth.fileResourceid = :sourceId");

        if (sourceType.equals(CodeBook.FILE_RESOUCE_TYPE_DIR)) {
            dirQuery.setParameter("companyId", companyId);
            dirQuery.setParameter("sourceId", sourceId);
            list = dirQuery.list();
        } else {
            fileQuery.setParameter("companyId", companyId);
            fileQuery.setParameter("sourceId", sourceId);
            list = fileQuery.list();
        }

        return list;
    }

    /**
     * 查询文件实体
     *
     * @param companyId 企业ID
     * @param fileId    文件ID
     * @return 文件实体
     */
    @Transactional
    @Override
    public FileModel getFileModel(Long companyId, Long fileId) {
        Session session = getCurrentSession();

        Query query = session.createQuery("select new com.sworker.model.FileModel(file.enterpriseid, " +
                "file.fileid, file.filename, " +
                "file.description, file.parentid," +
                "file.creator, file.createtime," +
                "file.editcount, file.viewcount," +
                "file.downloadcount," +
                "version.size, file.updator," +
                "file.updatetime, version.bucket," +
                "version.ossobjectid) from " +
                "SwFileinfoEntity as file " +
                "inner join file.fileVersionEntity as version " +
                "where file.enterpriseid = :companyId and file.fileid = :fileId");
        query.setParameter("companyId", companyId);
        query.setParameter("fileId", fileId);

        FileModel model = (FileModel)query.uniqueResult();

        return model;
    }

    /**
     * 获取文件版本实体
     *
     * @param versionId 版本ID
     * @return 文件版本实体
     */
    @Transactional
    @Override
    public SwFileVersionEntity getFileVersion(Long versionId) {
        Session session = getCurrentSession();
        SwFileVersionEntity entity = (SwFileVersionEntity)session.get(SwFileVersionEntity.class, versionId);
        return entity;
    }

    /**
     * 更新文件表状态
     *
     * @param fileId 文件主键
     * @param status 状态
     * @return flag
     */
    @Transactional
    @Override
    public Boolean updateFileStatus(Long fileId, Integer status) {
        Session session = getCurrentSession();
        SwFileinfoEntity fileinfoEntity = findById(fileId);
        fileinfoEntity.getFileVersionEntity().setStatus(status);
        session.merge(fileinfoEntity);
        return true;
    }

    /**
     * 更新文件浏览次数
     *
     * @param fileId 文件id
     * @return flag
     */
    @Transactional
    @Override
    public Boolean updateViewCount(Long fileId) {
        Session session = getCurrentSession();
        Integer returnflag = session.createQuery("update SwFileinfoEntity file set file.viewcount = file.viewcount + 1 " +
                "where file.fileid = :fileId")
                .setParameter("fileId", fileId)
                .executeUpdate();
        return (Boolean)ObjectUtil.convertValue(Boolean.class, returnflag);
    }

    /**
     * 更新下载次数
     *
     * @param fileId 文件id
     * @return flag
     */
    @Transactional
    @Override
    public Boolean updateDownloadCount(Long fileId) {
        Session session = getCurrentSession();
        Integer returnflag = session.createQuery("update SwFileinfoEntity file set file.downloadcount = file.downloadcount + 1 " +
                "where file.fileid = :fileId")
                .setParameter("fileId", fileId)
                .executeUpdate();
        return (Boolean)ObjectUtil.convertValue(Boolean.class, returnflag);
    }

    /**
     * 获取字文件列表
     *
     * @param baseDirectoryId 父目录id
     * @return 子文件列表
     */
    @Transactional
    @Override
    public List<SwFileinfoEntity> queryFileInfoList(Long baseDirectoryId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwFileinfoEntity file where " +
                "file.parentid = :baseDirectoryId");
        query.setParameter("baseDirectoryId", baseDirectoryId);

        return query.list();
    }

    /**
     * 获取路径列表
     *
     * @param baseDirectoryId 父目录id
     * @return 子文件列表
     */
    @Transactional
    @Override
    public List<SwFileDirectoryEntity> queryFileDirectoryList(Long baseDirectoryId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from  SwFileDirectoryEntity dir where " +
                "dir.parentid = :baseDirectoryId");
        query.setParameter("baseDirectoryId", baseDirectoryId);
        return query.list();
    }
}
