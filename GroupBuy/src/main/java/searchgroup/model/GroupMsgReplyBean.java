package searchgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="groupMsgReply")
public class GroupMsgReplyBean {
	@Id
	@Column(name = "groupMsgReply")
	@GeneratedValue(strategy = GenerationType.IDENTITY)    
	private int groupMsgReplyNo;
	@Column(name="member_No")
	private int memberNo;
	@Column(name="groupMsg_No")
	private int groupMsgNo;
	@Column(name="groupMsgReply_Content")
	private String groupMsgReplyContent;
	
	public GroupMsgReplyBean(int memberNo, int groupMsgNo, String groupMsgReplyContent) {
		this.memberNo = memberNo;
		this.groupMsgNo = groupMsgNo;
		this.groupMsgReplyContent = groupMsgReplyContent;
	}
	
	public int getGroupMsgReplyNo() {
		return groupMsgReplyNo;
	}
	public void setGroupMsgReplyNo(int groupMsgReplyNo) {
		this.groupMsgReplyNo = groupMsgReplyNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getGroupMsgNo() {
		return groupMsgNo;
	}
	public void setGroupMsgNo(int groupMsgNo) {
		this.groupMsgNo = groupMsgNo;
	}
	public String getGroupMsgReplyContent() {
		return groupMsgReplyContent;
	}
	public void setGroupMsgReplyContent(String groupMsgReplyContent) {
		this.groupMsgReplyContent = groupMsgReplyContent;
	}
	
	
}
