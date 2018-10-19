package kr.co.happy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/boardDetail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------ [boardDetail] GET -----");
		String bid = request.getParameter("bid");
		System.out.println("bid : " + bid);
		
		int intBid = Integer.parseInt(bid);
		
		//DB에서 자료 가져오기
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = dao.getBoardItem(intBid);
			
		request.setAttribute("data", dto);
		request.setAttribute("target", "boardDetail");
		
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	}
}
