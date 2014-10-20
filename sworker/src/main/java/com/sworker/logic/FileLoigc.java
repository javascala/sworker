package com.sworker.logic;

import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.OSSObject;
import com.sworker.common.CodeBook;
import com.sworker.common.OssObject;
import com.sworker.common.util.DateUtil;
import com.sworker.common.util.MD5Util;
import com.sworker.common.util.ObjectUtil;
import com.sworker.common.util.StringUtil;
import com.sworker.dao.IFileDao;
import com.sworker.entity.SwFileAuthorityEntity;
import com.sworker.entity.SwFileDirectoryEntity;
import com.sworker.entity.SwFileVersionEntity;
import com.sworker.entity.SwFileinfoEntity;
import com.sworker.model.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

import com.aliyun.openservices.oss.model.ObjectMetadata;

/**
 * Created by zhangjin on 2014/8/28.
 */
@Component
public class FileLoigc implements IFileLogic {

    @Resource
    private IFileDao fileDao;

    /**
     * 获取目录实体
     *
     * @param param 企业ID 目录ID 群组ID 用户ID
     * @return 目录实体
     */
    @Transactional
    @Override
    public SwFileDirectoryEntity getCurrentDirectory(FileParam param) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //目录实体
        DirectoryModel model = new DirectoryModel();
        if (param != null) {
            if (param.getCompanyId() != null && param.getDirectoryId() != null
                    && param.getGroupId() != null && param.getUserId() != null) {
                //获取当前用户所在群组对当前目录的权限列表
                List<SwFileAuthorityEntity> groupAuList = fileDao.groupAuthorityList(param.getCompanyId(), param.getDirectoryId(), param.getGroupId());
                //获取当前用户对当前目录的权限列表
                List<SwFileAuthorityEntity> userAuList = fileDao.userAuthorityList(param.getCompanyId(), param.getDirectoryId(), param.getGroupId());

                //获得目录
                SwFileDirectoryEntity entity = fileDao.getDirectory(param.getDirectoryId());

                if(entity != null){
                    //父类给子类赋值
                    Class fatherClass = entity.getClass();
                    Field field[] = fatherClass.getDeclaredFields();
                    for (int i = 0; i < field.length; i++) {
                        Field f = field[i];
                        Method m = fatherClass.getMethod("get" + upperHeadChar(f.getName()), new Class[]{});
                        Object obj = m.invoke(entity);
                        f.setAccessible(true);
                        f.set(model, obj);
                    }

                    if(groupAuList != null && userAuList != null){
                        groupAuList.addAll(userAuList);
                        for(Iterator<SwFileAuthorityEntity> iter = groupAuList.iterator() ; iter.hasNext(); ){
                            SwFileAuthorityEntity authorityEntity = iter.next();
                            if(authorityEntity.getAuthtype().equals(0))
                                model.setCanManage(true);
                            if(authorityEntity.getAuthtype().equals(1))
                                model.setCanScan(true);
                        }
                    }
                }
                //TODO 获取后台角色权限
            }
        }
        return model;
    }

    /**
     * 获取子目录实体
     *
     * @param param 企业ID 父目录ID 群组ID 用户ID
     * @return 子目录实体
     */
    @Override
    public List<DirectoryModel> getSubDirectorys(FileParam param) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //目录实体
        List<DirectoryModel> modelList = new ArrayList<DirectoryModel>();
        if(param != null){
            if(param.getCompanyId() != null && param.getBaseDirectoryId() != null &&
                    param.getGroupId() != null && param.getUserId() != null){
                //获取当前用户所在群组对当前目录的权限列表
                List<SwFileAuthorityEntity> authorityEntityList = fileDao.querySubGroupAuList(param.getCompanyId(),
                        param.getBaseDirectoryId(), param.getGroupId());
                //获取当前用户对当前目录的权限列表
                List<SwFileAuthorityEntity> userAuEntityList = fileDao.querySubUserAuList(param.getCompanyId(), param.getBaseDirectoryId(),
                        param.getUserId());

                //查询子目录列表
                List<SwFileDirectoryEntity> directoryEntityList = fileDao.querySubFileDirectoryList(param.getBaseDirectoryId());

                if(ObjectUtil.isNotEmpty(directoryEntityList)){
                    for (SwFileDirectoryEntity entity : directoryEntityList) {
                        //父类给子类赋值
                        Class fatherClass = entity.getClass();
                        DirectoryModel model = new DirectoryModel();
                        Field field[] = fatherClass.getDeclaredFields();
                        for (int i = 0; i < field.length; i++) {
                            Field f = field[i];
                            Method method = fatherClass.getDeclaredMethod("get" + upperHeadChar(f.getName()), new Class[]{});
                            Object obj = method.invoke(entity);
                            f.setAccessible(true);
                            f.set(model, obj);
                        }
                        modelList.add(model);
                    }

                    //添加对应的目录权限
                    if (ObjectUtil.isNotEmpty(authorityEntityList) || ObjectUtil.isNotEmpty(userAuEntityList)) {
                        authorityEntityList.addAll(userAuEntityList);

                        for (SwFileAuthorityEntity authorityEntity : authorityEntityList) {
                            for (DirectoryModel model : modelList) {
                                if(authorityEntity.getResourceid() != null &&
                                        model.getDirectoryid() != null &&
                                        authorityEntity.getResourceid().equals(model.getDirectoryid())){
                                    if(authorityEntity.getAuthtype().equals(CodeBook.FILEAU_TYPE_CANVIEW)){
                                        model.setCanScan(true);
                                    }else {
                                        model.setCanScan(false);
                                    }
                                    if(authorityEntity.getAuthtype().equals(CodeBook.FILEAU_TYPE_MANAGE)){
                                        model.setCanManage(true);
                                    }else {
                                        model.setCanManage(false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return modelList;
    }

    /**
     * 判断当前目录是否为空
     *
     * @param baseDirectoryId 父目录id
     * @return boolean
     */
    @Transactional
    @Override
    public Boolean isDirectoryEmpty(Long baseDirectoryId) {
        if (ObjectUtil.isNotEmpty(baseDirectoryId)) {
            List<SwFileinfoEntity> fileList = fileDao.queryFileInfoList(baseDirectoryId);
            if (ObjectUtil.isNotEmpty(fileList)) {
                return false;
            }
            List<SwFileDirectoryEntity> dirList = fileDao.queryFileDirectoryList(baseDirectoryId);
            if (ObjectUtil.isNotEmpty(dirList)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 删除目录
     *
     * @param companyId       公司id
     * @param baseDirectoryId 父目录id
     * @return boolean
     */
    @Override
    public Boolean deleteDirectory(BigInteger companyId, BigInteger baseDirectoryId) {
        if(ObjectUtil.isNotEmpty(companyId) && ObjectUtil.isNotEmpty(baseDirectoryId)){
            //TODO  删除目录， 删除目录下的子目录，删除目录下所以的文件
            //删除目录对应的权限

        }
        return null;
    }

    /**
     * 删除目录或文件权限
     *
     * @param companyId 公司id
     * @param sourceId  资源id
     * @return boolean
     */
    @Override
    public Boolean deleteObjectAuth(Long companyId, Long sourceId) {
        if(ObjectUtil.isNotEmpty(companyId) && ObjectUtil.isNotEmpty(sourceId)){
            return fileDao.deleteObjectAuth(companyId, sourceId);
        }
        return false;
    }

    /**
     * 判断父目录下有无重名目录
     *
     * @param param 企业ID 父目录ID 当前目录名称
     * @return boolean
     */
    @Transactional
    @Override
    public Boolean isDirectoryRepeat(FileParam param) {

        if (param != null) {
            if (param.getCompanyId() != null && param.getBaseDirectoryId() != null
                    && param.getFileName() != null) {
                List<SwFileDirectoryEntity> entityList = fileDao.queryDirectoryList(param.getCompanyId(), param.getBaseDirectoryId(), param.getFileName());
                if (entityList != null && entityList.size() > 0)
                    return true;
            }
        }

        return false;
    }

    /**
     * 重命名目录
     *
     * @param param 企业ID 目录ID  目录名称  更新人
     * @return boolean
     */
    @Transactional
    @Override
    public Boolean updateDirectory(FileParam param) {
        if(param != null){
            if(param.getCompanyId() != null && param.getBaseDirectoryId() !=null
                    && param.getUserId() != null){
                SwFileDirectoryEntity entity = new SwFileDirectoryEntity();
                entity.setEnterpriseid(param.getCompanyId());
                entity.setDirectoryid(param.getDirectoryId());
                entity.setDirname(param.getDirectoryName());
                entity.setUpdator(param.getUpdateUserId());
                entity.setUpdatetime(new Timestamp(System.currentTimeMillis()));
                fileDao.updateDirectory(entity);
                return true;
            }
        }

        return false;
    }

    /**
     * 根据关键字模糊查询返回目录列表
     *
     * @param param 企业ID 关键字 页码 记录数
     * @return 返回目录实体列表
     */
    @Transactional
    @Override
    public List<SwFileDirectoryEntity> findDirectoryByWord(FileParam param) {
        List<SwFileDirectoryEntity> list = new ArrayList<SwFileDirectoryEntity>();
        if (ObjectUtil.isNotEmpty(param)) {
            if (ObjectUtil.isNotEmpty(param.getCompanyId()) && ObjectUtil.isNotEmpty(param.getKeyWord())
                    && ObjectUtil.isNotEmpty(param.getPageNum()) && ObjectUtil.isNotEmpty(param.getCountNum())) {

                list = fileDao.queryDirectoryList(param.getCompanyId(), param.getKeyWord(), param.getPageNum(),
                        param.getCountNum());
            }
        }
        return list;
    }

    /**
     * 创建目录
     *
     * @param param 企业ID 群组ID 父目录ID 目录名称 是否继承上层文件夹
     *              创建人
     * @return
     */
    @Transactional
    @Override
    public Boolean createDirectory(FileParam param) {
        if(param != null){
            if(param.getCompanyId() != null && param.getFileName() != null
                    && !param.getFileName().equals("") && param.getBaseDirectoryId() != null){
                SwFileDirectoryEntity entity = new SwFileDirectoryEntity();
                entity.setEnterpriseid(param.getCompanyId());
                entity.setGroupid(param.getGroupId());
                entity.setParentid(param.getBaseDirectoryId());
                entity.setDirname(param.getDirectoryName());
                entity.setIsinherit(param.getIsExtends());
                entity.setCreator(param.getCreateUserId());
                entity.setCreatetime(new Timestamp(System.currentTimeMillis()));

                fileDao.createDirectory(entity);
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * 创建目录或文件权限
     *
     * @param param 企业ID 父资源ID 资源ID 是否继承上层文件夹
     *              管理权限用户列表 可见范围用户列表
     * @return
     */
    @Transactional
    @Override
    public Boolean createObjectAuth(FileParam param) {
        if(ObjectUtil.isNotEmpty(param)){
            if(ObjectUtil.isNotEmpty(param.getCompanyId()) && ObjectUtil.isNotEmpty(param.getBaseSourceId())
                    && ObjectUtil.isNotEmpty(param.getSourceId()) && ObjectUtil.isNotEmpty(param.getSourceType())){
                //父资源对应的群组权限列表
                List<SwFileAuthorityEntity> baseGroupAuList = null;
                //父资源第一的用户权限列表
                List<SwFileAuthorityEntity> baseUserAuList = null;


                //如果继承，检索上层目录的群组权限列表
                if (param.getIsExtends().equals(CodeBook.FILEAU_EXTENDS)) {
                    //资源对应群组权限列表
                    List<SwFileAuthorityEntity> groupAuList = new ArrayList<SwFileAuthorityEntity>();
                    //资源对应的用户权限列表
                    List<SwFileAuthorityEntity> userAuList = new ArrayList<SwFileAuthorityEntity>();
                    baseGroupAuList = fileDao.querySourceGroupAuList(param.getCompanyId(), param.getBaseSourceId(), CodeBook.FILEAU_ROLE_GROUP);
                    baseUserAuList = fileDao.querySourceGroupAuList(param.getCompanyId(), param.getBaseSourceId(), CodeBook.FILEAU_ROLE_USER);
                    for (SwFileAuthorityEntity authorityEntity : baseGroupAuList) {
                        SwFileAuthorityEntity entity = new SwFileAuthorityEntity();
                        entity.setEnterpriseid(authorityEntity.getEnterpriseid());
                        entity.setAuthrole(authorityEntity.getAuthrole());
                        entity.setAuthtype(authorityEntity.getAuthtype());
                        entity.setGrouproleid(authorityEntity.getGrouproleid());
                        if (param.getSourceType().equals(CodeBook.FILE_RESOUCE_TYPE_DIR)) {
                            entity.setResourceid(param.getSourceId());
                        } else {
                            entity.setFileResourceid(param.getSourceId());
                        }
                        groupAuList.add(entity);
                    }
                    for (SwFileAuthorityEntity entity : baseUserAuList) {
                        SwFileAuthorityEntity userEntity = new SwFileAuthorityEntity();
                        userEntity.setEnterpriseid(entity.getEnterpriseid());
                        userEntity.setAuthrole(entity.getAuthrole());
                        userEntity.setAuthtype(entity.getAuthtype());
                        userEntity.setUserroleid(entity.getUserroleid());
                        if (param.getSourceType().equals(CodeBook.FILE_RESOUCE_TYPE_DIR)) {
                            entity.setResourceid(param.getSourceId());
                        } else {
                            entity.setFileResourceid(param.getSourceId());
                        }
                        userAuList.add(userEntity);
                    }
                    groupAuList.addAll(userAuList);
                    fileDao.createFileAuList(groupAuList);
                }

                //保存管理权限用户列表权限
                if (ObjectUtil.isNotEmpty(param.getAuthorityUserList())) {
                    List<SwFileAuthorityEntity> userAutList = new ArrayList<SwFileAuthorityEntity>();
                    for (Integer userId : param.getAuthorityUserList()) {
                        SwFileAuthorityEntity userEntity = new SwFileAuthorityEntity();
                        userEntity.setEnterpriseid(param.getCompanyId());
                        userEntity.setAuthtype(CodeBook.FILEAU_TYPE_MANAGE);
                        userEntity.setAuthrole(CodeBook.FILEAU_ROLE_USER);
                        userEntity.setUserroleid(Long.valueOf(userId));
                        if (param.getSourceType().equals(CodeBook.FILE_RESOUCE_TYPE_DIR)) {
                            userEntity.setResourceid(param.getSourceId());
                        } else {
                            userEntity.setFileResourceid(param.getSourceId());
                        }
                        userAutList.add(userEntity);
                    }
                    fileDao.createFileAuList(userAutList);
                }

                //保存可见范围用户列表权限
                if (ObjectUtil.isNotEmpty(param.getVisiableUserList())) {
                    List<SwFileAuthorityEntity> userAuList = new ArrayList<SwFileAuthorityEntity>();
                    for (Integer userId : param.getVisiableUserList()) {
                        SwFileAuthorityEntity userEntity = new SwFileAuthorityEntity();
                        userEntity.setEnterpriseid(param.getCompanyId());
                        userEntity.setAuthtype(CodeBook.FILEAU_TYPE_CANVIEW);
                        userEntity.setAuthrole(CodeBook.FILEAU_ROLE_USER);
                        userEntity.setUserroleid(Long.valueOf(userId));
                        if (param.getSourceType().equals(CodeBook.FILE_RESOUCE_TYPE_DIR)) {
                            userEntity.setResourceid(param.getSourceId());
                        } else {
                            userEntity.setFileResourceid(param.getSourceId());
                        }
                        userAuList.add(userEntity);
                    }
                    fileDao.createFileAuList(userAuList);
                }

            }
            //TODO 群组权限列表是否要作为参数传入
        }
        return true;
    }

    /**
     * 根据关键字模糊查询返回文件列表
     *
     * @param param 企业ID 关键字  页码 记录数
     * @return 文件实体列表
     */
    @Transactional
    @Override
    public List<SwFileinfoEntity> findFileByWord(FileParam param) {
        List<SwFileinfoEntity> fileinfoEntityList = null;
        if (ObjectUtil.isNotEmpty(param) && ObjectUtil.isNotEmpty(param.getKeyWord())
                && ObjectUtil.isNotEmpty(param.getPageNum()) && ObjectUtil.isNotEmpty(param.getCountNum())) {
            fileinfoEntityList = fileDao.queryFileInfoList(param.getCompanyId(), param.getKeyWord(), param.getPageNum(), param.getCountNum());
        }
        return fileinfoEntityList;
    }

    /**
     * 判断父目录下有无重名文件
     *
     * @param companyId       企业ID
     * @param baseDirectoryId 父目录ID
     * @param fileName        当前文件名称
     * @return boolean
     */
    @Override
    public Boolean isFileRepeat(BigInteger companyId, BigInteger baseDirectoryId, String fileName) {
        return null;
    }

    /**
     * 修改文件名
     *
     * @param param 企业ID 文件ID 文件名称 更新人
     * @return 标志位
     */
    @Transactional
    @Override
    public Boolean updateFileName(FileParam param) {
        Boolean flag = false;
        //若修改文件，那么在云端的key是没有改的
        if (ObjectUtil.isNotEmpty(param) && ObjectUtil.isNotEmpty(param.getFileId())
                && ObjectUtil.isNotEmpty(param.getFileName()) && ObjectUtil.isNotEmpty(param.getUpdateUserId())) {
            SwFileinfoEntity fileinfoEntity = fileDao.queryFileInfo(param.getFileId());
            if (ObjectUtil.isNotEmpty(fileinfoEntity)) {
                fileinfoEntity.setFilename(param.getFileName());
                fileinfoEntity.setUpdator(param.getUpdateUserId());
                fileinfoEntity.setUpdatetime(new Timestamp(System.currentTimeMillis()));
                fileinfoEntity.setEditcount(fileinfoEntity.getEditcount() + 1);
                flag = fileDao.updateFileInfo(fileinfoEntity);
            }
        }
        return flag;
    }

    /**
     * 上传文件
     *
     * @param param 上传文件参数
     * @return 文件实体列表
     */
    @Transactional
    @Override
    public List<SwFileinfoEntity> putFile(FileUploadParam param) {
        List<SwFileinfoEntity> fileInfoList = new ArrayList<SwFileinfoEntity>();
        if (ObjectUtil.isNotEmpty(param.getCompanyId()) && ObjectUtil.isNotEmpty(param.getFileList())) {
            for (File file : param.getFileList()) {
                SwFileinfoEntity entity = new SwFileinfoEntity();
                String fileName = file.getName();
                InputStream fileInput = null;

                //阿里云上次文件
                //bucketName
                String bucketName = param.getBucket();

                ClientConfiguration config = new ClientConfiguration();

                OSSClient client = new OSSClient(OssObject.OSS_ENDPOINT, OssObject.ACCESS_ID,
                        OssObject.ACCESS_KEY, config);

                try {
                    fileInput = new FileInputStream(file.getPath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                ObjectMetadata objectMeta = new ObjectMetadata();
                objectMeta.setContentLength(file.length());

                String key = MD5Util.makeMD5(fileName);

                //保证bucket存在
                OssObject.ensureBucket(client, bucketName);

                //上传文件
                client.putObject(bucketName, key, fileInput, objectMeta);

                if (StringUtil.isEndsWithIgnoreCase(fileName, ".doc") ||
                        StringUtil.isEndsWithIgnoreCase(fileName, ".xls") ||
                        StringUtil.isEndsWithIgnoreCase(fileName, ".ppt") ||
                        StringUtil.isEndsWithIgnoreCase(fileName, ".docx")) {
                    entity.setFiletype(CodeBook.FILE_TYPE_DOC);
                } else if (StringUtil.isEndsWithIgnoreCase(fileName, ".png") ||
                        StringUtil.isEndsWithIgnoreCase(fileName, ".jpeg")) {
                    entity.setFiletype(CodeBook.FILE_TYPE_PIC);
                } else if (StringUtil.isEndsWithIgnoreCase(fileName, ".mp3") ||
                        StringUtil.isEndsWithIgnoreCase(fileName, ".avi")) {
                    entity.setFiletype(CodeBook.FILE_TYPE_MEDIA);
                }

                entity.setFilename(fileName);
                entity.setParentid(param.getBaseDirectoryId());
                entity.setIsinherit(param.getIsExtendsUpFile());
                entity.setCreator(param.getCreatorId());
                entity.setCreatetime(new Timestamp(System.currentTimeMillis()));
                entity.setEnterpriseid(param.getCompanyId());


                //保存文件版本
                SwFileVersionEntity versionEntity = new SwFileVersionEntity();
                versionEntity.setVersionname(fileName + DateUtil.getStringByDate(new Date()));
                versionEntity.setEnterpriseid(param.getCompanyId());
                versionEntity.setSize(file.length());
                versionEntity.setBucket(bucketName);
                versionEntity.setOssobjectid(key);
                versionEntity.setUpdator(param.getCreatorId());
                versionEntity.setUpdatetime(new Timestamp(System.currentTimeMillis()));

                entity.setFileVersionEntity(versionEntity);
                Long fileId = fileDao.createFile(entity);

                //创建文件权限
                FileParam fileParam = new FileParam();
                fileParam.setCompanyId(param.getCompanyId());
                fileParam.setBaseSourceId(param.getBaseDirectoryId());
                fileParam.setSourceType(CodeBook.FILE_RESOURCE_TYPE_FILE);
                fileParam.setSourceId(fileId);
                fileParam.setIsExtends(param.getIsExtendsUpFile());
                fileParam.setVisiableUserList(param.getVisiableUserList());
                fileParam.setAuthorityUserList(param.getAuthorityUserList());
                createObjectAuth(fileParam);

                fileInfoList.add(entity);
            }

        }
        return fileInfoList;
    }

    /**
     * 获取文件权限或目录权限
     *
     * @param companyId  企业ID
     * @param sourceId   资源ID
     * @param sourceType 资源类型
     * @return 权限键值对象
     */
    @Transactional
    @Override
    public Map<String, List> findObjectAuth(Long companyId, Long sourceId, Integer sourceType) {
        List<SwFileAuthorityEntity> manageList = new ArrayList<SwFileAuthorityEntity>();
        List<SwFileAuthorityEntity> scanList = new ArrayList<SwFileAuthorityEntity>();
        Map<String, List> map = new HashMap<String, List>();

        if (ObjectUtil.isNotEmpty(companyId) && ObjectUtil.isNotEmpty(sourceId) && ObjectUtil.isNotEmpty(sourceType)) {
            List<SwFileAuthorityEntity> auList = fileDao.queryFileAuthorityList(companyId, sourceId, sourceType);
            if (ObjectUtil.isNotEmpty(auList)) {
                for (SwFileAuthorityEntity authorityEntity : auList) {
                        if (authorityEntity.getAuthtype().equals(CodeBook.FILEAU_TYPE_CANVIEW)) {
                            scanList.add(authorityEntity);
                        } else {
                            manageList.add(authorityEntity);
                        }
                }
            }
        }

        map.put("manageList", manageList);
        map.put("scanList", scanList);

        return map;
    }

    /**
     * 上传文件新版本
     *
     * @param param 企业ID 文件ID bucket 更新说明 文件对象 更新人
     * @return 标志位
     */
    @Override
    public Boolean putFileVersion(FileParam param) {
        return null;
    }

    /**
     * 获取文件信息
     *
     * @param companyId 企业ID
     * @param fileId    文件ID
     * @return
     */
    @Transactional
    @Override
    public FileModel findFileInfo(Long companyId, Long fileId) {
        FileModel fileModel = new FileModel();
        if (ObjectUtil.isNotEmpty(companyId) && ObjectUtil.isNotEmpty(fileId)) {
            fileModel = fileDao.getFileModel(companyId, fileId);
        }
        return fileModel;
    }

    /**
     * 获取OSS文件对象
     *
     * @param bucketName OSSBucket
     * @param key        OSS对象key
     * @return 文件流
     */
    @Override
    public InputStream getFileObject(String bucketName, String key) {
        ClientConfiguration config = new ClientConfiguration();

        OSSClient client = new OSSClient(OssObject.OSS_ENDPOINT, OssObject.ACCESS_ID,
                OssObject.ACCESS_KEY, config);

        OSSObject ossObject = client.getObject(bucketName, key);
        return ossObject.getObjectContent();
    }

    /**
     * 获取文件历史版本
     *
     * @param versionId 当前版本ID
     * @return 文件版本实体
     */
    @Transactional
    @Override
    public SwFileVersionEntity getHistoryFileList(Long versionId) {
        SwFileVersionEntity entity = new SwFileVersionEntity();
        if (ObjectUtil.isNotEmpty(versionId)) {
            entity = fileDao.getFileVersion(versionId);
        }
        return entity;
    }

    /**
     * 修改文件描述
     *
     * @param fileId    文件ID
     * @param fileDes   文件描述
     * @return 目录实体列表
     */
    @Transactional
    @Override
    public Boolean updateFileDesc(Long fileId, String fileDes, Long updator) {
        Boolean flag = false;
        if (ObjectUtil.isNotEmpty(fileId) && ObjectUtil.isNotEmpty(fileDes)) {
            SwFileinfoEntity entity = fileDao.queryFileInfo(fileId);
            entity.setDescription(fileDes);
            entity.setUpdator(updator);
            entity.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            entity.setEditcount(entity.getEditcount() + 1);
            flag = fileDao.updateFileInfo(entity);
        }
        return flag;
    }

    /**
     * 修改文件状态
     *
     * @param param 文件ID 状态
     * @return 标志
     */
    @Transactional
    @Override
    public Boolean updateFileState(FileParam param) {
        Boolean flag = false;
        if (ObjectUtil.isNotEmpty(param) && ObjectUtil.isNotEmpty(param.getFileId()) && ObjectUtil.isNotEmpty(param.getStatus())) {
            flag = fileDao.updateFileStatus(param.getFileId(), param.getStatus());
        }
        return flag;
    }

    /**
     * 更新浏览次数
     *
     * @param fileId    文件ID
     * @return boolean
     */
    @Transactional
    @Override
    public Boolean updateViewCount(Long fileId) {
        Boolean flag = false;
        if (ObjectUtil.isNotEmpty(fileId)) {
            flag = fileDao.updateViewCount(fileId);
        }
        return flag;
    }

    /**
     * 更新下载次数
     *
     * @param fileId    文件ID
     * @return 标志位
     */
    @Transactional
    @Override
    public Boolean updateDownloadCount(Long fileId) {
        Boolean flag = false;
        if (ObjectUtil.isNotEmpty(fileId)) {
            flag = fileDao.updateDownloadCount(fileId);
        }
        return flag;
    }

    /**
     * 初始化群组文件空间
     *
     * @param groupId   文件ID
     * @param companyId 企业ID
     * @return 标志位
     */
    @Transactional
    @Override
    public Boolean initGroupFileSpace(Long groupId, Long companyId) {
        Boolean flag = false;
        if (ObjectUtil.isNotEmpty(groupId) && ObjectUtil.isNotEmpty(companyId)) {
            FileParam param = new FileParam();
            param.setGroupId(groupId);
            param.setCompanyId(companyId);
            param.setBaseDirectoryId(0l);
            param.setIsExtends(CodeBook.FILEAU_NOTEXTENDS);
            param.setFileName("根目录");
            flag = createDirectory(param);
        }
        return flag;
    }

    /**
     * 初始化用户文件空间
     *
     * @param userId   文件ID
     * @param companyId 企业ID
     * @return 标志位
     */
    @Transactional
    @Override
    public Boolean initUserFileSpace(Long userId, Long companyId) {
        Boolean flag = false;
        if (ObjectUtil.isNotEmpty(userId) && ObjectUtil.isNotEmpty(companyId)) {
            FileParam param = new FileParam();
            param.setUserId(userId);
            param.setCompanyId(companyId);
            param.setBaseDirectoryId(0l);
            param.setIsExtends(CodeBook.FILEAU_NOTEXTENDS);
            param.setFileName("用户根目录");
            flag = createDirectory(param);
        }
        return flag;
    }

    /**
     * 删除阿里云上的文件
     *
     * @param bucket bucket
     * @param key    文件key
     * @return 标志位
     */
    @Override
    public Boolean deleteOssFile(String bucket, String key) {
        Boolean flag = false;
        if (ObjectUtil.isNotEmpty(bucket) && ObjectUtil.isNotEmpty(key)) {
            ClientConfiguration config = new ClientConfiguration();

            OSSClient client = new OSSClient(OssObject.OSS_ENDPOINT, OssObject.ACCESS_ID,
                    OssObject.ACCESS_KEY, config);
            client.deleteObject(bucket, key);
            flag = true;
        }

        return flag;
    }

    /**
     * 首字母大写
     *
     * @param in 输入字符串
     * @return 输出字符串
     */
    private String upperHeadChar(String in) {
        String head = in.substring(0, 1);
        String out = head.toUpperCase() + in.substring(1, in.length());
        return out;
    }


}
