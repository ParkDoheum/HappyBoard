<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="boardReg" method="post">
	제목: <input type="text" name="btitle"><br>
	내용:
	<textarea name="bcontent" rows="10" cols="10"></textarea><br>
	비밀번호: <input type="password" name="pw"><br>
		
	<input type="hidden1" name="btype" value="${param.btype}">		
			
	<input type="submit" value="확인">
</form>