package cn.itcast.dao;

import cn.itcast.domain.User;

public interface UserDao extends BaseDao<User>{
	
	User getUserByCode(String usercode);
}
