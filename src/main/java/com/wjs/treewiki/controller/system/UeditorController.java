package com.wjs.treewiki.controller.system;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baidu.ueditor.ActionEnter;
import com.wjs.treewiki.service.system.FileStoreService;

@Controller()
@RequestMapping(value = "/system/ueditor")
public class UeditorController {

	@Autowired
	FileStoreService fileStoreService;

	@RequestMapping()
	@ResponseBody
	public Object index(HttpServletRequest request, String action) throws Exception {

		if ("uploadimage".equals(action) || "uploadfile".equals(action)) {
			MultipartHttpServletRequest mReq = (MultipartHttpServletRequest) request;
			Map<String, Object> result = new HashMap<String, Object>();
			MultipartFile file = mReq.getFile("upfile");
			String orgFileName = file.getOriginalFilename();

			if (!orgFileName.isEmpty()) {
				try {
					String fileName = orgFileName.substring(0, orgFileName.lastIndexOf("."));
					String prefix = orgFileName.substring(orgFileName.lastIndexOf(".") + 1);
					InputStream is = file.getInputStream();
					String filePath = fileStoreService
							.putObject("ueditor/" + fileName + "_" + System.currentTimeMillis() + "." + prefix, is);
					result.put("state", "SUCCESS");
					result.put("url", filePath);
					result.put("title", orgFileName);
					result.put("original", orgFileName);
					return result;
				} catch (Exception e) {
					result.put("state", "文件上传失败!");
					result.put("url", "");
					result.put("title", "");
					result.put("original", "");
				}
			} else {
				throw new Exception("文件名为空!");
			}

		} else if ("catchimage".equals(action)) {
			String[] sources = request.getParameterValues("source[]");

			Map<String, Object> result = new HashMap<String, Object>();

			List<Map<String, Object>> filePaths = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < sources.length; i++) {
				String source = sources[i];
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("source", source);

				String filePath = "";
				InputStream is = null;
				try {
					URL url = new URL(source);
					URLConnection con = url.openConnection();
					con.setConnectTimeout(5 * 1000);
					is = con.getInputStream();

					String prefix = source.substring(source.lastIndexOf(".") + 1);
					filePath = fileStoreService
							.putObject("ueditor/catchimage" + i + System.currentTimeMillis() + "." + prefix, is);

					is.close();
					data.put("state", "SUCCESS");
				} catch (Exception e) {
					data.put("state", "FAIL");
				} finally {
				}
				data.put("url", filePath);
				filePaths.add(data);
			}
			result.put("state", "SUCCESS");
			result.put("list", filePaths);
			return result;
		}

		String rootPath = getClass().getResource("/").getPath();
		return new ActionEnter(request, rootPath).exec();
	}

}
