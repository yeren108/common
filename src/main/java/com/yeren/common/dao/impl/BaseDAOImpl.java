package com.yeren.common.dao.impl;


import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





import com.yeren.common.dao.BaseDAO;
import com.yeren.common.utils.CollectionUtils;
import com.yeren.common.utils.GenericUtils;
import com.yeren.common.utils.TypeUtils;


/**
 * 数据访问层的基础类, 具体的Domain相关DAO类可以继承与这个类并制定泛型参数
 *
 * @param <T> 具体的Domain类
 */
@SuppressWarnings("unchecked")
public class BaseDAOImpl<T> implements BaseDAO<T> {
    /**
     * 要操作的Domain类的类型
     */
    protected Class<T> entityClass = GenericUtils.getSuperClassGenericType(this.getClass());

    // 日志
    private static final Logger LOG = LoggerFactory.getLogger(BaseDAOImpl.class.getName());

    /**
     * EntityManager实例, 一般由Spring注入
     */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * constructor
     */
    public BaseDAOImpl() {
    }

    /**
     * constructor
     * @param entityClass 实体
     */
    public BaseDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * 清楚
     */
    public void clear() {
        entityManager.clear();
    }

    /**
     * flush
     */
    public void flush() {
        entityManager.flush();
    }

    /**
     * 删除方法.
     * @param entityids 实体id
     */
    public void delete(Serializable... entityids) {
        if (entityids != null) {
            for (Object id : entityids) {
                // 如果找不到实体的话就不用删除了
                try {
                    if (id.getClass().getAnnotation(Entity.class) != null) {
                        entityManager.remove(id);
                    } else {
                        entityManager.remove(entityManager.getReference(this.entityClass, id));
                    }
                } catch (EntityNotFoundException ex) {
                    LOG.warn(ex.getMessage(), ex);
                }

            }
        }
    }

    /**
     * 根据实体类id查找对应的对象.
     * @param entityId 实体id
     * @return 实体
     */
    public T find(Serializable entityId) {
        if (entityId == null) {
            throw new IllegalArgumentException(this.entityClass.getName() + ":传入的实体id不能为空");
        }
        return entityManager.find(this.entityClass, entityId);
    }

    /**
     * 保存对象.
     * @param entity 实体
     */
    public void save(T entity) {
        entityManager.persist(entity);
    }

    public long getCount() {
        return (Long) entityManager.createQuery(
                "select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o")
                .getSingleResult();
    }

    /**
     * 更新实体类.
     * @param entity 实体
     */
    public void update(T entity) {
        entityManager.merge(entity);
    }

    /**
     * 查找所有实体类.
     * @return 实体
     */
    public List<T> findAll() {
        return findByCriteria(new HashMap<String, Object>());
    }

    /**
     * 根据属性=值的方式去查询
     *
     * @param parameters 属性名=值的查询条件表
     * @return 查询结果
     */
    protected List<T> findByCriteria(Map<String, Object> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> entity = cq.from(entityClass);
        cq.select(entity);
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            predicates.add(cb.equal(entity.get(entry.getKey()), entry.getValue()));
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
        }
        TypedQuery<T> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    /**
     * 根据属性=值的方式去查询
     *
     * @param propertyName  属性名
     * @param propertyValue 属性值
     * @return 查询结果
     */
    protected List<T> findByCriteria(String propertyName, Object propertyValue) {
        Map<String, Object> params = CollectionUtils.makeMap(propertyName, propertyValue);
        return findByCriteria(params);
    }

    /**
     * set查询参数.
     * @param query 查询对象
     * @param queryParams 参数
     */
    @SuppressWarnings("unused")
	private static void setQueryParams(Query query, Object[] queryParams) {
        if (queryParams != null && queryParams.length > 0) {
            for (int i = 0; i < queryParams.length; i++) {
                query.setParameter(i + 1, queryParams[i]);
            }
        }
    }

    /**
     * 获取实体的名称
     *
     * @param <E>   实体类行
     * @param clazz 实体类
     * @return 实体的名称
     */
    private static <E> String getEntityName(Class<E> clazz) {
        String entityname = clazz.getSimpleName();
        Entity entity = clazz.getAnnotation(Entity.class);
        if (entity.name() != null && !"".equals(entity.name())) {
            entityname = entity.name();
        }
        return entityname;
    }

    /**
     * 获取统计属性,该方法是为了解决hibernate解析联合主键select count(o) from Xxx o语句BUG而增加,hibernate对此jpql解析后的sql为select
     * count(field1,field2,...),显示使用count()统计多个字段是错误的
     * @param <E> 泛型
     * @param clazz class
     * @return String
     */
    private static <E> String getCountField(Class<E> clazz) {
        String out = "o";
        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            for (PropertyDescriptor propertydesc : propertyDescriptors) {
                Method method = propertydesc.getReadMethod();
                if (method != null && method.isAnnotationPresent(EmbeddedId.class)) {
                    PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType())
                            .getPropertyDescriptors();
                    out = "o." + propertydesc.getName() + "."
                            + (!"class".equals(ps[1].getName()) ? ps[1].getName() : ps[0].getName());
                    break;
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return out;
    }

    /**
     * 通过指定的查询的ID的QL进行查询
     *
     * @param queryId     查询的ID
     * @param parameters  参数
     * @return 行数据
     */
    protected List<T> findByNamedQuery(String queryId, Map<String, Object> parameters) {
        TypedQuery<T> namedQuery = entityManager.createNamedQuery(queryId, entityClass);
        if(parameters != null){
            for(String key : parameters.keySet()){
                namedQuery.setParameter(key, parameters.get(key));
            }
        }


        List<T> results = namedQuery.getResultList();

        return results;
    }


    /**
     * 通过指定的查询的ID的QL进行查询
     *
     * @param queryId     查询的ID
     * @param parameters  参数
     * @return 行数据
     */
    protected List<T> findByNamedQuery(String queryId, Map<String, Object> parameters, Integer maxResults) {
        TypedQuery<T> namedQuery = entityManager.createNamedQuery(queryId, entityClass);
        if(parameters != null){
            for(String key : parameters.keySet()){
                namedQuery.setParameter(key, parameters.get(key));
            }
        }

        if(maxResults != null){
            namedQuery.setMaxResults(maxResults);
        }


        List<T> results = namedQuery.getResultList();

        return results;
    }


    /**
     * 是否为简单对象
     *
     * @param clz 类型
     * @return 是否为简单对象
     */
    @SuppressWarnings("unused")
	private boolean isPrimitiveType(Class<?> clz) {
        return clz.isPrimitive()
                || TypeUtils.isSubClassOf(clz,
                Number.class,
                Boolean.class,
                Date.class,
                String.class);
    }

    public <T1> T1 find(Class<T1> entityClass, Serializable entityId) {
        if (entityId == null) {
            throw new IllegalArgumentException(entityClass.getName() + ":传入的实体id不能为空");
        }
        return entityManager.find(entityClass, entityId);
    }
}
