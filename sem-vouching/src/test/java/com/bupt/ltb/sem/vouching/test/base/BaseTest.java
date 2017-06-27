package com.bupt.ltb.sem.vouching.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基础的测试类
 * 
 * @author Hogan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class BaseTest {

}
