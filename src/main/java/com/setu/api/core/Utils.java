package com.setu.api.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.jayway.jsonpath.ReadContext;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class Utils {

	private static Properties prop=new Properties();
	private static InputStream fileStream=null;
	private static Channel channel=null;
	private static ChannelSftp SFTPchannel=null;
	public static Instant now=null;
	public static long timeBeforeSubmission;
	
	public static String readProperty(String uri) {
		try {
			String[] array=uri.split("->",2);
			String fileName=array[0];
			String propertyName=array[1];
			InputStream input =new FileInputStream("src/test/resources/data/"+fileName);
			prop.load(input);
			return prop.getProperty(propertyName);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getRandomString(int length) {
		char[] chars= "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb=new StringBuilder(10);
		Random random=new Random();
		for(int i =0;i<length;i++) {
			char c=chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String output=sb.toString();
		return output;
	}
	
	public static String analyzeValue(String arg1) {
		String value=arg1.split("-->")[0];
		value=value.replaceAll("&nbsp;", " ");
		if(value.contains("**")) {
			value=new StringBuffer(Math.round(new Date().getTime()/10000)).toString();}
		if(value.contains("$$rndString")) {value=getRandomString(8);}
		if(value.contains("$$epochTime")) {value=getEpochTime(value);}
		//
		if(arg1.contains("-->")) {
			String fileName=(arg1.split("-->")[1].split("/")[0]);
			String key=arg1.split("/")[1];
			System.out.println(fileName+":"+key+"="+value);
			Utils.saveData(fileName,key,value);
		}
		if(arg1.contains("<--")) {
			String strPrefix=(arg1.split("<--")[0].split("/")[0]);
			String fileName=(arg1.split("<--")[1].split("/")[0]);
			String key=arg1.split("/")[1];
			value=Utils.readProperty(fileName+"->"+key);
			value=strPrefix+value;
	}
		return value;
}
	public static boolean checkDateFormat(String strDateFormat, String strDate) {
		try {
			Date sdfrmt=new SimpleDateFormat(strDateFormat).parse(strDate);
		}
		catch(ParseException e) {
			return false;
		}
		return true;
	}
	
	public static void saveData(String fileName, String key,String attributeValue) {
		try {
			FileOutputStream fileOut=null;
			FileInputStream fileIn=null;
			prop.clear();
			File file=new File("src/test/resources/data/"+fileName);
			if(!file.exists())file.createNewFile();
			fileIn=new FileInputStream(file);
			prop.load(fileIn);
			prop.setProperty(key, attributeValue);
			fileOut=new FileOutputStream(file);
			prop.store(fileOut, "PB file");
			fileOut.close();
		}
		catch(Exception e) {
			System.out.println("error while saving data");
			e.printStackTrace();
		}
	}
	
	public static String returnValueFromResponse(ReadContext resJsonContext, String jsonQuery) {
 		try {
		List<Object> output2=resJsonContext.read(jsonQuery);
 				String attributeValue=null;
		if(output2.size()>0) {
			attributeValue=String.valueOf(output2.get(0));
			return attributeValue;
		}
		else {
			return null;
		}
 		}
 		catch (Exception e) {
 			return resJsonContext.read(jsonQuery);
 			}
	}
	
	public static long sendDiffMins(String responseTime) throws Exception{
		long epoch=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(responseTime).getTime();
		now =Instant.now();
		System.out.println(now.toEpochMilli());
		long diff=now.toEpochMilli()-epoch;
		long diffMinutes=(diff/(60*1000));
		return diffMinutes;
	}
	
	public static String getEpochTime(String value) {
		return value.substring(0,value.indexOf("$"))+Long.toString(Instant.now().toEpochMilli());
	}
}