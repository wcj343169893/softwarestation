package cn.ss.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cn.common.action.BasicAction;
import cn.common.util.Folder;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.dto.IndexDTO;
import cn.ss.entity.PhoneModel;
import cn.ss.entity.SoftwareInfo;
import cn.ss.service.PhoneBrandService;
import cn.ss.service.PhoneModelService;
import cn.ss.service.SoftwareInfoService;
import cn.ss.service.SoftwareTypeService;

/**
 * ������ҳ��Ϣ
 * 
 * @author �ĳ���
 * 
 */
public class IndexAction extends BasicAction {
	private SoftwareInfoService softwareInfoService;
	private PhoneBrandService phoneBrandService;
	private PhoneModelService phoneModelService;
	private SoftwareTypeService softwareTypeService;
	private IndexDTO indexDTO;
	/**
	 * ����id
	 */
	private int mid;
	private int c;
	private int isJava;
	private int p;
	private PageResult<SoftwareInfo> pageResult = new PageResult<SoftwareInfo>();
	private PhoneModel phoneModel;
	private int commend;
	/**
	 * 0���հ�.1���°�.2���ܰ�
	 */
	private int ranks;

	/**
	 * ��ҳ�Ű�
	 */
	private Map<String, String> maps = new HashMap<String, String>();
	/**
	 * ��ҳ�����Ű�
	 */
	private String index_str;
	private String fileName;

	// private int no;

	/**
	 * д����ҳ�Ű��ļ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String write() throws Exception {
		init();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (index_str != null && !"".equals(index_str.trim())) {
			try {
				//index_str = new String(index_str.getBytes("ISO-8859-1"),
					//	"GB2312");
				index_str=URLDecoder.decode(index_str,"UTF-8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Tool.writeFile(index_str, path, Folder.index, fileName);
			out.print("1");
		}
		return null;
	}

	/**
	 * ��ȡ��ҳ�Ű��ļ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String read() throws Exception {
		init();
		maps = Tool.readFile(path, Folder.index);
		return "index";
	}

	public String delete() throws Exception {
		init();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (Tool.deleteFile(path, Folder.index, fileName)) {
			out.print(2);
		} else {
			out.print(0);
		}
		return null;
	}

	@Override
	public String execute() throws Exception {
		init();
		maps = Tool.readFile(path, Folder.index);
		if (maps != null && maps.size() > 0) {
			int size = maps.size();
			Random ran = new Random();
			int index = ran.nextInt(size);
			Set set = maps.keySet();
			Object[] obj = set.toArray();
			index_str = maps.get(obj[index]);
		}
		if (index_str != null) {
			index_str = index_str.replaceAll("id=", "mid=" + mid + "&amp;id=");
		}
		request.setAttribute("index_str", index_str);

		//		
		// // 1.��ѯ����Ӿ� plusFine
		// List<SoftwareInfo> sipList = softwareInfoService.findAll(mid, 1, -1);
		// indexDTO.setSoftware_plusFineList(sipList);
		// // 2.�Ƽ� recommend
		// List<SoftwareInfo> sirList = softwareInfoService.findAll(mid, -1, 1);
		// SoftwareInfo s_recommend = null;
		// Random ran = new Random();
		// int size = sirList.size();
		// for (int i = 0; i < size; i++) {
		// int o = ran.nextInt(sirList.size());
		// if (sirList.get(o) != null) {
		// s_recommend = sirList.get(o);
		// break;
		// }
		// }
		// request.setAttribute("s_recommend", s_recommend);
		// indexDTO.setSoftware_recommendList(sirList);
		// // 3.�ö����20��
		// indexDTO.setSoftware_topsList(softwareInfoService.findAll(mid));

		// 4.��������б�
		indexDTO.setSoftwareTypeList(softwareTypeService.findAll(null));
		if (mid > 0) {
			indexDTO.setModel(phoneModelService.findById(mid));
		} else {
			indexDTO.setModel(null);// ���û���
		}
		return "success";
	}

	/**
	 * ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public String rank() throws Exception {
		phoneModel = null;
		if (mid != 0) {
			phoneModel = phoneModelService.findById(mid);
			if (phoneModel == null
					|| phoneModel.getPhoneseries().getOs().getName()
							.toLowerCase().equals("java")) {
				isJava = 2;
			}
		}
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		softwareInfoService.findByRank(pageResult, mid, isJava, ranks);
		return "rank";
	}

	/**
	 * ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public String news() throws Exception {
		phoneModel = null;
		if (mid != 0) {
			phoneModel = phoneModelService.findById(mid);
			if (phoneModel == null
					|| phoneModel.getPhoneseries().getOs().getName()
							.toLowerCase().equals("java")) {
				isJava = 2;
			}
		}
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		softwareInfoService.findAll(pageResult, mid, isJava);
		return "news";
	}

	/**
	 * �Ƽ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String commend() throws Exception {
		phoneModel = null;
		if (mid != 0) {
			phoneModel = phoneModelService.findById(mid);
			if (phoneModel == null
					|| phoneModel.getPhoneseries().getOs().getName()
							.toLowerCase().equals("java")) {
				isJava = 2;
			}
		}
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		softwareInfoService.findAll(pageResult, mid, isJava, 1);
		return "commend";
	}

	public SoftwareInfoService getSoftwareInfoService() {
		return softwareInfoService;
	}

	public void setSoftwareInfoService(SoftwareInfoService softwareInfoService) {
		this.softwareInfoService = softwareInfoService;
	}

	public PhoneBrandService getPhoneBrandService() {
		return phoneBrandService;
	}

	public void setPhoneBrandService(PhoneBrandService phoneBrandService) {
		this.phoneBrandService = phoneBrandService;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public PhoneModelService getPhoneModelService() {
		return phoneModelService;
	}

	public void setPhoneModelService(PhoneModelService phoneModelService) {
		this.phoneModelService = phoneModelService;
	}

	public IndexDTO getIndexDTO() {
		return indexDTO;
	}

	public void setIndexDTO(IndexDTO indexDTO) {
		this.indexDTO = indexDTO;
	}

	public SoftwareTypeService getSoftwareTypeService() {
		return softwareTypeService;
	}

	public void setSoftwareTypeService(SoftwareTypeService softwareTypeService) {
		this.softwareTypeService = softwareTypeService;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getIsJava() {
		return isJava;
	}

	public void setIsJava(int isJava) {
		this.isJava = isJava;
	}

	public PageResult<SoftwareInfo> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult<SoftwareInfo> pageResult) {
		this.pageResult = pageResult;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public PhoneModel getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(PhoneModel phoneModel) {
		this.phoneModel = phoneModel;
	}

	public int getCommend() {
		return commend;
	}

	public void setCommend(int commend) {
		this.commend = commend;
	}

	public int getRanks() {
		return ranks;
	}

	public void setRanks(int ranks) {
		this.ranks = ranks;
	}

	public Map<String, String> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, String> maps) {
		this.maps = maps;
	}

	public String getIndex_str() {
		return index_str;
	}

	public void setIndex_str(String indexStr) {
		index_str = indexStr;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
