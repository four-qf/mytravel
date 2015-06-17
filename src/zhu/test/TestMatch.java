package zhu.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMatch {

	/**
	 * @param args
	 *<img src="/travel/NoteUploads/aaaa/a49124.jpg" alt="" />
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String regEx_img = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
//			"<img.*src\\s*=\\s*(.*?)[^>]*?>"; 
		 Pattern p_image = Pattern.compile 
                (regEx_img,Pattern.CASE_INSENSITIVE);   
		 Matcher  m_image = p_image.matcher("<img src=\"/zhu/s.jpg\" alt= />"); 
		 if(m_image.find())
		 {
			 System.out.println("匹配到："+m_image.group());
			 System.out.println("匹配到："+m_image.group(1).split("\\/")[1]);
		 }
		 System.out.println("aaasasasa<img src=\"/zhu/s.jpg\" alt= />".contains(m_image.group()));
	}

}
