package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------ [boardDelete] GET -----");
				
		RequestDispatcher rd = request.getRequestDispatcher("boardDelete.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------ [boardDelete] POST -----");
		String pw = request.getParameter("pw");
		String bid = request.getParameter("bid");
		String btype = request.getParameter("btype");
		
		if(btype == null || btype.equals("")) {
			btype = "0";
		}
		System.out.println("bid : " + bid);
		System.out.println("btype : " + btype);
				
		int intBid = Integer.parseInt(bid);
		
		BoardDTO vo = new BoardDTO();
		vo.setBid(intBid);
		vo.setPw(pw);
		
		//DB
		BoardDAO dao = BoardDAO.getInstance();
		boolean chk = dao.checkPw(vo);
		if(chk) {
			dao.deleteBoard(intBid);			
			response.sendRedirect("boardList?btype=" + btype);	
		} else {
			request.setAttribute("msg", "비밀번호를 확인해 주세요.");
			RequestDispatcher rd = request.getRequestDispatcher("boardDelete.jsp?bid=" + bid + "&btype=" + btype);
			rd.forward(request, response);	
		}
	}
}
