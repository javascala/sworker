package com.sworker.common;

/**
 * Created by zhangjin on 2014/9/9.
 */
public class CodeBook {
    /**
     * 文件权限类型
     */
    public static final Integer FILEAU_TYPE_CANVIEW = 1;

    /**
     * 文件权限类型
     */
    public static final Integer FILEAU_TYPE_MANAGE = 0;

    /**
     * 文件是否集成权限
     */
    public static final Integer FILEAU_EXTENDS = 1;

    public static final Integer FILEAU_NOTEXTENDS = 0;


    /**
     * 文件权限角色：用户
     */
    public static final Integer FILEAU_ROLE_USER = 0;

    /**
     * 文件权限角色：群组
     */
    public static final Integer FILEAU_ROLE_GROUP = 1;

    /**
     * 文件权限角色：后台
     */
    public static final Integer FILEAU_ROLE_BACK = 2;


    /**
     * 文件类型: 文档类
     */
    public static final Integer FILE_TYPE_DOC = 0;

    /**
     * 文件类型: 图片类
     */
    public static final Integer FILE_TYPE_PIC = 1;

    /**
     * 文件类型: 视频类
     */
    public static final Integer FILE_TYPE_MEDIA = 2;

    /**
     * 资源类型: 文件
     */
    public static final Integer FILE_RESOURCE_TYPE_FILE = 0;

    /**
     * 资源类型: 路径
     */
    public static final Integer FILE_RESOUCE_TYPE_DIR = 1;
    
    /**
     * 积分统计 类型
     */
    public static final Integer PONINT_RECORDS_TYPE_AWARD=1;
    
    public static final Integer PONINT_RECORDS_TYPE_MODIFY=2;
    
    public static final Integer PONINT_RECORDS_TYPE_COUSUME=3;
    
    public static final Integer PONINT_RECORDS_TYPE_EXPIRED=4;
    
}
