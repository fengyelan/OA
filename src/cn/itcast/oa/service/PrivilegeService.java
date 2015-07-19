package cn.itcast.oa.service;

import java.util.Collection;
import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege> {

	List<Privilege> findTopList();

	/*
	 * 查询所有权限的url，要求是不重复
	 */
	Collection<String> getAllPrivilegeUrls();

}
