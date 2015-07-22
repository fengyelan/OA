package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ReplyService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements
		ReplyService {

	
	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
				.setParameter(0, topic)//
				.list();
	}
	
	@Override
	public void save(Reply reply) {
		//1.保存reply
		getSession().save(reply);
		
		//2.维护相关的topic的信息和forum信息
		Topic topic = reply.getTopic();
		System.out.println("topic.getForum():"+topic.getForum());
		Forum forum = topic.getForum();
		
		forum.setArticleCount(forum.getArticleCount()+1); // 论坛文章数量
		
		topic.setReplyCount(topic.getReplyCount()+1);//回复数量
		topic.setLastReply(reply);//最后发表的回复
		topic.setLastUpdateTime(reply.getPostTime());//最后更新的时间
				
		getSession().update(topic);
		getSession().update(forum);
		
	}

	
}
