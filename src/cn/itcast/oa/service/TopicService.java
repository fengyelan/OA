package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

public interface TopicService extends DaoSupport<Topic> {

	/*
	 * 
	 * 查询指定版块的所有主题，排序，所有置顶帖在最上面，
	 * 并且按照最后更新时间排序，让新状态在最上面
	 */
	List<Topic> findByForum(Forum forum);

}
