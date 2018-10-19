package kr.co.happy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
	public static BoardDAO instance = null;
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	private BoardDAO() {}
	private final int LIST_CNT = 20;
	
	//게시판 리스트
	public ArrayList<BoardDTO> getBoardList(int btype, int page) {
		ArrayList<BoardDTO> data = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			int start = (page - 1) * LIST_CNT + 1;
			int end = page * LIST_CNT;
			String sql = " select * from (select "
					+ " h.*, row_number() over(order by seq desc) as rnum"
					+ " from h_board h where h.btype = ?"
					+ " ) where rnum between ? and ? ";			
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, btype);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));				
				dto.setSeq(rs.getInt("seq"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBregdate(rs.getString("bregdate"));
				dto.setPw(rs.getString("pw"));
				data.add(dto);
				
				System.out.println(dto);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, rs);
		}
		return data;
	}
	
	//게시판 디테일
	public BoardDTO getBoardItem(int bid) {
		BoardDTO data = new BoardDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {			
			String sql = " SELECT * FROM h_board " + 
					" WHERE bid = ? ";			
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);			
			ps.setInt(1, bid);
			rs = ps.executeQuery();
			if(rs.next()) {				
				data.setBid(rs.getInt("bid"));
				data.setBtype(rs.getInt("btype"));
				data.setSeq(rs.getInt("seq"));				
				data.setBtitle(rs.getString("btitle"));
				data.setBcontent(rs.getString("bcontent"));
				data.setBregdate(rs.getString("bregdate"));
				data.setPw(rs.getString("pw"));
				System.out.println(data);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, rs);
		}
		return data;
	}
	
	//비밀번호 체크
	public boolean checkPw(BoardDTO vo) {
		BoardDTO result = getBoardItem(vo.getBid());
		
		if (result.getPw().equals(vo.getPw())) {
			return true;
		}
		
		return false;
	}
	
	//게시판 등록
	public void insertBoard(BoardDTO vo) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {			
			String sql = " INSERT INTO h_board " + 
					" (BID, BTYPE, SEQ, BTITLE, BCONTENT, PW) "
					+ " VALUES "
					+ "( (SELECT nvl(max(bid), 0) + 1 from h_board) "
					+ " , ? "  //1
					+ " , (SELECT nvl(max(seq), 0) + 1 from h_board where btype = ?) " //2
					+ " , ?, ?, ? )"; //3, 4, 5
			
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);	
			ps.setInt(1, vo.getBtype());
			ps.setInt(2, vo.getBtype());
			ps.setString(3,  vo.getBtitle());
			ps.setString(4, vo.getBcontent());
			ps.setString(5, vo.getPw());
			
			ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, null);
		}
	}
	
	//게시판 수정
	public void updateBoard(BoardDTO vo) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {			
			String sql = " UPDATE h_board SET "
					+ " BTITLE = ?"
					+ " , BCONTENT = ?"
					+ " WHERE BID = ? ";
			
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);	
			ps.setString(1, vo.getBtitle());
			ps.setString(2, vo.getBcontent());
			ps.setInt(3,  vo.getBid());				
			ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, null);
		}
	}
	
	//게시판 삭제
	public void deleteBoard(int bid) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {			
			String sql = " DELETE FROM h_board "						
					+ " WHERE BID = ? ";
			
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);				
			ps.setInt(1,  bid);				
			ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, null);
		}
	}
}
