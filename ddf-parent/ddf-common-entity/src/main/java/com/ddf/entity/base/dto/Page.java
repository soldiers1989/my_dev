package com.ddf.entity.base.dto;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;
    //总记录数
    private long totalCount;
    //总页数
    private int pages;
    //结果集
    private List<T> list;
    //是否为第一页
    private boolean firstPage = false;
    //是否为最后一页
    private boolean lastPage = false;
    
    
    //默认的构造方法，给json用的
    public Page(){
    	
    }
    /**
     * 常规做法page
     */
    public Page(int pageNum, int pageSize, long totalCount, List<T> list) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.pages = (int) Math.ceil((double) totalCount / (double) pageSize);
        this.list = list;
        this.size = list.size();
        //判断页面边界
        firstPage = pageNum == 1;
        lastPage = pageNum >= pages;
    }
    
    /**
     * 使用pageHelper
     */
    public Page(List<T> list) {
        if (list instanceof com.github.pagehelper.Page) {
        	com.github.pagehelper.Page<T> page = (com.github.pagehelper.Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();

            this.totalCount = page.getTotal();
            this.pages = page.getPages();
            this.list = page;
            this.size = page.size();
            //判断页面边界
            firstPage = pageNum == 1;
            lastPage = pageNum >= pages;
        }
    }
    
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public boolean isFirstPage() {
		return firstPage;
	}
	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
   
}