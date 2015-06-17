package com.tour.suse.news.action;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.tour.suse.base.BaseAction;
import com.tour.suse.entity.User;
import com.tour.suse.news.entity.News;

@Controller
@Scope("prototype")
public class NewsAction extends BaseAction<News> implements Preparable{
	
	private static final long serialVersionUID = 1L;

	private String attachmentFileName;
	
	private String attachmentContentType;
	
	private String newscontent;
	
	private String newstitle;
	
	private InputStream inputStream;
	
	private Integer newsid;
	
	private Integer pageNum ;
	
	private String fileName;
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}


	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public void setNewsid(Integer newsid) {
		this.newsid = newsid;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setNewscontent(String newscontent) {
		this.newscontent = newscontent;
	}

	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public String input(){
		if( newsid != null){
			model = newsService.getNewsById(newsid);
			
		}
		return "input";
		
	}
	
	public String save() throws IOException{
	
		if( newsid == null ){
			User user = (User) getSen().get("user");
			model.setUserid(user);
			model.setCreatetime(new Timestamp((new Date()).getTime()));
			File attachment = model.getAttachment();
			
			//资源上传
			//1.文件上传的保存路径
			if(attachment != null){
				String filepath = ServletActionContext.getServletContext().getRealPath("/attachment");
				System.out.println(filepath);
				try {
					//2.文件的输入流
					InputStream in = new FileInputStream(model.getAttachment());
					//3.构造目标文件
					System.out.println(attachmentFileName);
					File newfile = new File(filepath,attachmentFileName);
					
					//3.文件的输出流
					OutputStream os = new FileOutputStream(newfile);
		            byte[] buffer = new byte[in.available()];			
					//4.将文件out到输出流中
		            int len = 0;
		            while((len = in.read(buffer))>0){
		            	os.write(buffer, 0, len);
		            }
		            model.setAttachment(newfile);
		            in.close();
		            os.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		newsService.save(model);
		return "save";
	}
	
	public void prepareSave() {
		if(newsid != null){
			model = newsService.getNewsById(newsid);
		}else{
			model = new News();
		}
	}
	
    public String list(){
    	List<News> newslist = newsService.getAll();
    	int newsCount = newslist.size();
    	int pageCount = (newsCount-1)/pageSize +1;
    	int pageMax = 1 * pageSize-1;
    	if(pageMax<=newsCount-1){
    		getSen().put("pageMax", pageMax);
    	}else{
    		getSen().put("pageMax", newsCount-1);
    	}
		getSen().put("newslist", newslist );
		getSen().put("newsCount",newslist.size() );
		getSen().put("pageNum", 1);
		getSen().put("pageNow", 0);
		getSen().put("pageCount", pageCount);
		return "list";
		
	}
    
    public String delete(){
    	
    	try {
    		newsService.delete(newsid);
    		int newsCount = (int) getSen().get("newsCount") -1;
    		getSen().put("newsCount", newsCount);
			inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("utf-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
    	return "ajax-success";
    }

    public String page(){
    	getSen().put("pageNum", pageNum);
    	int pageNow = (pageNum-1)*pageSize ;
    	int pageMax = pageNum*pageSize-1;
    	
    	int newsCount = (int) getSen().get("newsCount");
    	getSen().put("pageNow", pageNow);
    	if(pageMax<=newsCount-1){
    		getSen().put("pageMax", pageMax);
    	}else{
    		getSen().put("pageMax", newsCount-1);
    	}
    	return "list";
    }
    
    //前段展示新闻内容
    public String shownews() {
    	model = newsService.getNewsById(newsid);
    	String filename = model.getAttachment().getName();
    	getSen().put("filename", filename);
    	return "shownews";
    }
    
    public String getDownloadAttachment() {
    	inputStream = ServletActionContext.getServletContext().getResourceAsStream("attachment/" + fileName);
    	return "success";
    } 
    
	public String execute() throws Exception {
		
		return "success";
				
	}

	public void prepare(){
	}
}
