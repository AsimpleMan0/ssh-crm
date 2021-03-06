package cn.itcast.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.LinkMan;
import cn.itcast.service.LinkManService;
import cn.itcast.vo.PageBean;
@Service("linkManService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class LinkManServiceImpl implements LinkManService {
	@Resource(name="linkManDao")
	private LinkManDao lmd;
	
	
	//分页方法
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1.调用dao查询总记录数
		Integer totalCount = lmd.getTotalCount(dc);
		//2.创建PageBean
		PageBean pageBean=new PageBean(currentPage, pageSize, totalCount);
		//3.调用dao查询分页列表数据
		List<LinkMan> list=lmd.getPageList(dc,pageBean.start(),pageBean.getPageSize());
		//4.列表数据放入PageBean中
		pageBean.setList(list);
		return pageBean;
	}	
	@Override
	//保存联系人
	public void save(LinkMan linkMan) {
		lmd.saveOrUpdate(linkMan);
	}
	public void setLmd(LinkManDao lmd) {
		this.lmd = lmd;
	}
	@Override
	//根据id获得联系人
	public LinkMan getById(Long lkm_id) {
		return lmd.getById(lkm_id);
	}
	@Override
	//删除联系人
	public void delete(LinkMan lk) {
		lmd.delete(lk);
	}

}
