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
	//�ϴ����ļ����Զ���װ��file����
	//�ں�̨�ṩһ����ǰ̨file���name��ͬ������
	private File photo;
	//���ύ��������Ϲ̶���׺FileName���ļ����ƻ��Զ���װ��������text/html
	private String photoFileName;
	//���ύ��������Ϲ̶���׺ContentType,�ļ�mime���ͻ��Զ���װ������text/html��
	private String photoContentType;
	

	private Integer currentPage;
	private Integer pageSize;
	public String list() throws Exception {
		System.out.println(select);
		//�������߲�ѯ����
		DetachedCriteria dc=DetachedCriteria.forClass(Customer.class);
		//�ж��Ƿ�Ϊ�ղ���װ
		if(StringUtils.isNotBlank(customer.getCust_name())){
			dc.add(Restrictions.like("cust_name","%"+customer.getCust_name().trim()+"%"));
		}
		//1.���÷�ҳservice��ѯ��ҳ���ݣ�PageBean��
		PageBean pageBean=cs.getPageBean(dc,currentPage,pageSize);
		//2.�����ݴ���request��ת����ҳ��
		ActionContext.getContext().put("pageBean",pageBean);
		return "list";
	}
	//�û�����
	public String add() throws Exception {
		if(photo!=null){
			System.out.println(photoFileName);
			System.out.println(photoContentType);
			//���ϴ����ļ����浽ָ��λ��
			photo.renameTo(new File("E:/upload/haha.jpg"));
		}
		//-------------------------
		//����service����customer
		cs.save(customer);
		//�ض��򵽿ͻ��б�
		return "toList";
	}
	//�û��޸�
	public String toEdit() throws Exception {
		//����service����id���customer����
		Customer c=cs.getById(customer.getCust_id());
		//�����ݷ�������
		ActionContext.getContext().put("customer",c);
		//�ض��򵽿ͻ��б�
		return "edit";
	}
	//�û�ɾ��
	public String delete() throws Exception {
		//����service����id���customer����
		Customer c=cs.getById(customer.getCust_id());
		//ִ��ɾ������
		cs.delete(c);
		//�ض��򵽿ͻ��б�
		return "delete";
	}
	//ͳ�ƿͻ���ҵ
	public String industryCount() throws Exception {
		List<Object[]> list=cs.getIndustry();
		ActionContext.getContext().put("list",list);
		return "industry";
	}
	//ͳ�ƿͻ���Դ
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