package zhu.test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.suse.entity.City;
import com.tour.suse.entity.Note;
import com.tour.suse.entity.NoteComment;
import com.tour.suse.entity.Scenic;
import com.tour.suse.entity.ScenicClass;
import com.tour.suse.entity.ScenicComment;
import com.tour.suse.entity.User;
@Transactional
@Service
public class TestService {
	@Resource
	private SessionFactory sessionFactory;

	
	public void add()
	{
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(
	"select jq from Scenic as jq,ScenicClass as lb where jq. = lb.id").list();
//	List<Object>  sList=  session.createQuery("" +
//	"from Scenic s,ScenicClass sc where sc.id=? and sc in elements(s.scenicClasses)")
//	.setParameter(0, 3l)
//	.list();
//	for(int i=0;i<sList.size();i++)
//	{
//		Object [] model = (Object[]) sList.get(i);
//		Scenic s  = (Scenic) model[0];
//		ScenicClass sc = (ScenicClass) model[1];
//		System.out.println(s.getName()+"ss:"+sc.getName());
//	}
//	System.out.println(sList);
//				.setParameter(1, "").list();
	}

	
	public void add2()
	{
		String content = "旅游景区市政基础规划设计环境不同于城市，因此不可依照惯性思维照搬城市要求，来进行旅游景区市政基础规划设计。一般来说，旅游景区距离城市较远，地势复杂，基础建设环境无法达到城市建设所要求的“五通一平”或“七通一平”。同时，旅游景区与城市的性质不同，其市政基础设施建设应充分考虑周边和当地的地理与资源条件，结合景区特点，对方方面面的问题做出科学合理地判断，准确掌握规划区的现状优势与不足，立足规划目标，找到经济、合理的规划设计方法，从而使整体规划设计思路更加明确、清晰、合理。1、旅游景区的季节性不容忽视旅游具有季节性，旺季人口聚集但常驻人口少，森";
		Session  session = sessionFactory.getCurrentSession();
		//景区评论
//		ScenicComment scenicComment= 
		Set<ScenicComment> scList = new HashSet<ScenicComment>(); 
		
		Set<ScenicClass> sc = new HashSet<ScenicClass>();
		sc.add(new ScenicClass((long)(Math.random()*10)));
		String brefJS ="五彩池是位于黄龙最上端的钙化彩池群，共有693个钙池，距入口4166米。这里，青山吐翠，5588米高的岷山主峰雪宝鼎巍然屹立在眼前，漫步池边，无数块大小不等，形状各异的彩池宛如盛满了各色五彩颜料的水彩板，兰绿、海兰、浅兰等等，艳丽奇绝。 " +
				"五彩池海拔3900米，是黄龙景点最高处。走到这里需要一定的体力，很多人因为体力不支而中途下山放弃了这个最美丽的看点，所以上山时，一定不要走太快，保存力。 " +
				"黄龙位于四川省阿坝州松潘县境内岷山山脉南麓 ,距成都450公里，距九寨沟120公里。历经千百年岁月形成的地表钙华婉蜒在雪山峡谷中，宛若黄色“巨龙”，故得名黄龙。黄龙海拔在3000米以上，3000余个碧透斑澜的彩池，宛如五彩珍珠镶嵌在原始森林中，被誉为“人间瑶池”。1992年被列为《世界遗产名录》。 " +
				"由于水是富含钙离子，水流缓慢，所以经过长年的沉积，水中渐渐生成不同形状的沉积。 " +
				"湖水色彩的起因，主要源于湖水对太阳光的散射、反射和吸收。太阳光或自然光是由不同波长的单色光组合而成的复色光，在光谱中，由红光至紫光，波长逐渐减小。九寨沟的湖水呈现艳丽的蓝绿色，说明湖水中短波长的散射远大于长波长，这就是瑞利散射（RAYLEIGHSCATTERING）效应。瑞利散射效应在九寨沟的湖水中之所以尤为突出，主要是因沟内植被郁闭度高、水循环条件较畅通以及石灰华对悬移质的固定作用，从而使水中悬浮物、有机物、浮游生物极少， " +
				"湖水的洁净度和透明度极高。 " +
				"HCO3-等离子也有增强短波光散射的作用。同时由于湖水透明度高，湖底的灰白色钙华、黄绿色藻类对透射光的选择性吸收和反射，也增加了湖水色彩的层次和变化。另外，九寨沟的湖泊处于地形起伏很大的深切峡谷中，不同地段同一时间、同一地段不同时间，太阳光的入射角及入射量、湖水表面对光的反射状况和湖水的透明度都有很大的变化，因此也造成了湖水色彩的更加变幻多姿。";
		for(int i=0;i<80;i++)
		{
			//景区评论
			for(int j=0;j<20;j++)
				scList.add(new ScenicComment("这景区景色不错阿","长期以来，我校高度重视在校学生的创新意识培养和创业技能提升，此次活动也引起我校师生的广泛关注和参与，很好地促进我校学生创新意识的培养、创业技能的提升，提前做好就业创业准备，为毕业后走上工作岗位打下良好的基础，也为推进国家的创新驱动战略培养未来的创新人才。 "
						,new Date(), 0.0f, 0.0f, 0.0f,0.0f, 0.0f, 1, new User(4l)));
			for (ScenicComment SC: scList)
			{
				session.save(SC);
			}//景区
			Scenic s =new Scenic("风景区"+i, "国家级",brefJS, 
					"images/a43471.jpg;images/d80243.jpg;images/I40927.jpg;images/m21263.jpg;images/a61649.png;images/g54655.jpg;images/e70600.jpg;",
					"创青春”创新创业大赛的前身“挑战杯”创业计划竞赛在我省已成功举办七届。近年来，创新创业竞赛规模不断扩大，工作机制日益成熟，社会影响日渐深远，在培养大学生的创新思维、创业精神、创造能力等方面发挥了重要作用。为进一步提升创新创业大赛的科学化水平，增强比赛的实践性和可操作性，今年的“创青春”创新创业大赛新增了“创业实践挑战赛”、“公益创业赛”，与“挑战杯”四川省大学生创业计划竞赛共同构成了本届大赛的三项主体赛事。作品类别涵盖农林、生物医学、环境科学、电子信息、材料机械等众多类别，涉及经济、生活、文化、社会等多个领域。评委由科研机构及企业专家、NGO组织负责人、YBC导师担任大赛评委，评审方式更加科学，评审制度更加完善，评审流程更加规范。 ",
					new Date(), new User(4l), new City(1l), scList,sc, 1, 0.0f, 0.0f, 0, 0, 0, 0, 0, 0.0f, 0.0f, 0.0f);
			session.save(s);
			//游记评论
			Set<NoteComment> ncList = new HashSet<NoteComment>();
			for(int j=0;j<20;j++)
			{
				NoteComment nc = new NoteComment(content, new Date(),new User(4l));
				session.save(nc);
				ncList.add(nc);
			}
			Note n = new Note( "游记"+i, 0, 0, 0, 0, "noteImages/w10934.jpg;", content,new Date(), 1, new User(4l), ncList,s);
			session.save(n);
		}
		
	}
	
	
	
	public void add3()
	{
		Session  session = sessionFactory.getCurrentSession();
		for(int j=0;j<100;j++)
			for(int i=0;i<100;i++)
			{}
	}
}
