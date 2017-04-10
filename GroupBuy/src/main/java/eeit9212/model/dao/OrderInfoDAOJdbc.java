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

import eeit9212.model.OrderInfoBean;
import eeit9212.model.OrderInfoDAO;

public class OrderInfoDAOJdbc implements OrderInfoDAO {
	private DataSource dataSource;

	public OrderInfoDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beans.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");

		OrderInfoDAO orderInfoDAOJdbc = new OrderInfoDAOJdbc(dataSource);
//		OrderInfoBean update=orderInfoDAOJdbc.updateOrderInfoStatus(1002, 8);
//		System.out.println(update);
		int insert=orderInfoDAOJdbc.insertOrderInfoAfterSuccess(7,"0913246578","aaaaa","asasasas");
		System.out.println("insertOrderInfoAfterSuccess="+insert);
//		System.out.println(orderInfoDAOJdbc.updateOrderInfoStatusByGroupInfoNo(1, 1004));
//		List<OrderInfoBean> select = orderInfoDAOJdbc.selectMyGroupOrderInfo(1);
//		System.out.println(select);
//		for (OrderInfoBean bean : select) {
//			System.out.print(bean.getOrderInfoNo() + "  ");
//			System.out.print(bean.getMemberNo() + "  ");
//			System.out.print(bean.getMemberName() + "  ");
//			System.out.print(bean.getGroupAttendanceTotalSuccess() + "  ");
//			System.out.print(bean.getGroupAttendanceTotalQt() + "  ");
//			System.out.print(bean.getOrderInfoPriceTotal() + "  ");
//			System.out.print(bean.getOrderInfoStatusNo() + "  ");
//			System.out.print(bean.getOrderInfoStatus() + "  ");
//			System.out.print(bean.getOrderInfoAfterSuccessPackageNo() + "  ");
//			System.out.print(bean.getOrderInfoAfterSuccessPayTime() + "  ");
//			System.out.print(bean.getOrderInfoAfterSuccessPhone() + "  ");
//			System.out.print(bean.getOrderInfoAfterSuccessDestination() + "  ");
//			System.out.println(bean.getOrderInfoAfterSuccessBankAccount());
//		}

		((ConfigurableApplicationContext) context).close();
	}

