package com.study.springboot;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.RemoteEndpoint.Basic;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

/*
	sessions가 각 호출 시 마다 생성되므로 static으로 정해 줘야 한다.
	브라우저가 websocket을 지원 해야 한다.
	웹 소켓 연결 후 별도 close동작 없이 브라우저를 닫을 경우 자동으로
	OnClose가 호출 된다.
*/
@Component
// 이 어노테이션을 명시함으로서 WEB 소켓으로 접속 가능한 URL 정보를 
// 명시하여 소켓 서버를 생성
@ServerEndpoint("/websocketendpoint2")
public class WebSocket2
{
	private static final java.util.Set<Session> sessions =
			java.util.Collections.synchronizedSet(new java.util.HashSet<Session>());
	
//	private static final java.util.Map<String, Session> clientMap =
//			java.util.Collections.synchronizedMap(new java.util.HashMap<String, Session>());
	
	/*
		@OnOpen 어노테이션은 새 세션의 생성을 차단할 수 있습니다.
		세션 클래스를 통해 사용자에게 데이터를 전송할 수 있습니다.
		onOpen의 메서드에서 사용자에게 응답이 성공했음을 알려줍니다
		
		클라이언트가 ServerEndpoint값인 “/websocketendpoint2“ url로 서버에 
		접속하게 되면 onOpen 메서드가 실행되며, 클라이언트 정보를 매개변수인 
		Session 객체를 통해 전달받습니다. 이때 정적 필드인 clients에 해당 
		session이 존재하지 않으면 clients에 접속된 클라이언트를 추가합니다.
	*/
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String userName)
	{
		System.out.println("Open session id : " + session.getId());
		
		try
		{
			final Basic basic = session.getBasicRemote();
			basic.sendText("Connection Established");
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		sessions.add(session);
	}
	
	/*
		클라이언트가 url을 바꾸거나 브라우저를 종료하면 자동으로 onClose() 
		메서드가 실행되며 해당 클라이언트 정보를 clients에서 제거합니다.
	*/
	@OnClose
	public void onClose(Session session)
	{
		System.out.println("Session " +session.getId()+ " has ended");
		sessions.remove(session);
	}
	
	/*
		사용자가 서버에 메시지를 보내면, 이 메서드가 메시지를 가로채서 그것에 
		반응할 수 있게 해줄 것이다.
		지금은 메시지가 문자열로 읽습니다.
		
		클라이언트로부터 메시지가 전달되면 WebSocket 클래스의 onMessage
		메서드에 의해  clients에 있는 모든 session에 메시지를 전달합니다.
	*/
	@OnMessage
	public void onMessage(String message, Session session)
	{
		System.out.println("Message from " + session.getId() + ": " + message);
		try
		{
			final Basic basic = session.getBasicRemote();
			basic.sendText("to :" + message);
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		sendAllSessionToMessage( session, message);
	}

	/*
		모든 사용자에게 메시지를 전달한다.
	*/
	private void sendAllSessionToMessage(Session self, String message)
	{
		try
		{
			for( Session session : WebSocket2.sessions ) {
				if( !self.getId().equals(session.getId()) )
					session.getBasicRemote().sendText("All :" + message);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@OnError
	public void onError( Throwable e, Session session)
	{
		
	}
}