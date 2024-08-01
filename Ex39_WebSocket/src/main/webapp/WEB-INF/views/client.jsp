<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex39 WebSocket</title>
	</head>
	<body>
	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if (id == null) {
	%>
		<jsp:forward page="login.jsp"/>
	<%
		} else {
			session.setAttribute("uid", id);
		}
	%>
		<div>
			사용자 아이디 : <%= id %>
		</div>
		<div>
			<input type="text" id="messageinput" />
		</div>
		<div>
			<button type="button" onclick="openSocket();">Open</button>
			<button type="button" onclick="send();">Send</button>
			<button type="button" onclick="closeSocket();">Close</button>
		</div>
		<!-- Server responses get written here -->
		<div id="messages"></div>
		<!-- Script to utilse the WebSocket -->
		<script type="text/javascript">
			var webSocket;
			var messages = document.getElementById("messages");
			
			function openSocket() 
			{
				if (webSocket !== undefined && webSocket.readyState !== Websocket.CLOSED) {
					writeResponse("WebSocket is aleady opened.")
					return;
				}
				
				webSocket = new WebSocket("ws://localhost:8081/websocketendpoint2");
				
				webSocket.onopen = function(event) {
					if (event.data === undefined)
						return;
					
					writeResponse(event.data);
				};
				
				webSocket.onmessage = function(event) {
					writeResponse(event.data);
				};
				
				webSocket.onclose = function(event){
					writeResponse("Connection closed");
				};
			}
			
			function send(){
				var id = "<%= id %>";
				var text = document.getElementById("messageinput").value;
				webSocket.send(id + "|" + text);
			}
			
			function closeSocket(){
				webSocket.close();
			}
			
			function writeResponse(text){
				messages.innerHTML += "<br/>" + text;
			}
		</script>
	
	</body>
</html>