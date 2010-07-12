package cn.ss.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.dao.SoftwareInfoDao;
import cn.ss.entity.SoftwareInfo;

public class SoftwareInfoService extends BasicService {
	private SoftwareInfoDao softwareInfoDao;

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
		softwareInfoDao.list(pageResult, softwareInfo, beginTime, endTime,
				name, oi, od, softwareTypeId, producer, promotionWay);
	}

	/**
	 * 查询(按照条件wap)
	 * 
	 * @param mid
	 *            机型id 可以为0:没有设置机型
	 * @param plusFine
	 *            加精0:否,1:是
	 * @param recommend
	 *            推荐0:否,1:是
	 * @return
	 */
	public List<SoftwareInfo> findAll(int mid, int plusFine, int recommend) {
		return softwareInfoDao.list(mid, plusFine, recommend, 0, 0, null, 0, 0);
	}

	/**
	 * 查询置顶软件
	 * 
	 * @return
	 */
	public List<SoftwareInfo> findAll(int mid) {
		return softwareInfoDao.list(mid, -1, -1, 0, 0, null, 0, 1);
	}

	/**
	 * 前台分类分页显示
	 * 
	 * @param pageResult
	 * @param mid
	 *            手机机型
	 * @param stid
	 *            分类id
	 * @param ty
	 *            是否过滤通用版0：显示，1：过滤
	 */
	public List<SoftwareInfo> findAll(PageResult<SoftwareInfo> pageResult,
			int mid, int stid, int isJava, int recommend) {
		return softwareInfoDao.list(mid, -1, recommend, stid, isJava,
				pageResult, 0, 0);
	}

	/**
	 * 查询最新更新
	 * 
	 * @param pageResult
	 * @param mid
	 * @param isJava
	 * @return
	 */
	public List<SoftwareInfo> findAll(PageResult<SoftwareInfo> pageResult,
			int mid, int isJava) {
		return softwareInfoDao.list(mid, -1, 2, -1, isJava, pageResult, 1, 0);
	}

	public List<SoftwareInfo> findByName(String name) {
		name = Tool.filterString(name);
		String hql = "from SoftwareInfo where name='" + name + "'";
		return dao.list(hql);
	}

	/**
	 * 排行
	 * 
	 * @param pageResult
	 * @param mid
	 * @param isJava
	 * @param ranks
	 * @return
	 */
	public void findByRank(PageResult<SoftwareInfo> pageResult, int mid,
			int isJava, int ranks) {
		softwareInfoDao.list(mid, ranks, isJava, pageResult);
	}

	/**
	 * 查询推荐
	 * 
	 * @param pageResult
	 * @param mid
	 * @param isJava
	 * @param commend
	 * @return
	 */
	public List<SoftwareInfo> findAll(PageResult<SoftwareInfo> pageResult,
			int mid, int isJava, int commend) {
		return softwareInfoDao.list(mid, -1, commend, -1, isJava, pageResult,
				0, 0);
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

	public SoftwareInfoDao getSoftwareInfoDao() {
		return softwareInfoDao;
	}

	public void setSoftwareInfoDao(SoftwareInfoDao softwareInfoDao) {
		this.softwareInfoDao = softwareInfoDao;
	}

}
