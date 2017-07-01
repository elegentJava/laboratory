package com.bupt.ltb.sem.vouching.mapper;

import java.util.List;

import com.bupt.ltb.sem.vouching.pojo.SenCategory;

/**
 * 语句类型数据层接口
 * 
 * @author Hogan
 */
public interface SenCategoryMapper {

	/**
	 * 查找所有语句类型
	 * 
	 * @return
	 */
	public List<SenCategory> findAllCategories();
}
