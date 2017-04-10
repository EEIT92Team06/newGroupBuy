package eeit9212.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.AttendGroupInfoBean;
import eeit9212.model.CreateGroupInfoBean;
import eeit9212.model.GroupInfoDAO;

public class GroupInfoDAOJdbc implements GroupInfoDAO {

	private DataSource dataSource;

	public GroupInfoDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beans.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		GroupInfoDAO groupInfoDAOJdbc = new GroupInfoDAOJdbc(dataSource);

//		 CreateGroupInfoBean selectGroupInfoByGroupInfoNo = groupInfoDAOJdbc.selectGroupInfoByGroupInfoNo(1);
//		System.out.println(selectGroupInfoByGroupInfoNo);

//		AttendGroupInfoBean selectMyAttendedByGroupInfoNo=groupInfoDAOJdbc.selectMyAttendedByGroupInfoNo(1,20);
//		System.out.println(selectMyAttendedByGroupInfoNo);
//		List<GroupInfoBean> selectMyAttendedGroupInfo = groupInfoDAOJdbc.selectMyAttendedGroupInfo(1);
//		// System.out.println(selectMyAttendedGroupInfo);
//		for (GroupInfoBean bean : selectMyAttendedGroupInfo) {
//			System.out.print(bean.getGroupInfoNo() + "  ");
//			System.out.print(bean.getOrderInfoNo() + "  ");
//			System.out.print(bean.getGroupInfoMemberNo() + "  ");
//			System.out.print(bean.getMemberName() + "  ");
//			System.out.print(bean.getGroupStatusNo() + "  ");
//			System.out.print(bean.getGroupStatus() + "  ");
//			System.out.print(bean.getProductType() + "  ");
//			System.out.print(bean.getGroupInfoName() + "  ");
//			System.out.print(bean.getGroupInfoMinProductQt() + "  ");
//			System.out.print(bean.getGroupInfoTotalProductQt() + "  ");
//			System.out.print(bean.getGroupInfoStartDate() + "  ");
//			System.out.print(bean.getGroupInfoDeadLine() + "  ");
//			System.out.print(bean.getGroupInfoContent() + "  ");
//			System.out.print(bean.getGroupInfoShippingWay() + "  ");
//			System.out.print(bean.getGroupInfoBankAccount() + "  ");
//			System.out.print(bean.getGrouperCredit() + "  ");
//			System.out.print(bean.getOrderStatusNo() + "  ");
//			System.out.print(bean.getOrderStatus() + "  ");
//			System.out.println(bean.getGroupInfoCoverPic());
//
//		}

//		List<GroupInfoBean> selectMyCreatedGroupInfo = groupInfoDAOJdbc.selectMyCreatedGroupInfo(1);
//		// System.out.println(selectMyCreatedGroupInfo);
//		for (GroupInfoBean bean1 : selectMyCreatedGroupInfo) {
//			System.out.print(bean1.getGroupInfoNo() + "  ");
//			System.out.print(bean1.getGroupInfoMemberNo() + "  ");
//			System.out.print(bean1.getMemberName() + "  ");
//			System.out.print(bean1.getGroupStatusNo() + "  ");
//			System.out.print(bean1.getGroupStatus() + "  ");
//			System.out.print(bean1.getProductType() + "  ");
//			System.out.print(bean1.getGroupInfoName() + "  ");
//			System.out.print(bean1.getGroupInfoMinProductQt() + "  ");
//			System.out.print(bean1.getGroupInfoTotalProductQt() + "  ");
//			System.out.print(bean1.getGroupInfoStartDate() + "  ");
//			System.out.print(bean1.getGroupInfoDeadLine() + "  ");
//			System.out.print(bean1.getGroupInfoContent() + "  ");
//			System.out.print(bean1.getGroupInfoShippingWay() + "  ");
//			System.out.print(bean1.getGroupInfoBankAccount() + "  ");
//			System.out.println(bean1.getGroupInfoCoverPic());
//		}

		((ConfigurableApplicationContext) context).close();
	}

