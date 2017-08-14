package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Customer;
import cn.itcast.vo.PageBean;

public interface CustomerService {
	
	//��ҳҵ�񷽷�
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	//����ͻ�����
	void save(Customer customer);
	//ͨ��id����û�
	Customer getById(Long cust_id);
	//ɾ���û�
	void delete(Customer c);
	
	//ͳ�ƿͻ���ҵ
	List<Object[]> getIndustry();
	//ͳ�ƿͻ���Դ
	List<Object[]> getSource();

}
