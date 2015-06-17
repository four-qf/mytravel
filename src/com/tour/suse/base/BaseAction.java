package com.tour.suse.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tour.suse.entity.User;
import com.tour.suse.filter.KeywordFilter;
import com.tour.suse.news.service.NewsService;
import com.tour.suse.service.CityService;
import com.tour.suse.service.NoteCommentService;
import com.tour.suse.service.NoteReplyService;
import com.tour.suse.service.NoteService;
import com.tour.suse.service.PrivilegeService;
import com.tour.suse.service.RoleService;
import com.tour.suse.service.ScenicClassService;
import com.tour.suse.service.ScenicCommentService;
import com.tour.suse.service.ScenicService;
import com.tour.suse.service.SubScenicService;
import com.tour.suse.service.UserService;

@Controller
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	protected String getRoot()
	{
		return ServletActionContext.getServletContext().getRealPath("/");
	}
	// =============== ModelDriven的支持 ==================

	/**
	 * 
	 */
	private static final long serialVersionUID = -7546586660533181286L;
	protected T model;
	protected  User user;
	@SuppressWarnings("unchecked")
	public BaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// =============== Service实例的声明 ==================DownloadService.java
	@Resource
	protected ScenicService scenicService; 
	@Resource
	protected SubScenicService subScenicService;
	@Resource
	protected ScenicClassService scenicClassService; 
	@Resource
	protected CityService cityService; 
	@Resource
	protected NoteService noteService; 
	@Resource
	protected  UserService userService;
	@Resource
	protected  ScenicCommentService scenicCommentService;
	@Resource
	protected NoteCommentService noteCommentService;
	@Resource
	protected NoteReplyService noteReplyService;
	@Resource
	protected RoleService roleService;
	@Resource
	protected PrivilegeService privilegeService;
	//
	@Resource
	protected KeywordFilter filter = KeywordFilter.getFilter();
	
	@Resource
	protected NewsService newsService;
	
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	/*
	 * 获取PrintWriter对象
	 * */
	protected PrintWriter getOut()
	{
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");//在火狐下避免返回数据为xml
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			return response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	protected PrintWriter getJsonOut()
	{
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");//在火狐下避免返回数据为xml
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			return response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	protected Map<String, Object> getSen()
	{
		return ActionContext.getContext().getSession();
	}
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	// ============== 分页用的参数 =============

	protected int pageNum = 1; // 当前页
	protected int pageSize = 12; // 每页显示多少条记录

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	 
	 protected User getUser()
	 {
		 if((User)ActionContext.getContext().getSession().get("admin")!=null)
			 return (User)ActionContext.getContext().getSession().get("admin");
		 else
			 return (User)ActionContext.getContext().getSession().get("user");
	 }
	
}
