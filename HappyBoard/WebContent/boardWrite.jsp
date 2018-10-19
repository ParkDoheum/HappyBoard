<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="boardWrite" method="post">
	제목: <input type="text" name="btitle" value="${data.btitle}"><br>
	내용:
	<textarea name="bcontent" rows="10" cols="10">${data.bcontent}</textarea><br>
	비밀번호: <input type="password" name="pw"><br>
	
	<input type="hidden" name="bid" value="${data.bid}">
	<input type="hidden" name="btype" value="${data.btype}">		
			
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
