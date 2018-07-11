package com.sawelly.fpog.common.page;

public class Page {
	private java.util.List results;

	private int currentPage;

	private int pages;

	private int pageSize;

	private int totalSize;

	private java.util.LinkedHashMap resultMap;

	public java.util.List getResults() {
		return results;
	}

	public void setResults(java.util.List results) {
		this.results = results;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public java.util.LinkedHashMap getResultMap() {
		return resultMap;
	}

	public void setResultMap(java.util.LinkedHashMap resultMap) {
		this.resultMap = resultMap;
	}

	public Page(int currentPage, int pageSize, int pages,
			java.util.List results, int totalSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.pages = pages;
		this.results = results;
		this.totalSize = totalSize;

	}

	public Page() {
	}
}
