package cn.yulezu.tags;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.yulezu.orm.Filtration;
import cn.yulezu.orm.Filtration.FiledType;
import cn.yulezu.orm.Filtration.MatchType;
import cn.yulezu.utils.MyStringUtils;

public class SearchTag extends TagSupport
{
	private static final long serialVersionUID = 1L;

	private String idName;
	private String className;
	private String styleName;
	private String matchType;
	private String fieldType;
	private String fieldList;

	@Override
	public int doStartTag() throws JspException
	{
		try
		{
			Enum.valueOf(MatchType.class, matchType);
		} catch (Exception e)
		{
			throw new IllegalArgumentException("filter名称" + matchType + "没有按规则编写,无法得到属性比较类型.", e);
		}
		try
		{
			Enum.valueOf(FiledType.class, fieldType).getValue();
		} catch (RuntimeException e)
		{
			throw new IllegalArgumentException("filter名称" + fieldType + "没有按规则编写,无法得到属性值类型.", e);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException
	{

		ServletRequest request = pageContext.getRequest();
		JspWriter out = pageContext.getOut();
		try
		{
			//filter_LIKES_name_OR_email
			String name = "filter_" + matchType + fieldType + "_" + fieldList.replaceAll(",", Filtration.OR);
			String value = MyStringUtils.iso2utf(request.getParameter(name));
			String idCode = (idName != null && !"".equals(idName)) ? "id=\"" + idName + "\"" : "";
			String classCode = (className != null && !"".equals(className)) ? "class=\"" + className + "\"" : "";
			String styleCode = (styleName != null && !"".equals(styleName)) ? "style=\"" + styleName + "\"" : "";
			String html = "<input type=\"text\" " + idCode + " name=\"" + name + "\" value=\""+value+"\" " + classCode + " " + styleCode + "/>";
			out.write(html);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getMatchType()
	{
		return matchType;
	}

	public void setMatchType(String matchType)
	{
		this.matchType = matchType.toUpperCase();
	}

	public String getFieldType()
	{
		return fieldType;
	}

	public void setFieldType(String fieldType)
	{
		this.fieldType = fieldType.toUpperCase();
	}

	public String getFieldList()
	{
		return fieldList;
	}

	public void setFieldList(String fieldList)
	{
		this.fieldList = fieldList;
	}

	public String getIdName()
	{
		return idName;
	}

	public void setIdName(String idName)
	{
		this.idName = idName;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public String getStyleName()
	{
		return styleName;
	}

	public void setStyleName(String styleName)
	{
		this.styleName = styleName;
	}

}
