package com.bupt.ltb.sem.vouching.mapper;

import java.util.List;

import com.bupt.ltb.sem.vouching.pojo.Competition;

/**
 * 竞技数据层接口
 * 
 * @author Hogan
 *
 */
public interface CompetitionMapper {

	/**
	 * 通过用户id获取竞技信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<Competition> findCompetitionsByUserId(Integer userId);

	/**
	 * 生成一条竞技记录
	 * 
	 * @param competition
	 * @return
	 */
	public Integer addCompetition(Competition competition);

}
