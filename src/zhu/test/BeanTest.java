package zhu.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {
private ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
	
	@Test
	public void testBean(){
		SessionFactory testAction = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println("springbean:"+testAction);
	}
}
