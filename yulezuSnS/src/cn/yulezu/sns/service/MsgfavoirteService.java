package cn.yulezu.sns.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.orm.hibernate.BaseService;
import cn.yulezu.sns.entity.Msgfavoirte;

@Service
@Transactional
public class MsgfavoirteService extends BaseService<Msgfavoirte> {
	@Override
	@Resource(name="msgfavoirteDao")
	public void setBaseDao(BaseDao<Msgfavoirte> baseDao) {
		this.baseDao = baseDao;
	}

}
