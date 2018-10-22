package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardMod")
public class BoardModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[boardMod][GET]");
		
		String bid = request.getParameter("bid");
		System.out.println("bid : " + bid);
		
		int intBid = Integer.parseInt(bid);
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO data = dao.getBoardItem(intBid);
		
		request.setAttribute("data", data);
		request.setAttribute("target", "boardMod");
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[boardMod][POST]");
		request.setCharacterEncoding("UTF-8");

		String bid = request.getParameter("bid");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String pw = request.getParameter("pw");
		System.out.println("bid : " + bid);
		System.out.println("btitle : " + btitle);
		System.out.println("bcontent : " + bcontent);
		System.out.println("pw : " + pw);
		int intBid = Integer.parseInt(bid);		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO data = dao.getBoardItem(intBid);
		
		if(data.getPw().equals(pw)) { //비번이 같음 > 수정 수행
			System.out.println("비번 맞음!!");
			
			
			
		} else { //비번이 틀림 > 수정페이지로 다시 보냄
			BoardDTO writedVo = new BoardDTO();
			writedVo.setBid(intBid);
			writedVo.setBtitle(btitle);
			writedVo.setBcontent(bcontent);
			
			request.setAttribute("msg", "비번 틀림");
			request.setAttribute("data", writedVo);
			request.setAttribute("target", "boardMod");
			RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
			rd.forward(request, response);
		}
		
	}

}
