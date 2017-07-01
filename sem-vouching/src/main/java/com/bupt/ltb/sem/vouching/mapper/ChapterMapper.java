package com.bupt.ltb.sem.vouching.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bupt.ltb.sem.vouching.pojo.Chapter;

/**
 * 章节数据层接口
 * 
 * @author Hogan
 * 
 */
public interface ChapterMapper {

	/**
	 * 修改章节的状态
	 * 
	 * @param map
	 * @return
	 */
	public int updateActiveStatus(Map<String, Object> map);

	/**
	 * 通过章节状态查询章节信息
	 * 
	 * @param isActive
	 * @return
	 */
	public List<Chapter> findAllChaptersByStatus(@Param("isActive") Integer isActive);

	/**
	 * 批量插入
	 * 
	 * @param chapters
	 * @return
	 */
	public Integer batchInsert(List<Chapter> chapters);
}
