
/* 
* @author Silver
* @date 2016年6月7日 上午9:45:40 
* Copyright  ©2016 网金社
*/

package com.wjs.treewiki.service.system.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.OSSObject;
import com.wjs.treewiki.service.system.FileStoreService;

/**
 * 文件存储阿里云实现
 * 
 * @author Silver
 * @date 2016年6月7日 上午9:45:40
 * 
 **/
//@Service("fileStoreService")
public class FileStoreAliyunServiceImpl implements FileStoreService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileStoreAliyunServiceImpl.class);

	private String endpoint;

	private String accessKeyId;

	private String accessKeySecret;

	private String bucketName;

	private String cnameUrl;

	private String group;

	public String getEndpoint() {

		return endpoint;
	}

	public void setEndpoint(String endpoint) {

		this.endpoint = endpoint;
	}

	public String getAccessKeyId() {

		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {

		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {

		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {

		this.accessKeySecret = accessKeySecret;
	}

	public String getBucketName() {

		return bucketName;
	}

	public void setBucketName(String bucketName) {

		this.bucketName = bucketName;
	}

	public String getCnameUrl() {

		return cnameUrl;
	}

	public void setCnameUrl(String cnameUrl) {

		this.cnameUrl = cnameUrl;
	}

	public FileStoreAliyunServiceImpl() {

		super();
	}

	public void init() {

		if (StringUtils.isEmpty(endpoint) || StringUtils.isEmpty(accessKeyId) || StringUtils.isEmpty(accessKeySecret)
				|| StringUtils.isEmpty(bucketName) || StringUtils.isEmpty(cnameUrl) || StringUtils.isEmpty(group)) {
			LOGGER.error("阿里云存储参数不完整：endpoint：" + ",accessKeyId:" + accessKeyId + ",accessKeySecret:" + accessKeySecret
					+ ",bucketName:" + bucketName + ",cnameUrl:" + cnameUrl + "group:" + group);
		}

		if (!cnameUrl.startsWith("http")) {
			LOGGER.error("阿里云存储参数cNameUrl需要以http或者https开始");
		}
		if (cnameUrl.endsWith("/")) {
			cnameUrl = cnameUrl.substring(0, cnameUrl.length() - 1);
		}
		group = formatPath(group);
		// 如果阿里云上面检查bucketName是否存在
		OSSClient client;
		try {
			client = getOSSClient();
		} catch (Exception e) {
			LOGGER.error("阿里云存储客户端初始化失败", e);
			return;
		}
		if (client == null) {

			LOGGER.error("初始化阿里云存储失败：endpoint：" + ",accessKeyId:" + accessKeyId + ",accessKeySecret:" + accessKeySecret
					+ ",bucketName:" + bucketName + ",cnameUrl:" + cnameUrl);
		}

		// 检查是否已经设置文件存储区（Cname）
		bucketExist();

		client.shutdown();

		// 检查是否设置外网访问文件路径
		// checkCname(); -- 金融云暂时不支持改接口
		LOGGER.info("初始化阿里云存储完成");
	}

	private OSSClient getOSSClient() {

		return new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}

	/**
	 * 查看bucketName是否已经在阿里云上面设置好
	 * 
	 * @author Silver
	 * @date 2016年6月7日 下午1:08:24
	 */

	private void bucketExist() {
		OSSClient client = null;
		BucketInfo bucketInfo = null;
		try {
			client = getOSSClient();
			bucketInfo = client.getBucketInfo(bucketName);
			if (bucketInfo == null) {
				// 不存在创建bucket
				LOGGER.error("bucket不存在{}", bucketName);
			}
		} catch (Exception e) {
			LOGGER.error("bucket不存在{}", bucketName, e);
		} finally {
			if (null != client) {
				client.shutdown();
			}
		}

	}

	public static void main(String[] args) {

	}

	/*
	 * @see com.ifaex.util.file.FileStoreService#putObject(java.lang.String,
	 * java.lang.String, java.io.File)
	 */

	public String putObject(String path, String fileName, final File file) {

		path = formatPath(path);
		fileName = formatFileName(fileName);

		final String key = group + path + fileName;
		OSSClient client = null;
		try {
			client = getOSSClient();
			client.putObject(bucketName, key, file);
		} catch (Exception e) {
			LOGGER.error("文件存储异常", e);
			return null;
		} finally {
			if (null != client) {
				client.shutdown();
			}
		}

		// 成功情况返回外网访问路径，失败情况报系统异常情况

		return cnameUrl + "/" + key;
	}

	/*
	 * @see com.ifaex.util.file.FileStoreService#putObject(java.lang.String,
	 * java.lang.String, java.io.InputStream)
	 */

	public String putObject(String path, String fileName, final InputStream inStream) {

		path = formatPath(path);
		fileName = formatFileName(fileName);

		final String key = group + path + fileName;

		OSSClient client = null;
		try {
			client = getOSSClient();
			client.putObject(bucketName, key, inStream);
		} catch (Exception e) {
			LOGGER.error("上传文件异常", e);
			return null;
		} finally {
			if (null != client) {
				client.shutdown();
			}
		}
		// 成功情况返回外网访问路径，失败情况报系统异常情况

		return cnameUrl + "/" + key;
	}

	public String putObject(String path, String fileName, final InputStream inStream, Integer time) {

		path = formatPath(path);
		fileName = formatFileName(fileName);

		final String key = group + path + fileName;

		OSSClient client = null;
		try {
			client = getOSSClient();
			client.putObject(bucketName, key, inStream);
		} catch (Exception e) {
			LOGGER.error("上传异常", e);
			return null;
		} finally {
			if (null != client) {
				client.shutdown();
			}
		}

		// 成功情况返回外网访问路径，失败情况报系统异常情况

		return cnameUrl + "/" + key;
	}

	/*
	 * @see
	 * com.wjs.common.util.file.FileStoreService#putObject(java.lang.String,
	 * java.io.InputStream)
	 */

	public String putObject(String path, final InputStream inStream) {
		if (path.startsWith("/")) {
			// 路径不能以/开头
			path = path.substring(1);
		}
		final String oosPath = path;

		OSSClient client = null;
		try {
			client = getOSSClient();
			client.putObject(bucketName, group + oosPath, inStream);
		} catch (Exception e) {
			LOGGER.error("上传异常", e);
			return null;
		} finally {
			if (null != client) {
				client.shutdown();
			}
		}

		// 成功情况返回外网访问路径，失败情况报系统异常情况

		return cnameUrl + "/" + group + path;
	}

	/**
	 * 路径设置成规范化路径 eg: xxx/xxx/xxx/ 不以/开头，以/结尾
	 * 
	 * @param path
	 * @return
	 * @author Silver
	 * @date 2016年6月7日 下午1:42:49
	 */

	private String formatPath(String path) {

		if (path.startsWith("/")) {
			// 路径不能以/开头
			path = path.substring(1);
		}
		if (!path.endsWith("/")) {
			// 路径以/结尾
			path = path + "/";
		}
		return path;
	}

	/**
	 * 文件名称前面不能带/
	 * 
	 * @param fileName
	 * @return
	 * @author Silver
	 * @date 2016年6月7日 下午1:44:54
	 */

	private String formatFileName(String fileName) {

		if (fileName.startsWith("/")) {
			fileName = fileName.substring(1);
		}
		return fileName;
	}

	/*
	 * @see com.ifaex.util.file.FileStoreService#getObject(java.lang.String,
	 * java.lang.String)
	 */

	public InputStream getObject(String path, String fileName) {
		path = formatPath(path);
		fileName = formatFileName(fileName);
		final String key = group + path + fileName;

		try {

			OSSClient client = null;
			try {
				client = getOSSClient();
				return client.getObject(bucketName, key).getObjectContent();
			} catch (Exception e) {
				LOGGER.error("文件获取异常", e);
				return null;
			} finally {
				if (null != client) {
					client.shutdown();
				}
			}

		} catch (Exception e) {
			return null;
		}
	}

	public InputStream getObject(String fileDirectory) {
		if (fileDirectory.startsWith("/")) {
			// 路径不能以/开头
			fileDirectory = fileDirectory.substring(1);
		}
		final String fileDir = fileDirectory;
		try {

			OSSClient client = null;
			try {
				client = getOSSClient();
				return client.getObject(bucketName, group + fileDir).getObjectContent();
			} catch (Exception e) {
				LOGGER.error("文件获取异常", e);
				return null;
			} finally {
				if (null != client) {
					client.shutdown();
				}
			}

		} catch (Exception e) {
			return null;
		}
	}

	public String getObjectNameIfExists(String key) {
		if (key.startsWith("/")) {
			// 路径不能以/开头
			key = group + key.substring(1);
		}
		final String oosKey = key;
		OSSObject oos;
		try {

			OSSClient client = null;
			try {
				client = getOSSClient();
				oos = client.getObject(bucketName, oosKey);
			} catch (Exception e) {
				LOGGER.error("文件获取异常", e);
				return null;
			} finally {
				if (null != client) {
					client.shutdown();
				}
			}

			if (oos != null) {
				return cnameUrl + "/" + key;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public String getObjectNameIfExists(String path, String fileName) {
		path = formatPath(path);
		fileName = formatFileName(fileName);
		final String key = group + path + fileName;
		OSSObject oos;
		try {

			OSSClient client = null;
			try {
				client = getOSSClient();
				oos = client.getObject(bucketName, key);
			} catch (Exception e) {
				LOGGER.error("文件获取异常", e);
				return null;
			} finally {
				if (null != client) {
					client.shutdown();
				}
			}

			if (oos != null) {
				return cnameUrl + "/" + key;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * @return the group
	 */

	public String getGroup() {

		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */

	public void setGroup(String group) {

		this.group = group;
	}

	public byte[] downObject(String path, String fileName) {

		path = formatPath(path);
		fileName = formatFileName(fileName);
		final String key = group + path + fileName;

		try {
			OSSClient client = null;
			try {
				client = getOSSClient();
				OSSObject oos = client.getObject(bucketName, key);
				InputStream inStream = oos.getObjectContent();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

				int c = inStream.read();
				while (c != -1) { // 必须一个字节一个字节读取。刚开始定义了一个字节数组byte[]，但是读取图片不正确。
					outputStream.write(c);
					c = inStream.read();
				}

				inStream.close();
				byte[] result = outputStream.toByteArray();
				outputStream.close();
				return result;
			} catch (Exception e) {
				LOGGER.error("文件获取异常", e);
				return null;
			} finally {
				if (null != client) {
					client.shutdown();
				}
			}

		} catch (Exception e) {
			return null;
		}
	}
}
