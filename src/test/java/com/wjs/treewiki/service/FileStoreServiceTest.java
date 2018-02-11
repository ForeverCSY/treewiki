
/* 
* @author Silver
* @date 2016年6月7日 下午2:17:22 
* Copyright  ©2016 网金社
*/

package com.wjs.treewiki.service;	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wjs.treewiki.JunitServiceBaseTest;
import com.wjs.treewiki.service.system.FileStoreService;



/**
 * @author Silver
 * @date 2016年6月7日 下午2:17:22 
 * 
 **/

public class FileStoreServiceTest extends JunitServiceBaseTest{
	
	@Autowired 
	private FileStoreService fileStoreService;
	@Test
	public void testPutFile(){
		String path = fileStoreService.putObject("a/", "1111.jpg", new File("D:/1.jpg"));
		System.out.println(path);
	}
	
	
	@Test
	public void testPutInStream(){
		FileInputStream inStream = null;
		try {
			inStream = new FileInputStream(new File("E://1.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(fileStoreService.putObject("a/", "1.jpg", inStream));
			
	}
	
	@Test
	public void testGetObjcet(){
		InputStream inStream = fileStoreService.getObject("a/", "1.jpg");
		try {
			FileOutputStream outStream = new FileOutputStream(new File("E://3.jpg"));
			//通过available方法取得流的最大字符数
			byte[] inOutb = new byte[inStream.available()];
			inStream.read(inOutb);  //读入流,保存在byte数组
			outStream.write(inOutb);  //写出流,保存在文件newFace.gif中
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test1() throws IOException{
	// 	System.out.println(pointService.getShareImgURL("不是吧","http://static.wjs-test.com/a/1.jpg","121212"));
	//	InputStream inStream = fileStoreService.getObject("data/121212/", "shareImg.jpg");
		InputStream inStream = fileStoreService.getObject("a/", "1.jpg");
		FileOutputStream outStream=null;
		int ch = 0;
		try {
			  outStream = new FileOutputStream(new File("E://3.jpg"));
			while((ch=inStream.read()) != -1){
				outStream.write(ch);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally{
	                  //关闭输入流等（略）
			inStream.close();
			outStream.close();
		}
	}
	
}

