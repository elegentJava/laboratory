package com.bupt.ltb.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.sem.corre.mapper.CorreCategoryMapper;
import com.bupt.ltb.sem.corre.pojo.CorreCategory;
import com.bupt.ltb.sem.corre.util.CorreUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TestService {
	
	@Resource
	private CorreCategoryMapper correCategoryMapper;

	@Test
	public void test() {
		List<CorreCategory> categories = correCategoryMapper.findAllCorreCategories();
		System.out.println(categories);
	}
	
	public static void main(String[] args) {
		String result = CorreUtils.generateCorrespondenceStr("{Commerce Department (of the US government)<br/>< 请填写欲征询的政府机构名称，如商务部Commerce Department，外经贸部MOFTEC(Ministry of Foreign Trade and Economic Cooperation), 俄罗斯大使馆Russian Embassy> }<br/>[Thank you for your long-standing support! |Thank you for your help from all aspects in these years.|Our co-operation has been very successful in a long period of time.]We [sincerely|genuinely] hope to [expand our business|broaden our business|establish our business] in {all kinds of cotton products<请填写欲开展贸易的相关产品>}, but there are no ideal partners in {China<请填写欲开展贸易的国家或地区>}. [It will be appreciated | We would be grateful]that you provide us some information about {those companies who are professional and reliable in this line<请填写希望得到哪些相关信息>}, so that we can [establish business relationship| establish cooperative relationship|build trade relation ship| enter into business relations] with them.<br/>[We are looking forward your to early reply!|We shall be grateful if you will reply at an early date.|Your early reply is appreciated.| We are anticipating your answer.] <br/>Yours sincerely{American Cotton Company<请填写己方公司名称>");
		System.out.println(result);
	}

}
