package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{
	
	
	
	
	private Long parentId;
	

	/*列表*/
	public String list() throws Exception {
		
		List<Department> departmentList=null;
		
		if(parentId == null){
			departmentList=departmentService.findTopList();
		}else{
			departmentList=departmentService.findChildrenList(parentId);
			Department parent=departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		
		ActionContext.getContext().put("departmentList", departmentList);

		return "list";
	}

	/*删除*/
	public String delete() throws Exception {

		departmentService.delete(model.getId());
		return "toList";
	}
	
	/*添加页面*/
	public String addUI() throws Exception {

		//准备树状部门列表数据
		//List<Department> departmentList=departmentService.findAll();
		//ActionContext.getContext().put("departmentList", departmentList);
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}
	
	/*添加*/
	public String add() throws Exception {

		Department parent=departmentService.getById(parentId);
		model.setParent(parent);
		departmentService.save(model);
		return "toList";
	}
	
	/*修改页面*/
	public String editUI() throws Exception {

		//准备数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		Department department=departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if(department.getParent()!=null){
			parentId=department.getParent().getId();
		}
		return "saveUI";
	}
	
	/*修改*/
	public String edit() throws Exception {

		Department department = departmentService.getById(model.getId());
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));
		departmentService.update(department);
		return "toList";
		
		
	}
	

	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
