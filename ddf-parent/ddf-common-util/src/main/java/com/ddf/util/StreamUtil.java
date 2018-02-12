package com.ddf.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StreamUtil {
	
	public static byte[] readStream(InputStream inputStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len=inputStream.read(buffer))!=-1){
            outStream.write(buffer,0,len);
        }
        outStream.close();
        inputStream.close();
        return outStream.toByteArray();
    }

	
	public static List<byte[]> copy2manyBytes(InputStream inputStream,int num) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len=inputStream.read(buffer))!=-1){
            outStream.write(buffer,0,len);
        }
        outStream.close();
        inputStream.close();
        
        List<byte[]> bytes = new ArrayList<byte[]>();
		for(int i=0;i<num;i++){
			bytes.add(outStream.toByteArray());
		}
		return bytes;
	}
	
	public static byte[] subBytes(byte[] sourceByte,int size){
		byte[] targetByte = new byte[size];
		for(int i=0;i<size;i++){
			targetByte[i] = sourceByte[i];
		}
		return targetByte;
	}
	
	public static InputStream[] copyInputStream(InputStream is,int num){
		InputStream[] inputStreams = new InputStream[num];
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		byte[] buffer = new byte[1024];  
		int len;  
		try {
			while ((len = is.read(buffer)) > -1 ) {  
			    baos.write(buffer, 0, len);  
			}
			baos.flush();
			for(int i=0;i<num;i++){
				inputStreams[i] = new ByteArrayInputStream(baos.toByteArray());
			}
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStreams;
	}
}
