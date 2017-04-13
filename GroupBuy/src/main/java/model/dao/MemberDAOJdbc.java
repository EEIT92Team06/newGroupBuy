package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import model.MemberBean;
import model.MemberDAO;
///GroupBuy/src/main/resources/eeit9211/beans.config.xml
public class MemberDAOJdbc implements MemberDAO {

	private DataSource dataSource;

	public MemberDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public MemberBean selectMemberInfo(int memberNo) {
		String selectMemberInfo= "SELECT * FROM MemberInfo WHERE member_No=?";
	/*	String selectMemberInfo= "SELECT member.member_No, otherStatus, member_Account, member_Name,"
				+ " member_Password, member_NickName, member_Birth, member_Phone, member_Address, member_Pic,"
				+ " groupAttendance_TotalSuccess, groupAttendance_TotalQt,"
				+ " (grouperCredit_TotalScore/grouperCredit_TotalPeople)groupCredit"
				+ " FROM member LEFT OUTER JOIN groupAttendance ON member.member_No=groupAttendance.member_No"
				+ " LEFT OUTER JOIN grouperCredit ON member.member_No=grouperCredit.member_No"
				+ " LEFT OUTER JOIN otherStatus ON member.member_Status=otherStatus.otherStatus_No"
				+ " WHERE member_No=?";
	*/
		MemberBean result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectMemberInfo);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result=new MemberBean();
				result.setMemberNo(rs.getInt("member_No"));
				result.setMemberStatus(rs.getString("otherStatus"));
				result.setMemberAccount(rs.getString("member_Account"));
				result.setMemberName(rs.getString("member_Name"));
				result.setMemberPassword(rs.getString("member_Password"));
				result.setMemberNickName(rs.getString("member_NickName"));
				result.setMemberBirth(rs.getDate("member_Birth"));
				result.setMemberPhone(rs.getString("member_Phone"));
				result.setMemberAddress(rs.getString("member_Address"));
				result.setMemberPic(rs.getString("member_Pic"));
				result.setGroupAttendanceTotalSuccess(rs.getInt("groupAttendance_TotalSuccess"));
				result.setGroupAttendanceTotalQt(rs.getInt("groupAttendance_TotalQt"));
				result.setGroupCredit(rs.getDouble("groupCredit"));
			}
		} catch (SQLException e) {
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
	public MemberBean updateMemberPassword(int memberNo, String memberPassword) {
		String updateMemberPassword="UPDATE member SET member_Password=? WHERE member_No=?";
		MemberBean result=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateMemberPassword);
			pstmt.setInt(2, memberNo);
			pstmt.setString(1, memberPassword);
			int i=pstmt.executeUpdate();
			if(i==1){
				result=new MemberBean();
				result.setMemberNo(memberNo);
				result.setMemberPassword(memberPassword);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
			if (conn!=null) {
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
	public MemberBean updateMemberPic(int memberNo, String memberPic) {
		String updateMemberPic="UPDATE member SET member_Pic=? WHERE member_No=?";
		MemberBean result=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateMemberPic);
			pstmt.setInt(2, memberNo);
			pstmt.setString(1, memberPic);
			int i=pstmt.executeUpdate();
			if(i==1){
				result=new MemberBean();
				result.setMemberNo(memberNo);
				result.setMemberPic(memberPic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}	
		}
		return result;
	};
	
	@Override
	public MemberBean updateMemberInfo(int memberNo, String memberNickName, String memberAddress){
		String updateMemberInfo="UPDATE member SET member_NickName=?, member_Address=? WHERE member_No=?";
		MemberBean result=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateMemberInfo);
			pstmt.setInt(3, memberNo);
			pstmt.setString(1, memberNickName);
			pstmt.setString(2, memberAddress);			
			int i=pstmt.executeUpdate();
			if(i==1){
				result=new MemberBean();
				result.setMemberNo(memberNo);
				result.setMemberNickName(memberNickName);
				result.setMemberAddress(memberAddress);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
			if (conn!=null) {
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
