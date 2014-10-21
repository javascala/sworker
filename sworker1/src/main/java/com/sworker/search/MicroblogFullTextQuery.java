package com.sworker.search;

import com.sworker.elasticsearch.base.FullTextQueryBase;


public class MicroblogFullTextQuery extends FullTextQueryBase{

	/**
	 * 搜索条件：全部 微博种类
	 */
	public MicroblogSearchTerm searchTerm = MicroblogSearchTerm.ALL;
	
	/**
	 * 搜索范围:微博内容
	 */
	public MicroblogSearchRange searchRange = MicroblogSearchRange.ALL;
	
	/**
	 * 排序
	 */
	public MicroblogSearchSortBy sortBy = MicroblogSearchSortBy.Id_Asc;
	

	public MicroblogSearchTerm getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(MicroblogSearchTerm searchTerm) {
		this.searchTerm = searchTerm;
	}

	public MicroblogSearchRange getSearchRange() {
		return searchRange;
	}

	public void setSearchRange(MicroblogSearchRange searchRange) {
		this.searchRange = searchRange;
	}

	public MicroblogSearchSortBy getSortBy() {
		return sortBy;
	}

	public void setSortBy(MicroblogSearchSortBy sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * 微博搜索排序
	 * @author lizc
	 *
	 */
    public enum MicroblogSearchSortBy
    {
    	/**
    	 * 按id顺序正序
    	 */
        Id_Asc,

        /**
         * 按时间正序
         */
        Date_Asc
    }
    
    /**
     * 微博搜索条件
     * @author lizc
     *
     */
    public enum MicroblogSearchTerm
    {
    	/**
    	 * 全部
    	 */
        ALL,
        
        /**
         * 感冒类
         */
        COLD
    }
    
    /**
     * 微博搜索范围
     * @author lizc
     *
     */
    public enum MicroblogSearchRange
    {
    	/**
    	 * 全部
    	 */
        ALL,

        /**
         * 作者名
         */
        AUTHOR,

        /**
         * 内容
         */
        BODY
    }
}
