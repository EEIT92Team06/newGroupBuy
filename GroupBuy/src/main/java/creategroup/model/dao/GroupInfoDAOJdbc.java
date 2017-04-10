package creategroup.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import creategroup.model.GroupInfoBean;
import creategroup.model.GroupInfoDAO;
import creategroup.model.GroupInfoDetailsBean;
import creategroup.model.PicBean;

public class GroupInfoDAOJdbc implements GroupInfoDAO {
	private DataSource dataSource;

	public GroupInfoDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	Connection conn = null;

	public final static String SELECT_BY_GroupInfo_No = "select*from groupInfo g join member m on g.member_No=m.member_No "
			+ "join groupStatus gs on g.groupStatus_No= gs.groupStatus_No "
			+ "join productType pt on g.productType_No=pt.productType_No " + "where groupInfo_No=?";
	public final static String SELECT_ALL = "select*from groupInfo g join member m on g.member_No=m.member_No "
			+ "join groupStatus gs on g.groupStatus_No= gs.groupStatus_No "
			+ "join productType pt on g.productType_No=pt.productType_No ";

	public final static String INSERTgroupInfo = "insert into groupInfo values(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERTgroupInfoDetails = "insert into groupInfoDetails values(?,?,?)";
	public static final String INSERTgroupInfoPic = "insert into groupInfoPic values(?,?)";

	public final static String UPDATE = "update groupInfo set groupInfo_No=?,member_No=?,groupStatus_No=?"
			+ "productType_No=?,groupInfo_Name=?,groupInfoMin_ProductQt=?"
			+ "groupInfo_tartDate=?,groupInfo_DeadLine=?,groupInfo_Content=?"
			+ "groupInfo_ShippingWay=?,groupInfo_BankAccount=?";
	public final static String DELETE = "delete from groupInfo where groupInfo_No=?";

