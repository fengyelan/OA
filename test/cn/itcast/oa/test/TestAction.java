package cn.itcast.oa.test;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component("testAction")
@Scope("prototype")
public class TestAction extends ActionSupport {

	@Resource
	private TestService testService;
	@Override
	public String execute() throws Exception {
		System.out.println("TestAction");
		testService.saveTwoUsers();
		return "success";
	}

}
