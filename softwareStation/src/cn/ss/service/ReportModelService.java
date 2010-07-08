package cn.ss.service;

import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.ReportModel;

public class ReportModelService extends BasicService {
	/**
	 * 分页查询所有
	 * 
	 * @param pageResult
	 * @param account
	 */
	public void findAll(PageResult<ReportModel> pageResult,
			ReportModel reportModel, String beginTime, String endTime) {
		StringBuffer hql = new StringBuffer("from ReportModel r where 1=1");
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql
					.append(" and DATE_FORMAT(r.reportTime,'%y %m %d') >= DATE_FORMAT('"
							+ beginTime + "','%y %m %d')");
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql
					.append(" and DATE_FORMAT(r.reportTime,'%y %m %d') <= DATE_FORMAT('"
							+ endTime + "','%y %m %d')");
		}
		if (reportModel != null) {
			if (reportModel.getBrandName() != null
					&& !"".equals(reportModel.getBrandName().trim())) {
				hql.append(" and r.brandName like '%"
						+ reportModel.getBrandName() + "%'");
			}
			if (reportModel.getModelName() != null
					&& !"".equals(reportModel.getModelName().trim())) {
				hql.append(" and r.modelName like '%"
						+ reportModel.getModelName() + "%'");
			}
			if (reportModel.getDeal() != null) {
				if (reportModel.getDeal() == 0 || reportModel.getDeal() == 1) {
					hql.append(" and r.deal = " + reportModel.getDeal());
				}
			}
		}
		hql.append(" order by r.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * 根据名称查询报告
	 * 
	 * @param model
	 *            机型
	 * @param brand
	 *            品牌
	 * @return
	 */
	public List<ReportModel> findAll(String model, String brand) {
		String sql = "from ReportModel rm where 1=1 ";
		if (model != null && !"".equals(model.trim())) {
			sql += " and rm.brandName='" + brand + "'";
		}
		if (brand != null && !"".equals(brand.trim())) {
			sql += " and rm.modelName='" + model + "'";
		}
		return dao.list(sql);
	}

	/**
	 * 获取指定数据
	 * 
	 * @param id
	 * @return
	 */
	public ReportModel findById(int id) {
		return (ReportModel) dao.get(ReportModel.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(ReportModel.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param reportModel
	 */
	public void add(ReportModel reportModel) {
		dao.add(reportModel);
	}

	/**
	 * 更新
	 * 
	 * @param ReportModel
	 */
	public void update(ReportModel reportModel) {
		dao.update(reportModel);
	}
}
