package creategroup.model;

import java.util.List;

public interface GroupInfoDetailsDAO {
	public GroupInfoDetailsBean select(int groupInfoDetailsNo);

	public List<GroupInfoDetailsBean> selectAll();

	public int insert(GroupInfoDetailsBean groupInfoDetailsBean);

	public GroupInfoDetailsBean update();

	public boolean delete(int groupInfoDetailsNo);
}
