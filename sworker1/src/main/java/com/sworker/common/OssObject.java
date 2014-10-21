package com.sworker.common;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;

/**
 * Created by zhangjin on 2014/9/11.
 */
public class OssObject {

    public static final String ACCESS_ID = "1FCtOwxD7V8qlzrc";

    public static final String ACCESS_KEY = "gvtrdkWluqecdyiBALY6NtHmdiNOwQ";

    public static final String OSS_ENDPOINT = "http://oss.aliyuncs.com/";

    public static void ensureBucket(OSSClient client, String bucketName)
            throws OSSException, ClientException {

        if (client.isBucketExist(bucketName)){
            return;
        }

        //创建bucket
        client.createBucket(bucketName);
    }
}