	public static void main(String[] args) throws FileNotFoundException {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		GroupInfoDAO groupInfoDAO = (GroupInfoDAO) context.getBean("groupInfoDAOJdbc");
		// select
		// GroupInfoBean groupInfoBean = groupInfoDAO.select(1);
		// System.out.println("groupInfoBean=" + groupInfoBean);

		// select all
		// List<GroupInfoBean> result = groupInfoDAO.selectAll();
		// System.out.println("result=" + result);

		// Insert
//		List<GroupInfoDetailsBean> groupInfoDetailsBeanlist = new ArrayList<GroupInfoDetailsBean>();
//		GroupInfoDetailsBean groupInfoDetailsBean = new GroupInfoDetailsBean();
//		groupInfoDetailsBean.setGroupInfoDetailsProdcutName("紅色內褲");
//		groupInfoDetailsBean.setGroupInfoDetailsProductPrice(500);
//		groupInfoDetailsBeanlist.add(groupInfoDetailsBean);
//		System.out.println(groupInfoDetailsBean);
//		GroupInfoDetailsBean groupInfoDetailsBean1 = new GroupInfoDetailsBean();
//		groupInfoDetailsBean1.setGroupInfoDetailsProdcutName("藍色內褲");
//		groupInfoDetailsBean1.setGroupInfoDetailsProductPrice(1000);
//		groupInfoDetailsBeanlist.add(groupInfoDetailsBean1);
//		System.out.println(groupInfoDetailsBean1);
//
//		List<GroupInfoPicBean> groupInfoPicBeanList = new ArrayList<GroupInfoPicBean>();
//		GroupInfoPicBean GroupInfoPicBean = new GroupInfoPicBean();
//		
//		GroupInfoPicBean.setGroupInfoPicProductPic(null);
//		
//		GroupInfoPicBean.setGroupInfoPicProductPic(null);
//		groupInfoPicBeanList.add(GroupInfoPicBean);
//		GroupInfoPicBean GroupInfoPicBean1 = new GroupInfoPicBean();
//		GroupInfoPicBean.setGroupInfoPicProductPic(null);
//		groupInfoPicBeanList.add(GroupInfoPicBean1);
//
//		GroupInfoBean groupInfoBean = new GroupInfoBean();
//		groupInfoBean.setMemberNo(1);
//		groupInfoBean.setGroupStatusNo(1);
//		groupInfoBean.setProductTypeNo(1);
//		groupInfoBean.setGroupInfoName("爽團");
//		groupInfoBean.setGroupInfoMinProductQt(10);
//		groupInfoBean.setGroupInfoStartDate(new java.util.Date());
//		groupInfoBean.setGroupInfoDeadLine(new java.util.Date());
//		groupInfoBean.setGroupInfoContent("這團是測試用");
//		groupInfoBean.setGroupInfoShippingWay("黑貓");
//		groupInfoBean.setGroupInfoBankAccount("0806449");
//		groupInfoBean.setGroupInfoCoverPic(null);
//		groupInfoBean.setGroupInfoDetailsBean(groupInfoDetailsBeanlist);
//		groupInfoBean.setGroupInfoPicBean(groupInfoPicBeanList);
//
//		int insertNumber = groupInfoDAO.insert(groupInfoBean,null,null);
//		System.out.println("insertNumber=" + insertNumber);

		// delete
		// boolean delete = groupInfoDAO.delete(1);
		// System.out.println("delete=" + delete);
		((ConfigurableApplicationContext) context).close();
	}

//	@Override
//	public GroupInfoBean select(int groupInfoNo) {
//		GroupInfoBean bean = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement(SELECT_BY_GroupInfo_No);
//			pstmt.setInt(1, groupInfoNo);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				bean = new GroupInfoBean();
//				bean.setGroupInfoNo(rs.getInt("groupInfo_No"));
//				bean.setMemberNo(rs.getInt("member_No"));
//				bean.setMemberAccount(rs.getString("member_Account"));
//				bean.setGroupStatusNo(rs.getInt("groupStatus_No"));
//				bean.setProductTypeNo(rs.getInt("productType_No"));
//				bean.setGroupInfoName(rs.getString("groupInfo_Name"));
//				bean.setGroupInfoMinProductQt(rs.getInt("groupInfo_MinProductQt"));
//				bean.setGroupInfoStartDate(rs.getDate("groupInfo_StartDate"));
//				bean.setGroupInfoDeadLine(rs.getDate("groupInfo_DeadLine"));
//				bean.setGroupInfoContent(rs.getString("groupInfo_Content"));
//				bean.setGroupInfoShippingWay(rs.getString("groupInfo_ShippingWay"));
//				bean.setGroupInfoBankAccount(rs.getString("groupInfo_BankAccount"));
//			}
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return bean;
//	}

//	@Override
//	public List<GroupInfoBean> selectAll() {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<GroupInfoBean> result = null;
//		try {
//			result = new ArrayList<GroupInfoBean>();
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement(SELECT_ALL);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				GroupInfoBean bean = new GroupInfoBean();
//				bean.setGroupInfoNo(rs.getInt("groupInfo_No"));
//				bean.setMemberNo(rs.getInt("member_No"));
//				bean.setMemberAccount(rs.getString("member_Account"));
//				bean.setGroupStatusNo(rs.getInt("groupStatus_No"));
//				bean.setProductTypeNo(rs.getInt("productType_No"));
//				bean.setGroupInfoName(rs.getString("groupInfo_Name"));
//				bean.setGroupInfoMinProductQt(rs.getInt("groupInfo_MinProductQt"));
//				bean.setGroupInfoStartDate(rs.getDate("groupInfo_StartDate"));
//				bean.setGroupInfoDeadLine(rs.getDate("groupInfo_DeadLine"));
//				bean.setGroupInfoContent(rs.getString("groupInfo_Content"));
//				bean.setGroupInfoShippingWay(rs.getString("groupInfo_ShippingWay"));
//				bean.setGroupInfoBankAccount(rs.getString("groupInfo_BankAccount"));
//				result.add(bean);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return result;
//
//	}


