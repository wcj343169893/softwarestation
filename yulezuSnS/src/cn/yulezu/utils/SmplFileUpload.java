package cn.yulezu.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;


/** -- 文件上传封装
 * @author tank
 *
 */
public class SmplFileUpload {
	private Logger logger = Logger.getLogger(SmplFileUpload.class);
	
	private List<FileItem> fileList = null;
	
	private HttpServletRequest request = null;
	
	private String fileType = null;
	
	private Integer fileSize = 0;
	
	private String path = null;
	
	private Map<String,String> items = null;
	public SmplFileUpload(HttpServletRequest request) {
		super();
		this.request = request;
	}

	/** -- 将请求中的表单数据转换为列表
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FileItem> parseRequest(){
		//判断表单类型是否为文件上传
		//boolean isMultipart = FileUpload.isMultipartContent(new ServletRequestContext(request));
		//if (isMultipart) {
			try {
				//获取表单数据
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024*1024);
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(20*1024*1024);
				
				fileList = upload.parseRequest(request);
			} catch (FileUploadException e) {
				logger.error("获取文件出错!");
				return null;
			}
			
		//}
		return fileList;
	}
	
	public void setSizeMax(Integer size){
		fileSize = size;
	}
	
	public void setType(String fileType){
		this.fileType = fileType;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	/** -- 获取表单数据
	 * @return
	 */
	public Map<String,String> itemFile(){
		if (fileList==null) {
			this.parseRequest();
		}
		items = new HashMap<String, String>();
		try {
			for (FileItem file : this.fileList) {
				if (file.isFormField()&&!file.getFieldName().equals("")) {
					items.put(file.getFieldName(), file.getString("utf-8"));
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("获取文件详细出错!");
		}
		return items;
	}
	
	/** -- 保存文件.
	 * @param request
	 * @param fileAft
	 * @return
	 */
	public Map<String,String> saveAs(String fileAft){
		if (fileList==null) {
			this.parseRequest();
		}
		if (items==null) {
			this.itemFile();
		}
		if (fileSize==0||fileType==null||path==null) {
			logger.error("参数为空!fileSize,fileType,path");
			return null;
		}
		try {
			for (FileItem file : this.fileList) {
				if (!file.isFormField()) {
					if (file.getSize()<0||file.getSize()>fileSize*1024) {
						request.setAttribute("message", "上传出错!大小："+fileSize+"KB以内");
						return null;
					}
					String fileName = file.getName();
					String fileAff = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
					if (!fileType.matches(".*(" + fileAff + ").*")) {
						request.setAttribute("message", "上传出错!类型："+fileType);
						return null;
					}
					if(!file.isFormField() && null!=file && file.getSize()>0 && !"".equals(fileAff))
					{
					fileName=fileAft+"_"+new Date().getTime()+"_"+file.getSize()+"."+fileAff;
					file.write(new File(path+fileName));
					items.put(file.getFieldName(), fileName);
					}
				}
			}
		} catch (Exception e) {
			logger.error("上传文件出错!");
			e.printStackTrace();
			request.setAttribute("message", "上传出错!");
			return null;
		}
		return items;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getPath() {
		return path;
	}
}
