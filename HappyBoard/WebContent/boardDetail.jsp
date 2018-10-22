<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

제목: ${data.btitle} <br>
내용:<br>
${data.bcontent}<br>
<br>
<a href="boardMod?bid=${data.bid}">수정</a>
<a href="boardDelete?bid=${data.bid}&btype=${data.btype}">삭제</a>
