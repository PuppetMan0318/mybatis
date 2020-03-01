package com.lagou.dao;

import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author Xuhui
 * @title UserDaoImpl
 * @date 2020/2/25 22:51
 */
public class UserDaoImpl implements IUserDao {
	@Override
	public List<User> findAll() throws Exception {
		/*
		该段代码重复，整个操作过程模板重复（加载配置文件，创建sqlSessionFactory，生产sqlSession）
		 */
		InputStream resourceStream = Resources.getResourceAsSteam("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		List<User> users = sqlSession.selectList("user.findAll");
		for (User user2 : users) {
			System.out.println(user2);
		}
		return users;
	}

	@Override
	public User findByCondition(User user) throws Exception {
		InputStream resourceStream = Resources.getResourceAsSteam("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		/*
		statementId存在硬编码
		 */
		User user1 = sqlSession.selectOne("user.selectOne", user);
		//System.out.println(user1);
		return user1;
	}

	@Override
	public void updateUser(User user) {

	}

	@Override
	public void insertUser(User user) {

	}

	@Override
	public void deleteUser(User user) {

	}
}
