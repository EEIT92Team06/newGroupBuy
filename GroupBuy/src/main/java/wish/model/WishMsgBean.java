package wish.model;

public class WishMsgBean {
	private int wishMsgNo;
	private int wishNo;
	private int memberNo;
	private String wishMsgContent;
	private String nickName;
	private String memberPic;
	public int getWishMsgNo() {
		return wishMsgNo;
	}
	public void setWishMsgNo(int wishMsgNo) {
		this.wishMsgNo = wishMsgNo;
	}
	public int getWishNo() {
		return wishNo;
	}
	public void setWishNo(int wishNo) {
		this.wishNo = wishNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getWishMsgContent() {
		return wishMsgContent;
	}
	public void setWishMsgContent(String wishMsgContent) {
		this.wishMsgContent = wishMsgContent;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMemberPic() {
		return memberPic;
	}
	public void setMemberPic(String memberPic) {
		this.memberPic = memberPic;
	}
	@Override
	public String toString() {
		return "WishMsgBean [wishMsgNo=" + wishMsgNo + ", wishNo=" + wishNo + ", memberNo=" + memberNo
				+ ", wishMsgContent=" + wishMsgContent + ", nickName=" + nickName + ", memberPic=" + memberPic + "]";
	}
}
