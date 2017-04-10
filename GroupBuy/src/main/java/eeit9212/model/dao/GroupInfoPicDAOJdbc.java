package eeit9212.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.GroupInfoPicBean;
import eeit9212.model.GroupInfoPicDAO;

public class GroupInfoPicDAOJdbc implements GroupInfoPicDAO {

	private DataSource dataSource;

	public GroupInfoPicDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beans.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		GroupInfoPicDAO groupInfoPicDAOJdbc = new GroupInfoPicDAOJdbc(dataSource);
		GroupInfoPicBean select1=groupInfoPicDAOJdbc.selectGroupInfoPicByNo(4);
		System.out.println(select1);
		
		List<GroupInfoPicBean> select = groupInfoPicDAOJdbc.selectGroupInfoPic(3);
		System.out.println(select);

		for (GroupInfoPicBean bean : select) {
			System.out.print(bean.getGroupInfoPicNo() + "  ");
			System.out.print(bean.getGroupInfoNo() + "  ");
			System.out.println(bean.getGroupInfoPicProductPic());
		}

		((ConfigurableApplicationContext) context).close();

	}


	@Override
	public GroupInfoPicBean selectGroupInfoPicByNo(int groupInfoPicNo) {
		String selectGroupInfoPicByNo = "select * from groupInfoPic where groupInfoPic_No=?";
		GroupInfoPicBean result=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectGroupInfoPicByNo);
			pstmt.setInt(1, groupInfoPicNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				result = new GroupInfoPicBean();
				result.setGroupInfoPicNo(rs.getInt("groupInfoPic_No"));
				result.setGroupInfoNo(rs.getInt("groupInfo_No"));
				result.setGroupInfoPicProductPic(rs.getBlob("groupInfoPic_ProductPic"));
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;

	}
	
	
	@Override
	public List<GroupInfoPicBean> selectGroupInfoPic(int groupInfoNo) {
		String selectGroupInfoPic = "select * from groupInfoPic where groupInfo_No=?";

		List<GroupInfoPicBean> result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectGroupInfoPic);
			pstmt.setInt(1, groupInfoNo);
			rs = pstmt.executeQuery();
			result = new ArrayList<GroupInfoPicBean>();
			while (rs.next()) {

				GroupInfoPicBean bean = new GroupInfoPicBean();
				bean.setGroupInfoPicNo(rs.getInt("groupInfoPic_No"));
				bean.setGroupInfoNo(rs.getInt("groupInfo_No"));
				bean.setGroupInfoPicProductPic(rs.getBlob("groupInfoPic_ProductPic"));
				result.add(bean);
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;

	}

}
