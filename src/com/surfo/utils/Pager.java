package com.surfo.utils;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * May 22, 20152:56:39 PM
 * @author fengxk
 * @version 1.0
 */
public class Pager {

	private List rows = new ArrayList();         // 要返回的当前查询页的记录列表


	private int total;                 //总记录数
	private int totalPage;             // 总页数
	private int page=1;           // 当前页
	private int pageNumber=15;            // 每页记录数pagesize
	
	
	public Pager() {
		super();
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}


	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public void init(){
		if(!PubMethod.isEmpty(this.rows)){
			this.pageNumber = Integer.valueOf(this.rows.get(0).toString()).intValue();
		}
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}


    public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

    
    /**
     * 计算当前页开始记录
     * @param pageSize 每页记录数
     * @param page 当前第几页
     * @return 当前页起始记录号的偏移量
     */
    public static int countOffset(final int pageSize,final int page){
        final int offset = pageSize*(page-1);
        return offset;
    }
    
    /**
     * 计算当前页,若为0或者请求的URL中没有"?page=",则用1代替
     * @param page 传入的参数(可能为空,即0,则返回1)
     * @return 当前页
     */
    public static int countpage(int page){
        final int curPage = (page==0?1:page);
        return curPage;
    }
	
}