package creategroup.model;

import java.util.List;

import creategroup.model.dao.GroupInfoDAOJdbc;

public class CreateGroupService {
  private GroupInfoDAOJdbc groupInfoDAOJdbc;
  public CreateGroupService(GroupInfoDAOJdbc groupInfoDAOJdbc){
	  this.groupInfoDAOJdbc=groupInfoDAOJdbc;
  }
  public int createGroup(GroupInfoBean bean,List<PicBean> groupInfoPicBeanList){
	int insertNum=0;
    insertNum=groupInfoDAOJdbc.insert(bean,groupInfoPicBeanList);
    
    return insertNum;
  }
}
