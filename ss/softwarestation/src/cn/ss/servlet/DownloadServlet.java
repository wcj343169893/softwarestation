package cn.ss.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.common.util.Folder;
import cn.ss.entity.DownloadLog;
import cn.ss.entity.Software;
import cn.ss.entity.SoftwareInfo;
import cn.ss.service.DownloadLogService;
import cn.ss.service.SoftwareInfoService;
import cn.ss.service.SoftwareService;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = request.getParameter("path");
//		response.setContentType("application/octet-stream");
//		response.setHeader("Content-Disposition", "attachment;" + " filename="
//				+ software.getDownloadPath());
		// ʵ����SmartUpload����
		SmartUpload smartUpload = new SmartUpload();
		// ��ʼ��SmartUpload���󣬽�request,response����
		smartUpload.initialize(this.config, request, response);
		// ���ò��Զ���
		smartUpload.setContentDisposition(null);
		try {
			// ����Ҫ���ص��ļ���·������������
			smartUpload.downloadFile(path,"application/octet-stream");
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

	}

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	public ServletConfig getConfig() {
		return config;
	}

	public void setConfig(ServletConfig config) {
		this.config = config;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
