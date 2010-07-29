package org.slave4j.demo.blog.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slave4j.demo.blog.entity.Team;
import org.slave4j.demo.blog.service.TeamService;
import org.slave4j.orm.PageData;
import org.slave4j.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/blog/team")
public class TeamAction
{
	@Resource
	private TeamService teamService;

	@RequestMapping("/list")
	public ModelMap list(HttpServletRequest request)
	{
		PageData<Team> pageData = new PageData<Team>();
		//给pageData设置参数
		WebUtils.setPageDataParameter(request, pageData);
		pageData = teamService.find(pageData);
		return new ModelMap(pageData);
	}

	@RequestMapping(value = "/new")
	public String addForm()
	{
		return "/blog/team/input";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model)
	{
		Team team = teamService.find(id);
		model.addAttribute(team);
		return "/blog/team/input";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Team team)
	{
		if (team.isNew())
		{
			teamService.save(team);
		} else
		{
			teamService.update(team);
		}

		return "redirect:/blog/team/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id)
	{
		teamService.delete(id);
		return "redirect:/blog/team/list";
	}

	@RequestMapping(value = "/visible/{id}")
	public String visible(@PathVariable("id") Integer id)
	{
		teamService.visible(id);
		return "redirect:/blog/team/list";
	}

	@RequestMapping(value = "/unVisible/{id}")
	public String unVisible(@PathVariable("id") Integer id)
	{
		teamService.unVisible(id);
		return "redirect:/blog/team/list";
	}
}
