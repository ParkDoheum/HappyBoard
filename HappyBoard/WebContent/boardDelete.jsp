<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>
</head>
<body>
	<form action="boardDelete" method="post">
		비밀번호: <input type="password" name="pw" value=""><br>
		<input type="hidden" name="bid" value="${param.bid }">
		<input type="hidden" name="btype" value="${param.btype }">	
				
		<input type="submit" value="확인">
	</form>
<% 
	String msg = (String)request.getAttribute("msg");
	if (msg != null && !msg.equals("")) {
%>	
	<script>
		alert("${msg}");
	</script>
<% } %>
</body>
</html>