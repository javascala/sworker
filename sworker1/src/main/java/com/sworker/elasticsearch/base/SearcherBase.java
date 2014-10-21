package com.sworker.elasticsearch.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sworker.elasticsearch.ISearchEngine;

public abstract class SearcherBase<E> {

    public IndexJsonBase<E> indexJson;
    public ISearchEngine searchEngine;
    public String companyId;
    public String indexType;

    /**
     * 添加索引
     * 
     * @param baseEntity 待添加索引的实体
     */
    public void insert(E  baseEntity) {
        List<E> entityList = new ArrayList<E>();
        entityList.add(baseEntity);
        this.insert(entityList);
    }

    /**
     * 添加索引
     * 
     * @param entityList 待添加的药品list
     */
    public void insert(List<E> entityList) {

        List<String> jsondataList = new ArrayList<String>();

        // 转化对象->json格式的数据
        jsondataList = indexJson.convert(entityList);

        searchEngine.insert(companyId, indexType, jsondataList);
    }

    /**
     * 删除索引
     * 
     * @param medicineId 待删除Id(String)
     */
    public void delete(String id) {
        
        // 取得关键字段的名字
        String fieldNameOfId = getNameOfPrimeKey();

        // 调用搜索引擎，进行单条删除操作
        searchEngine.delete(companyId, indexType, id, fieldNameOfId);
    }

    /**
     * 删除索引
     * @param <T>
     * 
     * @param medicineId 待删除Id(Integer)
     */
    public void delete(Integer id) {
        // 调用搜索引擎，进行删除操作
        delete(id.toString());
    }

    /**
     * 批量删除索引
     * 
     * @param ids 待删除的Id集合
     */
    public void delete(List ids) {
        
        // 取得关键字段的名字
        String fieldNameOfId = getNameOfPrimeKey();
        
        // 调用搜索引擎，进行批量删除操作
        searchEngine.delete(companyId, indexType, ids, fieldNameOfId);
    }

    /**
     * 更新索引
     * 
     * @param baseEntity 待更新的实体
     */
    public void update(E baseEntity) {
        String fieldNameOfId = getNameOfPrimeKey();

        // 查找主键所对应的值
        String jsonData = indexJson.convert(baseEntity);
        JSONObject json = (JSONObject) JSON.parse(jsonData);
        String id = json.get(fieldNameOfId).toString();

        // 调用搜索引擎，进行更新操作
        searchEngine.update(companyId, indexType, id, fieldNameOfId, jsonData);
    }

    /**
     * 批量更新索引
     * @param baseEntityList 实体集合
     */
    public void update(List<E> baseEntityList) {
        
        String fieldNameOfId = getNameOfPrimeKey();

        // 查找主键所对应的值
        List<String> jsonDataList = indexJson.convert(baseEntityList);
        List ids = new ArrayList();
        
        // 根据实体集合，取得主键数值集合
        for (int i = 0; i <jsonDataList.size(); i++) {
            String jsonData = jsonDataList.get(i);
            
            JSONObject json = (JSONObject) JSON.parse(jsonData);
            String id = json.get(fieldNameOfId).toString();
            ids.add(id);
        }


        // 调用搜索引擎，进行更新操作
        searchEngine.update(companyId, indexType, ids, fieldNameOfId, jsonDataList);
    }

    /**
     * 根据检索条件，进行全文检索
     * 
     * @param fullTextQuery 检索条件
     */
    public List<E> search(FullTextQueryBase fullTextQuery) {

        List<E> reList = new ArrayList();
        prepareCondtion(fullTextQuery);

        QueryBuilder queryBuilder = builderQueryBuilder(fullTextQuery);
        FilterBuilder filterBuilder = builderFilterBuilder(fullTextQuery);
        FieldSortBuilder sortBuilders = builderFieldSortBuilder(fullTextQuery);

        // 分页检索
        List<Map<String, Object>> result = searchEngine.search(companyId,
                indexType, queryBuilder, filterBuilder, sortBuilders,
                fullTextQuery.getPageIndex(), fullTextQuery.PageSize);

        System.out.println("查询到记录数=" + result.size());
        for (int i = 0; i < result.size(); i++) {
            Map map = result.get(i);
            reList.add((E) map);
            System.out.println(map);
        }
        System.out.println("search success ..");
        
        return reList;
    }

    /***
     * 重建索引
     */
    public void rebuildIndex() {

        List<E> list = new ArrayList();
        // 数据准备
        getIndexSource(list);

        List<String> jsondataList = indexJson.convert(list);

        searchEngine.rebuildIndex(companyId, indexType, jsondataList);

    }
    
    /**
     * 根据检索条件，过滤条件的处理
     * 
     * @param fullTextQuery 检索条件
     * @return 过滤条件
     */
    public FilterBuilder builderFilterBuilder(
            FullTextQueryBase fullTextQuery) {
        return null;
    }

    /**
     * 根据检索条件，排序条件的处理
     * 
     * @param fullTextQuery 检索条件
     * @return 排序条件
     */
    public FieldSortBuilder builderFieldSortBuilder(
            FullTextQueryBase fullTextQuery) {
        return null;
    }


    /**
     * 获取索引主键的名字
     * 
     * @return 索引主键的名字
     */
    public abstract String getNameOfPrimeKey();

    /**
     * 数据准备
     * 
     * @param fullTextQuery 检索条件
     */
    public abstract void prepareCondtion(FullTextQueryBase fullTextQuery);

    /**
     * 根据检索条件，检索范围的处理
     * 
     * @param fullTextQuery 检索条件
     * @return 检索范围
     */
    public abstract QueryBuilder builderQueryBuilder(
            FullTextQueryBase fullTextQuery);

    /**
     * 获取创建索引需要的数据源
     * 
     * @param list 数据源
     */
    public abstract void getIndexSource(List<E> list);

}
