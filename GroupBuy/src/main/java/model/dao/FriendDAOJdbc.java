package model.dao;

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

import model.FriendBean;
import model.FriendDAO;
import model.MemberBean;

public class FriendDAOJdbc implements FriendDAO {

	private DataSource dataSource;

	public FriendDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9211/beans.config.xml");
		FriendDAO friendDAOJdbc = (FriendDAO) context.getBean("friendDAOJdbc");

//		List<FriendBean> ss = friendDAOJdbc.selectMember(1, "2");
//		System.out.println(ss);
		boolean aaaaaa = friendDAOJdbc.insertRelation(55, 2, 2103);
		System.out.println(aaaaaa);
		System.out.println(friendDAOJdbc.selectRelationList(999, 2101));
		((ConfigurableApplicationContext) context).close();
	}

//	@Override
//	public List<String[]> selectMember1(String words) {
//		String selectMember = "select member_No, member_Account, member_Name, member_NickName, member_Pic"
//				+ " from member where member_Name LIKE ? OR member_NickName LIKE ? OR member_Account LIKE ?";
//		List<String[]> result = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			result = new ArrayList<String[]>();
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement(selectMember);
//			pstmt.setString(1, "%" + words + "%");
//			pstmt.setString(2, "%" + words + "%");
//			pstmt.setString(3, words);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				String[] result1 = new String[5];
//				result1[0] = rs.getString("member_No");
//				result1[1] = rs.getString("member_Account");
//				result1[2] = rs.getString("member_Name");
//				result1[3] = rs.getString("member_NickName");
//				result1[4] = rs.getString("member_Pic");
//				result.add(result1);
//			}
//		} catch (SQLException e) {
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
//	}

	@Override
	public List<FriendBean> selectRelationList(int loginMemberNo, int friendStatusNo) {
		String selectRelationList = "SELECT friend_No, memberfriend_No, member_Name, member_NickName, member_Pic "
				+ "FROM friend JOIN member ON friend.memberFriend_No=member.member_No "
				+ "WHERE friend.member_No=? AND friendStatus_No=?";
		List<FriendBean> result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			result = new ArrayList<FriendBean>();
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectRelationList);
			pstmt.setInt(1, loginMemberNo);
			pstmt.setInt(2, friendStatusNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FriendBean result1 = new FriendBean();
				result1.setFriendNo(rs.getInt("friend_No"));
				result1.setMemberNo(rs.getInt("memberFriend_No"));
				result1.setMemberFriendNo(rs.getInt("memberFriend_NO"));
				result1.setMemberName(rs.getString("member_Name"));
				result1.setMemberNickName(rs.getString("member_NickName"));
				result1.setMemberPic(rs.getString("member_Pic"));
				result.add(result1);
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

	@Override
	public List<FriendBean> selectRequestedList(int memberFriendNo, int friendStatusNo) {
		String selectRelationList = "SELECT friend_No, friend.member_No, member_Name, member_NickName, member_Pic "
				+ "FROM friend JOIN member ON friend.member_No=member.member_No "
				+ "WHERE friend.memberFriend_No=? AND friendStatus_No=?";
		List<FriendBean> result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			result = new ArrayList<FriendBean>();
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectRelationList);
			pstmt.setInt(1, memberFriendNo);
			pstmt.setInt(2, friendStatusNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FriendBean result1 = new FriendBean();
				result1.setFriendNo(rs.getInt("friend_No"));
				result1.setMemberNo(rs.getInt("member_No"));
				result1.setMemberFriendNo(rs.getInt("member_No"));
				result1.setMemberName(rs.getString("member_Name"));
				result1.setMemberNickName(rs.getString("member_NickName"));
				result1.setMemberPic(rs.getString("member_Pic"));
				result.add(result1);
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

	@Override
	public boolean insertRelation(int loginMemberNo, int memberFriendNo, int friendStatusNo) {
		String insertRelation = "INSERT INTO friend (member_No, memberfriend_No, friendStatus_No) VALUES(?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(insertRelation);
			pstmt.setInt(1, loginMemberNo);
			pstmt.setInt(2, memberFriendNo);
			pstmt.setInt(3, friendStatusNo);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				return true;
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
		return false;
	}

	@Override
	public boolean deleteRelationFromList(int friendNo) {
		String deleteRelationFromList = "DELETE FROM friend WHERE friend_No=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(deleteRelationFromList);
			pstmt.setInt(1, friendNo);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				return true;
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
		return false;
	}

	@Override
	public boolean deleteRelation(int loginMemberNo, int memberFriendNo) {
		String deleteRelation = "DELETE FROM friend WHERE member_No=? AND memberFriend_No=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(deleteRelation);
			pstmt.setInt(1, loginMemberNo);
			pstmt.setInt(2, memberFriendNo);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				return true;
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
		return false;
	}

	@Override
	public boolean updateRelation(int friendNo, int friendStatusNo) {
		String updateRelation = "UPDATE friend SET friendStatus_No=? WHERE friend_No=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateRelation);
			pstmt.setInt(1, friendStatusNo);
			pstmt.setInt(2, friendNo);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				return true;
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
		return false;
	}

	@Override
	public List<FriendBean> selectMember(int loginMemberNo, String words) {
		String selectMember ="SELECT friend_No, (fd.member_No)fdmember_No, memberFriend_No, friendStatus_No, member.member_No, member_Account, member_Name, member_NickName, member_Pic FROM member LEFT OUTER JOIN (SELECT * FROM friend WHERE member_No=? OR (memberFriend_No=? AND (friendStatus_No=2102 OR friendStatus_No=2103))) fd ON member.member_No=fd.member_No OR member.member_No=fd.memberFriend_No WHERE member.member_No!=? and (member_Name LIKE ? or member_NickName LIKE ? or member_Account LIKE ?)";
//		String selectMember = "SELECT friend_No, (fd.member_No)fdmember_No, memberFriend_No, friendStatus_No, "
//				+ "member.member_No, member_Account, member_Name, member_NickName, member_Pic "
//				+ "FROM member LEFT OUTER JOIN (SELECT * FROM friend WHERE member_No=? "
//				+ "OR (memberFriend_No=? AND (friendStatus_No=2102 OR friendStatus_No=2103))) fd "
//				+ "ON member.member_No=fd.member_No OR member.member_No=fd.memberFriend_No WHERE member.member_No!=? "
//				+ "and (member_Name LIKE ? or member_NickName LIKE ? or member_Account LIKE ?)";
		List<FriendBean> result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			result = new ArrayList<FriendBean>();
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectMember);
			pstmt.setInt(1, loginMemberNo);
			pstmt.setInt(2, loginMemberNo);
			pstmt.setInt(3, loginMemberNo);
			pstmt.setString(4, "%" + words + "%");
			pstmt.setString(5, "%" + words + "%");
			pstmt.setString(6, words);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FriendBean result1 = new FriendBean();
				result1.setFriendNo(rs.getInt("friend_No"));
				result1.setFdMemberNo(rs.getInt("fdmember_No"));
				result1.setMemberFriendNo(rs.getInt("memberFriend_No"));
				result1.setFriendStatusNo(rs.getInt("friendStatus_No"));
				result1.setMemberNo(rs.getInt("member_No"));
				result1.setMemberAccount(rs.getString("member_Account"));
				result1.setMemberName(rs.getString("member_Name"));
				result1.setMemberNickName(rs.getString("member_NickName"));
				result1.setMemberPic(rs.getString("member_Pic"));
				result.add(result1);
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

	@Override
	public FriendBean selectRelation(int loginMemberNo, int urlMemberNo) {
		String selectRelation = "SELECT friend_No, (fd.member_No)fdmember_No, memberFriend_No, friendStatus_No, "
				+ "member.member_No, member_Account, member_Name, member_NickName, member_Pic "
				+ "FROM member LEFT OUTER JOIN (SELECT * FROM friend WHERE member_No=? "
				+ "OR (memberFriend_No=? AND (friendStatus_No=2102 OR friendStatus_No=2103))) fd "
				+ "ON member.member_No=fd.member_No OR member.member_No=fd.memberFriend_No "
				+ "WHERE member.member_No!=? and member.member_No=?";
		FriendBean result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(selectRelation);
			pstmt.setInt(1, loginMemberNo);
			pstmt.setInt(2, loginMemberNo);
			pstmt.setInt(3, loginMemberNo);
			pstmt.setInt(4, urlMemberNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = new FriendBean();
				result.setFriendNo(rs.getInt("friend_No"));
				result.setFdMemberNo(rs.getInt("fdmember_No"));
				result.setMemberFriendNo(rs.getInt("memberFriend_No"));
				result.setFriendStatusNo(rs.getInt("friendStatus_No"));
				result.setMemberNo(rs.getInt("member_No"));
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
