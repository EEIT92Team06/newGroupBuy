package wish.model;

import java.util.List;

public interface WishPoolDAO {
	
	WishPoolBean select(int wishNo);
	
	List<WishPoolBean> select(); //所有許願
	
	List<WishPoolBean> selectMyWish(int memberNo); //我的許願
	
	Integer selectByCover(String cover);//透過封面取得WishNo
	
	List<WishPoolBean> selectForSearch(int productType);

	List<WishPoolBean> selectForSearch(String titleKeyWord,int productType);
	
    Boolean insert(WishPoolBean bean);
    
    Boolean delete(int wishNo);
    
    WishPoolBean update(WishPoolBean bean);
    
}
