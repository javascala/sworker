package com.sworker.dao;

import java.util.List;

import com.sworker.entity.SwAttachmentEntity;
/**
 * 
 * @author wanggang 2014/8/28.
 *
 */
public interface IAttachmentDao {
	/**
	 * 将附件实体保存进数据库
	 * @param att 附件实体
	 */
	public void saveAttachment(SwAttachmentEntity att);
	
	/**
	 * 根据附件ID删除附件
	 * @param id 附件ID
	 */
	public void deleteAttachmentById(Long id);
	
	/**
	 * 根据业务ID删除附件
	 * @param businessid 业务ID
	 */
	public void deleteAttachmentByBizID(Long businessid);
	
	/**
	 *通过userid 连接查询得到附件列表
	 * @param userid 用户ID
	 * @return 附件列表
	 */
	public List<SwAttachmentEntity> findAttachmentsByUserId(Long userid);
	
	/**
	 * 通过businessid 连接查询得到附件列表
	 * @param businessid 业务ID
	 * @return 附件列表
	 */
	public List<SwAttachmentEntity> findAttachmentsByBizID(Long businessid);
	
	/**
	 * 根据附件ID获取附件的详细信息
	 * @param id 附件ID
	 * @return 附件实体
	 */
	public SwAttachmentEntity getAttachment(Long id);
	
}
