package cn.yulezu.admin.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.yulezu.orm.PageData;
import cn.yulezu.sns.entity.City;
import cn.yulezu.sns.entity.Education;
import cn.yulezu.sns.entity.Honor;
import cn.yulezu.sns.entity.Rank;
import cn.yulezu.sns.entity.Ranklevel;
import cn.yulezu.sns.entity.Userinfo;
import cn.yulezu.sns.entity.Users;
import cn.yulezu.sns.service.UserinfoService;
import cn.yulezu.sns.service.UsersService;
import cn.yulezu.utils.PropertiesUtil;
import cn.yulezu.utils.SmplFileUpload;
import cn.yulezu.utils.WebUtils;

@Controller
@RequestMapping("/admin/users")
public class UsersAction
{
	@Resource
	private UsersService usersService;
	@Resource
	private UserinfoService userinfoService;
	
	@RequestMapping("/list")
	public ModelMap list(HttpServletRequest request)
	{
		PageData<Users> pageData = new PageData<Users>();
		//给pageData设置参数
		WebUtils.setPageDataParameter(request, pageData);
		pageData = usersService.find(pageData);

		return new ModelMap(pageData);
	}

	@RequestMapping(value = "/new")
	public String addForm(Model model)
	{
		return "/admin/users/input";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model)
	{
		Users users = usersService.find(id);
		List<Honor> honorList =  usersService.getHonorAll();
		model.addAttribute("users",users);
		model.addAttribute("honorList",honorList);
		return "/admin/users/input";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Users users,HttpServletRequest request)
	{
		if (users.isNew())
		{
			Userinfo userinfo = new Userinfo();
			users.setUserinfo(userinfo);
			users.setHonor(usersService.getHonorById(Integer.parseInt(request.getParameter("honor"))));
			userinfo.setUsers(users);
			userinfo.setInsertTime(new Date());
			userinfo.setVisible(true);
			usersService.save(users);
		} else  
		{
			users.setHonor(usersService.getHonorById(Integer.parseInt(request.getParameter("honor"))));
			usersService.update(users);
		}

		return "redirect:/admin/users/list";//重定向
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id)
	{
		usersService.delete(id);
		return "redirect:/admin/users/list";//重定向
	}

	@RequestMapping(value = "/visible/{id}")
	public String visible(@PathVariable("id") Integer id)
	{
		usersService.visible(id);
		return "redirect:/admin/users/list";//重定向
	}

	@RequestMapping(value = "/unVisible/{id}")
	public String unVisible(@PathVariable("id") Integer id)
	{
		usersService.unVisible(id);
		return "redirect:/admin/users/list";//重定向
	}

	@InitBinder
	public void InitBinder(WebDataBinder dataBinder)
	{
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
	}
	
	/**
	 * 得到用户详细信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userInfo/{id}")
	public String userInfo(@PathVariable("id") Integer id, Model model)
	{
		Userinfo userinfo = usersService.find(id).getUserinfo();
		List<City> cityList = userinfoService.getCityAll();//取得所有城市
		List<Education> educationList = userinfoService.getEducationALl();//得到所有学历
		model.addAttribute(cityList);
		model.addAttribute(educationList);
		model.addAttribute(userinfo);
		return "/admin/users/userInfoInput";
	}
	
	/**
	 * 保存用户详细信息
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/userInfoSave", method = RequestMethod.POST)
	public String saveUserInfo(Userinfo userinfo,HttpServletRequest request)
	{
		String uid = request.getParameter("uid");
		String city = request.getParameter("city");
		String education = request.getParameter("education");		
		userinfo.setUsers(usersService.find(Integer.parseInt(uid)));
		userinfo.setCity(userinfoService.getCityById(Integer.parseInt(city)));
		userinfo.setEducation(userinfoService.getEducationById(Integer.parseInt(education)));
		userinfoService.update(userinfo);
		return "redirect:/admin/users/list";//重定向
	}
	
	/**
	 * 城市列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cityList")
	public String cityList(Model model)
	{
		List<City> cityList = userinfoService.getCityAll();
		model.addAttribute(cityList);
		return "/admin/users/cityList";
	}
	
	/**
	 * 编辑城市
	 * @param city
	 * @return
	 */
	@RequestMapping(value = "/cityEdit/{id}")
	public String cityEdit(@PathVariable("id") Integer id,Model model)
	{
		City city =  userinfoService.getCityById(id);
		model.addAttribute(city);
		return "/admin/users/cityInput";
	}	
	
