package com.sworker.search;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;

import com.sworker.elasticsearch.base.IndexJsonBase;
import com.sworker.entity.SwMicroblogsEntity;


public class MicroblogIndexJson extends IndexJsonBase<SwMicroblogsEntity>{

	public static final String MICROBLOGID = "microblogid";
	public static final String AUTHOR = "author";
	public static final String BODY = "body";
	public static final String DATECREATED = "datecreated";
	
	/**
	 * 
	 */
    protected void prepareIndex(SwMicroblogsEntity baseClass, XContentBuilder jsonBuild) throws IOException {
        
        SwMicroblogsEntity microblog = (SwMicroblogsEntity)baseClass;
        
        jsonBuild.field(MICROBLOGID, microblog.getMicroblogid());
        jsonBuild.field(AUTHOR, microblog.getAuthor());
        jsonBuild.field(BODY, microblog.getBody());
        jsonBuild.field(DATECREATED, microblog.getDatecreated());

    }

}
