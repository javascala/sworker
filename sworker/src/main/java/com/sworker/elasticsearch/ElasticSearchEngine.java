/*
 * 版权: Copyright (C) 2014
 * 公司名: LDNS
 * 更新记录:
 *  2014/06/16 1.0.0　李占川　做成
 */

package com.sworker.elasticsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;

/**
 * ElasticSearch搜索引擎的封装类
 * 
 * @author 李占川
 * @version 1.0 2014/06/16
 * @since 1.0
 * 
 */
public class ElasticSearchEngine implements ISearchEngine {
    private Client client;

    public ElasticSearchEngine() {
        // 使用本机做为节点
        // this("172.16.2.213");
        this("172.16.50.161");
    }

    public ElasticSearchEngine(String ipAddress) {
        // 集群连接超时设置
        /*
         * Settings settings =
         * ImmutableSettings.settingsBuilder().put("client.transport.ping_timeout"
         * , "10s").build(); client = new TransportClient(settings);
         */
        // //自定义集群结点名称
        // String clusterName = "elasticsearch-lizc";
        //
        // //程序中更改集群结点名称
        // Settings settings =
        // ImmutableSettings.settingsBuilder().put("cluster.name",
        // clusterName).build();

        client = new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress(ipAddress,
                        9300));
        
    }

    /**
     * 追加单条索引
     * 
     * @param indexName 为索引库名，一个es集群中可以有多个索引库。 名称必须为小写 索引名，如：公司名或者id
     * @param indexType Type为索引类型，是用来区分同索引库下不同类型的数据的，一个索引库下可以有多个索引类型。 
     *              索引类型，如：微博，日志等
     * @param jsondata json格式索引字符串
     * @return
     */
    public IndexResponse insert(String indexName, String indexType, String jsondata) {
        // TODO：参数检查

        // 创建索引
        IndexResponse response = client.prepareIndex(indexName, indexType)
                .setSource(jsondata).execute().actionGet();
        return response;
    }

    /**
     * 追加索引
     * 
     * @param indexName 索引名，如：公司名或者id
     * @param indexType 索引类型，如：微博，日志等
     * @param jsondataList json格式索引字符串集合
     * @return
     */
    public void insert(String indexName, String indexType, List<String> jsondataList) {
        // TODO：参数检查

        // // 批量创建索引
        // try {
        // try {
        // // 预定义一个索引
        // client.admin().indices().prepareCreate(indexname).execute()
        // .actionGet();
        //
        // String value = "ik";
        //
        // // 定义索引字段属性
        // XContentBuilder mapping = XContentFactory.jsonBuilder()
        // .startObject();
        // mapping = mapping.startObject(type);
        // mapping = mapping.startObject("id")
        // // 创建索引时使用ik解析
        // .field("indexAnalyzer", value)
        // // 搜索时使用ik解析
        // .field("searchAnalyzer", value).field("store", "yes")
        // .endObject();
        //
        // mapping = mapping
        // .startObject("name")
        // // 创建索引时使用ik解析
        // .field("indexAnalyzer", value)
        // // 搜索时使用ik解析
        // .field("searchAnalyzer", value)
        // .field("include_in_all", true).field("store", "yes")
        // .endObject();
        //
        // mapping = mapping.startObject("function")
        // // 创建索引时使用ik解析
        // .field("indexAnalyzer", value)
        // // 搜索时使用ik解析
        // .field("searchAnalyzer", value).field("store", "yes")
        // .endObject();
        //
        // mapping = mapping.endObject();
        // mapping = mapping.endObject();
        //
        // PutMappingRequest mappingRequest = Requests
        // .putMappingRequest(indexname).type(type)
        // .source(mapping);
        // client.admin().indices().putMapping(mappingRequest).actionGet();
        // // System.out.println(mapping.);
        // } catch (IndexAlreadyExistsException e) {
        // System.out.println("索引库已存在");
        // }
        //
        // // 创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
        // IndexRequestBuilder requestBuilder = client.prepareIndex(indexname,
        // type).setRefresh(true);
        // for (int i = 0; i < jsondataList.size(); i++) {
        // requestBuilder.setSource(jsondataList.get(i)).execute().actionGet();
        // }
        //
        // } catch (Exception e) {
        // e.printStackTrace();
        // } finally {
        // client.close();
        // }
        // 创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
        
        // 批量创建索引
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        
        for (int i = 0; i < jsondataList.size(); i++) {
            IndexRequestBuilder requestBuilder = client.prepareIndex(indexName,
                    indexType).setRefresh(true);
            
            requestBuilder.setSource(jsondataList.get(i));
            
            bulkRequest.add(requestBuilder);
        }

        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        }
    }

    /**
     * 索引检索，检索前N条数据
     * @param indexName 索引库名
     * @param indexType 索引类型
     * @param queryBuilder 检索条件
     * @param filterBuilder 过滤条件
     * @param sortBuilder 排序条件
     * @param topNumber 前多少条数据
     * @return 检索结果集合
     */
    public List<Map<String, Object>> search(String indexName, String indexType,
            QueryBuilder queryBuilder, FilterBuilder filterBuilder,
            SortBuilder sortBuilder, int topNumber) {

        List<Map<String, Object>> searchRes = this.search(indexName, indexType,
                queryBuilder, filterBuilder, sortBuilder, 1, topNumber);

        return searchRes;
    }

    /**
     * 执行搜索，分页检索
     * @param indexName 索引库名
     * @param indexType 索引类型
     * @param queryBuilder 检索条件
     * @param filterBuilder 过滤条件
     * @param sortBuilder 排序条件
     * @param pageIndex 要显示的页码
     * @param pageSize 每页的数据数
     * @return 检索结果集合
     */
    public List<Map<String, Object>> search(String indexName, String indexType,
            QueryBuilder queryBuilder, FilterBuilder filterBuilder,
            SortBuilder sortBuilder, int pageIndex, int pageSize) {

        List<Map<String, Object>> searchRes = new ArrayList<Map<String, Object>>();
        // 创建查询索引
        SearchRequestBuilder searchRequestBuilder = client
                .prepareSearch(indexName);
        // 设置查询索引类型
        searchRequestBuilder.setTypes(indexType);

        // 设置查询类型
        // 1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询
        // 2.SearchType.SCAN = 扫描查询,无序
        // 3.SearchType.COUNT = 不设置的话,这个为默认值,还有的自己去试试吧
        searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

        // 设置查询关键词
        // fieldQuery 这个必须是你的索引字段哦,不然查不到数据,这里我只设置两个字段 id ,title
        searchRequestBuilder.setQuery(queryBuilder);

        // 设置查询数据的位置,分页用吧
        searchRequestBuilder.setFrom((pageIndex - 1) * pageSize);

        // 过滤条件
        searchRequestBuilder.setPostFilter(filterBuilder); // 过滤

        // 检索结果排序
        if (null != sortBuilder) {
            searchRequestBuilder.addSort(sortBuilder); // 排序
        }

        // 设置查询结果集的最大条数
        if (pageSize != 0) {
            searchRequestBuilder.setSize(pageSize);
        }

        // 设置是否按查询匹配度排序
        searchRequestBuilder.setExplain(true);
        // 最后就是返回搜索响应信息
        SearchResponse response = searchRequestBuilder.execute().actionGet();

        SearchHits searchHits = response.getHits();

        SearchHit[] hits = searchHits.getHits();
        for (int i = 0; i < hits.length; i++) {
            SearchHit hit = hits[i];
            searchRes.add(hit.getSource());
        }

        return searchRes;
    }

    /**
     * 根据详细条件，进行索引删除
     */
    public void delete(String indexName, QueryBuilder queryBuilder) {

        // 调用API，进行索引删除
        DeleteByQueryResponse response = client.prepareDeleteByQuery(indexName)
                .setQuery(queryBuilder).execute().actionGet();
        
    }
    
    /**
     * 根据条件，进行单条索引删除
     */
    public void delete(String indexName, String indexType, String id, String fieldNameOfId) {

        // 删除条件的整理
        QueryBuilder queryBuilder = QueryBuilders
                .boolQuery()
                .must(QueryBuilders.termQuery("_type", indexType))
                .must(QueryBuilders.termQuery(fieldNameOfId, id));
        
        // 删除
        this.delete(indexName, queryBuilder);

        System.out.println("delete success ..");
    }
    
    /**
     * 根据条件，进行批量索引删除
     */
    public void delete(String indexName, String indexType, List ids,
            String fieldNameOfId) {

        // 删除条件的整理
        QueryBuilder queryBuilder = QueryBuilders
                .boolQuery()
                .must(QueryBuilders.termQuery("_type", indexType))
                .must(QueryBuilders.termsQuery(fieldNameOfId, ids)); // 匹配多个ids

        // 删除
        this.delete(indexName, queryBuilder);

        System.out.println("batch delete success ..");
    }

    public void update(String indexName, String indexType, String id,
            String fieldNameOfId, String jsondata) {
        
        // 第一步：删除
        this.delete(indexName, indexType, id, fieldNameOfId);
        
        // 第二步：追加索引
        this.insert(indexName, indexType, jsondata);
        
        System.out.println("update success ..");
    }
    
    public void update(String indexName, String indexType, List ids,
            String fieldNameOfId, List<String> jsondata) {
        
        // 第一步：删除
        this.delete(indexName, indexType, ids, fieldNameOfId);
        
        // 第二步：追加索引
        this.insert(indexName, indexType, jsondata);
        
        System.out.println("batch update success ..");
    }
    

    /**
     * 重建索引
     * @param indexname 索引库名
     * @param indexType 索引类型
     * @param jsondataList 要重建的数据
     */
    public void rebuildIndex(String indexname, String type,
            List<String> jsondataList) {
        
        // 首先删除该indexname下的该类型的所有索引
        QueryBuilder qb = QueryBuilders.boolQuery().must(
                QueryBuilders.termQuery("_type", type));

        // 删除
        this.delete(indexname, qb);

        // 然后再把所有数据重新生成索引
        this.insert(indexname, type, jsondataList);

        System.out.println("rebuildIndex success ..");
    }
}
