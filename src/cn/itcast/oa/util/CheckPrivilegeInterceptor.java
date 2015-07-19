package cn.itcast.oa.util;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	

	public String intercept(ActionInvocation invocation) throws Exception {

		
		//获取信息
		User user = (User)ActionContext.getContext().getSession().get("user");
		String nameSpace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privUrl = nameSpace+actionName;
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<privUrl"+privUrl);
		
		//如果没有登录，就要转到登录页面
		//如果已经登录，就判断权限
		if(user == null){
			if(privUrl.startsWith("/user_login")){ //   /user_login /user_loginUI
				//如果是在去登录的路上就放行
				return invocation.invoke();
			}else{
				//如果不是去登录，就转到登录界面
				return "loginUI";
			}
			
		}else{
			
			//如果有权限，就放行
			//如果没有权限，就要转到提示页面
			if(user.hasPrivilegeByUrl(privUrl)||privUrl.startsWith("/home")){
				return invocation.invoke();
			}else{
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<"+user.getName());
				ActionContext.getContext().getSession().remove("user");
				return "noPrivilege";
			}
			
		}
		
		
		
	}

}
