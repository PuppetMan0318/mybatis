package com.lagou;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author Xuhui
 * @title Test
 * @date 2020/3/1 15:40
 */
public class Test {


	@org.junit.Test
	public void mapperTest() throws PropertyVetoException, DocumentException {
		InputStream resourceStream = Resources.getResourceAsSteam("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		IUserDao userDao = sqlSession.getMapper(IUserDao.class);
		User user = new User();
		user.setId(8);
		user.setUsername("sam");
		user.setPassword("1234");
		userDao.updateUser(user);

		User user1 = new User();
		user1.setId(8);
		user1.setUsername("sam1");
		user1.setPassword("1234");
		userDao.deleteUser(user1);

		User user2 = new User();
		user2.setId(9);
		user2.setUsername("sam1");
		user2.setPassword("1234");
		userDao.insertUser(user2);
	}
}