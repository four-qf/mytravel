package com.tour.suse.util;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.tour.suse.base.DaoSupport;
import com.tour.suse.entity.Note;
import com.tour.suse.entity.PageBean;
import com.tour.suse.entity.Scenic;
import com.tour.suse.entity.ScenicClass;
/**
 * 用于辅助拼接HQL语句
 * 
 * @author tyg
 * 
 */
public class QueryHelper {
	private String selectClause="";
	private String fromClause; // FROM子句
	private String whereClause = ""; // Where子句
	private String orderByClause = ""; // OrderBy子句

	private List<Object> parameters = new ArrayList<Object>(); // 参数列表

	/**
	 * 生成From子句
	 * 
	 * @param clazz
	 * @param alias
	 *            别名
	 */
	public QueryHelper(Class clazz,String alias) {
		fromClause = " FROM " + clazz.getSimpleName() + " " + alias;
	}
	
	/**
	 * 放入select字句 生成 From子句
	 * 如果不需要select则放入""
	 * @PARAM selectClause
	 * @param clazz
	 * @param alias
	 *            别名
	 */
	
	public QueryHelper(String  selectClause,Class clazz,String alias) {
		//传入string不为空字句
		if(!"".equals(selectClause.trim()))
		this.selectClause=selectClause;
			fromClause = " FROM " + clazz.getSimpleName() + " " + alias;
	}
	
	/**
	 * 放入select字句 生成 From子句
	 * 如果不需要select则放入""
	 * @PARAM selectClause
	 * @param clazz 
	 * @param alias
	 * @param boolean add 这个note专用
	 *           联表查询
	 */
	public QueryHelper(boolean add,String  selectClause,Class<Note> class1, Class<ScenicClass> class2, String alias, String alias2) {
		//传入string不为空字句
		if(!"".equals(selectClause.trim()))
		this.selectClause=selectClause;
		fromClause = "FROM "+class1.getSimpleName()+" " +alias+","+class2.getSimpleName()+" "+alias2; 
	}
	
	/**
	 * 放入select字句 生成 From子句
	 * 如果不需要select则放入""
	 * @PARAM selectClause
	 * @param clazz 
	 * @param alias
	 *           联表查询
	 */
	public QueryHelper(String  selectClause,Class<Scenic> class1, Class<ScenicClass> class2, String alias, String alias2) {
		//传入string不为空字句
		if(!"".equals(selectClause.trim()))
		this.selectClause=selectClause;
		fromClause = "FROM "+class1.getSimpleName()+" " +alias+","+class2.getSimpleName()+" "+alias2; 
	}
	
	/**
	 * 自动拼接where
	 * append2为true 则连接 and
	 * @param condition
	 * @param params
	 */
	public QueryHelper addCondition(boolean append2,String condition, Object... params) {
		//如果传入的是null则不拼接
	if(params!=null)
	{
		// 拼接
		if (whereClause.length() == 0) {
			whereClause = " WHERE (" + condition;
		} else if(append2){
			whereClause += " AND (" + condition;
		}else{
			whereClause += " OR (" + condition;
		}
		//传入的是集合
		if(params.length>1)
		{
			//同类型 剩余的条件 用or 连接  
			for (int i=0;i<params.length-1;i++) {
				whereClause += " OR " + condition;
			}
		}
		whereClause = whereClause+")";
		// 参数封装次对象
		if (params != null) {
			for (Object p : params) {
				parameters.add(p);
			}
		}
	}
		return this;
	}
	
	

	/**
	 * 拼接OrderBy子句
	 * 
	 * @param propertyName
	 *            参与排序的属性名
	 * @param asc
	 *            true表示升序，false表示降序
	 */
	public QueryHelper addOrderProperty(String propertyName, boolean asc) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName + (asc ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (asc ? " ASC" : " DESC");
		}
		return this;
	}

	/**
	 * 拼接OrderBy子句
	 * 搜索专用
	 */
	public QueryHelper addOrderProperty2(String concatC, Object ...param) {
		// order by replace(replace(text,'升旗',''),'解放','')
			if(param!=null)
				if(param.length>0){
					for(int i=0;i<param.length;i++)
					{
						if(i==0)
						{orderByClause = orderByClause.length() == 0?" ORDER BY replace(" :" ORDER BY replace(" ;}
						else
						{
							orderByClause += "replace(" ;
						}
					}
					String h="";
					for(int i=param.length-1;i>-1;i--)
					{
						if(i==param.length-1)
							h+=concatC+",'"+param[i]+"','')";
						else
							h+=",'"+param[i]+"','')";
					}
					orderByClause+=h;
				}
			System.out.println("clause:"+orderByClause);
		return this;
	}

	
	/**
	 * 如果第一个参数为true，则拼接OrderBy子句
	 * 
	 * @param append
	 * @param propertyName
	 * @param asc
	 */
	public QueryHelper addOrderProperty(boolean append, String propertyName, boolean asc) {
		if (append) {
			addOrderProperty(propertyName, asc);
		}
		return this;
	}

	/**
	 * 获取生成的用于查询数据列表的HQL语句
	 * 
	 * @return
	 */
	public String getListQueryHql() {
		return selectClause+fromClause + whereClause + orderByClause;
	}

	/**
	 * 获取生成的用于查询总记录数的HQL语句
	 * 
	 * @return
	 */
	public String getCountQueryHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * 获取HQL中的参数值列表
	 * 
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	/**
	 * 查询分页信息，并放到值栈栈顶
	 * @param service
	 * @param pageNum
	 * @param pageSize
	 */
	public void preparePageBean(DaoSupport<?> service, int pageNum, int pageSize) {
		PageBean pageBean = service.getPageBean(pageNum, pageSize, this);
		ActionContext.getContext().getValueStack().push(pageBean);
	}
	
	/**
	 * 查询分页信息，并放到值栈栈顶
	 * 两个对象  过滤分类信息   true 
	 * @param service
	 * @param pageNum
	 * @param pageSize
	 */
	public void preparePageBean(DaoSupport<?> service, int pageNum, int pageSize,boolean flag) {
		PageBean pageBean = service.getPageBean(pageNum, pageSize, this);
		if(flag){
			List  recordList= pageBean.getRecordList();
			List<Scenic> scenicList = new ArrayList<Scenic>();
			for (int i=0;i<recordList.size();i++)
			{
				Object [] obj = (Object[]) recordList.get(i);
				scenicList.add((Scenic) obj[0]);
				System.out.println(((Scenic) obj[0]).getId()+"id");
			}
			pageBean.setRecordList(scenicList);
		}
		System.out.println("个数："+pageBean.getRecordList().size());
		ActionContext.getContext().getValueStack().push(pageBean);
	}
	
	/**
	 * 查询分页信息，并返回值使用了select 封装到对象的
//	 * flag为真则表示查询到两个对像  解析一个对象的数据
	 * @param service
	 * @param pageNum
	 * @param pageSize
	 */
	public List preparePageBean2(DaoSupport<?> service, int pageNum, int pageSize) {
		PageBean pageBean = service.getPageBean(pageNum, pageSize, this);
		System.out.println("个数："+pageBean.getRecordList().size());
		return pageBean.getRecordList();
	}
}
