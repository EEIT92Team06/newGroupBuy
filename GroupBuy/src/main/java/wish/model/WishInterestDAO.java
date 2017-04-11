package wish.model;

import java.util.List;

public interface WishInterestDAO {
	List<Object[]> select(int wishNo); //某一許願點讚的所有人
	
	int count(int wishNo); //計算按讚人數
	
	Boolean insert(WishInterestBean bean);
	
	Boolean delete(WishInterestBean bean);
	
	WishInterestBean selectByWM(int wishNo,int memberNo); //用來判斷是否按讚

}
