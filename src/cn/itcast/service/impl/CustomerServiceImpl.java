package cn.itcast.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;
import cn.itcast.vo.PageBean;
@Service("customerService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class CustomerServiceImpl implements CustomerService {
	@Resource(name="customerDao")
	private CustomerDao cd;
	@Override
	//��ҳ����
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1.����dao��ѯ�ܼ�¼��
		Integer totalCount = cd.getTotalCount(dc);
		//2.����PageBean
		PageBean pageBean=new PageBean(currentPage, pageSize, totalCount);
		//3.����dao��ѯ��ҳ�б�����
		List<Customer> list=cd.getPageList(dc,pageBean.start(),pageBean.getPageSize());
		//4.�б����ݷ���PageBean��
		pageBean.setList(list);
		return pageBean;
	}
	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}
	
	@Override
	//����ͻ�
	public void save(Customer customer) {
		//1 ά��Customer�������ֵ����Ĺ�ϵ,����struts2������װ,�Ὣ������װ�������ֵ��id����.
		//��ô���������ֶ�ά����ϵ
		//2 ����Dao����ͻ�
		cd.saveOrUpdate(customer);
	}
	@Override
	//ͨ��id����û�
	public Customer getById(Long cust_id) {
		return cd.getById(cust_id);
	}
	@Override
	//ͳ�ƿͻ���ҵ
	public List<Object[]> getIndustry() {
		return cd.getIndustry();
	}
	@Override
	//ɾ���û�
	public void delete(Customer c) {
		cd.delete(c);
	}
	@Override
	//ͳ�ƿͻ���Դ
	public List<Object[]> getSource() {
		return cd.getSource();
	}
	
}
