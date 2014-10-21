package com.sworker.logic;

import java.util.List;

import com.sworker.entity.SwAttachmentEntity;

/**
 * 
 * @author wanggang 2014/8/28.
 *
 */
public interface IAttachmentLogic {
	/**
	 * 创建附件
	 * @param attachment 附件实体
	 */
	public void createAttachment(SwAttachmentEntity attachment);
	
	
	/**
	 * 根据附件ID,删除附件
	 * @param id 附件ID
	 */
	public void deleteAttachmentByID(Long id);
	
	
	/**
	 * 根据业务ID，删除附件
	 * @param businessid 业务ID
	 */
	public void deleteAttachmentByBizID(Long businessid);
	
	
	/**
	 * 获取用户附件列表
	 * @param userid
	 * @return 如果有检索结果，返回附件实体列表。无检索结果，则返回NULL
	 */
	public List<SwAttachmentEntity> getAttachmentListByUserID(Long userid);
	
	
	/**
	 * 根据业务ID，获取附件列表
	 * @param businessid
	 * @return 如果有检索结果，返回附件实体列表。无检索结果，则返回NULL
	 */
	public List<SwAttachmentEntity> getAttachmentListByBizID(Long businessid);
	
	
	/**
	 * 获取附件详细信息
	 * @param id 附件ID
	 * @return 如果有检索结果，返回附件实体。无检索结果，则返回NULL
	 */
	public SwAttachmentEntity getAttachmentDetail(Long id);
	
}
