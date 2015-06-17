package zhu.utils;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;

import com.sun.imageio.plugins.bmp.BMPImageReader;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import com.sun.imageio.plugins.png.PNGImageReader;
public class TestImgType {
	/**
	 * @param args
	 * @throws IOException 
	 */
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
////		String reg=".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
////		Pattern part= Pattern.compile(reg);
////		System.out.println(part.matcher("d://22.png").find());
//		System.out.println(TestImgType.getImageType("d://1.jpg"));
//	}
	 public static String getImageType(String path,File f) throws IOException {     
		 FileInputStream fis  = null;
		 if(path==null)
		 {fis= new FileInputStream(f); }
		 else {
			 fis= new FileInputStream(path); 
		 }
	   int leng = fis.available();     
	   BufferedInputStream buff = new BufferedInputStream(fis);     
	   byte[] mapObj = new byte[leng];     
	   buff.read(mapObj, 0, leng);     
	   
	   String type = "-1";     
	   ByteArrayInputStream bais = null;     
	   MemoryCacheImageInputStream mcis = null;     
   try {     
       bais = new ByteArrayInputStream(mapObj);     
       mcis = new MemoryCacheImageInputStream(bais);     
       Iterator itr = ImageIO.getImageReaders(mcis);  
       while (itr.hasNext()) {     
       ImageReader reader = (ImageReader)  itr.next();     
           if (reader instanceof GIFImageReader) {     
               type = "gif";     
           } else if (reader instanceof JPEGImageReader) {     
               type = "jpg";     
           } else if (reader instanceof PNGImageReader) {     
               type = "png";     
           } else if (reader instanceof BMPImageReader) {     
               type = "bmp";     
           } else if(reader instanceof JPEGImageReader)
           {
        	   type = "jpeg";
           }
       }     
   } finally {
       if (bais != null) {     
           try {     
               bais.close();     
           } catch (IOException ioe) {     
        	   ioe.printStackTrace();
        	   return "-1";
           }     
       }     
       if (mcis != null) {     
           try {     
               mcis.close();     
           } catch (IOException ioe) {     
        	   ioe.printStackTrace();
        	   return "-1";
           }     
       }     
   }     
   return type;     
 } 
}
