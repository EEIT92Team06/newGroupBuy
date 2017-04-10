package creategroup.model;

import java.io.Serializable;
import java.sql.Blob;

public class GroupInfoPicBean  implements Serializable{
	private int groupInfoPicNo;
	private GroupInfoBean groupInfoNo;
	private byte[] groupInfoPicProductPic;

	public int getGroupInfoPicNo() {
		return groupInfoPicNo;
	}

	public void setGroupInfoPicNo(int groupInfoPicNo) {
		this.groupInfoPicNo = groupInfoPicNo;
	}

	public GroupInfoBean getGroupInfoNo() {
		return groupInfoNo;
	}

	public void setGroupInfoNo(GroupInfoBean groupInfoNo) {
		this.groupInfoNo = groupInfoNo;
	}

	public byte[] getGroupInfoPicProductPic() {
		return groupInfoPicProductPic;
	}

	public void setGroupInfoPicProductPic(byte[] groupInfoPicProductPic) {
		this.groupInfoPicProductPic = groupInfoPicProductPic;
	}

}
