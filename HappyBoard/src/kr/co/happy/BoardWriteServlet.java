package kr.co.happy;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardWrite")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------ [boardWrite] GET -----");
		
		String bid = request.getParameter("bid");		
		System.out.println("bid : " + bid);		
		int intBid = Integer.parseInt(bid);		
		
		BoardDTO dto = new BoardDTO();
		
		if(intBid > 0) { //수정
			//DB에서 자료 가져오기
			BoardDAO dao = BoardDAO.getInstance();
			dto = dao.getBoardItem(intBid);
		} else { //등록
			String btype = request.getParameter("btype");
			System.out.println("btype : " + btype);
			int intBtype = Integer.parseInt(btype);
			dto.setBtype(intBtype);	
		}
		
		request.setAttribute("data", dto);
		request.setAttribute("target", "boardWrite");
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------ [boardWrite] POST -----");
		request.setCharacterEncoding("UTF-8");
		String bid = request.getParameter("bid");
		String btype = request.getParameter("btype");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String pw = request.getParameter("pw");
		
		System.out.println("bid : " + bid);
		System.out.println("btype : " + btype);
		int intBid = Integer.parseInt(bid);
		int intBtype = Integer.parseInt(btype);
		
		BoardDTO vo = new BoardDTO();
		vo.setBid(intBid);				 
		vo.setBtype(intBtype);
		vo.setPw(pw);
		vo.setBtitle(btitle);
		vo.setBcontent(bcontent);
		
		BoardDAO dao = BoardDAO.getInstance();
		String redirectTarget = "boardList";
		if(intBid > 0) { //수정			
			//비밀번호 체크
			boolean chk = dao.checkPw(vo);
			
			if(chk) { //비밀번호 확인 -> 수정 시작
				dao.updateBoard(vo);
				response.sendRedirect(redirectTarget + "?btype=" + btype);
			} else {
				request.setAttribute("msg", "비밀번호를 확인하세요.");
				request.setAttribute("data", vo);				
				request.setAttribute("target", "boardWrite");
				RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
				rd.forward(request, response);
			}			
		} else { //등록			
			dao.insertBoard(vo);
			response.sendRedirect(redirectTarget + "?btype=" + btype);
		}
	}
}
