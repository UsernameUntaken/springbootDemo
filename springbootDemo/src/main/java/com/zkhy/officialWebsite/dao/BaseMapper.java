package com.zkhy.officialWebsite.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 
 * @author LIULX
 *启动类一定不能扫描到BaseMapper文件，否则报错
 * @param <T>
 */
public interface BaseMapper <T> extends Mapper<T>,MySqlMapper<T>{

}
