package cn.itcast.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	private Long departmentId;
	private Long[] roleIds;
	

	
	/*列表*/
	public String list() throws Exception{
		
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}
	
	/*删除*/
	public String delete() throws Exception{
		userService.delete(model.getId());
		return "toList";
	}
	
	/*添加页面*/
	public String addUI() throws Exception{
		//准备树状部门列表数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		//准备岗位列表数据roleLis
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		return "saveUI";
	}
	
	/*添加*/
	public String add() throws Exception{

		//封装到对象中，当model时实体类型的时候，也可以使用model，但是要设置未封装的属性
		//设置所属的部门
		model.setDepartment(departmentService.getById(departmentId));
		//设置关联岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		//设置默认密码
		String md5Digest = DigestUtils.md5Hex("1234");
		model.setPassword(md5Digest);
		
		//保存到数据库
		userService.save(model);
		return "toList";
	}
	/*修改页面*/
	public String editUI() throws Exception{
		//准备树状部门列表数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		//准备岗位列表数据roleLis
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		//准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if(user.getDepartment()!=null){
			departmentId = user.getDepartment().getId();
		}
		if(user.getRoles()!=null){
			roleIds = new Long[user.getRoles().size()];
			int index=0;
			for(Role role:user.getRoles()){
				roleIds[index++]=role.getId();				
			}
		}
		return "saveUI";
	}
	
	/*修改*/
	public String edit() throws Exception{
		//1.从数据中取出原对象
		User user = userService.getById(model.getId());
		//2.设置要修改的属性
		user.setDescription(model.getDescription());
		user.setName(model.getName());
		user.setEmail(model.getEmail());
		user.setGender(model.getGender());
		user.setLoginName(model.getLoginName());
		user.setPhoneNumber(model.getPhoneNumber());
		//设置所属的部门
		user.setDepartment(departmentService.getById(departmentId));
		//设置关联岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		
		//3.更新到数据库
		userService.update(user);
		
		return "toList";
	}
	
	/*初始化密码1234*/
	public String initPassword() throws Exception{
		//1。从数据中取出原对象
		User user = userService.getById(model.getId());
		//2.设置要修改的属性
		String md5Digest = DigestUtils.md5Hex("1234");
		user.setPassword(md5Digest);
		
		//3.更新到数据库
		userService.update(user);
		
		return "toList";
	}
	/*登录页面*/
	public String loginUI() throws Exception{
		
		return "loginUI";
	}
	
	/*登录*/
	public String login() throws Exception{
		
		User user = userService.findByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		if(user == null){
			addFieldError("login", "用户名或者密码不正确!");
			return "loginUI";
		}else{
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
		
	}
	
	/*注销*/
	public String logout() throws Exception{
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}
