package com.sworker.elasticsearch;

import java.util.List;

import com.sworker.elasticsearch.base.FullTextQueryBase;


public interface ISearcher<T> {

    /**
     * 添加索引
     * 
     * @param baseEntity 待添加索引的实体
     */
    void insert(T baseEntity);
    
    /**
     * 添加索引
     * 
     * @param entityList 待添加的药品list
     */
	void insert(List<T> list);
	
    /**
     * 删除索引
     * 
     * @param medicineId 待删除Id(String)
     */
    void delete(String id);
    
    
    /**
     * 更新索引
     * 
     * @param baseEntity 待更新的实体
     */
    void update(T baseEntity);
    
    /**
     * 批量更新索引
     * @param baseEntityList 实体集合
     */
    void update(List<T> baseEntityList);
    
    /**
     * 根据检索条件，进行全文检索
     * 
     * @param fullTextQuery 检索条件
     */
    List<T> search(FullTextQueryBase fullTextQuery);
    
    /**
     * 重建索引
     */
    void rebuildIndex();
    
}
