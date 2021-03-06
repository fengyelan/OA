package cn.itcast.oa.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

public class User implements java.io.Serializable{

	private Long id;	
	private Department department;
	private Set<Role> roles=new HashSet<Role>();
	private String loginName;
	private String password;
	private String name;
	private String gender;
	private String phoneNumber;
	private String email;
	private String description;
	
	
	/*
	 * 
	 * 判断本用户是否有指定URL的权限
	 */
	public boolean hasPrivilegeByUrl(String url){
		
		//超级管理员有所有的权限
		if(isAdmin()){
			return true;
		}
		
		//去掉后面的参数
		int pos = url.indexOf("?");
		if(pos>-1){
			url = url.substring(0,pos);
		}
		
		//去掉UI后缀
		if(url.endsWith("UI")){
			url = url.substring(0,url.length()-2);
		}
		
		//如果本URL不需要控制，则登录哟过户就可以使用
		Collection<String> allPrivilegeUrls = (Collection<String>)ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		System.out.println("<<<<<<<<<<<<<<<allPrivilegeUrls"+allPrivilegeUrls.size());
		if(allPrivilegeUrls.contains(url)){
			return true; 
		}else{
			//普通用户要判断是否含有这个权限
			for(Role role:roles){
				for(Privilege priv:role.getPrivileges()){
					if(priv.getName().equals(url)){
						return true;
					}
				}
			}
			return false;
		}
	}
	
	/*
	 * 
	 * 判断本用户是否有指定名称的权限
	 */
	public boolean hasPrivilegeByName(String name){
		
		//超级管理员有所有的权限
		if(isAdmin()){
			return true;
		}
		
		//普通用户要判断是否含有这个权限
		for(Role role:roles){
			for(Privilege priv:role.getPrivileges()){
				if(priv.getName().equals(name)){
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * 
	 * 判断是否是超级管理员
	 */
	public boolean isAdmin(){
		return "admin".equals(loginName);
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
