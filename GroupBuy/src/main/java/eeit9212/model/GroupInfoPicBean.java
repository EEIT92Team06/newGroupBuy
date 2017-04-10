package eeit9212.model;

import java.sql.Blob;
import java.util.Arrays;

public class GroupInfoPicBean {
	
	private Integer groupInfoPicNo;
	private Integer groupInfoNo;
	private Blob groupInfoPicProductPic;
	
	@Override
	public String toString() {
		return "GroupInfoPicBean [groupInfoPicNo=" + groupInfoPicNo + ", groupInfoNo=" + groupInfoNo
				+ ", groupInfoPicProductPic=" + groupInfoPicProductPic + "]";
	}

	public Integer getGroupInfoPicNo() {
		return groupInfoPicNo;
	}

	public void setGroupInfoPicNo(Integer groupInfoPicNo) {
		this.groupInfoPicNo = groupInfoPicNo;
	}

	public Integer getGroupInfoNo() {
		return groupInfoNo;
	}

	public void setGroupInfoNo(Integer groupInfoNo) {
		this.groupInfoNo = groupInfoNo;
	}

	public Blob getGroupInfoPicProductPic() {
		return groupInfoPicProductPic;
	}

	public void setGroupInfoPicProductPic(Blob groupInfoPicProductPic) {
		this.groupInfoPicProductPic = groupInfoPicProductPic;
	}

}
