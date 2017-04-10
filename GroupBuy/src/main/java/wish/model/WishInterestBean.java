package wish.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class WishInterestBean implements Serializable{
	private int wishNo;
	private int memberNo;
	private int count;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "WishInterestBean [wishNo=" + wishNo + ", memberNo=" + memberNo + ", count=" + count + "]";
	}
	// 必須重新定義equals()與hashCode()
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
                   .append(this.wishNo)
                   .append(this.memberNo)
                   .toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==this){
			return true;
		}
		if (!(obj instanceof WishInterestBean)) {
			return false;
		}
		
		WishInterestBean wishInterestBean = (WishInterestBean) obj;
	    return new EqualsBuilder()
	                .append(this.wishNo, wishInterestBean.getWishNo())
	                .append(this.memberNo,wishInterestBean.getMemberNo())
	                .isEquals();
	}
	
}
