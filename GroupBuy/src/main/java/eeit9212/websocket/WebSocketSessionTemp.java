package eeit9212.websocket;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

public class WebSocketSessionTemp {

	static List<Session> sessionTemp;
	
	static{
		sessionTemp=new ArrayList<Session>();
	}
	
	public static List<Session> getSessionTemp(){
		return sessionTemp;
	}
	
}
