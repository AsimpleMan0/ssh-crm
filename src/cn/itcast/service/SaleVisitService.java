package cn.itcast.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.SaleVisit;
import cn.itcast.vo.PageBean;

public interface SaleVisitService {
	//客户拜访记录添加
	void save(SaleVisit saleVisit);
	//分页
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	//获得记录
	SaleVisit getById(String visit_id);
	//删除记录
	void delete(SaleVisit sv);

}
