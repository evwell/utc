package com.tc.core.frame.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.vo.VO;
import com.tc.core.frame.web.Page;


/**
 * 
 * 
 * @action 一个通用的Hibernate的dao接口<br>
 *         数据访问层里面的每一个接口都应该继承这个接口<br>
 *         而不用重写里面的方法
 */
public interface Dao<E extends VO> {

	/**
	 * 
	 * 该枚举类型用于,getStackValue方法中的Stack枚举
	 * 
	 */
	enum Stack {
		MAX, MIN, AVG, SUM;
	}

	static final String ERROR_MSG = "数据库操作异常！";
	
	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @return 一个实体对象
	 */
	E get(Serializable id) throws BusinessException;

	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @param lock
	 *            加锁实体
	 * @return 一个实体对象
	 */
	E get(Serializable id, LockMode lock) throws BusinessException;
	
	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @return 一个实体对象
	 */
	E load(Serializable id) throws BusinessException;

	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @param lock
	 *            加锁实体
	 * @return 一个实体对象
	 */
	E load(Serializable id, LockMode lock) throws BusinessException;

	/**
	 * 使用数据库函数
	 * 
	 * @param criteria
	 *            一个DetacherCriteria对象
	 * @param propertyName
	 *            实体类属性名
	 * @param stackName
	 *            Stack枚举类型中的任意一个
	 * @return 一行一列数据库
	 */
	Object getStackValue(DetachedCriteria criteria, String propertyName, String stackName) throws BusinessException;

	/**
	 * 查询数据库对应的记录数
	 * 
	 * @param criteria
	 *            一个DetachedCriteria对象
	 * @return 记录数
	 */
	Long getRowCount(DetachedCriteria criteria) throws BusinessException;

	/**
	 * 
	 * @return 加裁所有对象
	 */
	List<E> loadAll() throws BusinessException;

	/**
	 * 
	 * @param entity
	 *            保存一个实体
	 * @throws BusinessException
	 *             抛出Exception异常
	 */
	void save(E entity) throws BusinessException;

	/**
	 * 
	 * @param entity
	 *            删除一个实体
	 * @throws BusinessException
	 *             抛出异常
	 */
	void delete(E entity) throws BusinessException;

	/**
	 * 
	 * @param entity
	 *            删除一个实体
	 * @param lock
	 *            加锁实体
	 * @throws BusinessException
	 *             抛出异常
	 */
	void delete(E entity, LockMode lock) throws BusinessException;

	/**
	 * 
	 * @param entitys
	 *            删除多个实体
	 * @throws BusinessException
	 *             抛出异常
	 */
	void delete(Collection<E> entitys) throws BusinessException;

	/**
	 * 
	 * @param entity
	 *            修改一个实体
	 * @throws BusinessException
	 *             抛出异常
	 */
	void update(E entity) throws BusinessException;

	/**
	 * 
	 * @param entity
	 *            修改一个实体
	 * @param lock
	 *            加锁实体
	 * @throws BusinessException
	 *             抛出异常
	 */
	void update(E entity, LockMode lock) throws BusinessException;
	
	/**
	 * 
	 * @param hql
	 * @param params
	 * @throws BusinessException
	 */
	void update(String hql, Object[] params) throws BusinessException;

	/**
	 * 
	 * @param entity
	 *            当实体在数据库不在在与之对应记录时,则保存实体,在在实体,则更新实体
	 * @throws BusinessException
	 *             抛出异常
	 */
	void saveOrUpdate(E entity) throws BusinessException;

	/**
	 * 
	 * @param entitys
	 *            保存多个实体
	 * @throws BusinessException
	 *             抛出异常
	 */
	void saveOrUpdate(Collection<E> entitys) throws BusinessException;

	/*---------------------------利用hql,sql对数据库进行操作--------------------------------*/

	/**
	 * 
	 * @param hql
	 *            使用hql语句进行数据库增删改操作
	 * @return 受影响行的记录数
	 */
	Integer bulkUpdate(String hql) throws BusinessException;

	/**
	 * 
	 * @param hql
	 *            使用hql语句进行数据库增删改操作
	 * @param values
	 *            hql语句参数
	 * @return 受影响行的记录数
	 */
	Integer bulkUpdate(String hql, Object... values) throws BusinessException;

	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @return 一个list集合
	 */
	List<E> find(String hql) throws BusinessException;
	
	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @param e
	 *            hql语句参数
	 * @return 一个list集合
	 */
	List<E> findByValueBean(String hql, E e) throws BusinessException;

	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @param values
	 *            hql语句参数
	 * @return 一个list集合
	 */
	List<E> find(String hql, Object... values) throws BusinessException;

	/**
	 * 
	 * @param queryName
	 *            使用命名的hql语句进行查询
	 * @return 一个list集合
	 */
	List<E> findByNamedQuery(String queryName) throws BusinessException;

	/**
	 * 
	 * @param queryName
	 *            使用带参数的命名hql语句进行查询
	 * @param values
	 *            参数集合
	 * @return 一个list集合
	 */
	List<E> findByNamedQuery(String queryName, Object... values) throws BusinessException;

	/**
	 * 
	 * @param queryName
	 *            使用带参数的命名hql语句进行查询
	 * @param params
	 *            参数集合<br>
	 *            Map的键为参数名称即paramName<br>
	 *            Map的值则为values
	 * @return 一个list集合
	 */
	List<E> findByNamedParam(String queryName, Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param queryName
	 *            使用带参数的命名hql语句进行查询
	 * @param params
	 *            参数集合<br>
	 *            Map的键为参数名称即paramName<br>
	 *            Map的值则为values
	 * @return 一个list集合
	 */
	List<E> findByNamedQueryAndNamedParam(String queryName, Map<String, Object> params) throws BusinessException;

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
	 * @return 一个分页对象
	 */
	Page<E> findByCriteria(DetachedCriteria criteria, Integer firstResult, Integer maxResults) throws BusinessException;

	/**
	 * 加锁指定的实体
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 * @param lock
	 *            加锁
	 */
	void lock(E entity, LockMode lock) throws BusinessException;

	/**
	 * 强制立即更新到数据库,否则需要事务提交后,才会提交到数据库
	 */
	void flush() throws BusinessException;

	/**
	 * 
	 * @return 根据SimpleDao泛型类型,创建一个与会话无关的检索对象
	 */
	DetachedCriteria createDetachedCriteria() throws BusinessException;

	/**
	 * 
	 * @param c
	 *            为一个实体类型
	 * @return 根据指定的类型创建一个与会话无关的检索对象
	 */
	DetachedCriteria createDetachedCriteria(Class<? extends Serializable> c) throws BusinessException;

	/**
	 * 
	 * @return 创建与会话绑定的检索标准对象
	 */
	Criteria createCriteria() throws BusinessException;
	
}
