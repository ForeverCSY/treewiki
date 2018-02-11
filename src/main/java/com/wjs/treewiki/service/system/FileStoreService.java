package com.wjs.treewiki.service.system;

import java.io.File;
import java.io.InputStream;

public interface FileStoreService {
	
	/**
	 * 上传本地文件
	 * @param path  root/dir1/
	 * @param fileName 1.jpg
	 * @param file
	 * @return 文件访问路径地址
	 * @author Silver 
	 * @date 2016年6月7日 下午1:18:10
	 */
	public String putObject(String path, String fileName, File file) ;
	
	/**
	 * 上传本地流
	 * @param path
	 * @param fileName
	 * @param inStream
	 * @return 文件访问路径地址
	 * @author Silver 
	 * @date 2016年6月7日 下午1:18:25
	 */
	public String putObject(String path, String fileName, InputStream inStream) ;
	
	
	/**
	 * 
	 * 上传本地流
	 * @param path
	 * @param fileName
	 * @param inStream
	 * @param time
	 * @return  文件访问路径地址
	 * @author lianyp
	 * @date 2017年4月10日 下午5:29:50
	 */
	public String putObject(String path, String fileName, InputStream inStream,Integer time) ;

	/**
	 * 下载文件流
	 * @param path
	 * @param fileName
	 * @return
	 * @author Silver 
	 * @date 2016年6月7日 下午2:14:49
	 */
	public InputStream getObject(String path, String fileName) ;
	
	/**
	 * 
	 * 下载文件
	 * 【上面的方法测试不通过。
	 * 	首先把阿里云上的文件内容读取到byte[]中，然后关闭阿里云客户端，返回byte[]。
	 * 】 
	 * 【注意：营销平台用可以，如果是客户下载会把内存耗尽】
	 * @param path
	 * @param fileName
	 * @return
	 * @author zhaohj@wjs.com 
	 * @date 2017年11月14日 下午7:05:03
	 */
	public byte[] downObject(String path, String fileName);
	/**
	 * 下载文件流 
	 * @param fileDirectory
	 * @return
	 * @author huanglinlong 
	 * @date 2016年6月20日 下午6:31:56
	 */
	public InputStream getObject(String fileDirectory);
	/**
	 * 
	 * 判断文件是否存在 如果存在返回文件地址 否则返回空
	 * @param path
	 * @param fileName
	 * @return
	 * @author huanglinlong 
	 * @date 2016年6月20日 上午11:23:09
	 */
	public String getObjectNameIfExists(String path, String fileName);
	/**
	 * 上传本地流
	 * @param path
	 * @param fileName
	 * @param inStream
	 * @return 文件访问路径地址
	 * @author Silver 
	 * @date 2016年6月7日 下午1:18:25
	 */
	public String putObject(String path , InputStream inStream) ;

	
	/** 
	 *判断文件是否存在 如果存在返回文件地址 否则返回空
	 * @param key
	 * @return
	 * @author huanglinlong 
	 * @date 2016年7月8日 下午12:08:06 
	 */
	
	String getObjectNameIfExists(String key);
}
