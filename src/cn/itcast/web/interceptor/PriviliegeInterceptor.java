package cn.itcast.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.domain.User;

public class PriviliegeInterceptor extends MethodFilterInterceptor{

	@Override
	//不校验登陆和注册方法
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User u = (User) session.get("user");
		System.err.println("sdf"+u);
		if(u!=null){
			System.err.println(u);
			return invocation.invoke();
		}else{
			return "toLogin";
		}
	}
	
}
