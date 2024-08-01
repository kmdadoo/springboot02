<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex40 Naver Editor</title>
	</head>
	<body>
		<h1>NaverEditor in JSP</h1>
		<!-- HuskyEZCreator.js 파일의 경로가 맞는지 주의 -->
		<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8">
		</script>
		<form name="myeditor" id="myeditor" action="naver_submit" method=post>
			<!-- 에디터를 추가할 위치에 다음과 같이 textarea를 추가한다. 
			에디터에 기본으로 삽입할 글(수정모드)이 없다면 이 value 
			값을 지정하지 않으시면 됩니다.
			사용자가 에디터에서 작성한 내용은 여기에서 추가한 textarea value를 
			통해 서버로	전송된다.
			기존에 작성하여 저장한 글을 수정하는 경우에는 이 textarea value에 
			수정할 내용을 지정하여, 에디터가 로드되었을 때 에디터 편집 영역에 
			기존 글이 표시되도록 한다.-->
			<textarea name="ir1" id="ir1" rows="10" cols="100"></textarea>
			<button onclick="form_check();">작성완료</button>
		</form>
		<!-- textarea 아래 부분에 스크립트가 위치해야 -->
		<script type="text/javascript">
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "ir1",
		// SmartEditor2Skin.html 파일의 경로가 맞는지 주의
		sSkinURI: "./naver-editor/SmartEditor2Skin.html",
		fCreator: "createSEditor2"
		});
		
		// 수정완료 링크를 눌렀을 때 처리
		function form_check() 
		{
			console.log(document.getElementById("ir1").value);
			// 네이버에디터의 내용을 textarea에 적용
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			console.log(document.getElementById("ir1").value);
			
			document.myeditor.submit();
		}
		</script>
		<!-- 우리는 ir1 대신에 Contents 를 사용하면 된다. -->
	</body>
</html>