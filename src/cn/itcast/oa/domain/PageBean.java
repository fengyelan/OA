package cn.itcast.oa.domain;

import java.util.List;

/*
 * 
 * 分页功能中的一页的信息
 */
public class PageBean {
	//指定的参数
	private int currentPage;//当前页
	private int pageSize;//每页有多少条记录
	//查询数据库得到的
	private List recordList;//本页的数据列表
	private int pageCount;//总页数
	//计算
	private int recordCount;//总记录数
	private int beginPageIndex;//页码列表的开始的索引
	private int endPageIndex;//页码列表的结束索引
	
	
	public PageBean(int currentPage,int pageSize,int recordCount,List recordList){
		this.currentPage=currentPage;
		this.pageSize=pageSize;
		this.recordCount=recordCount;
		this.recordList=recordList;
		
		//计算总页数
		pageCount=(recordCount+pageSize-1)/pageSize;
		
		//计算页码的开始索引和结束索引
		if(pageCount<10){ 
			//总页数小于10页
			beginPageIndex = 1;
			endPageIndex = pageCount;			
		}else{
			//总页数大于10页
			
			//第一种情况：当前页附近有10个页码（前面4个+当前页+后面5个）
			beginPageIndex=currentPage-4;
			endPageIndex=currentPage+5;
			//第二个情况：当前页前面的页码不足4个，则显示前10页
			if(beginPageIndex<1){
				beginPageIndex=1;
				endPageIndex=10;
			}
			//第三个情况：当前页后面的页码不足5个，则显示后10页
			if(endPageIndex>pageCount){
				endPageIndex=pageCount;
				beginPageIndex=pageCount-10+1;
			}
		}
	}
	public List getRecordList() {
		return recordList;
	}
	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getBeginPageIndex() {
		return beginPageIndex;
	}
	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	
	
	
}
