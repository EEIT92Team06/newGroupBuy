package eeit9212.model;

import java.util.List;

public interface GroupInfoDetailsDAO {

	List<GroupInfoDetailsBean> selectGroupInfoDetail(int groupInfoNo);

}