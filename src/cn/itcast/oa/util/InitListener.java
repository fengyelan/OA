package cn.itcast.oa.util;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;


public class InitListener implements ServletContextListener {

	
	
	

	public void contextInitialized(ServletContextEvent sce) {

		//获取容器与相关的servlet对象
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());		
		PrivilegeService privilegeService = (PrivilegeService)ac.getBean("privilegeServiceImpl");
		
		// 准备数据 topPrivilegeList
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		sce.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("======================已经准备数据topPrivilegeList=================");

		//准备数据
		Collection<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		sce.getServletContext().setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		System.out.println("======================已经准备数据allPrivilegeUrls=================");
		
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		

	}

}
