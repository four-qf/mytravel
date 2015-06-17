package zhu.test;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
@Component("testAction")
@Scope("prototype")
public class TestAction extends ActionSupport {
	@Resource
	private TestService testService;
	public String execute(){
		System.out.println("aaa");
		testService.add();
		return "success";
	}
}
