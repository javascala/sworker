package com.sworker.common.util;

import java.security.MessageDigest;

/**
 * Created by zhangjin on 2014/9/11.
 */
public class MD5Util {
    /**
     * MD5加密
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static  String makeMD5(String str){

        MessageDigest md;

        try{
            md=MessageDigest.getInstance("MD5");

            md.update(str.getBytes());

            //String name=new Long(1,md.digest()).toString(16);

            String name=convertToHexString(md.digest());
            return name;

        }catch(Exception ex){
            ex.printStackTrace();

        }

        return null;

    }




    /**
     * 转换成16进制字符串
     * @param data 要转换的字节数组
     * @return
     */
    public static String convertToHexString(byte[] data) {
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            strBuffer.append(Integer.toHexString(0xff & data[i]));
        }
        return strBuffer.toString();
    }
}
