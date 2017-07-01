package com.bupt.ltb.sem.vouching.mapper;

import java.util.List;
import java.util.Map;

import com.bupt.ltb.sem.vouching.pojo.Sentence;

/**
 * 语句查询数据层接口
 * 
 * @author Hogan
 * 
 */
public interface SentenceMapper {

	/**
	 * 通过类别、类型、级别查询语句
	 * 
	 * @param map
	 * @return
	 */
	public List<Sentence> findSentencesByCTL(Map<String, Object> map);

	/**
	 * 通过类别、类型查询语句
	 * 
	 * @param map
	 * @return
	 */
	public List<Sentence> findSentencesByCT(Map<String, Object> map);
}
