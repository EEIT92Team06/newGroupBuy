package wish.model;

import java.util.List;

public interface WishPictureDAO {

	WishPictureBean select(int wishPicNo);
	
	List<WishPictureBean> selectByWishNo(int wishNo); //某個許願裡的所有圖片
	
	Boolean insert(WishPictureBean bean);
	
	WishPictureBean update(WishPictureBean bean);
	
	Boolean delete(int wishPicNo);
}
