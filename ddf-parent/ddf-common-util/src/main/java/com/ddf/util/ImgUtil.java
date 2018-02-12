package com.ddf.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;

public class ImgUtil {
	private static Logger logger=LoggerFactory.getLogger(ImgUtil.class);
	public static BufferedImage getHttpImg(String httpUrl){
		HttpClient client = new HttpClient();
		//链接超时
		client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);  
		//读取超时
		client.getHttpConnectionManager().getParams().setSoTimeout(10000);
		client.getParams().setBooleanParameter("http.protocol.expect-continue", false);
		GetMethod method = new GetMethod();
		try {
			URI uri = new URI(httpUrl, false);
			method.setURI(uri);
			method.addRequestHeader("Connection", "close");
			method.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
			int code = client.executeMethod(method);
			//System.out.println(httpUrl + " status: " + code);
			if (code == HttpStatus.SC_OK) {	
				InputStream is = method.getResponseBodyAsStream();
				BufferedImage bufferedImage = ImageIO.read(is);
				return bufferedImage;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			method.releaseConnection();
			method =null;
			client = null;
		}
		return null;
	}
	
	
	/*public static InputStream rotateImg4stream(InputStream bi,String savePath){
		try {
			InputStream[] inputStreams = StreamUtil.copyInputStream(bi,2);
			InputStream queryMetadata = inputStreams[0];
			InputStream imgInputStream = inputStreams[1];
		    Metadata metadata = JpegMetadataReader.readMetadata(queryMetadata);
		    Directory exif = metadata.getDirectory(ExifIFD0Directory.class);
		    if(exif!=null){
			    if(exif.containsTag(ExifIFD0Directory.TAG_ORIENTATION)){
			    	  BufferedImage src = ImageIO.read(imgInputStream);
			          BufferedImage des = RotateImage.Rotate(src, getAngel(exif.getInt(ExifIFD0Directory.TAG_ORIENTATION)));
			          
			          Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");// 得到迭代器  
			          ImageWriter writer = (ImageWriter) iter.next(); // 得到writer  
			    
			          // 得到指定writer的输出参数设置(ImageWriteParam )  
			          ImageWriteParam iwp = writer.getDefaultWriteParam();  
			          iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩  
			          iwp.setCompressionQuality(0.98f); // 设置压缩质量参数  
			    
			          iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);  
			          FileImageOutputStream output = new FileImageOutputStream(new File(savePath));
			          writer.setOutput(output);
			          writer.write(null, new IIOImage(des, null, null), iwp);
			          InputStream input = new FileInputStream(new File(savePath));
			          return input;
			    }else{
			    	return bi;
			    }
		    }else{
		    	return bi;
		    }
		} catch (JpegProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MetadataException e) {
			e.printStackTrace();
		}
		return null;
	}*/

	/*	
	
	Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(fileExtName);// 得到迭代器  
    ImageWriter writer = (ImageWriter) iter.next(); // 得到writer  

    // 得到指定writer的输出参数设置(ImageWriteParam )  
    ImageWriteParam iwp = writer.getDefaultWriteParam();  
    iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩  
    iwp.setCompressionQuality(0.9f); // 设置压缩质量参数  

    iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED); 
    
    File file=File.createTempFile("pattern", fileExtName);
    FileImageOutputStream output = new FileImageOutputStream(file);
    writer.setOutput(output);
    writer.write(null, new IIOImage(des, null, null), iwp);
    InputStream input = new FileInputStream(file); */
    
	public static InputStream rotateImg4stream(InputStream bi,String fileExtName){
		InputStream[] inputStreams = StreamUtil.copyInputStream(bi,2);
		InputStream queryMetadata = inputStreams[0];
		InputStream sourceInputStream = inputStreams[1];
		try {
		    Metadata metadata = JpegMetadataReader.readMetadata(queryMetadata);
		    Directory exif = metadata.getDirectory(ExifIFD0Directory.class);
		    if(exif == null){
		    	return sourceInputStream;
		    }
		    
		    if(exif.containsTag(ExifIFD0Directory.TAG_ORIENTATION)&&getAngel(exif.getInt(ExifIFD0Directory.TAG_ORIENTATION))!=0){
		    	  BufferedImage src = ImageIO.read(sourceInputStream);
		          BufferedImage des = RotateImage.Rotate(src, getAngel(exif.getInt(ExifIFD0Directory.TAG_ORIENTATION)));
		          
		          ByteArrayOutputStream os = new ByteArrayOutputStream();  
		          ImageIO.write(des, fileExtName, os);  
		          InputStream is = new ByteArrayInputStream(os.toByteArray());
		          return is;
		    }else{
		    	return sourceInputStream;
		    }
		} catch (Exception e) {
			logger.info("非jpeg格式图片:"+fileExtName);
		}
		return sourceInputStream;
	}
	
	private static int getAngel(int orientation){
		if(orientation==1){
			return 0 ;
		}else if(orientation == 6){
			return 90;
		}else if(orientation == 8){
			return 270;
		}else if(orientation == 3){
			return 180;
		}
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		//String fileName = ImageUtil.saveImg("https://a1.easemob.com/1193170219178738/test1/chatfiles/30c72660-2587-11e7-ac34-dbd22ee6b7fd", "C:\\Users\\fangrs2\\Desktop");
		File file = new File("E:\\abc.jpg");
		InputStream in = new FileInputStream(file);
		InputStream ins=rotateImg4stream(in, "jpg");
		OutputStream out=new FileOutputStream("E:\\bb.jpg");
		byte[] buf = new byte[1024];  
        int len = 0;
		 while( (len = ins.read(buf)) != -1 ){  
	            out.write(buf,0,len);  
	        }  
		/*InputStream is = rotateImg4stream(in, "E:\\bb.jpg");
		byte[] bytes = StreamUtil.readStream(is);
		FileOutputStream fos = new FileOutputStream(new File("E:\\cc.jpg"));
		fos.write(bytes);
		fos.flush();
		fos.close();*/
		/*BufferedImage img = getHttpImg("http://test.image.ianjia.com/group1/M00/00/19/CgUDYlkjoWuAN97yAAJQz1ddDTs436.jpg");
		System.out.println(img.getWidth()+":"+img.getHeight());*/
	}
}
