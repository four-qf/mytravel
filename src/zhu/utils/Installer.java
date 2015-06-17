package zhu.utils;

import java.util.Collection;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tour.suse.entity.City;
import com.tour.suse.entity.Privilege;
import com.tour.suse.entity.ScenicClass;
import com.tour.suse.entity.User;
import com.tour.suse.util.MD5Util;
@Repository
public class Installer {
	@Resource
	private SessionFactory sf;
	@Transactional
	public void installprivilege()
	{
		Session s = sf.getCurrentSession();
		// ==============================================================
		// 保存超级管理员用户
//		User user = new User();
//		user.setUsername("admin");
//		user.setUsername("超级管理员");
//		user.setPassword(MD5Util.md5code("admin"));
//		s.save(user); // 保存
		// ==============================================================
		// 保存权限数据
		Privilege menu, menu1, menu2, menu3, menu4, menu5,menu6,menu7;
		//---------后台界面进入 htIndexUI
//		menu = new Privilege("后台登录界面", null, null);
//		s.save(menu);
//		s.save(new Privilege("后台首页", "/user_htIndexUI", menu));//不必看是否含有
		/*
			新建文章
			文章查看
			推荐文章
			置顶文章
			通知公告
			文章回收站
		 * */
//		menu = new Privilege("文章管理",null,null);
//		menu1 = new Privilege("新建文章界面","/manager/m_add",menu);//m_addArticle.action
//		menu2 = new Privilege("文章查看界面","/manager/m_findArticle",menu);
//		menu3 = new Privilege("推荐文章界面","/manager/m_hotArticle",menu);
//		menu4 = new Privilege("置顶文章界面","/manager/m_topArticle",menu);
//		menu5 = new Privilege("通知公告界面","/manager/m_noticeArticle",menu);
//		menu6 = new Privilege("文章回收站界面","/manager/m_recoverArticle",menu);
//		s.save(menu);
//		s.save(menu1);
//		s.save(menu2);
//		s.save(menu3);
//		s.save(menu4);
//		s.save(menu5);
//		s.save(menu6);
//			s.save(new Privilege("新建文章", "/manager/m_addArticle", menu1));
//			
//			s.save(new Privilege("新建文章", "/manager/m_addArticle", menu2));
//			s.save(new Privilege("更新文章", "/manager/m_updateArticle", menu2));
//			s.save(new Privilege("删除文章", "/manager/delete", menu2));
//			s.save(new Privilege("批量删除文章", "/manager/m_piLingDelete", menu2));
//		
//			s.save(new Privilege("新建文章", "/manager/m_addArticle", menu3));
//			s.save(new Privilege("更新文章", "/manager/m_updateArticle", menu3));
//			s.save(new Privilege("删除文章", "/manager/delete", menu3));
//			s.save(new Privilege("批量取消推荐文章", "/manager/m_plCancelHoted", menu3));
//			s.save(new Privilege("取消推荐文章", "/manager/m_cancelHoted", menu3));
//		
//			s.save(new Privilege("新建文章", "/manager/m_addArticle", menu4));
//			s.save(new Privilege("更新文章", "/manager/m_updateArticle", menu4));
//			s.save(new Privilege("删除文章", "/manager/delete", menu4));
//			s.save(new Privilege("批量取消置顶文章", "/manager/m_plCancelToped", menu4));
//			s.save(new Privilege("取消置顶文章", "/manager/m_cancelToped", menu4));
//		
//			s.save(new Privilege("新建文章", "/manager/m_addArticle", menu5));
//			s.save(new Privilege("更新文章", "/manager/m_updateArticle", menu5));
//			s.save(new Privilege("删除文章", "/manager/delete", menu5));
//			s.save(new Privilege("批量取消公告文章", "/manager/m_plCancelNoticed", menu5));
//			s.save(new Privilege("取消公告文章", "/manager/m_cancelNoticed", menu5));
//		
//			s.save(new Privilege("新建文章", "/manager/m_addArticle", menu6));
//			s.save(new Privilege("批量恢复文章", "/manager/m_piLiangResume", menu6));
//			s.save(new Privilege("批量彻底删除文章", "/manager/m_piLingDeleteForever", menu6));
//			s.save(new Privilege("恢复文章", "/manager/m_resume", menu6));
//			s.save(new Privilege("彻底删除文章", "/manager/m_deleteForever", menu6));
	//------------------------------新闻管理结束
			//景区开始
			menu = new Privilege("景区也与游记管理",null,null);
			menu1 = new Privilege("新建景区","/scenic/scenic_addUI",menu);//m_addArticle.action
			menu2 = new Privilege("景区列表","/scenic/scenic_list",menu);
			menu3 = new Privilege("游记列表","/note/note_list",menu);
			menu4 = new Privilege("游记回收站","/note/note_list2",menu);
			menu5 = new Privilege("景区回收站","/scenic/scenic_list2",menu);
			s.save(menu);
			s.save(menu1);
			s.save(menu2);//含有批量删除、编辑、管理评论、删除
			s.save(menu3);
			s.save(menu4);
			s.save(menu5);
			
				s.save(new Privilege("批量删除", "/scenic/scenic_deleteList2", menu2));
				s.save(new Privilege("更新景区", "/scenic/scenic_editUI", menu2));
				Privilege menu21 = new Privilege("管理评论", "/scenic/scenicComment_getListBySceniId", menu2);
				s.save(menu21);
					s.save(new Privilege("删除评论", "/scenic/scenicComment_delete", menu2));
				s.save(new Privilege("删除景区", "/scenic/scenic_delete2", menu2));
			
				s.save(new Privilege("批量删除","/note/note_deleteList",menu3));
				s.save( new Privilege("删除游记","/note/note_delete",menu3));
				
			
				s.save(new Privilege("批量删除","/note/note_deleteList2",menu4));
				s.save( new Privilege("删除游记","/note/note_delete2",menu4));
				
			
				s.save(new Privilege("批量删除", "/scenic/scenic_deleteList", menu5));
				s.save(new Privilege("批量删除", "/scenic/scenic_delete", menu5));
		///////-----------------------景区与游记结束
//		//-----------栏目管理开始
//				menu = new Privilege("栏目管理",null,null);
//				menu1 = new Privilege("新建栏目","/manager/m_addtypeUI",menu);//m_addArticle.action
//				menu2 = new Privilege("栏目列表","/manager/findArticleType",menu);
//				menu3 = new Privilege("栏目排序","/manager/m_orderType",menu);
//				s.save(menu);
//				s.save(menu1);
//				s.save(menu2);
//				s.save(menu3);
////					s.save(new Privilege("删除栏目", "/manager/m_deleteType", menu2));
////					s.save(new Privilege("更新栏目", "/manager/m_findTypeById", menu2));
			
		//---栏目管理结束
//		//------------------资源管理
//				menu = new Privilege("资源管理",null,null);
//				menu1 = new Privilege("资源上传","/manager/downLoad_addUI",menu);//m_addArticle.action
//				menu2 = new Privilege("资源列表","/manager/downLoad_list",menu);
//				s.save(menu);
//				s.save(menu1);
//				s.save(menu2);
//					s.save(new Privilege("删除资源", "/manager/downLoad_delete", menu2));
//					s.save(new Privilege("更新资源", "/manager/downLoad_update", menu2));
		//----------资源管理结束
		//-------系统管里开始
					menu = new Privilege("系统管理",null,null);
					menu1 = new Privilege("管理员列表","/manager/user_adminList",menu);//m_addArticle.action
					menu2 = new Privilege("用户列表","/manager/user_userList",menu);
					menu3 = new Privilege("角色列表","/manager/role_list",menu);
//					menu4 = new Privilege("网站信息管理","/manager/weblogo",menu);
//					menu5 = new Privilege("友情链接管理","/manager/friendLink_list",menu);
					s.save(menu);
					s.save(menu1);
					s.save(menu2);
					s.save(menu3);
					s.save(menu4);
					s.save(menu5);
						s.save(new Privilege("添加管理员", "/manager/user_addAdminUI", menu1));
						s.save(new Privilege("删除管理员", "/manager/user_deleteAjax", menu1));
						s.save(new Privilege("更新管理员", "/manager/user_editAdmin", menu1));
						s.save(new Privilege("初始化管理员密码", "/manager/user_initpwAjax", menu1));
						
						s.save(new Privilege("删除用户", "/manager/user_deleteAjax", menu2));
						s.save(new Privilege("初始化用户密码", "/manager/user_initpwAjax", menu2));
						
						s.save(new Privilege("删除角色", "/manager/role_delete", menu3));
						s.save(new Privilege("修改角色", "/manager/role_edit", menu3));
						s.save(new Privilege("设置权限", "/manager/role_setPrivilege", menu3));
						
						s.save(new Privilege("修改网站信息", "/manager/topImage", menu4));
						s.save(new Privilege("添加链接", "/manager/friendLink_add", menu5));
						s.save(new Privilege("修改链接", "/manager/friendLink_edit", menu5));
						s.save(new Privilege("删除链接", "/manager/friendLink_delete", menu5));
						//----------------系统管理结束
						//------------个人信息管理
						menu = new Privilege("个人信息管理",null,null);
						menu1 = new Privilege("查看信息","/manager/user_personInfoUI",menu);//m_addArticle.action
						menu2 = new Privilege("密码修改","/manager/user_updatePwUI",menu);
						s.save(menu);
						s.save(menu1);
						s.save(menu2);
						//------------------数据统计开始
				
	}
	@Transactional
	public void install()
	{
		Session session = sf.getCurrentSession();
		//遗产类
		ScenicClass sc = new ScenicClass("遗产类");
		session.save(sc);
		session.save(new ScenicClass("自然遗产类",sc));
		session.save(new ScenicClass("文化遗产类", sc));
		session.save(new ScenicClass("自然和文化双遗产类", sc));
		session.save(new ScenicClass("生物圈保护区"));
		session.save(new ScenicClass("地质公园"));
		//A级景区
		 sc =  new ScenicClass("A级旅游景区");
		 	session.save(sc);
		 	session.save( new ScenicClass("5A级旅游景区",sc));
			session.save(new ScenicClass("4A级旅游景区",sc));
			session.save(new ScenicClass("3A级旅游景区",sc));
			session.save(new ScenicClass("2A级旅游景区",sc));
			session.save( new ScenicClass("1A级旅游景区",sc));
			
		 session.save(new ScenicClass("风景名胜区"));
		 session.save(new ScenicClass("森林公园"));
		 session.save(new ScenicClass("自然保护区"));
		 session.save(new ScenicClass("湿地公园"));
		 session.save(new ScenicClass("工农业旅游示范点"));
		 session.save(new ScenicClass("非物质文化遗产"));
		 //重点文化保护单位
		 sc =  new ScenicClass("重点文化保护单位");
		 session.save(sc);
		 session.save( new ScenicClass("古遗址", sc));
		 session.save( new ScenicClass("古墓葬", sc));
		 session.save( new ScenicClass("古建筑", sc));
		 session.save( new ScenicClass("石窟寺石石刻", sc));
		 session.save( new ScenicClass("近现代史迹", sc));
		 session.save(new  ScenicClass("近现代代表性建筑物", sc));
		 
		 session.save(new ScenicClass("红色旅游景点景区"));
		 //旅游城市和旅游强县
		 sc =  new ScenicClass("旅游城市和旅游强县");
		 session.save(sc);
		  session.save(new ScenicClass("最佳旅游城市", sc));
		  session.save(new  ScenicClass("优秀旅游城市", sc));
		  session.save(new  ScenicClass("旅游强县", sc));
		 
		 
		 //历史文化名城名镇名村
		 sc =  new ScenicClass("历史文化名城名镇名村");
		 session.save(sc);
		 session.save(new ScenicClass("历史文化名城", sc));
		 session.save(new ScenicClass("历史文化名镇", sc));
		 session.save(new ScenicClass("历史文化名村", sc));
		 
		session.save(new ScenicClass("特色景观旅游名镇(村)示范"));
		
		session.save(new City("成都市"));
		session.save(new City("自贡市"));
		session.save(new City("攀枝花市"));
		session.save(new City("泸州市"));
		session.save(new City("德阳市"));
		session.save(new City("绵阳市"));

		session.save(new City("广元市"));
		session.save(new City("遂宁市"));
		session.save(new City("内江市"));
		session.save(new City("乐山市"));
		session.save(new City("南充市"));
		session.save(new City("眉山市"));
		session.save(new City("宜宾市"));
		session.save(new City("广安市"));
		
		session.save(new City("达州市"));
		session.save(new City("雅安市"));
		session.save(new City("巴中市"));
		session.save(new City("资阳市"));
		session.save(new City("甘孜藏族自治州"));
		session.save(new City("凉山彝族自治州"));
		session.save(new City("阿坝藏族羌族自治州"));
	}
	@Test
	@Transactional
	public void getData()
	{
//		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//		sf = (SessionFactory) ac.getBean("sessionFactory");
//		Session session = sf.openSession();
//		Privilege p = (Privilege) session.get(Privilege.class, 60l);
//		System.out.println("p:"+p.getChildren().size());
		Session s = sf.getCurrentSession();
		// ==============================================================
		// 保存超级管理员用户
		User user = new User();
		user.setUsername("admin");
		user.setNickName("超级管理员");
		user.setPassword(MD5Util.md5code("admin"));
		s.save(user); // 保存
	}
	public static void main(String[] args) {
		ApplicationContext ac=null;
		try{
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		}catch(Exception e){e.printStackTrace();}
		System.out.println("sss");
		Installer installer = (Installer) ac.getBean("installer");
//		installer.install();
		installer.installprivilege();
//		installer.getData();
//		System.out.println(installer.getAllPrivilegeUrls().size());
		
		}
	
	@Transactional
	public Collection<Privilege> getAllPrivilegeUrls() {
		return sf.openSession().createQuery(
				"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")
				.list();//DISTINCT ���ظ�
	}
}
