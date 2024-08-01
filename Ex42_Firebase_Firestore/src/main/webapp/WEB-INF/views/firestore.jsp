<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Firebase Firestore</title>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	
	<script type="module">
import { initializeApp } from 'https://www.gstatic.com/firebasejs/10.12.4/firebase-app.js'
// 이제 Firebase를 사용하도록 설정했으므로 웹 앱에서 다음과 같은 사용 가능한 
// Firebase 서비스를 추가(컬렉션과 문서를 만듭니다. )
import { getFirestore, collection, getDocs, setDoc, doc } 
	from 'https://www.gstatic.com/firebasejs/10.12.4/firebase-firestore.js'


var firebaseConfig = {
  	apiKey: "AIzaSyCMoPkVAje8SOjguS1UexR0iYV9Y0wue9o",
    authDomain: "springboot-1ecbe.firebaseapp.com",
    databaseURL: "https://springboot-1ecbe-default-rtdb.firebaseio.com",
    projectId: "springboot-1ecbe",
    storageBucket: "springboot-1ecbe.appspot.com",
    messagingSenderId: "935994052211",
    appId: "1:935994052211:web:f6271f5523bbb8da272406",
};
// 앱에서 Firebase를 초기화하고 Firebase 앱 객체를 만듭니다.
var app = initializeApp(firebaseConfig);
var db = getFirestore(app);

async function writeDoc() {
	var email = $('#email').val();
	var pwd = $('#pwd').val();

	var postData = {
		email: email,
		pw: pwd
	};

	var timeElapsed = Date.now();
	var newRef = doc(db, 'members', 'member' + timeElapsed);

	await setDoc(newRef, postData);
}

async function selectDoc() {
	$('#chatMessageArea').empty();
	// get 메서드를 사용해 전체 컬렉션을 가져올 수도 있습니다.
	const snapshot = await getDocs(collection(db, "members"));
	snapshot.forEach((doc) => {
		var doc_id = doc_id;
		var data = doc.data();
		console.log(doc_id + " - " + data.email + " : " + data.pw);
		appendMessage(data.email + " : " + data.pw);
	});
}

//  chatMessageArea  화면에 나올 내용
function appendMessage(msg) {
	$('#chatMessageArea').append(msg + "<br>");
	var chatAreaHeight = $('#chatArea').height();
	var maxScroll = $('#chatMessageArea').height() - chatAreaHeight;
	$('#chatArea').scrollTop(maxScroll);
}

$(document).ready(function() {
	console.log(111);
	$('#select').click(function() { selectDoc(); });
	$('#write').click(function() { writeDoc(); });
});
	</script>
	<style>
	#chatArea{
		width: 200px; height: 100px; overflow-y: auto; boder: 1px solid black;
	}
	</style>
	</head>
	<body>
		<input type="button" id="select" value="조회">
		
		<div id="chatArea"><div id="chatMessageArea"></div></div>
		<br>
		
		<input type="text" id="email">email<br>
		<input type="text" id="pwd">pwd<br>	
		<input type="button" id="write" value="작성">
	</body>
</html>