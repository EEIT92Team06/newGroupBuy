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

import org.json.JSONObject;

@ServerEndpoint("/groupsocket/{keyNo}/{groupInfoNo}")
public class GroupSocket {

	// 收到client訊息便執行此方法.
	// 印出client所發送的訊息,傳送訊息給client.
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		List<Session> sessionTemp = WebSocketSessionTemp.getSessionTemp();
		System.out.println("收到訊息Received: " + message);
		JSONObject jsonObject = new JSONObject(message);
		Map<String, Object> nowUserProperties = session.getUserProperties();
		Object fromKeyNo = nowUserProperties.get("keyNo");
		System.out.println("來自keyNo: " + fromKeyNo);
		jsonObject.put("fromKeyNo", fromKeyNo);
		if ("sendAllOrder".equals(jsonObject.get("target"))) {
			for (Session sess : sessionTemp) {
				Map<String, Object> userProperties = sess.getUserProperties();
				Object groupNo=userProperties.get("groupInfoNo");
				System.out.println("現在買家的groupNo=" + groupNo);	
				if (fromKeyNo.equals(groupNo)) {
					System.out.println("把"+jsonObject+"傳到keyNo="+userProperties.get("keyNo"));
					sess.getBasicRemote().sendText(jsonObject.toString());
				}
			}
			return;
		}
		for (Session sess : sessionTemp) {
			Map<String, Object> userProperties = sess.getUserProperties();
			Object keyNo = userProperties.get("keyNo");		
			System.out.println("現在的keyNo=" + keyNo);
			System.out.println("要送的對象:"+jsonObject.get("target"));
			if (jsonObject.get("target").equals(keyNo)) {
				System.out.println("把"+jsonObject+"傳到keyNo="+keyNo);
				sess.getBasicRemote().sendText(jsonObject.toString());
			}
		}
	}

	@OnOpen // client開啟連接.
	public void onOpen(Session session,@PathParam("keyNo")String keyNo,@PathParam("groupInfoNo")String groupInfoNo) {
		System.out.println("Client connected");
		System.out.println("keyNo="+keyNo);
		System.out.println("groupInfoNo="+groupInfoNo);
		Map<String, Object> userProperties = session.getUserProperties();
		
		userProperties.put("keyNo", keyNo);
		userProperties.put("groupInfoNo", groupInfoNo);
		List<Session> sessionTemp = WebSocketSessionTemp.getSessionTemp();
		sessionTemp.add(session);
		System.out.println("sessionTemp.size()="+sessionTemp.size());

	}

	@OnClose // client關閉連接.
	public void onClose(Session session) {
		System.out.println(session.getUserProperties().get("keyNo")+"號keyNo連線中止");
		try {
			session.close();
		} catch (IOException e) {
//			e.printStackTrace();
		}
		List<Session> sessionTemp = WebSocketSessionTemp.getSessionTemp();
		sessionTemp.remove(session);
		System.out.println("Connection closed");

	}

	@OnError
	public void onError(Session session,Throwable e) {
		System.out.println(session.getUserProperties().get("keyNo")+"號keyNo發生錯誤");
		System.out.println("Connection error");
//		e.printStackTrace();
//		onClose(session);
	}

}
