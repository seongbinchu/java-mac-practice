package org.green.test.paging;

public interface PagingConstants {
	
	public static String getParams(int page, int pageSize, String searchType, String keyword) {
			return "?page="+page+"&pageSize="+pageSize+"&searchType="+searchType+"&keyword="+keyword;
	}
	
	public static int getTotalPage(int totalCount, int pageSize) {
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
		
		if(totalPage == 0) {
			totalPage = 1;
		}		
		
		return totalPage; 
	}
}
