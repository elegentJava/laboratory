package com.bupt.ltb.sem.vouching.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bupt.ltb.sem.vouching.pojo.Glossary;

/**
 * 词汇查询数据层接口
 * 
 * @author Hogan
 * 
 */
public interface GlossaryMapper {

	/**
	 * 查询所有词汇
	 * 
	 * @return
	 */
	public List<Glossary> findAllGlossaries();

	/**
	 * 通过单词查询所有词汇
	 * 
	 * @param word
	 * @return
	 */
	public List<Glossary> findGlossariesByWord(@Param("word") String word);

	/**
	 * 根据单词来源查询所有词汇
	 * 
	 * @param sources
	 * @return
	 */
	public List<Glossary> findGlossariesBySource(Integer[] sources);

	/**
	 * 通过单词来源和单词缩写查询所有词汇
	 * 
	 * @param map
	 * @return
	 */
	public List<Glossary> findGlossariesBySourceAndWord(Map<String, Object> map);

}
