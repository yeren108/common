package com.yeren.common.dao;

import javax.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;

/**
 * 基础DAO
 * @param <T> 泛型
 */
public interface BaseDAO<T> {
    /**
     * 放入实体管理器.
     * @param entityManager 实体管理器
     */
    void setEntityManager(EntityManager entityManager);

    /**
     * 获取实体管理器.
     * @return EntityManager
     */
    EntityManager getEntityManager();


    /**
     * 获取记录总数.
     *
     * @return 记录总数
     */
    long getCount();

    /**
     * 清除一级缓存的数据.
     */
    void clear();

    /**
     * 主动提交事务.
     */
    void flush();

    /**
     * 保存实体.
     *
     * @param entity 实体
     */
    void save(T entity);

    /**
     * 更新实体.
     *
     * @param entity 实体
     */
    void update(T entity);

    /**
     * 删除实体.
     *
     * @param entityids 实体id数组
     */
    void delete(Serializable... entityids);

    /**
     * 获取实体.
     *
     * @param entityId 实体id
     * @return 实体对象
     */
    T find(Serializable entityId);

    /**
     * 一次查出所有记录.
     * @return 实体对象集合
     */
    List<T> findAll();

    /**
     * 获取指定类型的实体.
     * @param claimClass    定损单class
     * @param claimId       定损单Id
     * @param <T>           泛型
     * @return 实体
     */
    @SuppressWarnings("hiding")
	<T> T find(Class<T> claimClass, Serializable claimId);

}
