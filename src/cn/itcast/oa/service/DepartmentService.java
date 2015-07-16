package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department> {

	List<Department> findTopList();

	List<Department> findChildrenList(Long parentId);

	

}
