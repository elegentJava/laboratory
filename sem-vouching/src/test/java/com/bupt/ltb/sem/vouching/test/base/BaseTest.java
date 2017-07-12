package com.bupt.ltb.sem.vouching.test.base;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bupt.ltb.sem.vouching.mapper.UserMapper;

/**
 * 基础的测试类
 * 
 * @author Hogan
 */
@Ignore
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class BaseTest {
	
	@Resource
	private UserMapper userMapper;
	
	protected HttpSession session = new MyTestSession();
	
	private class MyTestSession implements HttpSession{

		@Override
		public long getCreationTime() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getLastAccessedTime() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public ServletContext getServletContext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setMaxInactiveInterval(int interval) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getMaxInactiveInterval() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getAttribute(String name) {
			return userMapper.findUserById(1);
		}

		@Override
		public Object getValue(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Enumeration<?> getAttributeNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] getValueNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setAttribute(String name, Object value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void putValue(String name, Object value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeAttribute(String name) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeValue(String name) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void invalidate() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isNew() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public HttpSessionContext getSessionContext() {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
