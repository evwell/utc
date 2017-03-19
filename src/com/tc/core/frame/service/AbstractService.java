package com.tc.core.frame.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.dao.Dao;
import com.tc.core.frame.vo.VO;
import com.tc.core.frame.web.Page;

import static com.tc.core.util.CommonUtil.*;

public abstract class AbstractService<E extends VO> implements Service<E> {
	
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	protected Dao<E> dao;
	
	private String allActiveQueryName="allActiveQuery";
	
	/**
	 * 为E对应的实例类型
	 */
	private Class<?> entityClass;

	/**
	 * 获取E实例类的类型
	 */
	public AbstractService() {
		logger.debug("DAO Constrator begin.....");
		Class<?> c = this.getClass();
		Type t = c.getGenericSuperclass();					
		logger.debug("this.getClass().getGenericSuperclass()=" + t);
		if (t instanceof ParameterizedType) {
			this.entityClass = (Class<?>) ((ParameterizedType) t).getActualTypeArguments()[0];
		}
		logger.debug("DAO Constrator end! entityClass=" + this.entityClass);
	}

	public Dao<E> getDao() {
		return dao;
	}

	@Override
	public List<E> findByNamedQuery(String queryName) throws BusinessException {
		return dao.findByNamedQuery(queryName);
	}

	public void setDao(Dao<E> dao) {
		this.dao = dao;
	}

	@Override
	public void save(E e) throws BusinessException {
		dao.save(e);
	}

	@Override
	public void saveOrUpdate(Collection<E> es) throws BusinessException {
		dao.saveOrUpdate(es);
	}

	@Override
	public void saveOrUpdate(E e) throws BusinessException {
		dao.saveOrUpdate(e);
	}

	@Override
	public void update(E e) throws BusinessException {
		dao.update(e);
	}

	@Override
	public void delete(E e) throws BusinessException {
		dao.delete(e);
	}

	@Override
	public void delete(Collection<E> es) throws BusinessException {
		dao.delete(es);
	}

	@Override
	public E get(Serializable id) throws BusinessException {
		return (E) dao.get(id);
	}
	
	@Override
	public E load(Serializable id) throws BusinessException {
		return (E) dao.load(id);
	}

	@Override
	public List<E> loadAllActive() throws BusinessException {
		logger.debug(log4jString("loadAllActive"));
		return dao.findByNamedQuery(allActiveQueryName);
	}

	@Override
	public List<E> loadAll() throws BusinessException {
		logger.debug(log4jString("loadAll"));
		return dao.loadAll();
	}
	
	public List<E> find(String hql, Object... values) throws BusinessException{
		logger.debug(log4jString(hql));
		return dao.find(hql, values);
	}
	
	public List<E> findByValueBean(String hql, E e) throws BusinessException{
		logger.debug(log4jString(hql));
		return dao.findByValueBean(hql, e);
	}

	@Override
	public List<E> findByNamedQuery(String queryName, Object... values) throws BusinessException {
		logger.debug(log4jString(queryName));
		return dao.findByNamedQuery(queryName, values);
	}

	@Override
	public List<E> findByCriteria(DetachedCriteria criteria) throws BusinessException {
		return dao.findByCriteria(criteria);
	}

	@Override
	public Page<E> findByCriteria(DetachedCriteria criteria, Integer firstResult, Integer maxResults) throws BusinessException {
		return dao.findByCriteria(criteria, firstResult, maxResults);
	}

	@Override
	public List<E> find(E e) throws BusinessException {
		return this.findByCriteria(buildCommonDetachedCriteriaRecursion(DetachedCriteria.forClass(e.getClass()),e));
	}

	public Page<E> find(E e, Integer firstResult, Integer maxResults) throws BusinessException{
		return this.findByCriteria(buildCommonDetachedCriteriaRecursion(DetachedCriteria.forClass(e.getClass()),e),firstResult,maxResults);
	}
}
