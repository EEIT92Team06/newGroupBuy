package eeit9212.model;

import java.util.List;

public interface GroupInfoPicDAO {

	GroupInfoPicBean selectGroupInfoPicByNo(int groupInfoPicNo);

	List<GroupInfoPicBean> selectGroupInfoPic(int groupInfoNo);

}