@Override
public int updateGroupInfoDeadLine(int groupInfoNo,Timestamp deadLine){
		
		String updateGroupInfoDeadLine = "update groupInfo set groupInfo_DeadLine=? where groupInfo_No=?";
		int result=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateGroupInfoDeadLine);
			pstmt.setTimestamp(1, deadLine);
			pstmt.setInt(2, groupInfoNo);
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
	

	@Override
	public int updateGroupStatus(int groupInfoNo,int groupStatusNo){
		
		String updateGroupStatus = "update groupInfo set groupStatus_No=? where groupInfo_No=?";
		int result=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateGroupStatus);
			pstmt.setInt(1, groupStatusNo);
			pstmt.setInt(2, groupInfoNo);
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

	@Override
	public AttendGroupInfoBean selectMyAttendedByGroupInfoNo(int memberNo,int groupInfoNo) {

		String selectMyAttendedByGroupInfoNo = "select * from selectMyAttendedGroupInfo where orderInfoMember_No=? and groupInfo_No=?";

		AttendGroupInfoBean result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectMyAttendedByGroupInfoNo);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, groupInfoNo);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				result = new AttendGroupInfoBean();
				result.setGroupInfoNo(rs.getInt("groupInfo_No"));
				result.setOrderInfoNo(rs.getInt("orderInfo_No"));
				result.setGroupInfoMemberNo(rs.getInt("groupInfoMember_No"));
				result.setMemberName(rs.getString("member_Name"));
				result.setGroupStatusNo(rs.getInt("groupStatus_No"));
				result.setGroupStatus(rs.getString("groupStatus"));
				result.setProductType(rs.getString("productType"));
				result.setGroupInfoName(rs.getString("groupInfo_Name"));
				result.setGroupInfoMinProductQt(rs.getInt("groupInfo_MinProductQt"));
				result.setGroupInfoTotalProductQt(rs.getInt("groupInfo_TotalProductQt"));
				result.setGroupInfoStartDate(rs.getTimestamp("groupInfo_StartDate"));
				result.setGroupInfoDeadLine(rs.getTimestamp("groupInfo_DeadLine"));
				result.setGroupInfoContent(rs.getString("groupInfo_Content"));
				result.setGroupInfoShippingWay(rs.getString("groupInfo_ShippingWay"));
				result.setGroupInfoBankAccount(rs.getString("groupInfo_BankAccount"));
				result.setGrouperCredit(rs.getDouble("grouperCredit"));
				System.out.println(rs.getDouble("grouperCredit"));
				result.setOrderStatusNo(rs.getInt("otherStatus_No"));
				result.setOrderStatus(rs.getString("otherStatus"));
				result.setGroupInfoCoverPic(rs.getBlob("groupInfo_CoverPic"));
				
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}finally{
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
	public List<AttendGroupInfoBean> selectMyAttendedGroupInfo(int memberNo) {

		String selectMyAttendedGroupInfo = "select * from selectMyAttendedGroupInfo where orderInfoMember_No=?";

		List<AttendGroupInfoBean> result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectMyAttendedGroupInfo);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
			result = new ArrayList<AttendGroupInfoBean>();
			while (rs.next()) {
				AttendGroupInfoBean bean = new AttendGroupInfoBean();
				bean.setGroupInfoNo(rs.getInt("groupInfo_No"));
				bean.setOrderInfoNo(rs.getInt("orderInfo_No"));
				bean.setGroupInfoMemberNo(rs.getInt("groupInfoMember_No"));
				bean.setMemberName(rs.getString("member_Name"));
				bean.setGroupStatusNo(rs.getInt("groupStatus_No"));
				bean.setGroupStatus(rs.getString("groupStatus"));
				bean.setProductType(rs.getString("productType"));
				bean.setGroupInfoName(rs.getString("groupInfo_Name"));
				bean.setGroupInfoMinProductQt(rs.getInt("groupInfo_MinProductQt"));
				bean.setGroupInfoTotalProductQt(rs.getInt("groupInfo_TotalProductQt"));
				bean.setGroupInfoStartDate(rs.getTimestamp("groupInfo_StartDate"));
				bean.setGroupInfoDeadLine(rs.getTimestamp("groupInfo_DeadLine"));
				bean.setGroupInfoContent(rs.getString("groupInfo_Content"));
				bean.setGroupInfoShippingWay(rs.getString("groupInfo_ShippingWay"));
				bean.setGroupInfoBankAccount(rs.getString("groupInfo_BankAccount"));
				bean.setGrouperCredit(rs.getDouble("grouperCredit"));
				bean.setOrderStatusNo(rs.getInt("otherStatus_No"));
				bean.setOrderStatus(rs.getString("otherStatus"));
				bean.setGroupInfoCoverPic(rs.getBlob("groupInfo_CoverPic"));
				result.add(bean);
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}finally{
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
	public CreateGroupInfoBean selectGroupInfoByGroupInfoNo(int groupInfoNo) {

		String selectGroupInfoBygroupInfoNo = "select * from selectMyCreatedGroupInfo where groupInfo_No=?";
		CreateGroupInfoBean result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectGroupInfoBygroupInfoNo);
			pstmt.setInt(1, groupInfoNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new CreateGroupInfoBean();
				result.setGroupInfoNo(rs.getInt("groupInfo_No"));
				result.setGroupInfoMemberNo(rs.getInt("member_No"));
				result.setMemberName(rs.getString("member_Name"));
				result.setGroupStatusNo(rs.getInt("groupStatus_No"));
				result.setGroupStatus(rs.getString("groupStatus"));
				result.setProductType(rs.getString("productType"));
				result.setGroupInfoName(rs.getString("groupInfo_Name"));
				result.setGroupInfoMinProductQt(rs.getInt("groupInfo_MinProductQt"));
				result.setGroupInfoTotalProductQt(rs.getInt("groupInfo_TotalProductQt"));			
				result.setGroupInfoStartDate(rs.getTimestamp("groupInfo_StartDate"));
				result.setGroupInfoDeadLine(rs.getTimestamp("groupInfo_DeadLine"));
				result.setGroupInfoContent(rs.getString("groupInfo_Content"));
				result.setGroupInfoShippingWay(rs.getString("groupInfo_ShippingWay"));
				result.setGroupInfoBankAccount(rs.getString("groupInfo_BankAccount"));
				result.setGroupInfoCoverPic(rs.getBlob("groupInfo_CoverPic"));

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
	public List<CreateGroupInfoBean> selectMyCreatedGroupInfo(int memberNo) {

		String selectMyCreatedGroupInfo = "select * from selectMyCreatedGroupInfo where member_No=?";
		List<CreateGroupInfoBean> result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectMyCreatedGroupInfo);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
			result = new ArrayList<CreateGroupInfoBean>();
			while (rs.next()) {
				CreateGroupInfoBean bean = new CreateGroupInfoBean();
				bean.setGroupInfoNo(rs.getInt("groupInfo_No"));
				bean.setGroupInfoMemberNo(rs.getInt("member_No"));
				bean.setMemberName(rs.getString("member_Name"));
				bean.setGroupStatusNo(rs.getInt("groupStatus_No"));
				bean.setGroupStatus(rs.getString("groupStatus"));
				bean.setProductType(rs.getString("productType"));
				bean.setGroupInfoName(rs.getString("groupInfo_Name"));
				bean.setGroupInfoMinProductQt(rs.getInt("groupInfo_MinProductQt"));
				bean.setGroupInfoTotalProductQt(rs.getInt("groupInfo_TotalProductQt"));
				bean.setGroupInfoStartDate(rs.getTimestamp("groupInfo_StartDate"));
				bean.setGroupInfoDeadLine(rs.getTimestamp("groupInfo_DeadLine"));
				bean.setGroupInfoContent(rs.getString("groupInfo_Content"));
				bean.setGroupInfoShippingWay(rs.getString("groupInfo_ShippingWay"));
				bean.setGroupInfoBankAccount(rs.getString("groupInfo_BankAccount"));
				bean.setGroupInfoCoverPic(rs.getBlob("groupInfo_CoverPic"));
				result.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
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
