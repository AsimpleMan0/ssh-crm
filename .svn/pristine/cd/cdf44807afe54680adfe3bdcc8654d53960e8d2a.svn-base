package cn.itcast.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.LinkMan;
import cn.itcast.vo.PageBean;

public interface LinkManService {
	//保存联系人
	void save(LinkMan linkMan);
	//分页业务方法
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	//根据id获得联系人
	LinkMan getById(Long lkm_id);
	//删除联系人
	void delete(LinkMan lk);
}
