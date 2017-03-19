package com.tc.core.frame.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.vo.VO;
import com.tc.core.frame.web.Page;

/**
 * 
 * 
 * @action 一个公共的Hibernate通用dao实现类<br>
 *         数据库访问层,每一个实现类都应该来继承该类<br>
 *         不应该重写里面的方法,需要相应的方法,直接到数据访问层每个类对应的接口中添加
 */
public abstract class AbstractDao<E extends VO> extends HibernateDaoSupport
		implements Dao<E> {
	
	private Logger logger = Logger.getLogger(this.getClass());

	public String log4jString(String str){
		return com.tc.core.util.CommonUtil.log4jString(str);
	}
	
	/**
	 * 为E对应的实例类型
	 */
	private Class<?> entityClass;

	/**
	 * 获取E实例类的类型
	 */
	public AbstractDao() {
		logger.debug("DAO Constrator begin.....");
		Class<?> c = this.getClass();
		Type t = c.getGenericSuperclass();					
		logger.debug("this.getClass().getGenericSuperclass()=" + t);
		if (t instanceof ParameterizedType) {
			this.entityClass = (Class<?>) ((ParameterizedType) t).getActualTypeArguments()[0];
		}
		logger.debug("DAO Constrator end! entityClass=" + this.entityClass);
	}

	@SuppressWarnings("unchecked")
	public E get(Serializable id) throws BusinessException {
		logger.debug(id);
		return (E) this.getHibernateTemplate().get(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public E get(Serializable id, LockMode lock) throws BusinessException {
		E entity = (E) this.getHibernateTemplate().get(this.entityClass, id, lock);
		if (entity != null) {
			this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
		}
		return entity;
	}

	public Object getStackValue(DetachedCriteria criteria, String propertyName, String value) throws BusinessException {
		switch (Stack.valueOf(value)) {
		case MAX:
			criteria.setProjection(Projections.max(propertyName));
			break;
		case MIN:
			criteria.setProjection(Projections.min(propertyName));
			break;
		case AVG:
			criteria.setProjection(Projections.avg(propertyName));
			break;
		default:
			criteria.setProjection(Projections.sum(propertyName));
			break;
		}
		return this.getHibernateTemplate().findByCriteria(criteria, 0, 1).get(0);
	}

	public Long getRowCount(DetachedCriteria criteria) throws BusinessException {
		Long count = (Long) this.getHibernateTemplate().findByCriteria(criteria.setProjection(Projections.rowCount()), 0, 1).get(0);
		logger.debug("Total Rows : " + count);
		return count;
	}

	@SuppressWarnings("unchecked")
	public E load(Serializable id) throws BusinessException {
		return (E) this.getHibernateTemplate().load(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public E load(Serializable id, LockMode lock) throws BusinessException {
		E entity = (E) this.getHibernateTemplate().load(this.entityClass, id, lock);
		if (entity != null) {
			this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<E> loadAll() throws BusinessException {
		return (List<E>) this.getHibernateTemplate().loadAll(entityClass);
	}

	@SuppressWarnings("unchecked")
	public List<E> find(String hql) throws BusinessException {
		return (List<E>) this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	public List<E> find(String hql, Object... values) throws BusinessException {
		return (List<E>) this.getHibernateTemplate().find(hql, values);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findByValueBean(String hql, E e) throws BusinessException {
		return (List<E>) this.getHibernateTemplate().findByValueBean(hql, e);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByNamedQuery(String queryName, Object... values) throws BusinessException {
		return (List<E>) this.getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByNamedQuery(String queryName) throws BusinessException {
		return (List<E>) this.getHibernateTemplate().findByNamedQuery(queryName);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByNamedQueryAndNamedParam(String queryName, Map<String, Object> params) throws BusinessException {
		return (List<E>) this.getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
				(String[]) params.keySet().toArray(), params.values().toArray());
	}

	@SuppressWarnings("unchecked")
	public List<E> findByNamedParam(String queryName, Map<String, Object> params) throws BusinessException {
		return (List<E>) this.getHibernateTemplate().findByNamedParam(queryName, (String[]) params.keySet().toArray(),
				params.values().toArray());
	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(DetachedCriteria criteria) throws BusinessException {
		return (List<E>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public Page<E> findByCriteria(DetachedCriteria criteria, Integer firstResult, Integer maxResults) throws BusinessException {
		List<E> list = (List<E>) this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
		return new Page<E>(this.getRowCount(criteria),  list);
	}

	public void save(E entity) throws BusinessException {
		this.getHibernateTemplate().save(entity);
	}

	public void saveOrUpdate(E entity) throws BusinessException {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdate(Collection<E> entitys) throws BusinessException {
		try{
			for(E e : entitys){
				this.getHibernateTemplate().saveOrUpdate(e);
			}
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException(Dao.ERROR_MSG);
		}
	}

	public void delete(E entity) throws BusinessException {
		this.getHibernateTemplate().delete(entity);
	}

	public void delete(E entity, LockMode lock) throws BusinessException {
		this.getHibernateTemplate().delete(entity, lock);
		this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
	}

	public void delete(Collection<E> entitys) throws BusinessException {
		this.getHibernateTemplate().deleteAll(entitys);
	}

	public void update(E entity) throws BusinessException {
		this.getHibernateTemplate().update(entity);
	}

	public void update(E entity, LockMode lock) throws BusinessException {
		this.getHibernateTemplate().update(entity, lock);
		this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
	}

	
	public void update(String hql, Object[] params) throws BusinessException {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		
		query.executeUpdate();
		tx.commit();
		session.close();
	}

	public Integer bulkUpdate(String hql) throws BusinessException {
		return this.getHibernateTemplate().bulkUpdate(hql);
	}

	public Integer bulkUpdate(String hql, Object... values) throws BusinessException {
		return this.getHibernateTemplate().bulkUpdate(hql, values);
	}

	public void flush() throws BusinessException {
		this.getHibernateTemplate().flush();
	}

	public void lock(E entity, LockMode lock) throws BusinessException {
		this.getHibernateTemplate().lock(entity, lock);
	}

	public DetachedCriteria createDetachedCriteria() throws BusinessException {
		return DetachedCriteria.forClass(this.entityClass);
	}

	public DetachedCriteria createDetachedCriteria(Class<? extends Serializable> c) throws BusinessException {
		return DetachedCriteria.forClass(c);
	}

	public Criteria createCriteria() throws BusinessException {
		return this.createDetachedCriteria().getExecutableCriteria(this.currentSession());
	}

}
