package wish.model;

import java.util.List;

public interface WishMsgDAO {
	WishMsgBean select(int wishMsgNo);
	
	List<Object[]> selectByWishNo(int wishNo); //每個許願底下的所有留言
	
	Boolean insert(WishMsgBean bean);
	
	WishMsgBean update(WishMsgBean bean);
	
	Boolean delete(int wishMsgNo);
	
	
}
