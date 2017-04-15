package searchgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="groupMsg")
public class GroupMsgBean {
	@Id
	@Column(name = "groupMsg_No")
	@GeneratedValue(strategy = GenerationType.IDENTITY)    
	private int groupMsgNo;
	@Column(name="member_No")
	private int memberNo;
	@Column(name="groupInfo_No")
	private int groupInfoNo;
	@Column(name="groupMsg_Content")
	private String groupMsgContent;
	
	public GroupMsgBean() {
	}
	
	public GroupMsgBean(int memberNo, int groupInfoNo, String groupMsgContent) {
		this.memberNo = memberNo;
		this.groupInfoNo = groupInfoNo;
		this.groupMsgContent = groupMsgContent;
	}
	
	public int getGroupMsgNo() {
		return groupMsgNo;
	}
	public void setGroupMsgNo(int groupMsgNo) {
		this.groupMsgNo = groupMsgNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getGroupInfoNo() {
		return groupInfoNo;
	}
	public void setGroupInfoNo(int groupInfoNo) {
		this.groupInfoNo = groupInfoNo;
	}
	public String getGroupMsgContent() {
		return groupMsgContent;
	}
	public void setGroupMsgContent(String groupMsgContent) {
		this.groupMsgContent = groupMsgContent;
	}
	
	
	@Override
	public String toString() {
		return "groupMsgBean [groupMsgNo=" + groupMsgNo + ", memberNo=" + memberNo + ", groupInfoNo=" + groupInfoNo
				+ ", groupMsgContent=" + groupMsgContent + "]";
	}
	
	
}
