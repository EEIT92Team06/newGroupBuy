package wish.model;

public class WishPictureBean {
    
	private int wishPictureNo;
	private int wishNo;
	private String wishPicture;
	private WishPoolBean wishPoolBean;
	public int getWishPictureNo() {
		return wishPictureNo;
	}
	public void setWishPictureNo(int wishPictureNo) {
		this.wishPictureNo = wishPictureNo;
	}
	public int getWishNo() {
		return wishNo;
	}
	public void setWishNo(int wishNo) {
		this.wishNo = wishNo;
	}
	public String getWishPicture() {
		return wishPicture;
	}
	public void setWishPicture(String wishPicture) {
		this.wishPicture = wishPicture;
	}
	public WishPoolBean getWishPoolBean() {
		return wishPoolBean;
	}
	public void setWishPoolBean(WishPoolBean wishPoolBean) {
		this.wishPoolBean = wishPoolBean;
	}
	@Override
	public String toString() {
		return "WishPictureBean [wishPictureNo=" + wishPictureNo + ", wishNo=" + wishNo + ", wishPicture=" + wishPicture
				+ ", wishPoolBean=" + wishPoolBean + "]";
	}
	
	
	
	
	
}
