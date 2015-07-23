package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.TopicService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements
		TopicService {

	//排序
	//所有置顶帖在最上面，
	//并且按照最后更新的时间排序，让最新状态在最上面
	@Deprecated
	public List<Topic> findByForum(Forum forum) {
		return getSession().createQuery(//
				"FROM Topic t WHERE t.forum = ? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.list();
		
		
	}
	@Override
	public void save(Topic topic) {
		
		//1.设置并且保存
		topic.setType(Topic.TYPE_NORMAL);
		topic.setLastReply(null);
		topic.setReplyCount(0);
		topic.setLastUpdateTime(topic.getPostTime());
		
		getSession().save(topic);
		
		//2.维护forum相关的特殊的属性
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount()+1); // 主题数量
		forum.setArticleCount(forum.getArticleCount()+1);//文章数量（主题数加回复数）
		forum.setLastTopic(topic);//最后发表的主题
		
		getSession().update(forum);
	}
	
	@Deprecated
	public PageBean getPageBeanByForum(int currentPage, int pageSize,
			Forum forum) {
		//查询列表
		List list = getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY t.postTime")//
				.setParameter(0, forum)
				.setFirstResult((currentPage-1)*pageSize)
				.setMaxResults(pageSize)
				.list();
		
		//查询总数量
		Long count = (Long)getSession().createQuery("SELECT COUNT(*) FROM Topic t WHERE t.forum=?")//
		.setParameter(0, forum).uniqueResult();
		return new PageBean(currentPage, pageSize,count.intValue() , list);
	}
	

	

	
}
