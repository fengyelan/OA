package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.ForumService;
import cn.itcast.oa.service.PrivilegeService;
import cn.itcast.oa.service.ReplyService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.TopicService;
import cn.itcast.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	//========modelDriven的支持=========
	protected T model;
	
	public BaseAction(){
		
		try {
			//通过反射得到model的真实类型，
			ParameterizedType pt=(ParameterizedType)this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>)pt.getActualTypeArguments()[0];
			//通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	public T getModel() {
		
		return model;
	}
	
	/*
	 * 
	 * 获取当前用户
	 */
	public User getCurrentUser(){
		return (User)ActionContext.getContext().getSession().get("user");
	}
	
	//==========service的实例的声明===========
	@Resource
	protected RoleService roleService;
	
	@Resource
	protected DepartmentService departmentService;
	
	@Resource
	protected UserService userService;
	
	@Resource
	protected PrivilegeService privilegeService;
	
	@Resource
	protected ForumService forumService;
	
	@Resource
	protected TopicService topicService;
	
	@Resource
	protected ReplyService replyService;

}
