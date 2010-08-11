package cn.yulezu.sns.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.orm.hibernate.BaseService;
import cn.yulezu.sns.entity.Administator;

@Service
@Transactional
public class AdministatorService extends BaseService<Administator> {
	@Override
	@Resource(name="administatorDao")
	public void setBaseDao(BaseDao<Administator> baseDao) {
		this.baseDao = baseDao;
	}

}
