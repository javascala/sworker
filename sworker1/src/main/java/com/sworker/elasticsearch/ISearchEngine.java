package com.sworker.elasticsearch;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;

public interface ISearchEngine {

    /**
     * 
     * @param indexName
     * @param indexType
     * @param jsondata
     * @return
     */
    IndexResponse insert(String indexName, String indexType, String jsondata);

    /**
     * 
     * @param indexName
     * @param indexType
     * @param jsondataList
     */
    void insert(String indexName, String indexType, List<String> jsondataList);

    /**
     * 
     * @param indexName
     * @param queryBuilder
     */
    void delete(String indexName, QueryBuilder queryBuilder);

    /**
     * 
     * @param indexName
     * @param indexType
     * @param id
     * @param fieldNameOfId
     */
    void delete(String indexName, String indexType, String id,
            String fieldNameOfId);

    /**
     * 
     * @param indexName
     * @param indexType
     * @param ids
     * @param fieldNameOfId
     */
    void delete(String indexName, String indexType, List ids,
            String fieldNameOfId);

    void update(String indexName, String indexType, String id,
            String fieldNameOfId, String jsondata);
    
    void update(String indexName, String indexType, List ids,
            String fieldNameOfId, List<String> jsondata);
    
    /**
     * 索引检索，检索前N条数据
     * 
     * @param indexName 索引库名
     * @param indexType 索引类型
     * @param queryBuilder 检索条件
     * @param filterBuilder 过滤条件
     * @param sortBuilder 排序条件
     * @param topNumber 前多少条数据
     * @return 检索结果集合
     */
    List<Map<String, Object>> search(String indexName, String indexType,
            QueryBuilder queryBuilder, FilterBuilder filterBuilder,
            SortBuilder sortBuilder, int topNumber);

    /**
     * 执行搜索，分页检索
     * 
     * @param indexName 索引库名
     * @param indexType 索引类型
     * @param queryBuilder 检索条件
     * @param filterBuilder 过滤条件
     * @param sortBuilder 排序条件
     * @param pageIndex 要显示的页码
     * @param pageSize 每页的数据数
     * @return 检索结果集合
     */
    List<Map<String, Object>> search(String indexName, String indexType,
            QueryBuilder queryBuilder, FilterBuilder filterBuilder,
            SortBuilder sortBuilder, int pageIndex, int pageSize);

    /**
     * 重建索引
     * 
     * @param indexname 索引库名
     * @param indexType 索引类型
     * @param jsondataList 要重建的数据
     */
    void rebuildIndex(String indexname, String indexType,
            List<String> jsondataList);
}
