package com.bupt.ltb.sem.corre.mapper;

import com.bupt.ltb.sem.corre.pojo.Corre;

/**
 * 函电数据层接口
 * 
 * @author Hogan
 * @date 2017年6月19日
 */
public interface CorreMapper {
	public Corre findCorreById(int ccid);
}
