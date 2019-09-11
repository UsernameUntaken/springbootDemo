package com.zkhy.officialWebsite.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zkhy.officialWebsite.common.enums.ResponseCodeEnum;
import com.zkhy.officialWebsite.dao.BaseMapper;
import com.zkhy.officialWebsite.exception.ServiceException;
import com.zkhy.officialWebsite.service.IBaseService;


/**
 * 不能用@Service注解，会报single but find two之类的错误
 * @author LIULX
 *
 * @param <M>
 * @param <T>
 */
public  class BaseServiceImpl<M extends BaseMapper<T>,T> implements IBaseService<T>{
	
	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected  M baseMapper;
	
	@Override
	public List<T> select(T record) {
		return baseMapper.select(record);
	}

	@Override
	public int selectCount(T record) {
		return baseMapper.selectCount(record);
	}

	@Override
	public T selectByPrimaryKey(Object key) {
		return baseMapper.selectByPrimaryKey(key);
	}
	
	@Override
	public List<T> selectByExample(Object example) {
		return baseMapper.selectByExample(example);
	}

	@Override
	public boolean insert(T record) {
		return baseMapper.insert(record) >0 ? true : false;
	}

	@Override
	public boolean insertSelective(T record) {
		return baseMapper.insertSelective(record) >0 ? true : false;
	}

	@Override
	public boolean delete(T record) {
		return baseMapper.delete(record) >0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Object key) {
		return baseMapper.deleteByPrimaryKey(key) >0 ? true : false;
	}

	@Override
	public boolean updateByPrimaryKey(T record) {
		return baseMapper.updateByPrimaryKey(record) >0 ? true : false;
	}

	@Override
	public boolean updateByPrimaryKeySelective(T record) {
		return baseMapper.updateByPrimaryKeySelective(record)  >0 ? true : false;
	}

	@Override
	public T selectOne(T record) {
		return baseMapper.selectOne(record);
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> void checkRequiredParam(T str, String paramName) {
		if (null == str) {
			throw new ServiceException(ResponseCodeEnum.REQUIRED_ARGUMENT.getCode(), ResponseCodeEnum.REQUIRED_ARGUMENT.getDesc() + paramName + ", 参数类型为字符型, 且不能为空");
		} else if (str instanceof String && "".equals(str)) {
			throw new ServiceException(ResponseCodeEnum.REQUIRED_ARGUMENT.getCode(), ResponseCodeEnum.REQUIRED_ARGUMENT.getDesc() + paramName + ", 参数类型为整型, 且不能为空");
		} else if (str instanceof List && ((List<?>) str).size() <= 0) {
			throw new ServiceException(ResponseCodeEnum.REQUIRED_ARGUMENT.getCode(), ResponseCodeEnum.REQUIRED_ARGUMENT.getDesc() + paramName + ", 参数类型为数组, 且不能为空");
		} else if (str instanceof BigDecimal && ((BigDecimal) str).compareTo(BigDecimal.ZERO) == -1) {
			throw new ServiceException(ResponseCodeEnum.REQUIRED_ARGUMENT.getCode(), ResponseCodeEnum.REQUIRED_ARGUMENT.getDesc() + paramName + ", 参数最多保留小数点后两位, 且不能为空");
		}
	}

}
