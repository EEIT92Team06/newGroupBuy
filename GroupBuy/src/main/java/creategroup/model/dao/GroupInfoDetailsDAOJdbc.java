package creategroup.model.dao;

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

import creategroup.model.GroupInfoDetailsBean;
import creategroup.model.GroupInfoDetailsDAO;

public class GroupInfoDetailsDAOJdbc implements GroupInfoDetailsDAO {
	private DataSource dataSource;

	public GroupInfoDetailsDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		GroupInfoDetailsDAO groupInfoDetailsDAO = (GroupInfoDetailsDAO) context.getBean("groupInfoDetailsDAOJdbc");
		// select
		// GroupInfoDetailsBean select = groupInfoDetailsDAO.select(2);
		// System.out.println("select=" + select);

		// selectAll
		// List<GroupInfoDetailsBean> selectAll=groupInfoDetailsDAO.selectAll();
		// System.out.println("selectAll="+selectAll);

		// insert
//		 GroupInfoDetailsBean groupInfoDetailsBean=(GroupInfoDetailsBean)context.getBean("groupInfoDetailsBean");
//		 groupInfoDetailsBean.setGroupInfoNo(1);
//		 groupInfoDetailsBean.setGroupInfoDetailsProdcutName("牛肉麵");
//		 groupInfoDetailsBean.setGroupInfoDetailsProductPrice(120);
//		 int insertNumber=groupInfoDetailsDAO.insert(groupInfoDetailsBean);
//		 System.out.println("insertNumber="+insertNumber);
		
		//delete
		boolean delete=groupInfoDetailsDAO.delete(12);
		System.out.println("delete="+delete);
		((ConfigurableApplicationContext) context).close();

	}

	public static final String SELECT = "select * from  groupInfoDetails where groupInfoDetails_No=?";

	@Override
	public GroupInfoDetailsBean select(int groupInfoDetailsNo) {
		GroupInfoDetailsBean result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SELECT);
			pstmt.setInt(1, groupInfoDetailsNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				GroupInfoDetailsBean groupInfoDetailsBean = new GroupInfoDetailsBean();
				groupInfoDetailsBean.setGroupInfoDetailsNo(rs.getInt("groupInfoDetails_No"));
				groupInfoDetailsBean.setGroupInfoNo(rs.getInt("groupInfo_No"));
				groupInfoDetailsBean.setGroupInfoDetailsProdcutName(rs.getString("groupInfoDetails_ProdcutName"));
				groupInfoDetailsBean.setGroupInfoDetailsProductPrice(rs.getDouble("groupInfoDetails_ProductPrice"));
				result = groupInfoDetailsBean;
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

	public static final String SELECTALL = "select * from groupInfoDetails";

	@Override
	public List<GroupInfoDetailsBean> selectAll() {
		List<GroupInfoDetailsBean> result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			result = new ArrayList<GroupInfoDetailsBean>();
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SELECTALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GroupInfoDetailsBean groupInfoDetailsBean = new GroupInfoDetailsBean();
				groupInfoDetailsBean.setGroupInfoDetailsNo(rs.getInt("groupInfoDetails_No"));
				groupInfoDetailsBean.setGroupInfoNo(rs.getInt("groupInfo_No"));
				groupInfoDetailsBean.setGroupInfoDetailsProdcutName(rs.getString("groupInfoDetails_ProdcutName"));
				groupInfoDetailsBean.setGroupInfoDetailsProductPrice(rs.getDouble("groupInfoDetails_ProductPrice"));
				result.add(groupInfoDetailsBean);
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

	public static final String INSERT = "insert into groupInfoDetails values(?,?,?)";

	@Override
	public int insert(GroupInfoDetailsBean groupInfoDetailsBean) {
		int insertNumber = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, groupInfoDetailsBean.getGroupInfoNo());
			pstmt.setString(2, groupInfoDetailsBean.getGroupInfoDetailsProdcutName());
			pstmt.setDouble(3, groupInfoDetailsBean.getGroupInfoDetailsProductPrice());
			insertNumber = pstmt.executeUpdate();
			if (insertNumber == 1) {
				return insertNumber;
			}

		} catch (SQLException e) {
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
		return insertNumber;
	}

	@Override
	public GroupInfoDetailsBean update() {
		return null;
	}

	public static final String DELETE = "delete from groupInfoDetails where groupInfoDetails_No=?";

	@Override
	public boolean delete(int groupInfoDetailsNo) {
		boolean result = false;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1,groupInfoDetailsNo );
			int deleteNumber = pstmt.executeUpdate();
			if (deleteNumber == 1) {
				result = true;
			}
		} catch (SQLException e) {
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
