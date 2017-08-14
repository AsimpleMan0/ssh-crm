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
	//�ͻ��ݷü�¼����
	public String add() throws Exception {
		//��õ�ǰ�û�
		User user = (User) ActionContext.getContext().getSession().get("user");
		saleVisit.setUser(user);
		System.err.println(saleVisit.getVisit_id());
		svs.save(saleVisit);
		return "toList";
	}
	//�ͻ��ݷü�¼�б�
	private Integer currentPage;
	private Integer pageSize;
	public String list() throws Exception {
		//�������߲�ѯ����
		DetachedCriteria dc=DetachedCriteria.forClass(SaleVisit.class);
		//�ж��Ƿ�Ϊ�ղ���װ
		if(saleVisit.getCustomer()!=null&&saleVisit.getCustomer().getCust_id()!=null){
			dc.add(Restrictions.eq("customer.cust_id",saleVisit.getCustomer().getCust_id()));
		}
		//1.���÷�ҳservice��ѯ��ҳ���ݣ�PageBean��
		PageBean pageBean=svs.getPageBean(dc,currentPage,pageSize);
		//2.�����ݴ���request��ת����ҳ��
		ActionContext.getContext().put("pageBean",pageBean);
		return "list";
	}
	
	public String edit() throws Exception {
		SaleVisit sv=svs.getById(saleVisit.getVisit_id());
		System.err.println("fdsafasfasdfasdfasdfasdf");
		ActionContext.getContext().put("saleVisit",sv);
		System.err.println("���ش�");
		return "edit";
	}
	//��ϵ��ɾ��
	public String delete() throws Exception {
		//����service����id���customer����
		SaleVisit sv=svs.getById(saleVisit.getVisit_id());
		//ִ��ɾ������
		svs.delete(sv);
		//�ض��򵽿ͻ��б�
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