package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum>{

	/*
	 * 显示版块的主题列表
	 */
	public String list() throws Exception{
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	} 
	/*
	 * 显示单个版块（主题列表）
	 */
	public String show() throws Exception{
		
		//准备数据，指定的版块
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		
		//准备数据，指定版块的主题列表
		List<Topic> topicList = topicService.findByForum(forum);
		ActionContext.getContext().put("topicList", topicList);
		return "show";
	} 
}
