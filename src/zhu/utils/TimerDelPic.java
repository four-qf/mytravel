package zhu.utils;

import java.io.File;
import java.io.Serializable;
import java.util.TimerTask;

import org.springframework.stereotype.Controller;
@Controller
public class TimerDelPic extends TimerTask{
	private File file=null;
	public TimerDelPic(){}
	public TimerDelPic(File temfile){file=temfile;}
	@Override
	public void run() {
		//执行删除当前图片
		try {
			System.out.println("come"+file.getName());
			if(file!=null && file.exists())
			{
				System.out.println("删除了图片:"+file.delete());;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}
