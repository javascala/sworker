package com.sworker.logic;

import java.io.File;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.enumutil.AttachmentType;
import com.sworker.dao.IAttachmentDao;
import com.sworker.entity.SwAttachmentEntity;

/**
 * 
 * @author wanggang 2014/8/28.
 *
 */
@Component("attachmentlogic")
public class AttachmentLogic implements IAttachmentLogic {
	
	@Resource
	private IAttachmentDao attachmentdao;
	
	/**
	 * 创建附件
	 * @param attachment 附件实体
	 */
	@Override
	@Transactional
	public void createAttachment(SwAttachmentEntity attachment) {
		if(null != attachment){
			
			//附件属性非空验证
			if(null !=attachment.getName() && null !=attachment.getSuffix()
				&& null != attachment.getType() && null != attachment.getFolder()
				&& null != attachment.getBusinessid()){
				
				//Md5加密
				String name=makeMD5(attachment.getName()+attachment.getSuffix());
				//生成文件路径
				StringBuilder sb=new StringBuilder("file.sens.com");
				sb.append(File.separator+"sens123456"+File.separator);
				
				//判断附件类型为图片
				if(attachment.getType().equals(AttachmentType.img.getValue())){
				 	sb.append("img"+File.separator+this.time()+File.separator+name+attachment.getSuffix());
				 	
				 	attachment.setFolder(sb.toString());
					
				 	
				}else if(attachment.getType().equals(AttachmentType.doc.getValue())){
					
					//判断附件类型为文档
					sb.append("doc"+File.separator+this.time()+File.separator+name+attachment.getSuffix());
					
					attachment.setFolder(sb.toString());
			
					
				}else if(attachment.getType().equals(AttachmentType.sound.getValue())){
					
					//判断附件类型为音频
					sb.append("sound"+File.separator+this.time()+File.separator+name+attachment.getSuffix());
					
					attachment.setFolder(sb.toString());
					
				}else if(attachment.getType().equals(AttachmentType.video.getValue())){
					//判断附件类型为视频
					sb.append("video"+File.separator+this.time()+File.separator+name+attachment.getSuffix());
					
					attachment.setFolder(sb.toString());
					
				}
				
				
				//TODO
				//调用文件服务的putFile方法
				
				//参数无法获取
				
				//上传成功则
				attachmentdao.saveAttachment(attachment);
			}
			
		}
	}

	
	
	/**
	 * 根据附件ID,删除附件
	 * @param id 附件ID
	 */
	@Override
	@Transactional
	public void deleteAttachmentByID(Long id){
		
		if(null!=id){
			
			//TODO something 
			
			//调用文件服务delFile方法
			//.....
			
			attachmentdao.deleteAttachmentById(id);
			
		}
		
	}
	
	
	/**
	 * 根据业务ID，删除附件
	 * @param businessid 业务ID
	 */
	@Override
	@Transactional
	public void deleteAttachmentByBizID(Long businessid){
		
		if(null!=businessid){
			
			//TODO 文件操作
			
			//调用文件服务delFile方法
			//.......
			
			attachmentdao.deleteAttachmentByBizID(businessid);
			
		}
	}


	/**
	 * 获取用户附件列表
	 * @param userid
	 * @return 如果有检索结果，返回附件实体列表。无检索结果，则返回NULL
	 */
	@Override
	@Transactional
	public List<SwAttachmentEntity> getAttachmentListByUserID(Long userid) {

		if(null!=userid){
			
		return attachmentdao.findAttachmentsByUserId(userid);
		
		}
		return null;
	}


	
	
	/**
	 * 根据业务ID，获取附件列表
	 * @param businessid
	 * @return 如果有检索结果，返回附件实体列表。无检索结果，则返回NULL
	 */
	@Override
	@Transactional
	public List<SwAttachmentEntity> getAttachmentListByBizID(Long businessid) {
		 if(null!=businessid){
			 
			return attachmentdao.findAttachmentsByBizID(businessid);
			 
		 }
		return null;
	}

	
	/**
	 * 获取附件详细信息
	 * @param id 附件ID
	 * @return 如果有检索结果，返回附件实体。无检索结果，则返回NULL
	 */
	@Override
	@Transactional
	public SwAttachmentEntity getAttachmentDetail(Long id) {
		
		if(null!=id)  {
			
			
			//TODO
			
			
		}
		return null;
	}
	
	
	
	
	
	
	
	/**
	 * 获取当前日期
	 * @return 当前日期字符串形式
	 */
   private  String time()
		{
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyyMMdd");
			String time=format.format(date);
			return time;
		}
	
		
	/**
	 * MD5加密
	 * @param str 要加密的字符串
	 * @return 加密后的字符串
	 */
	private  String makeMD5(String str){
		
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
	private  String convertToHexString(byte[] data) {
		  StringBuffer strBuffer = new StringBuffer();
		  for (int i = 0; i < data.length; i++) {
		   strBuffer.append(Integer.toHexString(0xff & data[i]));
		  }
		  return strBuffer.toString();
		 }

}
