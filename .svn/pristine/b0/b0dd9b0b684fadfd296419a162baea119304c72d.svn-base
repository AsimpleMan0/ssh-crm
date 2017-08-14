package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Customer;
import cn.itcast.vo.PageBean;

public interface CustomerService {
	
	//分页业务方法
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	//保存客户方法
	void save(Customer customer);
	//通过id获得用户
	Customer getById(Long cust_id);
	//删除用户
	void delete(Customer c);
	
	//统计客户行业
	List<Object[]> getIndustry();
	//统计客户来源
	List<Object[]> getSource();

}
