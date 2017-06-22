package com.bupt.ltb.sem.corre.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bupt.ltb.sem.corre.pojo.CorreCategory;

/**
 * 函电类型数据层接口
 * 
 * @author Hogan
 * @date 2017年6月19日
 */
public interface CorreCategoryMapper {

	/**
	 * 查询所有目录项
	 * 
	 * @return
	 */
	public List<CorreCategory> findAllCorreCategories();

	public List<CorreCategory> findAllCategoriesByFatherId(@Param("fatherId") Integer fatherId);

}
