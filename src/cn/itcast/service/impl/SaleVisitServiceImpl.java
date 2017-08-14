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
	//�ͻ��ݷü�¼���
	public void save(SaleVisit saleVisit) {
		svd.saveOrUpdate(saleVisit);
	}
	//��ҳ����
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1.����dao��ѯ�ܼ�¼��
		Integer totalCount = svd.getTotalCount(dc);
		//2.����PageBean
		PageBean pageBean=new PageBean(currentPage, pageSize, totalCount);
		//3.����dao��ѯ��ҳ�б�����
		List<SaleVisit> list=svd.getPageList(dc,pageBean.start(),pageBean.getPageSize());
		//4.�б����ݷ���PageBean��
		pageBean.setList(list);
		return pageBean;
	}	
	@Override
	//��ü�¼
	public SaleVisit getById(String visit_id) {
		return svd.getById(visit_id);
	}
	
	@Override
	//ɾ����¼
	public void delete(SaleVisit sv) {
		svd.delete(sv);
	}
	public void setSvd(SaleVisitDao svd) {
		this.svd = svd;
	}
	
	
}
