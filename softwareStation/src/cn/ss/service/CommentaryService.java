package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.Commentary;

public class CommentaryService extends BasicService {
	/**
	 * ��ѯ����
	 * 
	 * @param pageResult
	 * @param account
	 */
	public void findAll(PageResult<Commentary> pageResult,
			Commentary commentary, String beginTime, String endTime, int sid,String content) {
		StringBuffer hql = new StringBuffer("from Commentary c where 1=1");
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql.append(" and DATE_FORMAT(c.commentTime,'%y %m %d') >= DATE_FORMAT('"+beginTime+"','%y %m %d')");
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql.append(" and DATE_FORMAT(c.commentTime,'%y %m %d') <= DATE_FORMAT('"+endTime+"','%y %m %d')");
		}
		if (content!=null&&!"".equals(content.trim())) {
			content=content.replace("'", "");
			hql.append(" and c.content like '%" + content + "%'");
		}
		if (sid > 0) {
			hql.append(" and c.softwareInfo.id = " + sid);
		}
		if (commentary != null) {

		}
		hql.append(" order by c.id "+pageResult.getSort());
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * ��ȡָ������
	 * 
	 * @param id
	 * @return
	 */
	public Commentary findById(int id) {
		return (Commentary) dao.get(Commentary.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(Commentary.class, id);
	}

	/**
	 * ����
	 * 
	 * @param commentary
	 */
	public void add(Commentary commentary) {
		dao.add(commentary);
	}

	/**
	 * ����
	 * 
	 * @param Commentary
	 */
	public void update(Commentary commentary) {
		dao.update(commentary);
	}
}
