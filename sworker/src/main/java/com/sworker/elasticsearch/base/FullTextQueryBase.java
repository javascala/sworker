package com.sworker.elasticsearch.base;

import java.util.List;


public class FullTextQueryBase {

    public String companyId;
    
    public String indexType;
    
	/**
	 * 每页显示的记录数
	 */
    public static final int PageSize = 20;
    
    public int pageIndex = 1;
    
    /**
     * 关键词集合
     */
    public List<String> keywords;
    
    /**
     * 关键词
     */
    public String keyword;
    
    public int getPageIndex() {
        if (pageIndex < 1) {
            return 1;
        } else {
            return pageIndex;
        }
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }
    
    
}
