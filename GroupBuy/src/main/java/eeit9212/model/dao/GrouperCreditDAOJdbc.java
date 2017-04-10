package eeit9212.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.GrouperCreditDAO;

public class GrouperCreditDAOJdbc implements GrouperCreditDAO {

	private DataSource dataSource;

	public GrouperCreditDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beans.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		GrouperCreditDAO grouperCreditDAOJdbc = new GrouperCreditDAOJdbc(dataSource);
//		System.out.println(grouperCreditDAOJdbc.selectGrouperCredit(4));
		System.out.println(grouperCreditDAOJdbc.insertGrouperCredit(6, 3));
//		System.out.println(grouperCreditDAOJdbc.updateGrouperCredit(4, 5));

		((ConfigurableApplicationContext) context).close();

	}


	@Override
	public boolean selectGrouperCredit(int memberNo) {
		String selectGrouperCredit = "select * from grouperCredit where member_No=?";
		boolean result=false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectGrouperCredit);
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
	public int insertGrouperCredit(int memberNo,int score) {
		String insertGrouperCredit = "insert into grouperCredit values(?,?,1)";
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(insertGrouperCredit);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, score);
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
	public int updateGrouperCredit(int memberNo,int score) {
		String updateGrouperCredit = "update grouperCredit set grouperCredit_TotalScore=grouperCredit_TotalScore+?,grouperCredit_TotalPeople=grouperCredit_TotalPeople+1 where member_No=?";
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateGrouperCredit);
			pstmt.setInt(1, score);
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
