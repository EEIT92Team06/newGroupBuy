package wish.model;

import java.util.HashSet;
import java.util.Set;

public class WishPoolBean {
	private int wishNo;
	private int memberNo;
	private int productType;
	private String title;
	private String productName;
	private String content;
	private Double price;
	private String source;
	private String coverPic;
	private String memberNickName;
	
	private Set<WishPictureBean>pictures = new HashSet<>();
	private Set<WishInterestBean>interests = new HashSet<>();
	private Set<WishMsgBean>msgs = new HashSet<>();
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
	public int getProductType() {
		return productType;
	}
	public void setProductType(int productType) {
		this.productType = productType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	public Set<WishPictureBean> getPictures() {
		return pictures;
	}
	public void setPictures(Set<WishPictureBean> pictures) {
		this.pictures = pictures;
	}
	public Set<WishInterestBean> getInterests() {
		return interests;
	}
	public void setInterests(Set<WishInterestBean> interests) {
		this.interests = interests;
	}
	public Set<WishMsgBean> getMsgs() {
		return msgs;
	}
	public void setMsgs(Set<WishMsgBean> msgs) {
		this.msgs = msgs;
	}
	@Override
	public String toString() {
		return "WishPoolBean [wishNo=" + wishNo + ", memberNo=" + memberNo + ", productType=" + productType + ", title="
				+ title + ", productName=" + productName + ", content=" + content + ", price=" + price + ", source="
				+ source + ", coverPic=" + coverPic + ", memberNickName=" + memberNickName + "]";
	}

}
