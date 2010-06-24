package cn.ss.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ss.entity.SoftwareInfo;

/**
 * 处理软件信息
 * 
 * @author 文朝军
 * 
 */
public class SoftwareInfoDao extends HibernateDaoSupport {
	public List<SoftwareInfo> list() {//涉及到高级查询Criteria
		return null;
	}
}
