package cn.ss.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import cn.common.action.BasicAction;
import cn.common.util.Folder;
import cn.common.util.Tool;
import cn.ss.entity.DownloadLog;
import cn.ss.entity.Software;
import cn.ss.entity.SoftwareInfo;
import cn.ss.service.DownloadLogService;
import cn.ss.service.SoftwareInfoService;
import cn.ss.service.SoftwareService;

public class SoftwareAction extends BasicAction {
	private static final long serialVersionUID = -5649579764754613826L;
	private SoftwareService softwareService;
	private SoftwareInfoService softwareInfoService;
	private DownloadLogService downloadLogService;
	private Software software;
	/**
	 * 软件id
	 */
	private int id;
	/**
	 * 软件信息id
	 */
	private int id2;
	private int mid;

	public String delete() throws Exception {
		init();
		software = softwareService.findById(id);
		// 把softwareinfo重新加载软件数量
		SoftwareInfo softwareInfo = software.getSoftwareInfo();
		System.out.println("id2:" + id2);
		if (softwareInfo != null && softwareInfo.getId() == id2) {// 判断请求的软件的软件信息id与传过来的id2是否一致
			System.out.println("softwareInfo.getId:" + softwareInfo.getId());
			// 先删除服务器上的文件
			if (software != null
					&& !"".equals(software.getDownloadPath().trim())) {
				Tool.deleteFile(uploadPath, Folder.file, software
						.getSoftwareInfo().getId(), software.getDownloadPath());
			}
			// 删除数据库中的记录
			softwareService.delete(id);
			int number = softwareInfo.getSoftwareList() != null ? softwareInfo
					.getSoftwareList().size() : 0;
			softwareInfo.setNumber(number);
			softwareInfoService.update(softwareInfo);
		}
		return "delete";
	}

	/**
	 * 下载文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String download() throws Exception {
		init();
		try {
			software = softwareService.findById(id);
			// 把softwareinfo重新加载软件数量
			SoftwareInfo softwareInfo = software.getSoftwareInfo();
			System.out.println("id2:" + id2);
			if (softwareInfo != null && softwareInfo.getId() == id2) {// 判断请求的软件的软件信息id与传过来的id2是否一致
				String path = request.getSession().getServletContext()
						.getRealPath("/")
						+ "upload/"
						+ Folder.file
						+ "/"
						+ id2
						+ "/"
						+ software.getDownloadPath();
				System.out.println(path);
				File file = new File(path);
				if (file.exists()) {

					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition", "attachment;"
							+ " filename=" + software.getDownloadPath());
					FileInputStream fileInputStream = new FileInputStream(file);
					ServletOutputStream servletOutputStream = response
							.getOutputStream();
					byte[] b = new byte[1024];
					int len = 0;
					while ((len = fileInputStream.read(b, 0, 1024)) > -1) {

						servletOutputStream.write(b, 0, len);

					}
					servletOutputStream.flush();
					servletOutputStream.close();
					fileInputStream.close();

					// 下载记录
					DownloadLog downloadLog = new DownloadLog();
					downloadLog.setNumber(1);
					downloadLog.setSoftwareInfo(softwareInfo);
					downloadLog.setDownloadTime(new Date());
					downloadLogService.add(downloadLog);
				} else {
					//writerError(response);输出到页面（文件不存在）
				}
			}
		} catch (Exception e) {
			//writerError(response);
		}
		return null;
	}

	public void writerError(HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		// response.setContentType("text/vnd.wap.wml");
		PrintWriter write = response.getWriter();
		xmlHeader(write);
		write.println("<wml>");
		write.println("<card title = \"JavaTest\">");
		write.println("<p>下载文件不存在<a href='showsoftwareInfo.php?id=" + id
				+ "&mid=" + mid + "'>返回</a></p>");
		write.println("</card>");
		write.println("</wml>");
	}

	public void xmlHeader(PrintWriter out) {
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<!DOCTYPE wml PUBLIC \"-//WAPFORUM//DTD WML 1.1//EN\" "
				+ "\"http://www.wapforum.org/DTD/wml_1.1.xml\">");
	}

	public SoftwareService getSoftwareService() {
		return softwareService;
	}

	public void setSoftwareService(SoftwareService softwareService) {
		this.softwareService = softwareService;
	}

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SoftwareInfoService getSoftwareInfoService() {
		return softwareInfoService;
	}

	public void setSoftwareInfoService(SoftwareInfoService softwareInfoService) {
		this.softwareInfoService = softwareInfoService;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public DownloadLogService getDownloadLogService() {
		return downloadLogService;
	}

	public void setDownloadLogService(DownloadLogService downloadLogService) {
		this.downloadLogService = downloadLogService;
	}

}
