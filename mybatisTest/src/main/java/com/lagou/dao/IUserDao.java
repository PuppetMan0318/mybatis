package com.lagou.dao;

import com.lagou.pojo.User;

import java.util.List;

/**
 * @author Xuhui
 * @title IUserDao
 * @date 2020/2/25 22:49
 */
public interface IUserDao {

	/**
	 * @return 查询所有用户
	 */
	public List<User> findAll() throws Exception;

	/**
	 * @param user 包含条件的User对象
	 * @return 根据条件进行用户查询
	 */
	public User findByCondition(User user) throws Exception;

	/**
	 * 更新
	 *
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 新增
	 *
	 * @param user
	 */
	void insertUser(User user);

	/**
	 * 删除
	 *
	 * @param user
	 */
	void deleteUser(User user);


}
