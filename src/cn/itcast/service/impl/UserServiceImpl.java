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
		//���ݵ�¼���Ʋ�ѯ��¼�û�
		User exitU = userDao.getUserByCode(u.getUser_code());
		//�ж��û��Ƿ���� 
		if(exitU==null){
			throw new RuntimeException("�û��������ڣ�");
		}
		//�ж������Ƿ���ȷ
		if(!exitU.getUser_password().equals(MD5Utils.md5(u.getUser_password()))){
			throw new RuntimeException("�������");
		}
		return exitU;
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveUser(User u) {
		//����dao��ѯ�û��Ƿ����
		User exitU=userDao.getUserByCode(u.getUser_code());
		if(exitU!=null){
			throw new RuntimeException("�û��Ѿ����ڣ�");
		}
		
		//ʹ��MD5��������м���
		u.setUser_password(MD5Utils.md5(u.getUser_password()));
		
		userDao.save(u);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	
}