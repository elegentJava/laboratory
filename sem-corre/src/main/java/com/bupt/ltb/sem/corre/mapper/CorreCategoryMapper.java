package com.bupt.ltb.sem.corre.mapper;

import java.util.List;

import com.bupt.ltb.sem.corre.pojo.Corre;
import com.bupt.ltb.sem.corre.pojo.CorreCategory;

/**
 * 函电类型数据层接口
 * 
 * @author Hogan
 * @date 2017年6月19日
 */
public interface CorreCategoryMapper {
	//查询所有目录项
	public List<CorreCategory> findAllCorreCategories();
	//通过cc_id查询目录
	public Corre findCorreById(int cc_id);
}
