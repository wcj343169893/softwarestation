package cn.yulezu.sns.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.orm.hibernate.BaseService;
import cn.yulezu.sns.entity.Administator;
import cn.yulezu.sns.entity.Adminrecord;

@Service
@Transactional
public class AdminrecordService extends BaseService<Adminrecord> {

	@Override
	@Resource(name = "adminrecordDao")
	public void setBaseDao(BaseDao<Adminrecord> baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 保存管理员操作记录
	 * 
	 * @param administator
	 * @param body
	 */
	public void saveRecord(Administator administator, String body) {
		Adminrecord adminrecord = new Adminrecord();
		adminrecord.setBody(body);
		adminrecord.setInsertTime(new Date());
		adminrecord.setVisible(true);
		adminrecord.setAdministator(administator);
		super.save(adminrecord);
	}
}