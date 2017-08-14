package cn.itcast.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

//HibernateøÚº‹≤‚ ‘¿‡
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Resource(name="userService")
	private UserService us;
	@Test
	public void fun4(){
		User user=new User();
		user.setUser_code("wu");
		user.setUser_name("∞Æƒ„");
		user.setUser_password("123456");
		us.saveUser(user);
	}
	
	@Resource(name="userDao")
	private UserDao ud;
	@Test
	public void fun3(){
		
		User user = ud.getUserByCode("xia");
		System.out.println(user);
	}
	
	@Test
	public void fun2(){
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		//----------------------------------------
		User user=new User();
		user.setUser_code("xia");
		user.setUser_name("Œ‚—ﬁœº");
		user.setUser_password("123456");
		
		session.save(user);
		
		//----------------------------------------
		tx.commit();
		session.close();
		sessionFactory.close();
		
	}
	
	@Test
	public void fun1(){
		Configuration cfg=new Configuration().configure();
		
		SessionFactory sf = cfg.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		//----------------------------------------
		User user=new User();
		user.setUser_code("tom");
		user.setUser_name("Ã¿ƒ∑");
		user.setUser_password("123456");
		
		session.save(user);
		
		//----------------------------------------
		tx.commit();
		session.close();
		sf.close();
		
	}
}
