package com.tk_ord.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/WebSocket_server_order/{userName}")
public class WebSocket_Order {

	// 有人連進WS_server得到一個websocket Session物件存在Set內(執行緒安全)
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) {
		// 取得USER連進來的session後存進Set裡
		connectedSessions.add(userSession);
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		synchronized (this) {

			// 把每個session取出迭代 確定還有連線中,
			for (Session session : connectedSessions) {
				if (session.isOpen() && userSession.getId() != session.getId())
					// 使用非同步的方法送出user的訊息到前端
					session.getAsyncRemote().sendText(message);
			}
//			System.out.println("Message received: " + message);
		}
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
//		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}