public int updateOrderInfoStatusByOrderStatusNo(int groupInfoNo,int orderInfoStatusNo,int whereOrderInfoStatusNo){
		
		String updateOrderInfoStatusByOrderStatusNo="update orderInfo set orderInfoStatus_No=? where groupInfo_No=? and orderInfoStatus_No=?";
		int result=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;	
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateOrderInfoStatusByOrderStatusNo);
			pstmt.setInt(1, orderInfoStatusNo);
			pstmt.setInt(2, groupInfoNo);
			pstmt.setInt(3, whereOrderInfoStatusNo);
			result= pstmt.executeUpdate();
			if(result==0){
				result=-1;
			}
		
		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		finally {
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

	public int updateOrderInfoStatusByGroupInfoNo(int groupInfoNo,int orderInfoStatusNo){
		
		String updateOrderInfoStatusByGroupInfoNo="update orderInfo set orderInfoStatus_No=? where groupInfo_No=?";
		int result=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;	
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateOrderInfoStatusByGroupInfoNo);
			pstmt.setInt(1, orderInfoStatusNo);
			pstmt.setInt(2, groupInfoNo);
			result= pstmt.executeUpdate();
			if(result==0){
				result=-1;
			}
		
		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		finally {
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
	

	public int updatePackageNo(String packageNo,int orderInfoNo){
		
		String updatePackageNo="update orderInfoAfterSuccess set orderInfoAfterSuccess_PackageNo=? where orderInfo_No=?;";
		int result=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;	
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updatePackageNo);
			pstmt.setString(1, packageNo);
			pstmt.setInt(2, orderInfoNo);
			result= pstmt.executeUpdate();
			if(result==0){
				result=-1;
			}
		
		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		finally {
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

	public int insertOrderInfoAfterSuccess(int orderInfoNo,String orderInfoAfterSuccessPhone,
			String orderInfoAfterSuccessDestination,String orderInfoAfterSuccessBankAccount){
		String insertOrderInfoAfterSuccess="insert into orderInfoAfterSuccess values(?,null,getdate(),?,?,?)";
		
		int result=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(insertOrderInfoAfterSuccess);
			pstmt.setInt(1, orderInfoNo);
			pstmt.setString(2, orderInfoAfterSuccessPhone);
			pstmt.setString(3, orderInfoAfterSuccessDestination);
			pstmt.setString(4, orderInfoAfterSuccessBankAccount);
			try{
			result= pstmt.executeUpdate();
			}catch(Exception e){
				result=-1;
			}
			

			
		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		finally {
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
	
	

	public OrderInfoBean selectMyOrderInfoByNo(int orderInfoNo) {

		String selectMyGroupOrderInfo = "select * from selectMyGroupOrderInfo where orderInfo_No=?";
		String selectOrderInfoPriceTotal = "select * from selectTotalPrice where orderInfo_No=?";
		
		OrderInfoBean result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectMyGroupOrderInfo);
			pstmt.setInt(1, orderInfoNo);
			rs = pstmt.executeQuery();
			pstmt1 = conn.prepareStatement(selectOrderInfoPriceTotal);	
			if (rs.next()) {
				result = new OrderInfoBean();
				result.setOrderInfoNo(rs.getInt("orderInfo_No"));
				result.setMemberName(rs.getString("member_Name"));
				result.setGroupAttendanceTotalSuccess(rs.getInt("groupAttendance_TotalSuccess"));
				result.setOrderInfoStatusNo(rs.getInt("orderInfoStatus_No"));
				result.setOrderInfoStatus(rs.getString("orderInfoStatus"));
				result.setOrderInfoAfterSuccessPackageNo(rs.getString("orderInfoAfterSuccess_PackageNo"));
				result.setOrderInfoAfterSuccessPayTime(rs.getTimestamp("orderInfoAfterSuccess_PayTime"));
				// 把該筆資料的orderInfo_No對orderInfoDetails做一次匯總查詢取出總價。
				pstmt1.setInt(1, rs.getInt("orderInfo_No"));
				rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					result.setOrderInfoPriceTotal(rs1.getDouble("orderInfo_PriceTotal"));
				}
				result.setOrderInfoAfterSuccessPhone(rs.getString("orderInfoAfterSuccess_Phone"));
				result.setOrderInfoAfterSuccessDestination(rs.getString("orderInfoAfterSuccess_Destination"));
				result.setOrderInfoAfterSuccessBankAccount(rs.getString("orderInfoAfterSuccess_BankAccount"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt1 != null) {
				try {
					pstmt1.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
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

	
	
	public OrderInfoBean updateOrderInfoStatus(int orderInfoStatusNo,int orderInfoNo){
		String updateOrderInfoStatus="update orderInfo set orderInfoStatus_No=? where orderInfo_No=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		OrderInfoBean result=null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateOrderInfoStatus);
			pstmt.setInt(1, orderInfoStatusNo);
			pstmt.setInt(2, orderInfoNo);
			int rs= pstmt.executeUpdate();
			if(rs==0){
				result=null;
			}
			else{
				result=this.selectMyOrderInfoByNo(orderInfoNo);
				
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		finally {
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
	
	
	

	public double selectTotalPrice(int orderInfoNo) {

		String selectTotalPrice = "select * from selectTotalPrice where orderInfo_No=?";

		double result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectTotalPrice);
			pstmt.setInt(1, orderInfoNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("orderInfo_PriceTotal");

			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		finally {
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

	public List<OrderInfoBean> selectMyGroupOrderInfo(int groupInfoNo) {

		String selectMyGroupOrderInfo = "select * from selectMyGroupOrderInfo where groupInfo_No=?";
		String selectOrderInfoPriceTotal = "select * from selectTotalPrice where orderInfo_No=?";

		List<OrderInfoBean> result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectMyGroupOrderInfo);
			pstmt.setInt(1, groupInfoNo);
			rs = pstmt.executeQuery();
			pstmt1 = conn.prepareStatement(selectOrderInfoPriceTotal);
			result = new ArrayList<OrderInfoBean>();
			while (rs.next()) {
				OrderInfoBean bean = new OrderInfoBean();
				bean.setOrderInfoNo(rs.getInt("orderInfo_No"));
				bean.setMemberNo(rs.getInt("member_No"));
				bean.setMemberName(rs.getString("member_Name"));
				bean.setGroupAttendanceTotalSuccess(rs.getInt("groupAttendance_TotalSuccess"));
				bean.setGroupAttendanceTotalQt(rs.getInt("groupAttendance_TotalQt"));
				bean.setOrderInfoStatusNo(rs.getInt("orderInfoStatus_No"));
				bean.setOrderInfoStatus(rs.getString("orderInfoStatus"));
				bean.setOrderInfoAfterSuccessPackageNo(rs.getString("orderInfoAfterSuccess_PackageNo"));
				bean.setOrderInfoAfterSuccessPayTime(rs.getTimestamp("orderInfoAfterSuccess_PayTime"));
				// 把該筆資料的orderInfo_No對orderInfoDetails做一次匯總查詢取出總價。
				pstmt1.setInt(1, rs.getInt("orderInfo_No"));
				rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					bean.setOrderInfoPriceTotal(rs1.getDouble("orderInfo_PriceTotal"));
				}
				bean.setOrderInfoAfterSuccessPhone(rs.getString("orderInfoAfterSuccess_Phone"));
				bean.setOrderInfoAfterSuccessDestination(rs.getString("orderInfoAfterSuccess_Destination"));
				bean.setOrderInfoAfterSuccessBankAccount(rs.getString("orderInfoAfterSuccess_BankAccount"));

				result.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt1 != null) {
				try {
					pstmt1.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
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
