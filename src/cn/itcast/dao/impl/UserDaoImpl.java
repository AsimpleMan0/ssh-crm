package cn.itcast.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
//HibernateDaoSupport Ϊdao ע��sessionFactory
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	@Override
	public User getUserByCode(String usercode) {
		/*//HQL
		return getHibernateTemplate().execute(new HibernateCallback<User>() {
			@Override
			public User doInHibernate(Session session) throws HibernateException {
				String hql=" from User where user_code=? ";
				Query query = session.createQuery(hql);
				query.setParameter(0,usercode);
				User user = (User) query.uniqueResult();
				return user;
			}
		});*/
		//Cirteria
		DetachedCriteria dc=DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("user_code",usercode));
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}


}
