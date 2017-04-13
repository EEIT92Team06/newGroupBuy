package searchgroup.model;

import java.util.List;

public interface GroupMsgDAO {
	public List<Object[]> selectMsg(int groupInfoNo);
	
	public List<Object[]> selectReplyMsg(int groupMsgNo);
		
	public void insert(GroupMsgBean groupMsgBean);
	
	public void insertReply(GroupMsgReplyBean groupMsgReplyBean);
}
