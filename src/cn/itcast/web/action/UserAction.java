package cn.itcast.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	
	@Resource(name="userService")
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//�û���¼
	public String login() throws Exception {
		//����serviceִ�е�¼�߼�
		User u = userService.getUserByCode(user);
		//�����ص�User�������session��
		ActionContext.getContext().getSession().put("user",u);
		//�ض�����Ŀ��ҳ
		return "toHome";
	}
	//�û�ע��
	public String register() throws Exception {
		//����serviceִ��ע��
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			ActionContext.getContext().put("error",e.getMessage());
			return "register";
		}
		//�ض���ע��ҳ��
		return "toLogin";
	}
	@Override
	public User getModel() {
		return user;
	}
	
}