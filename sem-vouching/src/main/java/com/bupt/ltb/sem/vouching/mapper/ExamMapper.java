package com.bupt.ltb.sem.vouching.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bupt.ltb.sem.vouching.pojo.Exam;

/**
 * 试卷数据层接口
 * 
 * @author Hogan
 * 
 */
public interface ExamMapper {

	/**
	 * 保存试卷信息
	 * 
	 * @param exam
	 * @return
	 */
	public int saveExam(Exam exam);

	/**
	 * 根据激活状态和教师ID查询试卷详情信息
	 * 
	 * @param map
	 * @return
	 */
	public List<Exam> findExamDetailByStatusAndTeacher(Map<String, Object> map);

	/**
	 * 根据试卷ID修改试卷状态
	 * 
	 * @param map
	 * @return
	 */
	public Integer updateExamStatusById(Map<String, Object> map);

	/**
	 * 根据ID删除试卷
	 * 
	 * @param examId
	 * @return
	 */
	public Integer deleteExamById(@Param("examId") Integer examId);

	/**
	 * 通过ID查询指定试卷
	 * 
	 * @param examId
	 * @return
	 */
	public Exam findExamById(@Param("examId") Integer examId);

	/**
	 * 修改试卷的题目ID集合字符串
	 * 
	 * @param exam
	 * @return
	 */
	public Integer updateQuestionId(Exam exam);

	/**
	 * 查询用户未参加过的考试
	 * 
	 * @param map
	 * @return
	 */
	public List<Exam> findUnjoinedExam(Map<String, Object> map);

	/**
	 * 根据试卷状态查询试卷信息
	 * 
	 * @param active
	 * @return
	 */
	public List<Exam> findExamsBySatus(@Param("isActive") Integer isActive);

	/**
	 * 通过考试名称查询考试信息
	 * 
	 * @param name
	 * @return
	 */
	public Exam findExamByName(@Param("examName") String examName);

	/**
	 * 通过教师ID查询所有创建的试卷ID
	 * 
	 * @param userId
	 * @return
	 */
	public List<Integer> findExamIdsByUserId(@Param("teacherId") Integer teacherId);

}