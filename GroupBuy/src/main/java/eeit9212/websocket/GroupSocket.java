package eeit9212.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/groupsocket")
public class GroupSocket {

	//收到client訊息便執行此方法.
	//印出client所發送的訊息,傳送訊息給client.
	  @OnMessage
	  public void onMessage(String message, Session session)
	    throws IOException, InterruptedException {
	   
	    System.out.println("收到訊息Received: " + message);
	   
	    session.getBasicRemote().sendText("Server has been received message.");
	   
//	   int sentMessages = 1;
//	    while(sentMessages < 4){
//	      Thread.sleep(1000);
//	      session.getBasicRemote().
//	        sendText("Server is counting 1 to 3. Count: "
//	          + sentMessages);
//	      sentMessages++;
//	    }
	   
//	    session.getBasicRemote().sendText("End");
	  }
	   
	  @OnOpen//client開啟連接.
	  public void onOpen() {
	    System.out.println("Client connected");
	  }
	 
	  @OnClose//client關閉連接.
	  public void onClose() {
	    System.out.println("Connection closed");
	  }
	
	
	
	
	
}
