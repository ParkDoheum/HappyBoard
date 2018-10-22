package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardReg")
public class BoardRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[boardReg][GET]");
		request.setAttribute("target", "boardReg");
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[boardReg][POST]");
		request.setCharacterEncoding("UTF-8");

		String btype = request.getParameter("btype");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String pw = request.getParameter("pw");
		System.out.println("btype : " + btype);
		System.out.println("btitle : " + btitle);
		System.out.println("bcontent : " + bcontent);
		System.out.println("pw : " + pw);
		
		int intBtype = Integer.parseInt(btype);
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO vo = new BoardDTO();
		vo.setBtype(intBtype);
		vo.setBtitle(btitle);
		vo.setBcontent(bcontent);
		vo.setPw(pw);
		dao.insertBoard(vo);
		
		response.sendRedirect("boardList?btype=" + btype);
		
	}

}
