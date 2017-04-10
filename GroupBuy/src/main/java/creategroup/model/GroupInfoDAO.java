package creategroup.model;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public interface GroupInfoDAO {
	//public GroupInfoBean select(int groupInfoNo);

	//public List<GroupInfoBean> selectAll();

	public int insert(GroupInfoBean bean,List<PicBean> groupInfoPicBeanList);
	
	//public GroupInfoBean update();

	//public boolean delete(int groupInfoNo);




}
