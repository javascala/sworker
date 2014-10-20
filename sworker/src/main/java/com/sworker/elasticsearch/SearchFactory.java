package com.sworker.elasticsearch;

public class SearchFactory {

	/***
	 * 获取所有Searcher
	 * 
	 * @return 所有继承ISearcher的类
	 */
	public static Iterable<ISearcher> GetSearchers() {
		// return DIContainer.Resolve<IEnumerable<ISearcher>>().OrderBy(s =>
		// s.DisplayOrder);
		return null;
	}

	/***
	 * 获取SearchEngine
	 * 
	 * @return 搜索引擎
	 */
	public static ISearchEngine getSearchEngine() {
		ISearchEngine searchEngine = null;

		//searchEngine = new ElasticSearchEngine();

		return searchEngine;
	}
}
