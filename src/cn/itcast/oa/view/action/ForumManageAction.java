package cn.itcast.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ForumManageAction extends ActionSupport {

	
	public String list() throws Exception {
		
		return "list";
	}

	public String delete() throws Exception {
		
		return "list";
	}

	public String addUI() throws Exception {
	
		return "saveUI";
	}
	public String add() throws Exception{
		
		return "toList";
	}
	public String editUI() throws Exception {
		
		return "saveUI";
	}
	public String edit() throws Exception{
		
		return "toList";
	}
	public String moveUp() throws Exception{
		
		return "toList";
	}
	public String moveDown() throws Exception{
		
		return "toList";
	}
	
}
