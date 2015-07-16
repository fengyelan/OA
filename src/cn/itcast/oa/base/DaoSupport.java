package cn.itcast.oa.base;

import java.util.List;

public interface DaoSupport<T> {

	/*
	 * 
	 * 保存实体
	 * 
	 * */
	void save(T entity);
	/* 
	 * 删除实体
     */
	void delete(Long id);
	/* 
	 * 更新实体
     */
	
	void update(T entity);
	/* 
	 * 按照id查询
     */
	T getById(Long id);
	/* 
	 * 查询所有
     */
	List<T> findAll();
	/* 
	 * 按照id查询
     */
	List<T> getByIds(Long[] ids);
}
