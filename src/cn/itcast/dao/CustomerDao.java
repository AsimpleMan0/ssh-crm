package cn.itcast.dao;


import java.util.List;

import cn.itcast.domain.Customer;

public interface CustomerDao extends BaseDao<Customer>{
	
	//统计客户行业
	List<Object[]> getIndustry();
	//统计客户来源
	List<Object[]> getSource();
}
