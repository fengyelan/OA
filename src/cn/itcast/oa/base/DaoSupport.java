package cn.itcast.oa.base;

import java.util.List;

import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;

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
	
	/*
	 * 
	 * 查询分页信息
	 * HQL:用于进行查询的HQL语句
	 * paras：参数列表，和HQL里面的参数列表一一对应
	 */
	PageBean getPageBean(int currentPage, int pageSize, String HQL,List<Object> paras);
}
