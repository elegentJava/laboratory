package com.bupt.ltb.sem.vouching.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bupt.ltb.sem.vouching.pojo.Class;

/**
 * 班级数据层接口
 * 
 * @author Hogan
 * 
 */
public interface ClassMapper {

	/**
	 * 根据状态查询班级
	 * 
	 * @param status
	 * @return
	 */
	public List<Class> findClassByStatus(@Param("status") Integer status);

	/**
	 * 查询所有班级信息
	 * 
	 * @return
	 */
	public List<Class> findAllClass();

	/**
	 * 修改班级状态
	 * 
	 * @param map
	 * @return
	 */
	public Integer updateClassSatatus(Map<String, Object> map);

	/**
	 * 删除一个班级
	 * 
	 * @param classId
	 * @return
	 */
	public Integer deleteSingle(Integer classId);

	/**
	 * 批量删除班级信息
	 * 
	 * @param classIds
	 * @return
	 */
	public Integer batchDelete(Integer[] classIds);

	/**
	 * 通过班级名称查询班级信息
	 * 
	 * @param className
	 * @return
	 */
	public Class findCLassByName(String className);

	/**
	 * 添加班级
	 * 
	 * @param clas
	 * @return
	 */
	public Integer addClass(Class clas);

	/**
	 * 通过用户的角色查询班级信息
	 * 
	 * @param roleTeacher
	 * @return
	 */
	public List<Class> findClassByUserRole(Integer roleTeacher);

}
