package com.song.web.room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

public class Room {
	private static Room instance = null;
//	private List<Session> sessions = new ArrayList<Session>();
//	private	Set<String> memberIds = new HashSet<String>();
	private List<Map<String,?>> list = new ArrayList<Map<String,?>>();
	
	public synchronized void join(Session session ,String memberId){
		Map map = new HashMap();
		System.out.println("memberIdroom : " + memberId);
		map.put("session", session);
		map.put("memberId", memberId);
		list.add(map);
		
		System.out.println(list.get(0).get("session"));
		System.out.println(list.get(0).get("memberId"));
	}

//	public synchronized void leave(Session session){
//		sessions.remove(session);
//	}
//	public synchronized void sendMessage(String message){
//		for(Session session: sessions){
//			if(session.isOpen()){
//				try{
//					session.getBasicRemote().sendText(message);
//				}catch(IOException e){
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	
	public synchronized void sendMessageDirect(String memberName,String message , String sendId , String receId){
		System.out.println("sendMessage !!");
		for(Map<String, ?>  member : list){
			System.out.println("member.get(memberId):" +member.get("memberId"));
			System.out.println("sendId : "+sendId);
			System.out.println("receId : "+receId);
			if((member.get("memberId").equals(sendId) 
					|| (member.get("memberId").equals(receId))
					&& ((Session)member.get("session")).isOpen())){
				try{
					System.out.println("here");
					((Session)member.get("session")).getBasicRemote().sendText(memberName + " : "+message);
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
//	
	public synchronized static Room getRoom(){
		if(instance == null) {
			instance = new Room();
		}
		return instance;
	}
}
