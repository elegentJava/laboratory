package com.bupt.ltb.sem.corre.mapper;

import java.util.List;

import com.bupt.ltb.sem.corre.pojo.CorreCategory;

/**
 * 函电类型数据层接口
 * 
 * @author Hogan
 * @date 2017年6月19日
 */
public interface CorreCategoryMapper {

	public List<CorreCategory> findAllCorreCategories();
}
