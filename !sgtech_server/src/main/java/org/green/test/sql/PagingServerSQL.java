package org.green.test.sql;

public interface PagingServerSQL {
	public static String addPaging(String sql, int offset, int pageSize) { 
		return sql +" LIMIT "+ offset +"," +pageSize ; 
	}
	
	public static String addSearchType(String sql, String searchType) {
		return "SELECT * FROM ( "+sql +") t1 WHERE t1."+searchType+" LIKE ?";
	}
	
	public static String getCount(String sql) {
		return "SELECT COUNT(*) FROM ("+sql+") cnt1";
	}
	
}
