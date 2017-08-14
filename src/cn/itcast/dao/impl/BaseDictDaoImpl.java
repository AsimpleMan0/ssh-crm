package cn.itcast.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.BaseDictDao;
import cn.itcast.domain.BaseDict;
@Repository("baseDictDao")
public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {
	
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
	@Override
	//获得数据字典List对象
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		//1.创建cirteria查询对象
		DetachedCriteria dc=DetachedCriteria.forClass(BaseDict.class);
		//2.封装条件
		dc.add(Restrictions.eq("dict_type_code",dict_type_code));
		//3.执行查询
		List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);
		//返回数据
		return list;
	}

}
