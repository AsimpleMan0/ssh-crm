package cn.itcast.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	@Override
	// 统计客户行业
	@SuppressWarnings("all")
	public List<Object[]> getIndustry() {
		String sql = "SELECT bd.`dict_item_name`,COUNT(*) total FROM cst_customer c,base_dict bd"
				+ " WHERE c.`cust_industry`=bd.`dict_id` GROUP BY c.`cust_industry`";
		List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
			@Override
			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				List<Object[]> list = query.list();
				return list;
			}
		});
		return list;
	}

	@Override
	// 统计客户来源
	@SuppressWarnings("all")
	public List<Object[]> getSource() {
		String sql = "SELECT bd.`dict_item_name`, COUNT(*) total FROM cst_customer c,base_dict bd"+
					" WHERE c.`cust_source`=bd.`dict_id` GROUP BY c.`cust_source`";
		List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
			@Override
			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				List<Object[]> list = query.list();
				return list;
			}
		});
		return list;
	}
}