	@Override
	public int insert(GroupInfoBean bean,List<PicBean> groupInfoPicBeanList) {
		int insertNum = 0;
		int insertGroupInfo = 0;
		int insertGroupInfoDetails = 0;
		int insertGroupInfoPic = 0;

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet generatedKey = null;
		int GroupInfoPk = 0;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(INSERTgroupInfo, Statement.RETURN_GENERATED_KEYS);

			if (bean != null) {
				// 新增 groupInfo(創團主表)
				pstmt.setInt(1, bean.getMemberNo());
				pstmt.setInt(2, bean.getGroupStatusNo());
				pstmt.setInt(3, bean.getProductTypeNo());
				pstmt.setString(4, bean.getGroupInfoName());
				pstmt.setInt(5, bean.getGroupInfoMinProductQt());
				java.util.Date start = new java.util.Date();
				long d=start.getTime();
				Timestamp time=new Timestamp(d);
				pstmt.setTimestamp(6,time);
				java.util.Date dead = bean.getGroupInfoDeadLine();
				pstmt.setTimestamp(7,bean.getGroupInfoDeadLine());
				pstmt.setString(8, bean.getGroupInfoContent());
				pstmt.setString(9, bean.getGroupInfoShippingWay());
				pstmt.setString(10, bean.getGroupInfoBankAccount());
				pstmt.setInt(11, 0);
				pstmt.setBinaryStream(12, bean.getGroupInfoCoverPic().getFis(), bean.getGroupInfoCoverPic().getSize());
				insertGroupInfo = pstmt.executeUpdate();
				generatedKey = pstmt.getGeneratedKeys();
				if (generatedKey.next()) {
					GroupInfoPk = generatedKey.getInt(1);
				} else {
					throw new SQLException("Creating user failed, no generated key obtained.");
				}
				// 新增 groupInfoDetails(創團明細)
				List<GroupInfoDetailsBean> items = bean.getGroupInfoDetails();
				for (GroupInfoDetailsBean groupInfoDetailsBean : items) {
					pstmt2 = conn.prepareStatement(INSERTgroupInfoDetails);
					pstmt2.setInt(1, GroupInfoPk);
					pstmt2.setString(2, groupInfoDetailsBean.getGroupInfoDetailsProdcutName());
					pstmt2.setDouble(3, groupInfoDetailsBean.getGroupInfoDetailsProductPrice());
					insertGroupInfoDetails = pstmt2.executeUpdate();
				}
				// 新增groupInfoPic(創團照片)
				List<PicBean> items1 = bean.getGroupInfoPics();
				for (PicBean groupInfoPicBean : items1) {
					pstmt3 = conn.prepareStatement(INSERTgroupInfoPic);
					pstmt3.setInt(1, GroupInfoPk);
					pstmt3.setBinaryStream(2, groupInfoPicBean.getFis(), groupInfoPicBean.getSize());
					insertGroupInfoPic = pstmt3.executeUpdate();
				}
				conn.commit();
				insertNum = 1;
				conn.setAutoCommit(true);
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
		return GroupInfoPk;

	}

	public static byte[] toBinaryByFileStream(String filePath) {
		byte[] pic = null;
		File file = new File(filePath);
		try {
			InputStream fis = new FileInputStream(file);
			if (fis != null) {
				int len = fis.available();
				pic = new byte[len];
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;

	}

//	@Override
//	public GroupInfoBean update() {
//		return null;
//	}

//	@Override
//	public boolean delete(int groupInfoNo) {
//		boolean delete = false;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement(DELETE);
//			pstmt.setInt(1, groupInfoNo);
//			int deleteNum = pstmt.executeUpdate();
//			if (deleteNum == 1) {
//				delete = true;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return delete;
//	}

}
