package com.wjs.treewiki;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/spring-test-context.xml" })
public class JunitServiceBaseTest {

	@Test
	public void test(){
		System.out.println("单测成功");
	}
	

}