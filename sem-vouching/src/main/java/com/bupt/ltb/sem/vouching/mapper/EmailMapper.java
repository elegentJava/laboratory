package com.bupt.ltb.sem.vouching.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bupt.ltb.sem.vouching.pojo.Email;

/**
 * 邮件数据层接口
 * 
 * @author Hogan
 * 
 */
public interface EmailMapper {

	/**
	 * 查询所有未删除的收件信
	 * 
	 * @param receiveId
	 * @return
	 */
	public List<Email> findReceiveEmails(@Param("receiveId") int receiveId);

	/**
	 * 查询所有未删除的发件信
	 * 
	 * @param receiveId
	 * @return
	 */
	public List<Email> findSendEmails(@Param("sendId") int sendId);

	/**
	 * 保存邮件信息
	 * 
	 * @param email
	 * @return
	 */
	public Integer saveEmail(Email email);

	/**
	 * 修改邮件状态
	 * 
	 * @param email
	 * @return
	 */
	public Integer updateEmailStatus(Email email);

	/**
	 * 通过ID查询邮件
	 * 
	 * @param integer
	 * @return
	 */
	public Email findEmailById(@Param("emailId") Integer emailId);

	/**
	 * 批量修改邮件的删除状态
	 * 
	 * @param map
	 * @return
	 */
	public Integer batchUpdateEmailDelStatus(Map<String, Object> map);

	/**
	 * 查询所有未读的收件信
	 * 
	 * @param userId
	 * @return
	 */
	public List<Email> findReceiveUnreadEmails(@Param("receiveId") Integer receiveId);

	/**
	 * 查询未读收件信的记录数
	 * 
	 * @param userId
	 * @return
	 */
	public Integer findUnreadReceiveEmailCount(@Param("receiveId") Integer receiveId);
}
