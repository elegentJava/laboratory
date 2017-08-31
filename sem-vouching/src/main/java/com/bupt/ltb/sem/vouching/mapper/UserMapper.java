package com.bupt.ltb.sem.vouching.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bupt.ltb.sem.vouching.pojo.User;

/**
 * 用户数据接口
 * 
 * @author Hogan
 */
public interface UserMapper {

	/**
	 * 通过账号密码查询用户
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public User findUserByAP(@Param("account") String account, @Param("password") String password);

	/**
	 * 修改用户的在线状态
	 * 
	 * @param userId
	 * @param isOline
	 * @return
	 */
	public int updateOnlineStatus(@Param("userId") Integer userId, @Param("isOnline") Integer isOnline);

	/**
	 * 修改用户的在线状态和登录时间
	 * 
	 * @param user
	 * @return
	 */
	public Integer updateStatusAndLastLoginDate(User user);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public List<User> findUserAndClassByRole(@Param("role") Integer role);

	/**
	 * 通过账号查找用户信息
	 * 
	 * @param account
	 * @return
	 */
	public User findByAccount(@Param("account") String account);

	/**
	 * 批量增加用户数据
	 * 
	 * @param users
	 */
	public Integer batchInsert(List<User> users);

	/**
	 * 重置密码
	 * 
	 * @param userId
	 * @param defaultPassword
	 * @return
	 */
	public Integer updatePassword(@Param("userId") Integer userId, @Param("password") String password);

	/**
	 * 通过id和密码查找指定用户信息
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public User findUserByIdAndPassword(@Param("userId") Integer userId, @Param("password") String password);

	/**
	 * 通过班级id查询用户信息
	 * 
	 * @param classId
	 * @return
	 */
	public List<User> findUsersByClassId(@Param("classId") Integer classId);

	/**
	 * 通过id查询用户信息
	 * 
	 * @param parseInt
	 * @return
	 */
	public User findUserById(@Param("userId") Integer userId);

	/**
	 * 查询用户积分
	 * 
	 * @param rankLimit
	 * @return
	 */
	public List<User> findUserByCredit(@Param("rankLimit") Integer rankLimit);

	/**
	 * 更新用户的积分
	 * 
	 * @param user
	 * @return
	 */
	public Integer updateCredit(User user);


	/**
	 * 练习竞技后更新用户的学分
	 * @param user
	 * @return
	 */
	public Integer updatePCCredit(User user);
	/**
	 * 更新用户获得的总学分
	 * @param user
	 * @return
	 */
	public Integer updateObtainCredit(User user);

}
