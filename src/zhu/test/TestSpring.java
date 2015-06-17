package zhu.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	private ApplicationContext ac= new ClassPathXmlApplicationContext("config/applicationContext.xml");
	@Test
	public void testTrancational()
	{
		TestService ts=  (TestService) ac.getBean("testService");
//		ts.add2();
		for(int i=0;i<10;i++){
			ts.add2();
			System.out.print("i;"+i);
		}
//		ts.add3();
	}
}
