package cn.itcast.vo;

import java.util.List;

public class PageBean {
	//��ǰҳ��
	private Integer currentPage;
	//��ҳ��
	private Integer totalPage;
	//ÿҳ��ʾ����
	private Integer pageSize;
	//�ܼ�¼��
	private Integer totalCount;
	//�ͻ��б�
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
		
		//������ҳ��
		this.totalPage=(this.totalCount+this.pageSize-1)/this.pageSize;
		
		if(this.currentPage>this.totalPage){
			this.currentPage=this.totalPage;
		}
	}
	
	//������ʼ���� 	
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
