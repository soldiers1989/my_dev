package org.fmzg.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;

public class ExifTester {

	public static void main(String[] args) {
		File imgFile = new File("C:\\Users\\fangrs2\\Desktop\\16FA34A7A7EE31D78A89032ECA703AE7.jpg");
		try {
			  Metadata metadata = JpegMetadataReader.readMetadata(imgFile);
			  Directory exif = metadata.getDirectory(ExifIFD0Directory.class);
			  if(exif!=null){
				  if(exif.containsTag(ExifIFD0Directory.TAG_ORIENTATION)){
						BufferedImage src = ImageIO.read(imgFile);
				        BufferedImage des = RotateImage.Rotate(src, 90);
				        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");  
				        if (iter.hasNext()) {  
				            ImageWriter writer = iter.next();  
				            ImageWriteParam param = writer.getDefaultWriteParam();  
				          
				            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);  
				            param.setCompressionQuality(0.975f); 
				            param.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
				            FileImageOutputStream out = new FileImageOutputStream(new File(  
				                    "C:\\Users\\fangrs2\\Desktop\\IMG_0362.jpg"));  
				            writer.setOutput(out);  
				            // writer.write(bi);  
				            writer.write(null, new IIOImage(des, null, null), param);  
				            out.close();  
				            writer.dispose();  
				        }  
				       // ImageIO.write(des,"jpg", new File("C:\\Users\\fangrs2\\Desktop\\IMG_0362.jpg"));  
				  }
			  }
		} catch (JpegProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}
}
