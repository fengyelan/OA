package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.dao.DepartmentDao;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.DepartmentService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements DepartmentService {

	
	@Resource
	private SessionFactory sessionFactory;
	

	public List<Department> findChildrenList(Long parentId) {
		
		
		return sessionFactory.getCurrentSession().createQuery(//
		"FROM Department d WHERE d.parent.id=?")//
		.setParameter(0, parentId)//
		.list();
		
	}

	
	public List<Department> findTopList() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery(//
		"FROM Department d WHERE d.parent IS NULL")//
		.list();
	}

	
	

}
