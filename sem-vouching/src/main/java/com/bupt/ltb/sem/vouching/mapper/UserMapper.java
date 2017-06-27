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

}
