package cn.itcast.web.action;


import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;
import cn.itcast.vo.PageBean;
@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private Customer customer=new Customer();
	
	@Resource(name="customerService")
	private CustomerService cs;
	private String select;
	//上传的文件会自动封装到file对象
	//在后台提供一个与前台file组件name相同的属性
	private File photo;
	//在提交建名后加上固定后缀FileName，文件名称会自动封装到属性中text/html
	private String photoFileName;
	//在提交建名后加上固定后缀ContentType,文件mime类型会自动封装到属性text/html中
	private String photoContentType;
	

	private Integer currentPage;
	private Integer pageSize;
	public String list() throws Exception {
		System.out.println(select);
		//创建离线查询对象
		DetachedCriteria dc=DetachedCriteria.forClass(Customer.class);
		//判断是否为空并封装
		if(StringUtils.isNotBlank(customer.getCust_name())){
			dc.add(Restrictions.like("cust_name","%"+customer.getCust_name().trim()+"%"));
		}
		//1.调用分页service查询分页数据（PageBean）
		PageBean pageBean=cs.getPageBean(dc,currentPage,pageSize);
		//2.将数据存入request域，转发到页面
		ActionContext.getContext().put("pageBean",pageBean);
		return "list";
	}
	//用户添加
	public String add() throws Exception {
		if(photo!=null){
			System.out.println(photoFileName);
			System.out.println(photoContentType);
			//将上传的文件保存到指定位置
			photo.renameTo(new File("E:/upload/haha.jpg"));
		}
		//-------------------------
		//调用service保存customer
		cs.save(customer);
		//重定向到客户列表
		return "toList";
	}
	//用户修改
	public String toEdit() throws Exception {
		//调用service跟据id获得customer对象
		Customer c=cs.getById(customer.getCust_id());
		//将数据放入域中
		ActionContext.getContext().put("customer",c);
		//重定向到客户列表
		return "edit";
	}
	//用户删除
	public String delete() throws Exception {
		//调用service跟据id获得customer对象
		Customer c=cs.getById(customer.getCust_id());
		//执行删除操作
		cs.delete(c);
		//重定向到客户列表
		return "delete";
	}
	//统计客户行业
	public String industryCount() throws Exception {
		List<Object[]> list=cs.getIndustry();
		ActionContext.getContext().put("list",list);
		return "industry";
	}
	//统计客户来源
	public String sourceCount() throws Exception {
		List<Object[]> list=cs.getSource();
		ActionContext.getContext().put("list",list);
		return "source";
	}	
	@Override
	public Customer getModel() {
		return customer;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	
	public void setCs(CustomerService cs) {
		this.cs = cs;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	
}
