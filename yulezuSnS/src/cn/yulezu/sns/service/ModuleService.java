package cn.yulezu.sns.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.orm.hibernate.BaseService;
import cn.yulezu.sns.entity.Module;

@Service
@Transactional
public class ModuleService extends BaseService<Module> {
	@Override
	@Resource(name = "moduleDao")
	public void setBaseDao(BaseDao<Module> baseDao) {
		this.baseDao = baseDao;
	}

	/**查询父级模块
	 * @return
	 */
	public List<Module> findParentModule() {
		return super
				.findList("from Module where parent.id = 1 or parent.id is null");
	}

	/**查询父级模块，除了自身
	 * @param id
	 * @return
	 */
	public List<Module> findParentModule(Integer id) {
		return super
				.findList(
						"from Module where (parent.id = ? or parent.id is null) and id !=? and 1=?",
						1, id, 1);
	}
}
