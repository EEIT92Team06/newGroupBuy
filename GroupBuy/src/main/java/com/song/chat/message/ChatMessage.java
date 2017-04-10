package com.song.chat.message;

public class ChatMessage {
	private MessageType messageType;
	private String message;
	private String memberName;
	private String receId;
	

	public void setMessageType(MessageType v) {
		this.messageType = v;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessage(String v) {
		this.message = v;
	}

	public String getMessage() {
		return this.message;
	}

	public String getReceId() {
		return receId;
	}

	public void setReceId(String receId) {
		this.receId = receId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
}