	/**
	 * 保存城市
	 * @param city
	 * @return
	 */
	@RequestMapping(value = "/citySave")
	public String saveCity(City city)
	{
		userinfoService.saveCity(city);
		return "redirect:/admin/users/cityList";
	}
	
//	/**
//	 * 删除城市
//	 * @param city
//	 * @return
//	 */
//	@RequestMapping(value = "/cityDelete/{id}")
//	public String cityDelete(@PathVariable("id") Integer id,Model model)
//	{
//		userinfoService.deleteCityById(id);
//		return "redirect:/admin/users/cityList";
//	}	
	
	/**
	 * 学历列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/educationList")
	public String educationList(Model model)
	{
		List<Education> educationList = userinfoService.getEducationALl();
		model.addAttribute(educationList);
		return "/admin/users/educationList";
	}	
	
	/**
	 * 保存学历
	 * @param education
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/educationSave")
	public String educationSave(Education education,Model model)
	{
		userinfoService.saveEducation(education);
		return this.educationList(model);
	}
	
	/**
	 * 编辑学历
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/educationEdit/{id}")
	public String educationEdit(@PathVariable("id") Integer id,Model model)
	{
		Education education =  userinfoService.getEducationById(id);
		model.addAttribute(education);
		return "/admin/users/educationInput";
	}	
	
	/**
	 * 保存头衔类型 
	 * @param honor
	 * @return
	 */
	@RequestMapping(value = "/honorSave",method=RequestMethod.POST)
	public String honorSave(Honor honor)
	{
		usersService.saveOrUpdateHonor(honor);
		return "redirect:/admin/users/honorList";
	}
	
