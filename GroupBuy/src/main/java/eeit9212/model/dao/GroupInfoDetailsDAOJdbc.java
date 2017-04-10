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

import eeit9212.model.GroupInfoDetailsBean;
import eeit9212.model.GroupInfoDetailsDAO;
import eeit9212.model.OrderInfoDetailsBean;

public class GroupInfoDetailsDAOJdbc implements GroupInfoDetailsDAO {

	private DataSource dataSource;

	public GroupInfoDetailsDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beans.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		GroupInfoDetailsDAO groupInfoDetailsDAOJdbc = new GroupInfoDetailsDAOJdbc(dataSource);
		List<GroupInfoDetailsBean> select=groupInfoDetailsDAOJdbc.selectGroupInfoDetail(1);
		System.out.println(select);
		((ConfigurableApplicationContext) context).close();
	}


	@Override
	public List<GroupInfoDetailsBean> selectGroupInfoDetail(int groupInfoNo){
		String selectGroupInfoDetail="select * from groupInfoDetails where groupInfo_No=?";
		
		List<GroupInfoDetailsBean> result=null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectGroupInfoDetail);
			pstmt.setInt(1, groupInfoNo);
			rs = pstmt.executeQuery();
			result=new ArrayList<GroupInfoDetailsBean>();
			while (rs.next()) {
				GroupInfoDetailsBean bean = new GroupInfoDetailsBean();
				bean.setGroupInfoDetailsNo(rs.getInt("groupInfoDetails_No"));
				bean.setGroupInfoDetailsProdcutName(rs.getString("groupInfoDetails_ProdcutName"));
				bean.setGroupInfoDetailsProductPrice(rs.getDouble("groupInfoDetails_ProductPrice"));
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
