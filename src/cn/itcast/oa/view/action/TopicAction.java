package cn.itcast.oa.view.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {
	
	private Long forumId;

	protected int currentPage=1;
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	protected int pageSize = 10;


	/*
	 * 显示单个主题（主题和回帖列表）
	 */
	public String show() throws Exception{
		
		//准备数据，topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		
//		//准备数据 replyList
//		List<Reply> replyList = replyService.findByTopic(topic);
//		ActionContext.getContext().put("replyList", replyList);
		
		//准备数据,分页数据
		String hql="FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC";
		List<Object> list = new ArrayList<Object>();
		list.add(topic);
		PageBean pageBean = replyService.getPageBean(currentPage,pageSize,hql,list);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	} 
	
	/*
	 * 发表新主题页面
	 */
	public String addUI() throws Exception{
		//准备数据
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	} 
	
	/*
	 * 发表新主题
	 */
	public String add() throws Exception{
		
		//封装

		model.setForum(forumService.getById(forumId));
		
		//-->当前直接获取的类型
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		Timestamp tt=new Timestamp(System.currentTimeMillis());
		String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tt);
		model.setPostTime(Timestamp.valueOf(format));
			
		//保存
		topicService.save(model);
		
		return "toShow"; //转到主题的显示界面
	} 
	
	//----
	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
}
