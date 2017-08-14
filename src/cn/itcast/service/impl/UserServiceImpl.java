package cn.itcast.service.impl;

import javax.annotation.Resource;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.utils.MD5Utils;

@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource(name="userDao")
	private UserDao userDao;
	@Override
	public User getUserByCode(User u) {
		//根据登录名称查询登录用户
		User exitU = userDao.getUserByCode(u.getUser_code());
		//判断用户是否存在 
		if(exitU==null){
			throw new RuntimeException("用户名不存在！");
		}
		//判断密码是否正确
		if(!exitU.getUser_password().equals(MD5Utils.md5(u.getUser_password()))){
			throw new RuntimeException("密码错误！");
		}
		return exitU;
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveUser(User u) {
		//调用dao查询用户是否存在
		User exitU=userDao.getUserByCode(u.getUser_code());
		if(exitU!=null){
			throw new RuntimeException("用户已经存在！");
		}
		
		//使用MD5对密码进行加密
		u.setUser_password(MD5Utils.md5(u.getUser_password()));
		
		userDao.save(u);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	
}
