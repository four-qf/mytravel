package zhu.utils;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//map���� �Ƚ�ת����
public class CompareSort implements Comparator<  Map.Entry<String, Float> > {

	@SuppressWarnings("unchecked")
	public int compare(  Map.Entry<String, Float> o1,  Map.Entry<String, Float> o2) {
		Map.Entry<String,Float> map1=(Map.Entry<String,Float>)o1;  
	     Map.Entry<String,Float> map2=(Map.Entry<String,Float>)o2;  
	     if((map1.getValue()- map2.getValue())>0)//从大到小
	    	 return  -2 ;
	     else
	    	 return 2;
	}

}
