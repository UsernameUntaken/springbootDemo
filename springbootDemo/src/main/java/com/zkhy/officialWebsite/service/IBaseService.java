package com.zkhy.officialWebsite.service;

import java.util.List;

public interface IBaseService<T> {

	/**
	 * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
	 * @param record
	 * @return
	 */
	T selectOne(T record);
	
	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	 * @param record
	 * @return
	 */
    List<T> select(T record);
    
    /**
     * 根据实体类不为null的字段查询总数,条件全部使用=号and条件
     * @param record
     * @return
     */
    int selectCount(T record);

    /**
     * 根据主键进行查询,必须保证结果唯一
     * 单个字段做主键时,可以直接写主键的值
     * 联合主键时,key可以是实体类,也可以是Map
     * @param key
     * @return
     */
    T selectByPrimaryKey(Object key);
    
    /**
     * 构造example和criteria自定义查询
     * 例如:
     * Example example = new Example(Refund.class);
	 * example.createCriteria().andEqualTo("orderId", orderId);
	 * example.orderBy("successtime").desc();
     * @param example
     * @return
     */
    List<T> selectByExample(Object example);

    /**
     * 插入一条数据
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     * @param record
     * @return
     */
    boolean insert(T record);

    /**
     * 插入一条数据,只插入不为null的字段,不会影响有默认值的字段
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     * @param record
     * @return
     */
    boolean insertSelective(T record);

    /**
     * 根据实体类中字段不为null的条件进行删除,条件全部使用=号and条件
     * @param record
     * @return
     */
    boolean delete(T record);

    /**
     * 通过主键进行删除,这里最多只会删除一条数据
     * 单个字段做主键时,可以直接写主键的值
     * 联合主键时,key可以是实体类,也可以是Map
     * @param key
     * @return
     */
    boolean deleteByPrimaryKey(Object key);

    /**
     * 根据主键进行更新,这里最多只会更新一条数据
     * 参数为实体类
     * @param record
     * @return
     */
    boolean updateByPrimaryKey(T record);

    /**
     * 根据主键进行更新
     * 只会更新不是null的数据
     * @param record
     * @return
     */
    boolean updateByPrimaryKeySelective(T record);
    
    /**
     * 检查必填参数
     * @param str
     * @param paramName
     */
    @SuppressWarnings("hiding")
	<T> void checkRequiredParam(T str, String paramName);
}
