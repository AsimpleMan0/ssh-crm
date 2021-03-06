package cn.itcast.web.action;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.SaleVisit;
import cn.itcast.domain.User;
import cn.itcast.service.SaleVisitService;
import cn.itcast.vo.PageBean;
@Controller("saleVisitAction")
@Scope("prototype")
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{
	private SaleVisit saleVisit=new SaleVisit();
	@Resource(name="saleVisitService")
	private SaleVisitService svs;
	//客户拜访记录添加
	public String add() throws Exception {
		//获得当前用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		saleVisit.setUser(user);
		System.err.println(saleVisit.getVisit_id());
		svs.save(saleVisit);
		return "toList";
	}
	//客户拜访记录列表
	private Integer currentPage;
	private Integer pageSize;
	public String list() throws Exception {
		//创建离线查询对象
		DetachedCriteria dc=DetachedCriteria.forClass(SaleVisit.class);
		//判断是否为空并封装
		if(saleVisit.getCustomer()!=null&&saleVisit.getCustomer().getCust_id()!=null){
			dc.add(Restrictions.eq("customer.cust_id",saleVisit.getCustomer().getCust_id()));
		}
		//1.调用分页service查询分页数据（PageBean）
		PageBean pageBean=svs.getPageBean(dc,currentPage,pageSize);
		//2.将数据存入request域，转发到页面
		ActionContext.getContext().put("pageBean",pageBean);
		return "list";
	}
	
	public String edit() throws Exception {
		SaleVisit sv=svs.getById(saleVisit.getVisit_id());
		System.err.println("fdsafasfasdfasdfasdfasdf");
		ActionContext.getContext().put("saleVisit",sv);
		System.err.println("防守打法");
		return "edit";
	}
	//联系人删除
	public String delete() throws Exception {
		//调用service跟据id获得customer对象
		SaleVisit sv=svs.getById(saleVisit.getVisit_id());
		//执行删除操作
		svs.delete(sv);
		//重定向到客户列表
		return "delete";
	}
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}


	public void setSvs(SaleVisitService svs) {
		this.svs = svs;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
