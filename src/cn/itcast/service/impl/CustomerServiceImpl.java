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
	//分页方法
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1.调用dao查询总记录数
		Integer totalCount = cd.getTotalCount(dc);
		//2.创建PageBean
		PageBean pageBean=new PageBean(currentPage, pageSize, totalCount);
		//3.调用dao查询分页列表数据
		List<Customer> list=cd.getPageList(dc,pageBean.start(),pageBean.getPageSize());
		//4.列表数据放入PageBean中
		pageBean.setList(list);
		return pageBean;
	}
	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}
	
	@Override
	//保存客户
	public void save(Customer customer) {
		//1 维护Customer与数据字典对象的关系,由于struts2参数封装,会将参数封装到数据字典的id属性.
		//那么我们无需手动维护关系
		//2 调用Dao保存客户
		cd.saveOrUpdate(customer);
	}
	@Override
	//通过id获得用户
	public Customer getById(Long cust_id) {
		return cd.getById(cust_id);
	}
	@Override
	//统计客户行业
	public List<Object[]> getIndustry() {
		return cd.getIndustry();
	}
	@Override
	//删除用户
	public void delete(Customer c) {
		cd.delete(c);
	}
	@Override
	//统计客户来源
	public List<Object[]> getSource() {
		return cd.getSource();
	}
	
}
