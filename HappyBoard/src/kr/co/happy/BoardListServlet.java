package kr.co.happy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[/baordList] [Get]");
		String btype = request.getParameter("btype");
		String page = request.getParameter("page");
		
		int intBtype = 0;
		if(btype != null && !btype.equals("")) {
			intBtype = Integer.parseInt(btype);	
		}
		
		int intPage = 1;
		if(page != null && !page.equals("")) {
			intPage = Integer.parseInt(page);	
		}
		
		//DB에서 자료 가져오기
		BoardDAO dao = BoardDAO.getInstance();
		ArrayList<BoardDTO> list = dao.getBoardList(intBtype, intPage);
			
		request.setAttribute("data", list);
		request.setAttribute("target", "boardList");
		request.setAttribute("btype", intBtype);
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	}
}
