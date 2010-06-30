package cn.ss.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import cn.common.dao.hibImpl.HibernateSessionFactory;
import cn.ss.dto.SoftwareInfoDTO;
import cn.ss.entity.PhoneOs;
import cn.ss.entity.Software;
import cn.ss.entity.SoftwareInfo;

public class TestSoftwareAction extends TestCase {
	public void testAddSoftware() throws Exception {
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		session.getTransaction().begin();
		Software software = new Software();
		List<PhoneOs> phoneosList = new ArrayList<PhoneOs>();
		phoneosList.add((PhoneOs) session.get(PhoneOs.class, 2));
		phoneosList.add((PhoneOs) session.get(PhoneOs.class, 3));

		software.setPhoneOsList(phoneosList);
		session.save(software);
		// session.delete(session.get(Software.class, 21));
		session.getTransaction().commit();
	}

	/**
	 * 测试软件列表的查询和排序
	 * 
	 * @throws Exception
	 */
	public void testSort() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/softwareStation",
						"root", "520520");
		String sql = "SELECT si.* ,SUM(cl.number) clicknumber ,SUM(dl.number) downloadnumber,SUM(al.number) activenumber ,SUM(al.number*al.price) allprice FROM softwareinfo si	LEFT JOIN clicklog cl ON cl.softwareId=si.id	LEFT JOIN downloadlog dl ON dl.softwareId=si.id	LEFT JOIN activelog al ON al.softwareId=si.id 	GROUP BY cl.softwareId	ORDER BY clicknumber desc";
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery(sql);
		List<SoftwareInfoDTO> SoftwareInfoDTOList = new ArrayList<SoftwareInfoDTO>();
		SoftwareInfoDTO sidtd = null;
		while (result.next()) {
			sidtd = new SoftwareInfoDTO();
			sidtd.setId(result.getInt("id"));
			sidtd.setClick(result.getInt("clicknumber"));
			sidtd.setDownload(result.getInt("downloadnumber"));
			sidtd.setActive(result.getInt("activenumber"));
			sidtd.setAllPrice(result.getDouble("allprice"));
			SoftwareInfoDTOList.add(sidtd);
		}
		result.close();
		state.close();
		conn.close();
		for (SoftwareInfoDTO softwareInfoDTO : SoftwareInfoDTOList) {
			System.out.println("id:" + softwareInfoDTO.getId()
					+ "\t   clicknumber:" + softwareInfoDTO.getClick()
					+ "\t d:" + softwareInfoDTO.getDownload() + "\t a:"
					+ softwareInfoDTO.getActive() + "\t all:"
					+ softwareInfoDTO.getAllPrice());
		}
	}

	public static void main(String[] args) {
		Session session = HibernateSessionFactory.getSession();
		/*
		 * 
		 * session.beginTransaction(); // session.getTransaction().begin(); //
		 * Query query = session // .createQuery("FROM SoftwareInfo"); //
		 * SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); Criteria
		 * criteria = session.createCriteria(SoftwareInfo.class, "si"); Criteria
		 * cSoftware = criteria.createCriteria("softwareList", "s"); //
		 * cSoftware.setProjection(Projections.projectionList().add( //
		 * Projections.groupProperty("s.softwareInfo"))); Criteria cOs =
		 * cSoftware.createCriteria("phoneOsList", "os"); Criteria extenssion =
		 * cOs.createCriteria("extensionList", "ex"); //
		 * extenssion.add(Restrictions.or(Restrictions.eq("name", ".jar"), //
		 * Restrictions.eq("name", ".jar"))); //
		 * extenssion.add(Expression.ilike("name", ".jar")); Criteria ps =
		 * cOs.createCriteria("phoneseriesList"); Criteria pm =
		 * ps.createCriteria("phoneModelList");
		 * pm.add(Restrictions.or(Expression.eq("id", 40), Restrictions.eq(
		 * "ex.name", ".jar"))); // cOs.add(Restrictions.eq("id", 6)); // Object
		 * obj=criteria.uniqueResult(); List list = criteria.list();
		 * System.out.println(list.size()); for (int i = 0; i < list.size();
		 * i++) {// 现在能查询手机支持的平台软件，但是没有包括通用版本，以及版本高低的区分 System.out.println("id:"
		 * + ((SoftwareInfo) list.get(i)).getId() + "\t name:" + ((SoftwareInfo)
		 * list.get(i)).getName() + "\t type:" + ((SoftwareInfo)
		 * list.get(i)).getSoftwareType().getName()); } // Criteria
		 * criteria1=criteria.createCriteria("activeLogList"); //
		 * criteria1.addOrder(Order.desc("number")); // try { //
		 * criteria1.add(Restrictions.like("activeTime", //
		 * sdf.parse("2010-06-22"))); // } catch (ParseException e) { // // TODO
		 * Auto-generated catch block // e.printStackTrace(); // } // List
		 * list=criteria.list(); // List<SoftwareInfo> softwareInfoList =
		 * query.list(); // for (SoftwareInfo softwareInfo : softwareInfoList) {
		 * // System.out.println(softwareInfo.getId()); // } // Software
		 * software = new Software(); // List<PhoneOs> phoneosList = new
		 * ArrayList<PhoneOs>(); // phoneosList.add((PhoneOs)
		 * session.get(PhoneOs.class, 2)); // phoneosList.add((PhoneOs)
		 * session.get(PhoneOs.class, 3));
		 * 
		 * // software.setPhoneOsList(phoneosList); // session.save(software);
		 * // session.delete(session.get(Software.class, 21)); //
		 * session.getTransaction().commit(); // for (Object object : list) { //
		 * SoftwareInfo s = (SoftwareInfo) object; // System.out.print(s.getId()
		 * + " ---  "); // System.out.println(s.getActiveLogList().size()); // }
		 * // String sql = //
		 * "SELECT si.* ,SUM(cl.number) clicknumber ,SUM(dl.number),SUM(al.number),SUM(al.number*al.price) FROM softwareinfo si	LEFT JOIN clicklog cl ON cl.softwareId=si.id	LEFT JOIN downloadlog dl ON dl.softwareId=si.id	LEFT JOIN activelog al ON al.softwareId=si.id 	GROUP BY cl.softwareId	ORDER BY clicknumber desc"
		 * ; // Query query = session.createSQLQuery(sql); // ScrollableResults
		 * result = query.scroll(); // while (result.next()) { //
		 * System.out.println("1"); // }
		 */
		Criteria si = session.createCriteria(SoftwareInfo.class);

		// dl.add(Expression
		// .sql("DATE_FORMAT(downloadTime,'%y %m') BETWEEN  DATE_FORMAT('"
		// + sdf_moth.format(date)
		// + "','%y %m') AND   DATE_FORMAT('"
		// + sdf_moth.format(date) + "','%y %m')"));

		// System.out.println("list:" + si.list());
		boolean flag = false;
		if (flag) {
			Criteria s = si.createCriteria("softwareList", "s").createCriteria(
					"phoneOsList").createCriteria("phoneseriesList")
					.createCriteria("phoneModelList").add(
							Expression.idEq(new Integer(42)));
			s.setProjection(Projections.projectionList().add(
					Projections.groupProperty("s.softwareInfo")));
		}
		Criteria dl = si.createCriteria("downloadLogList", "dl");
		dl.setProjection(Projections.projectionList().add(
				Projections.sum("number"), "downloads").add(
				Projections.groupProperty("dl.softwareInfo")));
		dl
				.add(Expression
						.sql("DATE_FORMAT(downloadTime,'%y %m %d') =  DATE_FORMAT(now(),'%y %m %d')"));
		dl.addOrder(Order.desc("downloads"));
		// Criteria si = session.createCriteria(SoftwareInfo.class);
		// Criteria dl = si.createCriteria("clickLogList", "cl");
		// dl.setProjection(Projections.projectionList().add(
		// Projections.sum("number"), "cls").add(
		// Projections.groupProperty("cl.softwareInfo")));
		// dl.addOrder(Order.desc("cls"));
		List list = si.list();
		// System.out.println("list:" + list.size());
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			Object[] objs = (Object[]) obj;
			SoftwareInfo softwareInfo = (SoftwareInfo) objs[1];
			System.out.println(objs[0] + " " + softwareInfo.getId());
		}

	}

	public void testCriteria() throws Exception {
		Session session = HibernateSessionFactory.getSession();
		// List results = session.createCriteria(SoftwareInfo.class)
		// .setProjection(
		// Projections.projectionList().add(
		// Projections.rowCount(), "catCountByColor").add(
		// Projections.avg("weight"), "avgWeight").add(
		// Projections.max("weight"), "maxWeight").add(
		// Projections.groupProperty("color"), "color"))
		// .addOrder(Order.desc("catCountByColor")).addOrder(
		// Order.desc("avgWeight")).list();

	}
}
