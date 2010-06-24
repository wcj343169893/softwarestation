package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.SoftwareInfo;

public class SoftwareInfoService extends BasicService {
	/**
	 * 查询所有
	 * 
	 * @param pageResult
	 * @param softwareInfo
	 */
	public void findAll(PageResult<SoftwareInfo> pageResult,
			SoftwareInfo softwareInfo, String beginTime, String endTime,
			String name, int oi, int od, int softwareTypeId, String producer,
			int promotionWay) {
		StringBuffer hql = new StringBuffer("FROM SoftwareInfo si where 1=1");
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql
					.append(" and DATE_FORMAT(si.createTime ,'%Y %c %e')>=DATE_FORMAT('"
							+ beginTime + "','%Y %c %e')");
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql
					.append(" and DATE_FORMAT(si.createTime ,'%Y %c %e') <= DATE_FORMAT('"
							+ endTime + "','%Y %c %e')");
		}
		if (name != null && !"".equals(name)) {
			name = name.replace("'", "");
			hql.append(" and si.name like '%" + name + "%'");
		}
		if (softwareTypeId > 0) {
			hql.append(" and si.softwareType.id =" + softwareTypeId + "");
		}
		if (producer != null && !"".equals(producer)) {
			producer = producer.replace("'", "");
			hql.append(" and si.producer like '%" + producer + "%'");
		}
		if (promotionWay ==3) {
			hql.append(" and si.promotionWay = 0");
		}

		// 更新softwareinfo里面的点击，下载，激活，总价 暂时未实现

		hql.append(" " + getOrderString(oi));
		hql.append(od == 0 ? " desc" : " asc");
		dao.listByPage(hql.toString(), pageResult);
	}

	public String getOrderString(int oi) {
		String str = "";
		// 新增一个无语的排序
		if (oi > -1) {
			switch (oi) {
			case 0:
				str = "ORDER BY si.id";
				break;
			case 1:// 点击
				str = "ORDER BY cl.number";
				break;
			case 2:// 下载
				str = "ORDER BY dl.number";
				break;
			case 3:// 激活
				str = "ORDER BY al.number";
				break;
			case 4:// 单价(元)
				str = "ORDER BY al.price";
				break;
			case 5:// 收入(元)
				str = "ORDER BY al.number*al.price";
				break;
			case 6:// 下载/点击
				str = "ORDER BY dl.number/cl.number";
				break;
			case 7:// 激活/下载
				str = "ORDER BY al.number/dl.number";
				break;
			case 8:// 激活/点击
				str = "ORDER BY al.number/cl.number";
				break;
			case 9:// 收入/点击
				str = "ORDER BY al.number*al.price/cl.number";
				break;
			default:
				break;
			}
		}
		return str;
	}

	/**
	 * 获取指定数据
	 * 
	 * @param id
	 * @return
	 */
	public SoftwareInfo findById(int id) {
		return (SoftwareInfo) dao.get(SoftwareInfo.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(SoftwareInfo.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param softwareInfo
	 */
	public void add(SoftwareInfo softwareInfo) {
		dao.add(softwareInfo);
	}

	/**
	 * 更新
	 * 
	 * @param softwareInfo
	 */
	public void update(SoftwareInfo softwareInfo) {
		dao.update(softwareInfo);
	}
}
