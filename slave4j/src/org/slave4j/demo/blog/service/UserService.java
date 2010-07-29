package org.slave4j.demo.blog.service;

import java.util.List;

import javax.annotation.Resource;

import org.slave4j.demo.blog.dao.GradeDao;
import org.slave4j.demo.blog.dao.TeamDao;
import org.slave4j.demo.blog.entity.Grade;
import org.slave4j.demo.blog.entity.Team;
import org.slave4j.demo.blog.entity.User;
import org.slave4j.orm.hibernate.BaseDao;
import org.slave4j.orm.hibernate.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends BaseService<User> {
	@Override
	@Resource(name = "userDao")
	public void setBaseDao(BaseDao<User> baseDao) {
		this.baseDao = baseDao;
	}

	@Resource
	private TeamDao teamDao;
	@Resource
	private GradeDao gradeDao;

	@Transactional(readOnly = true)
	public List<Team> findTeamList() {
		List<Team> teamList = teamDao.findAll();
		return teamList;
	}

	public List<Grade> findGradeList() {
		List<Grade> gradeList = gradeDao.findAll();
		return gradeList;
	}

}
