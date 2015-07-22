package cn.itcast.oa.view.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {

	private Long topicId;
	
	
	/*
	 * 发表新帖界面
	 */
	public String addUI() throws Exception{
		//准备数据topic
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		
		return "addUI";
	} 
	
	/*
	 * 回复帖子
	 */
	public String add() throws Exception{
		
		//封装
		model.setTopic(topicService.getById(topicId));		
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		Timestamp tt = new Timestamp(System.currentTimeMillis());
		String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tt);
		model.setPostTime(Timestamp.valueOf(format));
		
		
		//保存
		replyService.save(model);
		
		return "toTopicShow"; //转到新回复所在主题的显示界面
	} 
	
	//--------
	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
