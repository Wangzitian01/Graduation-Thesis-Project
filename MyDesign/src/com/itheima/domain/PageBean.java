package com.itheima.domain;

import java.util.List;

public class PageBean<T> {
	/**
	 * 2个传
	 * 2个算
	 * 2个查
	 * 2个动态条
	 */
	private int pageNumber;//当前页
	private int pageSize ; //每页显示个数
	
	private int startIndex; //开始索引
	private int totalPage ; //总页数
	
	private int totalRecord;//总记录数
	private List<T> data;

	private int start;//遍历开始
	private int end ;//遍历的结束
	
	private void jisuan(){
		//计算主要是给 start和 end 赋值即可
		//前五后四 或者前四后五
		start = 1; 
		end = 10;
		//开始判断 如果总页数不满足于十页 有没有必要前四后五 没有 start=1 end 等于最大页数
		//因为totalPage的计算都是来源于get方法计算 第一次只要有一个地方需要提前调用 getTotalPage
		if(getTotalPage() < 10 ){ //此处建议大家 手动调用getTotalPage 后面无所谓
			start  =1 ;
			end = totalPage;
		}else{
			//表示总页数一定大于十页  一定大于十页就可以进行条件判断
			start = pageNumber - 4 ;
			end = pageNumber + 5;
			
			//最小极限值判断   start 有可能小于1     当前页为 1 2 3 4 的时候 小于1  不需要前四后五 一直保持十页即可
			if(start < 1 ){
				start  = 1;
				end = 10;
			}
			//最大极限值判断 end有可能大于总页数 例如 总页数 13也 当前页已经是12页  end = 12 + 5 > 13
			if(end > totalPage ){
				end = totalPage;
				start = totalPage - 9 ;
			}
			
			
		}
		
		
		
		
		
	}
	
	public int getTotalPage() {
		//总记录数/每页显示个数
		if(totalRecord%pageSize==0){
			totalPage = totalRecord/pageSize;
		}else{
			totalPage = totalRecord/pageSize + 1;
		}
		return totalPage;
	}
	public int getStartIndex() {
		//开始索引= (当前页-1)*pageSize
		startIndex = (pageNumber - 1 )*pageSize;
		return startIndex;
	}
	public int getStart() {
		jisuan();
		return start;
	}
	public int getEnd() {
		jisuan();
		return end;
	}
	
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	public PageBean(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
	
	
	
}
