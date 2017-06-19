package com.bupt.ltb.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TestService {

	@Test
	public void test() {
		System.out.println("s");
	}

}
