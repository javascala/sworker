package com.sworker.logic;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sworker.entity.SwMicroblogsEntity;

/**
 * Created by jiangcy on 2014/8/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class MicroBlogsTest {
	
	@Resource
	private IMicroBlogsLogic blogsLogic;
	
	/**
	 * 插入微博
	 */
	@Test
	public void createTest() {
		SwMicroblogsEntity blogEntity = new SwMicroblogsEntity();
		blogEntity.setAuthor("dds");
		blogEntity.setBody("2222高兴，happ,123!");
		blogEntity.setUserid(2L);
		Date date = new Date();
		DateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		blogEntity.setDatecreated(Timestamp.valueOf(formats.format(date)));
		blogsLogic.createMicroBlog(blogEntity);
	}
	
	/**
	 * 通过用户Id列表，获取微博列表
	 */
	@Test
	public void getByListTest() {
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(3L);
		userIds.add(1L);
		List<SwMicroblogsEntity> blogs = blogsLogic.getMicroBlogs(userIds);
		Assert.assertEquals(5, blogs.size());
		Iterator<SwMicroblogsEntity> blogsIterator = blogs.iterator();
		//将日期打印看是否是按降序排列
		while (blogsIterator.hasNext()){
			System.out.println(blogsIterator.next().getDatecreated());
		}
	}
	
	/**
	 * 通过用户id，获取微博列表
	 */
	@Test
	public void getByUserIdTest() {
		List<SwMicroblogsEntity> blogs = blogsLogic.getMicroBlogs(3L);
		Assert.assertEquals(3, blogs.size());
		Iterator<SwMicroblogsEntity> blogsIterator = blogs.iterator();
		//将日期打印看是否是按降序排列
		while (blogsIterator.hasNext()){
			System.out.println(blogsIterator.next().getDatecreated());
		}
	}
	
	/**
	 * 删除微博
	 */
	@Test
	public void deleteTest() {
		SwMicroblogsEntity blogEntity = blogsLogic.getMicroBlogs(8L).get(0);
		blogsLogic.deleteMicroBlog(blogEntity);
	}
	
	/**
	 * 转发源微博
	 */
	@Test
	public void testForward(){
		//插入新微薄实体
		SwMicroblogsEntity newBlog = new SwMicroblogsEntity();
		newBlog.setAuthor("jcy");
		newBlog.setBody("开心，happy，123！");
		newBlog.setUserid(9L);
		Date date = new Date();
		DateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		newBlog.setDatecreated(Timestamp.valueOf(formats.format(date)));
		//获取被转发微博的实体,此测试微博originalMicroblogId为空
		SwMicroblogsEntity srcBlog = blogsLogic.getMicroBlogs(6L).get(0);
		//转发微博
		blogsLogic.forwordMicroBlog(srcBlog, newBlog);
	}
	
	/**
	 * 转发非源微博
	 */
	@Test
	public void testForward2(){
		//插入新微博实体
		SwMicroblogsEntity newBlog = new SwMicroblogsEntity();
		newBlog.setAuthor("tt");
		newBlog.setBody("hehe！");
		newBlog.setUserid(8L);
		Date date = new Date();
		DateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		newBlog.setDatecreated(Timestamp.valueOf(formats.format(date)));
		//获取被转发微博的实体,此测试微博originalMicroblogId不为空
		SwMicroblogsEntity srcBlog = blogsLogic.getMicroBlogs(8L).get(0);
		//转发微博
		blogsLogic.forwordMicroBlog(srcBlog, newBlog);
	}
	
	/**
	 * 删除3个回复数且微博回复数大于3
	 */
	@Test
	public void deleteReplyTest(){
		SwMicroblogsEntity blog = blogsLogic.getMicroBlogs(1L).get(0);
		Long count = blog.getReplycount() - 3;
		blogsLogic.decreaseMicroBlogReplyCount(blog, 3);
		//获取删除回复后的微博
		SwMicroblogsEntity newBlog = blogsLogic.getMicroBlogs(1L).get(0);
		//断言删除前回复数减3和删除后的回复数相等
		Assert.assertEquals(count, newBlog.getReplycount());
	}
	
	/**
	 * 删除3个回复数且微博回复数小于3
	 */
	@Test
	public void deleteReplyTest2(){
		SwMicroblogsEntity blog = blogsLogic.getMicroBlogs(2L).get(0);
		blogsLogic.decreaseMicroBlogReplyCount(blog, 3);
		//获取删除回复后的微博
		SwMicroblogsEntity newBlog = blogsLogic.getMicroBlogs(2L).get(0);
		//断言删除后回复数未0
		Assert.assertEquals(new Long(0), newBlog.getReplycount());
	}
}