package eeit9212.model;

import java.sql.Timestamp;
import java.util.List;

public interface GroupInfoDAO {

	int updateGroupInfoDeadLine(int groupInfoNo, Timestamp deadLine);

	int updateGroupStatus(int groupInfoNo, int groupStatusNo);

	AttendGroupInfoBean selectMyAttendedByGroupInfoNo(int memberNo, int groupInfoNo);

	List<AttendGroupInfoBean> selectMyAttendedGroupInfo(int memberNo);

	CreateGroupInfoBean selectGroupInfoByGroupInfoNo(int groupInfoNo);

	List<CreateGroupInfoBean> selectMyCreatedGroupInfo(int memberNo);

}