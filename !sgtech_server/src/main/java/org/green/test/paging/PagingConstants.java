package org.green.test.paging;

public interface PagingConstants {
	
	public static String getParams(int page, int pageSize, String searchType, String keyword) {
			return "?page="+page+"&pageSize="+pageSize+"&searchType="+searchType+"&keyword="+keyword;
	}
	
}
