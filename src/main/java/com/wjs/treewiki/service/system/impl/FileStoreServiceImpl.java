package com.wjs.treewiki.service.system.impl;

import java.io.File;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.wjs.treewiki.service.system.FileStoreService;


//@Service("fileStoreService")
public class FileStoreServiceImpl implements FileStoreService {

	@Override
	public String putObject(String fileFullName, InputStream is) {
		
		System.out.println("path--->" + fileFullName);
		return "https://www.wjs.com/themes/web/images/base/logo.png";
	}

	@Override
	public String putObject(String path, String fileName, File file) {
		return null;
	}

	@Override
	public String putObject(String path, String fileName, InputStream inStream) {
		return null;
	}

	@Override
	public String putObject(String path, String fileName, InputStream inStream, Integer time) {
		return null;
	}

	@Override
	public InputStream getObject(String path, String fileName) {
		return null;
	}

	@Override
	public byte[] downObject(String path, String fileName) {
		return null;
	}

	@Override
	public InputStream getObject(String fileDirectory) {
		return null;
	}

	@Override
	public String getObjectNameIfExists(String path, String fileName) {
		return null;
	}

	@Override
	public String getObjectNameIfExists(String key) {
		return null;
	}

}
