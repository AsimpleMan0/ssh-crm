package cn.itcast.vo;

import java.util.List;

public class PageBean {
	//当前页码
	private Integer currentPage;
	//总页数
	private Integer totalPage;
	//每页显示条数
	private Integer pageSize;
	//总记录数
	private Integer totalCount;
	//客户列表
	private List list;
	
	public PageBean(Integer currentPage, Integer pageSize, Integer totalCount) {
		super();
		this.totalCount = totalCount;
		
		
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		
		if(this.pageSize==null){
			this.pageSize=3;
		}
		
		if(this.currentPage==null||this.currentPage<1){
			this.currentPage=1;
		}
		
		//计算总页数
		this.totalPage=(this.totalCount+this.pageSize-1)/this.pageSize;
		
		if(this.currentPage>this.totalPage){
			this.currentPage=this.totalPage;
		}
	}
	
	//计算起始索引 	
	public Integer start(){
		return (this.currentPage-1)*this.pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	
}
