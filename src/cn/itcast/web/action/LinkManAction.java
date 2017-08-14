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
		//�������߲�ѯ����
		DetachedCriteria dc=DetachedCriteria.forClass(LinkMan.class);
		//�ж��Ƿ�Ϊ�ղ���װ
		if(StringUtils.isNotBlank(linkMan.getLkm_name())){
			dc.add(Restrictions.like("lkm_name","%"+linkMan.getLkm_name().trim()+"%"));
		}
		if(linkMan.getCustomer()!=null&&linkMan.getCustomer().getCust_id()!=null){
			dc.add(Restrictions.eq("customer.cust_id",linkMan.getCustomer().getCust_id()));
		}
		//1.���÷�ҳservice��ѯ��ҳ���ݣ�PageBean��
		PageBean pageBean=lms.getPageBean(dc,currentPage,pageSize);
		//2.�����ݴ���request��ת����ҳ��
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
	//��ϵ��ɾ��
	public String delete() throws Exception {
		//����service����id���customer����
		LinkMan lk=lms.getById(linkMan.getLkm_id());
		//ִ��ɾ������
		lms.delete(lk);
		//�ض��򵽿ͻ��б�
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
