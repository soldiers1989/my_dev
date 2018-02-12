package com.ddf.util;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.lang.Rational;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;

public class ImageGeo {

	public double lat = 0.0;
	public double lon = 0.0;
	public double alt = 0.0;
	public boolean error = false;

	public ImageGeo(String filename) {
		try {
			error = false;
			File jpegFile = new File(filename);
			Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);

			GpsDirectory gpsdir = (GpsDirectory) metadata.getDirectory(GpsDirectory.class);
			Rational latpart[] = gpsdir.getRationalArray(GpsDirectory.TAG_GPS_LATITUDE);
			Rational lonpart[] = gpsdir.getRationalArray(GpsDirectory.TAG_GPS_LONGITUDE);
			String northing = gpsdir.getString(GpsDirectory.TAG_GPS_LATITUDE_REF);
			String easting = gpsdir.getString(GpsDirectory.TAG_GPS_LONGITUDE_REF);

			try {
				alt = gpsdir.getDouble(GpsDirectory.TAG_GPS_ALTITUDE);
			} catch (Exception ex) {
			}

			double latsign = 1.0d;
			if (northing.equalsIgnoreCase("S"))
				latsign = -1.0d;
			double lonsign = 1.0d;
			if (easting.equalsIgnoreCase("W"))
				lonsign = -1.0d;
			lat = (Math.abs(latpart[0].doubleValue()) + latpart[1].doubleValue() / 60.0d + latpart[2].doubleValue() / 3600.0d) * latsign;
			lon = (Math.abs(lonpart[0].doubleValue()) + lonpart[1].doubleValue() / 60.0d + lonpart[2].doubleValue() / 3600.0d) * lonsign;

			if (Double.isNaN(lat) || Double.isNaN(lon))
				error = true;
		} catch (Exception ex) {
			error = true;
		}
		System.out.println(filename + ": (" + lat + ", " + lon + ")");
	}

	/*public static String splitJoinImg(String img_url, ImgLocation imgLocation) {

		try {
			String category = "m";
			if (imgLocation == ImgLocation.BIG_HOUSE_DETAIL||imgLocation == ImgLocation.HOUSE_NEWS_BIG||imgLocation == ImgLocation.NEW_HOUSE_HX) {
				category = "t";
			}
			String img_size_str = "";
			String img_size = imgLocation.getImgSize();
			if (!StringUtils.isEmpty(img_size)) {
				for (ImgLocation location : ImgLocation.values()) {
					if(location==imgLocation){
						img_size_str = "." + category + img_size;
						break;
					}
				}
				if (!StringUtils.isEmpty(img_size_str) && !StringUtils.isEmpty(img_url) && img_url.indexOf(".") > 0) {
					String img_src_1 = img_url.substring(0, img_url.lastIndexOf("."));
					String img_src_2 = img_url.substring(img_url.lastIndexOf("."), img_url.length());
					img_url = img_src_1 + img_size_str + img_src_2;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img_url;
	}*/
}
