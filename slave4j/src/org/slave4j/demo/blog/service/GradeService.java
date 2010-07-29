package org.slave4j.demo.blog.service;

import javax.annotation.Resource;

import org.slave4j.demo.blog.entity.Grade;
import org.slave4j.orm.hibernate.BaseDao;
import org.slave4j.orm.hibernate.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GradeService extends BaseService<Grade> {
	@Override
	@Resource(name = "gradeDao")
	public void setBaseDao(BaseDao<Grade> baseDao) {
		this.baseDao = baseDao;
	}
}
