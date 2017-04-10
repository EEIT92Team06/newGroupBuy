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

import eeit9212.model.AttendGroupInfoBean;
import eeit9212.model.OrderInfoDetailsBean;
import eeit9212.model.OrderInfoDetailsDAO;

public class OrderInfoDetailsDAOJdbc implements OrderInfoDetailsDAO {
	private DataSource dataSource;

	public OrderInfoDetailsDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beans.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		OrderInfoDetailsDAO orderInfoDetailsDAOJdbc = new OrderInfoDetailsDAOJdbc(dataSource);
		OrderInfoDetailsBean select1=orderInfoDetailsDAOJdbc.selectOneOrderInfoDetails(1, 6);
		System.out.println(select1);
		
		List<OrderInfoDetailsBean> select = orderInfoDetailsDAOJdbc.selectOrderInfoDetails(1);
		System.out.println(select);

		for (OrderInfoDetailsBean bean : select) {
			System.out.print(bean.getOrderInfoDetailsNo() + "  ");
			System.out.print(bean.getGroupInfoDetailsProdcutName() + "  ");
			System.out.print(bean.getProductTotalPriceByQt() + "  ");
			System.out.println(bean.getOrderInfoDetailsProductQt());
		}

		((ConfigurableApplicationContext) context).close();
	}

	/* (non-Javadoc)
	 * @see eeit9212.model.dao.OrderInfoDetailsDAO#selectOneOrderInfoDetails(int, int)
	 */
	@Override
	public OrderInfoDetailsBean selectOneOrderInfoDetails(int orderInfoNo,int groupInfoDetailsNo) {
		
		String selectOneOrderInfoDetails="select * from selectOrderInfoDetails where orderInfo_No=? and groupInfoDetails_No=?";
		
		OrderInfoDetailsBean result=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectOneOrderInfoDetails);
			pstmt.setInt(1, orderInfoNo);
			pstmt.setInt(2, groupInfoDetailsNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = new OrderInfoDetailsBean();
				result.setOrderInfoDetailsNo(rs.getInt("orderInfoDetails_No"));
				result.setGroupInfoDetailsProdcutName(rs.getString("groupInfoDetails_ProdcutName"));
				result.setProductTotalPriceByQt(rs.getDouble("productTotalPriceByQt"));
				result.setOrderInfoDetailsProductQt(rs.getInt("orderInfoDetails_ProductQt"));

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
	
	/* (non-Javadoc)
	 * @see eeit9212.model.dao.OrderInfoDetailsDAO#selectOrderInfoDetails(int)
	 */
	@Override
	public List<OrderInfoDetailsBean> selectOrderInfoDetails(int orderInfoNo) {
//		String selectOrderInfoDetails = "select orderInfoDetails_No,groupInfoDetails.groupInfoDetails_ProdcutName,"
//				+ "groupInfoDetails.groupInfoDetails_ProductPrice*orderInfoDetails_ProductQt productTotalPriceByQt"
//				+ ",orderInfoDetails_ProductQt" + " from orderInfoDetails"
//				+ " join groupInfoDetails on orderInfoDetails.groupInfoDetails_No=groupInfoDetails.groupInfoDetails_No"
//				+ " where orderInfo_No=?";
		
		String selectOrderInfoDetails="select * from selectOrderInfoDetails where orderInfo_No=?";
		
		List<OrderInfoDetailsBean> result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectOrderInfoDetails);
			pstmt.setInt(1, orderInfoNo);
			rs = pstmt.executeQuery();
			result = new ArrayList<OrderInfoDetailsBean>();
			while (rs.next()) {
				OrderInfoDetailsBean bean = new OrderInfoDetailsBean();
				bean.setOrderInfoDetailsNo(rs.getInt("orderInfoDetails_No"));
				bean.setGroupInfoDetailsProdcutName(rs.getString("groupInfoDetails_ProdcutName"));
				bean.setProductTotalPriceByQt(rs.getDouble("productTotalPriceByQt"));
				bean.setOrderInfoDetailsProductQt(rs.getInt("orderInfoDetails_ProductQt"));
				result.add(bean);

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

}
