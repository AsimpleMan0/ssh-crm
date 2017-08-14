package cn.itcast.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.SaleVisitDao;
import cn.itcast.domain.SaleVisit;
import cn.itcast.service.SaleVisitService;
import cn.itcast.vo.PageBean;
@Service("saleVisitService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class SaleVisitServiceImpl implements SaleVisitService{
	@Resource(name="saleVisitDao")
	private SaleVisitDao svd;
	@Override
	//客户拜访记录添加
	public void save(SaleVisit saleVisit) {
		svd.saveOrUpdate(saleVisit);
	}
	//分页方法
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1.调用dao查询总记录数
		Integer totalCount = svd.getTotalCount(dc);
		//2.创建PageBean
		PageBean pageBean=new PageBean(currentPage, pageSize, totalCount);
		//3.调用dao查询分页列表数据
		List<SaleVisit> list=svd.getPageList(dc,pageBean.start(),pageBean.getPageSize());
		//4.列表数据放入PageBean中
		pageBean.setList(list);
		return pageBean;
	}	
	@Override
	//获得记录
	public SaleVisit getById(String visit_id) {
		return svd.getById(visit_id);
	}
	
	@Override
	//删除记录
	public void delete(SaleVisit sv) {
		svd.delete(sv);
	}
	public void setSvd(SaleVisitDao svd) {
		this.svd = svd;
	}
	
	
}
