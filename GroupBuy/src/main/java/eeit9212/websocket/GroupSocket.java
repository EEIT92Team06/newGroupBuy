package eeit9212.websocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/groupsocket/{keyNo}")
public class GroupSocket {

	// 收到client訊息便執行此方法.
	// 印出client所發送的訊息,傳送訊息給client.
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		List<Session> sessionTemp = WebSocketSessionTemp.getSessionTemp();
		System.out.println("收到訊息Received: " + message);
		for (Session sess : sessionTemp) {
			Map<String, Object> userProperties = sess.getUserProperties();
			Object keyNo=userProperties.get("keyNo");
			if (message.equals(keyNo)) {
				sess.getBasicRemote().sendText(message);
			} 
		}
	}

	@OnOpen // client開啟連接.
	public void onOpen(Session session,@PathParam("keyNo")String keyNo) {
		System.out.println("Client connected");
		
		Map<String, Object> userProperties = session.getUserProperties();
		userProperties.put("keyNo", keyNo);
		List<Session> sessionTemp = WebSocketSessionTemp.getSessionTemp();
		sessionTemp.add(session);

	}

	@OnClose // client關閉連接.
	public void onClose(Session session) {
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Session> sessionTemp = WebSocketSessionTemp.getSessionTemp();
		sessionTemp.remove(session);
		System.out.println("Connection closed");

	}

	@OnError
	public void onError(Session session,Throwable e) {
		System.out.println("Connection error");
		e.printStackTrace();
//		onClose(session);
	}

}
