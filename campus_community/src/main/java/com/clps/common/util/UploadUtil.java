/**
 * Project Name:campus_community
 * File Name:UploadUtil.java
 * Package Name:com.clps.common.util
 * Date:2017年5月12日下午10:37:59
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.common.util;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:UploadUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年5月12日 下午10:37:59 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@SuppressWarnings("all")
public final class UploadUtil {
	/**
	 * saveFile:(这里用一句话描述这个方法的作用). <br/>
	 * 
	 * @author Charles
	 * @param request
	 *            当前请求
	 * @param file
	 *            所要上传的文件
	 * @param filePath
	 *            所要上传的服务器路径
	 * @param localPath
	 *            所要上传的工作空间路径
	 * @return
	 * @since JDK 1.8
	 */
	public static boolean saveFile(HttpServletRequest request, MultipartFile file, String filePath, String localPath) {
		File newFile = null;
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				if (filePath != null) {
					newFile = new File(filePath);
					// 文件所在目录不存在就创建
					if (!newFile.getParentFile().exists()) {
						newFile.getParentFile().mkdirs();
					}
					// 转存文件
					file.transferTo(newFile);
					if (localPath != null) {
						File localFile = new File(localPath);
						if (!localFile.getParentFile().exists()) {
							localFile.getParentFile().mkdirs();
						}
						// 复制文件到工作空间
						FileUtils.copyFile(newFile, localFile);
					}
				} else {
					if (localPath != null) {
						File localFile = new File(localPath);
						if (!localFile.getParentFile().exists()) {
							localFile.getParentFile().mkdirs();
						}
						file.transferTo(localFile);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
}
