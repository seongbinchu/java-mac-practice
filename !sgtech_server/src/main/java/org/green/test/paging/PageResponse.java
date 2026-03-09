package org.green.test.paging;

import java.util.List;

public class PageResponse {
	private List content;
	private int totalCount;
	
	public PageResponse() {
		super();
	}

	public PageResponse(List content, int totalCount) {
		super();
		this.content = content;
		this.totalCount = totalCount;
	}

	public List getContent() {
		return content;
	}

	public void setContent(List content) {
		this.content = content;
	}


	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
