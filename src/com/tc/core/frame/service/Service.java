package com.tc.core.frame.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.vo.VO;
import com.tc.core.frame.web.Page;

public interface Service<E extends VO> {

	void save(E e) throws BusinessException;

	void saveOrUpdate(E e) throws BusinessException;

	void saveOrUpdate(Collection<E> es) throws BusinessException;

	void update(E e) throws BusinessException;

	void delete(E e) throws BusinessException;

	void delete(Collection<E> es) throws BusinessException;

	E get(Serializable id) throws BusinessException;
	
	E load(Serializable id) throws BusinessException;

	List<E> loadAllActive() throws BusinessException;

	List<E> loadAll() throws BusinessException;

	/**
	 * 简单属性等于值条件查询，支持深度对象条件
	 * @param e
	 * @return
	 * @throws BusinessException
	 */
	List<E> find(E e) throws BusinessException;
	
	/**
	 * 简单属性等于值条件查询，支持深度对象条件
	 * @param e
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws BusinessException
	 */
	Page<E> find(E e, Integer firstResult, Integer maxResults) throws BusinessException;
	
	List<E> find(String hql, Object... values) throws BusinessException;
	
	List<E> findByValueBean(String hql, E e) throws BusinessException;

	List<E> findByNamedQuery(String queryName) throws BusinessException;
	
	List<E> findByNamedQuery(String queryName, Object... values) throws BusinessException;
	
	/**
	 * 
	 * @param criteria
	 *            使用指定的检索标准来检索数
	 * @return 一个list集合
	 */
	List<E> findByCriteria(DetachedCriteria criteria) throws BusinessException;

	/**
	 * 
	 * @param criteria
	 *            使用指定的检索标准来分页检索数据
	 * @param firstResult
	 *            开始条数
	 * @param maxResults
	 *            返回记录数
	 * @return 一个list集合
	 */
	Page<E> findByCriteria(DetachedCriteria criteria, Integer firstResult, Integer maxResults) throws BusinessException;
}
