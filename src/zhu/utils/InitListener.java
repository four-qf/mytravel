package zhu.utils;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tour.suse.entity.City;
import com.tour.suse.entity.Privilege;
import com.tour.suse.entity.ScenicClass;
import com.tour.suse.service.CityService;
import com.tour.suse.service.PrivilegeService;
import com.tour.suse.service.ScenicClassService;

public class InitListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		//获取与容器相关的service对象	
		ApplicationContext ac= WebApplicationContextUtils
		.getWebApplicationContext(sce.getServletContext());
		
		CityService cityService = (CityService) ac.getBean("cityServiceImpl");
		ScenicClassService	scenicClassService=(ScenicClassService)ac.getBean("scenicClassServiceImpl");
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");
		
		//准备数据 放入application中
		List<City> cityList=cityService.findAll();
		List<ScenicClass> scenicClassList = scenicClassService.findTopList();
		sce.getServletContext().setAttribute("cityList", cityList);
		sce.getServletContext().setAttribute("scenicClassList", scenicClassList);
		System.out.println("scenicClassList:"+scenicClassList);
		
		// 准备数据：topPrivilegeList
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		sce.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("------------> 已准备数据topPrivilegeList <------------");
		
		//服务器开启 第一此加载 控制权限 种类
		Collection<Privilege> allPrivileges = privilegeService.getAllPrivilegeUrls();
		System.out.println("size:"+allPrivileges.size());
		sce.getServletContext().setAttribute("allPrivileges", allPrivileges);
		System.out.println("------------> 已准备数据allPrivileges <------------");
		
	}

}
