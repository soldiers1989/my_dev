package com.ddf.util;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;


public class FileRepository {
	private static Log logger = LogFactory.getLog(FileRepository.class);
	private static Byte[] lock=new Byte[0];

	/**
	 * 
	 * @param relativeDir
	 * @param in
	 * @param suffix
	 * @param mark
	 * @return 返回保存的文件的相对路径
	 * @throws IOException
	 */
	public static String saveImageWithWaterMark(String relativeDir, InputStream photoIn, String suffix) throws IOException {
		Assert.notNull(photoIn, "没有输入流。");
		Assert.notNull(relativeDir, "没有文件的目的目录。");
		suffix = suffix == null ? "" : suffix;
		String relativefile = getRandomFilenameByDate(relativeDir, suffix);
		saveFile(photoIn, relativefile);

		return relativefile;
	}

	public static File newFile(String file) {
		return new File(getAbsolutePath(file));
	}


	private static String getAbsolutePath(String relativePath) {
		if (relativePath == null)
			return null;
		if (!relativePath.startsWith("/"))
			relativePath = "/" + relativePath;
		if (relativePath.endsWith("/"))
			StringUtils.removeEnd(relativePath, "/");
		return relativePath;
	}


	public static OutputStream getOutputStream(String relativeFile) throws FileNotFoundException {
		if (relativeFile == null)
			return null;
		else {
			try {
				return FileUtils.openOutputStream(new File(getAbsolutePath(relativeFile)));
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			return null;
		}
	}


	public static final String getRandomFilename(String relativeDir, String suffix) {
		Assert.notNull(relativeDir, "不能为null");
		suffix = suffix == null ? "" : suffix;
		synchronized(lock){
			return relativeDir + "/" + System.currentTimeMillis() + suffix;
		}
	}

	public static final String getRandomFilenameByDate(String relativeDir, String suffix) {
		String uniqueString = UniqueStringGenerator.getUniqueString();
		relativeDir = mkDirByDate(relativeDir);
		Assert.notNull(relativeDir, "不能为null");
		suffix = suffix == null ? "" : suffix;
		return relativeDir + "/" + uniqueString + suffix;
	}


	public static synchronized void saveFile(InputStream sourceFileIn, String targetFile) throws IOException {
		File file = new File(getAbsolutePath(targetFile));
		FileUtils.copyInputStreamToFile(sourceFileIn, file);
	}


	public static String mkDirByDate(String relativeDir) {
		relativeDir = relativeDir == null ? "" : relativeDir;
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM");
		
		relativeDir=StringUtils.removeEnd(relativeDir, "/");
		relativeDir = relativeDir + "/" + format.format(now);
		format.applyPattern("dd.HH");
		relativeDir = relativeDir + "/" + format.format(now);
		File dest = new File(getAbsolutePath(relativeDir));
		if (!dest.exists()) {
			// directory does not exist, create it.
			if (!dest.mkdirs())
				// make dir failed.
				return null;
		}
		return relativeDir;
	}

	public static boolean deleteFile(String file) {
		if (file == null)
			return false;
		File file2 = new File(getAbsolutePath(file));
		if (file2.exists())
			try {
				FileUtils.forceDelete(file2);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		return false;
	}

}
