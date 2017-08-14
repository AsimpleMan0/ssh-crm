package cn.itcast.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import cn.itcast.service.LinkManService;
import cn.itcast.vo.PageBean;
@Controller("linkManAction")
@Scope("prototype")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	private LinkMan linkMan=new LinkMan();
	@Resource(name="linkManService")
	private LinkManService lms;
	
	private Integer currentPage;
	private Integer pageSize;
	public String list() throws Exception {
		//创建离线查询对象
		DetachedCriteria dc=DetachedCriteria.forClass(LinkMan.class);
		//判断是否为空并封装
		if(StringUtils.isNotBlank(linkMan.getLkm_name())){
			dc.add(Restrictions.like("lkm_name","%"+linkMan.getLkm_name().trim()+"%"));
		}
		if(linkMan.getCustomer()!=null&&linkMan.getCustomer().getCust_id()!=null){
			dc.add(Restrictions.eq("customer.cust_id",linkMan.getCustomer().getCust_id()));
		}
		//1.调用分页service查询分页数据（PageBean）
		PageBean pageBean=lms.getPageBean(dc,currentPage,pageSize);
		//2.将数据存入request域，转发到页面
		ActionContext.getContext().put("pageBean",pageBean);
		return "list";
	}
	public String add() throws Exception {
		lms.save(linkMan);
		return "toList";
	}
	public String edit() throws Exception {
		LinkMan lkm=lms.getById(linkMan.getLkm_id());
		ActionContext.getContext().put("linkMan",lkm);
		return "edit";
	}
	//联系人删除
	public String delete() throws Exception {
		//调用service跟据id获得customer对象
		LinkMan lk=lms.getById(linkMan.getLkm_id());
		//执行删除操作
		lms.delete(lk);
		//重定向到客户列表
		return "delete";
	}		
	@Override
	public LinkMan getModel() {
		return linkMan;
	}

	public void setLms(LinkManService lms) {
		this.lms = lms;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
