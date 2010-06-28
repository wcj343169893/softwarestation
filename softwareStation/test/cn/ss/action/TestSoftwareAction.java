package cn.ss.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.ConnectionManager;

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
		session.beginTransaction();
		// session.getTransaction().begin();
		// Query query = session
		// .createQuery("FROM SoftwareInfo");
		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Criteria criteria = session.createCriteria(SoftwareInfo.class, "si");
		Criteria cSoftware = criteria.createCriteria("softwareList", "s");
		cSoftware.setProjection(Projections.projectionList().add(
				Projections.groupProperty("s.softwareInfo")));
		Criteria cOs = cSoftware.createCriteria("phoneOsList");
		Criteria ps=cOs.createCriteria("phoneseriesList");
		Criteria pm=ps.createCriteria("phoneModelList");
		pm.add(Expression.eq("id",42));
//		cOs.add(Restrictions.eq("id", 6));
		List list = criteria.list();
		 System.out.println(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("id:" + ((SoftwareInfo) list.get(i)).getId()
					+ "\t name:" + ((SoftwareInfo) list.get(i)).getName());
		}
		// Criteria criteria1=criteria.createCriteria("activeLogList");
		// criteria1.addOrder(Order.desc("number"));
		// try {
		// criteria1.add(Restrictions.like("activeTime",
		// sdf.parse("2010-06-22")));
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// List list=criteria.list();
		// List<SoftwareInfo> softwareInfoList = query.list();
		// for (SoftwareInfo softwareInfo : softwareInfoList) {
		// System.out.println(softwareInfo.getId());
		// }
		// Software software = new Software();
		// List<PhoneOs> phoneosList = new ArrayList<PhoneOs>();
		// phoneosList.add((PhoneOs) session.get(PhoneOs.class, 2));
		// phoneosList.add((PhoneOs) session.get(PhoneOs.class, 3));

		// software.setPhoneOsList(phoneosList);
		// session.save(software);
		// session.delete(session.get(Software.class, 21));
		// session.getTransaction().commit();
		// for (Object object : list) {
		// SoftwareInfo s = (SoftwareInfo) object;
		// System.out.print(s.getId() + " ---  ");
		// System.out.println(s.getActiveLogList().size());
		// }
		// String sql =
		// "SELECT si.* ,SUM(cl.number) clicknumber ,SUM(dl.number),SUM(al.number),SUM(al.number*al.price) FROM softwareinfo si	LEFT JOIN clicklog cl ON cl.softwareId=si.id	LEFT JOIN downloadlog dl ON dl.softwareId=si.id	LEFT JOIN activelog al ON al.softwareId=si.id 	GROUP BY cl.softwareId	ORDER BY clicknumber desc";
		// Query query = session.createSQLQuery(sql);
		// ScrollableResults result = query.scroll();
		// while (result.next()) {
		// System.out.println("1");
		// }
	}

}
