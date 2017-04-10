package eeit9212.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.GroupAttendanceDAO;

public class GroupAttendanceDAOJdbc implements GroupAttendanceDAO{

	private DataSource dataSource;

	public GroupAttendanceDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beans.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		GroupAttendanceDAO groupAttendanceDAOJdbc = new GroupAttendanceDAOJdbc(dataSource);
		
		System.out.println(groupAttendanceDAOJdbc.selectGroupAttendance(2));
		System.out.println(groupAttendanceDAOJdbc.insertGroupAttendance(5, 0));
		System.out.println(groupAttendanceDAOJdbc.updateGroupAttendance(5, 1));
		
		

		((ConfigurableApplicationContext) context).close();

	}


	@Override
	public boolean selectGroupAttendance(int memberNo) {
		String selectGroupAttendance = "select * from groupAttendance where member_No=?";
		boolean result=false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectGroupAttendance);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = true;	
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
	public int insertGroupAttendance(int memberNo,int success) {
		String insertGroupAttendance = "insert into groupAttendance values(?,1,?);";
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(insertGroupAttendance);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, success);
			result=pstmt.executeUpdate();
		} catch (

		SQLException e) {
			result=-1;
			e.printStackTrace();
		} finally {			
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
	public int updateGroupAttendance(int memberNo,int success) {
		String updateGroupAttendance = "update groupAttendance set groupAttendance_TotalQt=groupAttendance_TotalQt+1,groupAttendance_TotalSuccess=groupAttendance_TotalSuccess+? where member_No=?";
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateGroupAttendance);
			pstmt.setInt(1, success);
			pstmt.setInt(2, memberNo);
			result=pstmt.executeUpdate();
			if (result==0) {
				result=-1;
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {			
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
