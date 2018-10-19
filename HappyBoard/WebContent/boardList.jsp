<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.co.happy.*" %>
<%
	int btype = (int)request.getAttribute("btype");
	ArrayList<BoardDTO> list = (ArrayList)request.getAttribute("data");
%>
<table>
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>날짜</th>
</tr>
<%	
	for(BoardDTO dto : list) {	
%>
	<tr>
		<td><%=dto.getSeq() %></td>
		<td><a href="boardDetail?bid=<%=dto.getBid()%>"><%=dto.getBtitle() %></a></td>
		<td><%=dto.getBregdate() %></td>
	</tr>
<%
	}
%>
</table>

<a href="boardWrite?btype=<%=btype%>&bid=0">글쓰기</a>