	/**
	 * 头衔类型列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/honorList")
	public String honorList(Model model)
	{
		List<Honor> honorList = usersService.getHonorAll();
		model.addAttribute(honorList);
		return "/admin/users/honorList";
	}
	
	
	
	/**
	 * 头衔类型编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/honorEdit/{id}")
	public String honorEdit(@PathVariable("id") Integer id,Model model)
	{
		Honor honor =  usersService.getHonorById(id);
		model.addAttribute(honor);
		return "/admin/users/honorInput";
	}
	
	/**
	 * 头衔信息录入
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/rankInput/{id}")
	public String rankInput(@PathVariable("id") Integer id,Model model)
	{
		Honor honor = usersService.getHonorById(id);
		List<Ranklevel> ranklevelList = usersService.getRanklevelAll();
		model.addAttribute(honor);
		model.addAttribute("ranklevelList",ranklevelList);
		return "/admin/users/rankInput";
	}
	
	/**
	 * 头衔保存
	 * @param rank
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/rankSave")
	public String rankSave(Rank rank,HttpServletRequest request,Model model)
	{
		SmplFileUpload upload = new SmplFileUpload(request);
		PropertiesUtil pt = new PropertiesUtil();
		Properties pro = pt.getProperties("/config.properties");//得到配置文件
		upload.setPath(pro.getProperty("uploadPath")+pro.getProperty("rankUploadPath") );//上传路径
		upload.setFileType(pro.getProperty("uploadType"));//限制上传类型
		upload.setSizeMax(Integer.parseInt(pro.getProperty("uploadSizeMax")));//限制上传大小
		@SuppressWarnings("rawtypes")
		Map params = upload.saveAs("rank");//上传到指定路径,并返回表单的所有数据,key=表单名，value=表单值
		if(null==params)//如果是null表示上传失败
		{
			return this.rankList(Integer.parseInt(request.getParameter("rank")),model);
		}
		String honor = params.get("honor").toString();//取得头衔类型
		if(null!=params.get("id") && !"".equals(params.get("id").toString()))//id不为空表示修改数据
		{
			rank = usersService.getRankById(Integer.parseInt(params.get("id").toString()));//根据id得到一个头衔
			if(null!=params.get("img"))//不为空表示有上传图片
			{
				String img = pro.getProperty("uploadPath")+pro.getProperty("rankUploadPath")+rank.getImg();//上传路径
				File oldFile = new File(img);
				oldFile.delete();//删除保存在文件夹的图片
				rank.setImg(params.get("img").toString());
			}
		}else{
			if(null!=params.get("img"))//不为空表示有上传图片
			{
				rank.setImg(params.get("img").toString());
			}else{
				request.setAttribute("message", "上传文件不能为空!");
				Integer honorId = Integer.parseInt(params.get("honor").toString());
				return this.rankInput(honorId, model);
			}	
		}
		rank.setHonor(usersService.getHonorById(Integer.parseInt(honor)));//保存头衔类型
		rank.setRanklevel(usersService.getRanklevelById(Integer.parseInt(params.get("ranklevel").toString())));//保存头衔等级
		rank.setName(params.get("name").toString());
		usersService.saveOrUpdateRank(rank);//保存至数据库
		return this.rankList(Integer.parseInt(honor), model);
	}
	
	/**
	 * 头衔列表
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/rankList/{id}")
	public String rankList(@PathVariable("id") Integer id,Model model)
	{
		List<Rank> rankList = usersService.getRankByHonor(id);
		model.addAttribute(rankList);
		model.addAttribute("honorId",id);
		return "/admin/users/rankList";
	}
	
	/**
	 * 删除一个头衔
	 * @param id
	 * @param honorId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/rankDelete/{id}/{honorId}")
	public String rankDelete(@PathVariable("id") Integer id,@PathVariable("honorId") Integer honorId,Model model)
	{ 
		Rank rank = usersService.getRankById(id);
		PropertiesUtil pt = new PropertiesUtil();
		Properties pro = pt.getProperties("/config.properties");//得到配置文件
		String img = pro.getProperty("uploadPath")+pro.getProperty("rankUploadPath")+rank.getImg();//上传路径
		File file = new File(img);
		file.delete();//删除保存在文件夹的图片
		usersService.deleteRank(rank);//删除数据库记录
		return this.rankList(honorId, model); 
	}
	
	/**
	 * 编辑一个头衔
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/rankEdit/{id}/{honorId}")
	public String rankEdit(@PathVariable("id") Integer id,@PathVariable("honorId") Integer honorId,Model model)
	{
		Rank rank = usersService.getRankById(id);
		List<Ranklevel> ranklevelList = usersService.getRanklevelAll();
		model.addAttribute(rank);
		model.addAttribute("honor",usersService.getHonorById(honorId));
		model.addAttribute("ranklevelList",ranklevelList);
		return "/admin/users/rankInput";
	}
	
	/**
	 * 添加头衔等级
	 * @return
	 */
	@RequestMapping(value = "/ranklevelInput")
	public String ranklevelInput()
	{
		return "/admin/users/rankLevelInput";
	}
	
	/**
	 * 保存头衔等级
	 * @param ranklevel
	 * @return
	 */
	@RequestMapping(value = "/ranklevelSave")
	public String ranklevelSave(Ranklevel ranklevel,Model model)
	{
		usersService.saveOrUpdateRanklevel(ranklevel);
		return this.ranklevelList(model);
	}
	
	/**
	 * 头衔等级列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ranklevelList")
	public String ranklevelList(Model model)
	{
		List<Ranklevel> ranklevelList =  usersService.getRanklevelAll();
		model.addAttribute(ranklevelList);
		return "/admin/users/rankLevelList";
	}
	
	/**
	 * 编辑头衔等级
	 * @return
	 */
	@RequestMapping(value = "/ranklevelEdit/{id}")
	public String ranklevelEdit(@PathVariable("id") Integer id,Model model)
	{
		Ranklevel ranklevel = usersService.getRanklevelById(id);
		model.addAttribute(ranklevel);
		return "/admin/users/rankLevelInput";
	}
	
	/**
	 * 删除头衔等级
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ranklevelDelete/{id}")
	public String ranklevelDelete(@PathVariable("id") Integer id,Model model)
	{
		Ranklevel ranklevel = usersService.getRanklevelById(id);
		usersService.deleteRanklevel(ranklevel);
		return this.ranklevelList(model);
	}
	
	
}