package com.song.web.socket;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.song.chat.message.ChatMessage;
import com.song.chat.message.MessageType;
import com.song.web.room.Room;

@ServerEndpoint(value = "/chat")
public class ChatEndpoint {
	private Logger log = Logger.getLogger(ChatEndpoint.class.getSimpleName());
	private Room room = Room.getRoom();
	
	@OnOpen
	public void open(final Session session, EndpointConfig config){
	}
	@OnMessage
	public void onMessage(final Session session, final String messageJson){
		ObjectMapper mapper = new ObjectMapper();
		ChatMessage chatMessage = null;
		try {
			chatMessage = mapper.readValue(messageJson, ChatMessage.class);
		} catch (IOException e) {
			String message = "Beadly formatted message";
			try {
				session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, message));
			} catch (IOException e1) {
				log.severe(e1.getMessage());
			}
		}
		String sellerId = chatMessage.getReceId();
		System.out.println("sellerId : " + sellerId);
		Map<String, Object> properties = session.getUserProperties();
		if(chatMessage.getMessageType() == MessageType.LOGIN){
			String memberId = chatMessage.getMessage();
			String memberName = chatMessage.getMemberName();
			System.out.println("111memberId : " + memberId);
			properties.put("memberId", memberId);
			properties.put("memberName", memberName);
			room.join(session , memberId);
		}
		else{
			String memberId = (String) properties.get("memberId");
			String memberName = (String) properties.get("memberName");
			System.out.println("chatMessage.getMessage() : "+chatMessage.getMessage());
			System.out.println("chatMessage.getReceId() : "+chatMessage.getReceId());
			room.sendMessageDirect(memberName,(chatMessage.getMessage()),memberId,chatMessage.getReceId());
		}
	} 
	
//	@OnClose
//	public void onClose(Session session, CloseReason reason){
//		room.leave(session);
//		room.sendMessage((String)session.getUserProperties().get("name")+" - Left the room");
//	}
	
	@OnError
	public void onError(Session session, Throwable ex){
		log.info("Error: " + ex.getMessage());
	}
	
	
	
}
