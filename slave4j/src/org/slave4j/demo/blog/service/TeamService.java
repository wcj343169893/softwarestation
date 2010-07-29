package org.slave4j.demo.blog.service;

import javax.annotation.Resource;

import org.slave4j.demo.blog.entity.Team;
import org.slave4j.orm.hibernate.BaseDao;
import org.slave4j.orm.hibernate.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamService extends BaseService<Team>
{
	@Override
	@Resource(name="teamDao")
	public void setBaseDao(BaseDao<Team> baseDao)
	{
		this.baseDao = baseDao;
	}
}
