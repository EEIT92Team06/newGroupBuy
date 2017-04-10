package wish.model;

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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
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
	@Override
	public String toString() {
		return "WishPoolBean [wishNo=" + wishNo + ", memberNo=" + memberNo + ", productType=" + productType + ", title="
				+ title + ", productName=" + productName + ", content=" + content + ", price=" + price + ", source="
				+ source + ", coverPic=" + coverPic + "]";
	}
}
