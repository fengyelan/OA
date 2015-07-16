package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;

@Transactional
@SuppressWarnings("unchecked")
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz; 
	
	public DaoSupportImpl(){
		//利用反射技术得到T的真实类型
		ParameterizedType pt=(ParameterizedType)this.getClass().getGenericSuperclass();//获取当前new的对象的泛型的父类类型
		this.clazz=(Class<T>)pt.getActualTypeArguments()[0];//获取第一个类型参数的真实类型
		System.out.println("clazz----"+clazz);
		//System.out.println("clazz111111----"+pt.getActualTypeArguments().length);
	}
	/*
	 * 获取当前可以的session
	 * */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public void save(T entity) {
		// TODO Auto-generated method stub
		getSession().save(entity);
	}

	public void update(T entity) {
		// TODO Auto-generated method stub
		getSession().update(entity);
	}
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Object obj=getById(id);
		if(obj!=null){
			getSession().delete(obj);
		}
	}


	public List<T> findAll() {
		// TODO Auto-generated method stub
		return getSession().createQuery(//
				"FROM "+clazz.getSimpleName())//
				.list();
	}

	
	public T getById(Long id) {
		// TODO Auto-generated method stub
		if(id==null){
			return null;
		}else{
			return (T)getSession().get(clazz, id);
		}
	}

	
	public List<T> getByIds(Long[] ids) {
		// TODO Auto-generated method stub
		if(ids == null ||ids.length==0){
			return Collections.EMPTY_LIST;
		}else{
			return getSession().createQuery(//
					"FROM "+clazz.getSimpleName()+" WHERE id IN (:ids)")//
					.setParameterList("ids", ids)//
					.list();
		}
	}

	

	
}
