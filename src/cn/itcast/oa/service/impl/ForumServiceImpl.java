package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.service.ForumService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements ForumService {

	
	@Override
	public void save(Forum forum) {
		//保存
		super.save(forum);
		//设置positin的值
		forum.setPosition(forum.getId().intValue());
	}





	@Override
	public List<Forum> findAll() {
		return getSession().createQuery("FROM Forum f ORDER BY f.position").list();
	}
	
	



	public void moveDown(Long id) {
		
		//找出相关的Forum
		Forum forum = getById(id); //当前要移动的forum
		Forum other =(Forum)getSession().createQuery(
			"FROM Forum f WHERE f.position>? ORDER BY f.position")
			.setParameter(0, forum.getPosition())
			.setFirstResult(0)
			.setMaxResults(1)
			.uniqueResult();//找到下面的forum

		if(other == null){
			return;
		}
		
		//交换position的值
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		
		//更新到数据库中(可以不写，因为对象现在是持久化状态)
		getSession().update(forum);
		getSession().update(other);
		
		
	}

	public void moveUp(Long id) {
		//找出相关的Forum
		Forum forum = getById(id); //当前要移动的forum
		Forum other = (Forum)getSession().createQuery(
				"FROM Forum f WHERE f.position<? ORDER BY f.position DESC")
				.setParameter(0, forum.getPosition())
				.setFirstResult(0)
				.setMaxResults(1)
				.uniqueResult();//找到上面的forum
		
		if(other == null){
			return;
		}
		
		//交换position的值
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		
		//更新到数据库中(可以不写，因为对象现在是持久化状态)
		getSession().update(forum);
		getSession().update(other);
		
	}

}
