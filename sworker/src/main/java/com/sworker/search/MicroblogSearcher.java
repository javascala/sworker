package com.sworker.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import com.sworker.elasticsearch.ISearcher;
import com.sworker.elasticsearch.SearchFactory;
import com.sworker.elasticsearch.base.FullTextQueryBase;
import com.sworker.elasticsearch.base.SearcherBase;
import com.sworker.entity.SwMicroblogsEntity;
import com.sworker.search.MicroblogFullTextQuery.MicroblogSearchRange;
import com.sworker.search.MicroblogFullTextQuery.MicroblogSearchSortBy;
import com.sworker.search.MicroblogFullTextQuery.MicroblogSearchTerm;

@Component
public class MicroblogSearcher extends SearcherBase<SwMicroblogsEntity> implements ISearcher<SwMicroblogsEntity> {
	
	public static String CODE = "NoticeSearcher";
	
	public MicroblogSearcher(){
		searchEngine = SearchFactory.getSearchEngine();
		indexJson = new MicroblogIndexJson();
		companyId = "ldns";
		indexType = MicroblogSearcher.CODE;
	}
	
    /**
     * 获取索引主键的名字
     * @return 索引主键的名字
     */
	public String getNameOfPrimeKey() {
	    return MicroblogIndexJson.MICROBLOGID;
	}
    
    /**
     * 数据准备
     * @param fullTextQuery 检索条件
     */
    public void prepareCondtion(FullTextQueryBase fullTextQuery) {
//        fullTextQuery.setCompanyId(companyId);
//        fullTextQuery.setIndexType(MedicineSearcher.CODE);
    }
    
    /**
     * 根据检索条件，检索范围的处理
     * @param fullTextQuery 检索条件
     * @return 检索范围
     */
    public QueryBuilder builderQueryBuilder(FullTextQueryBase fullTextQuery) {
        
        MicroblogFullTextQuery microblogFullTextQuery = (MicroblogFullTextQuery)fullTextQuery;
        
        // 检索关键词处理
        //List<String> keywords = medicineFullTextQuery.getKeywords();
        String keyword = microblogFullTextQuery.getKeyword();
        
        // 搜索范围处理
        List<String> fieldsList  = new ArrayList<String>();
        MicroblogSearchRange microblogSearchRange = microblogFullTextQuery.getSearchRange();
        if (null != microblogSearchRange) {
            switch (microblogSearchRange)
            {
                case AUTHOR:
                    fieldsList.add(MicroblogIndexJson.AUTHOR);
                
                    break;
                case BODY:
                    fieldsList.add(MicroblogIndexJson.BODY);
                
                    break;
                case ALL:
                    fieldsList.add(MicroblogIndexJson.AUTHOR);
                    fieldsList.add(MicroblogIndexJson.BODY);
                    break;
                default:
                    fieldsList.add(MicroblogIndexJson.AUTHOR); // 默认全检索
                    fieldsList.add(MicroblogIndexJson.BODY);
                    break;
            }
        }
        String[] fields = new String[fieldsList.size()];
        fieldsList.toArray(fields);
        
        // 查询条件
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, fields);  
        
        return queryBuilder;
    }
    
    /**
     * 根据检索条件，过滤条件的处理
     * @param fullTextQuery 检索条件
     * @return 过滤条件
     */
    @Override
    public FilterBuilder builderFilterBuilder(FullTextQueryBase fullTextQuery) {
        
        MicroblogFullTextQuery microblogFullTextQuery = (MicroblogFullTextQuery)fullTextQuery;
        
        // 过滤条件
        FilterBuilder filterBuilder = null;
        MicroblogSearchTerm microblogSearchTerm = microblogFullTextQuery.getSearchTerm();
//        if (microblogSearchTerm != MicroblogSearchTerm.ALL) {
//            String filterFiled = "";
//            String filter = "";
//            switch (microblogSearchTerm)
//            {
//                case COLD:
//                    filterFiled = MicroblogIndexJson.TYPE;
//                    filter = "1";
//                    break;
//                default:
//
//                    break;
//            }
//            
//            filterBuilder = FilterBuilders.boolFilter()
//                     .must(FilterBuilders.termFilter(filterFiled, filter));
//        }
        
        return filterBuilder;
    }
    
    /**
     * 根据检索条件，排序条件的处理
     * @param fullTextQuery 检索条件
     * @return 排序条件
     */
    @Override
    public FieldSortBuilder builderFieldSortBuilder(FullTextQueryBase fullTextQuery) {
        
        MicroblogFullTextQuery microblogFullTextQuery = (MicroblogFullTextQuery)fullTextQuery;
        
        // 排序条件
        String sortBy = "";
        SortOrder sortOrder = SortOrder.ASC; // 默认升序排列
        MicroblogSearchSortBy microblogSearchSortBy = microblogFullTextQuery.getSortBy();
        switch (microblogSearchSortBy)
        {
            case Id_Asc:
                sortBy = MicroblogIndexJson.MICROBLOGID;
                sortOrder = SortOrder.ASC;
                break;
            case Date_Asc:
                sortBy = MicroblogIndexJson.DATECREATED;
                sortOrder = SortOrder.ASC;
                break;
            default:
                sortBy = MicroblogIndexJson.MICROBLOGID;  // 默认按照id升序排列
                sortOrder = SortOrder.ASC;
                break;
        }
        FieldSortBuilder sortBuilders = SortBuilders.fieldSort(sortBy).order(sortOrder);
        
        return sortBuilders;
    }
	
    /**
     * 获取创建索引需要的数据源
     * @param list 数据源
     */
	public void getIndexSource(List<SwMicroblogsEntity> list) {
	    
	    // 调用服务接口，检索该应用下的所有数据。
	    // 这里由于没有服务，只是模拟数据
	    list.clear();
//        for (int i = 0; i < 20; i++) {
//            list.add(new MicroblogFullTextQuery(i, "银花感冒颗粒", "功能主治：银花感冒颗粒 ，头痛,清热，解表，利咽。", 1));
//        }
        
	}
	
}
