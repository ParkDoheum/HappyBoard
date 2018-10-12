package kr.co.happy;

public class BoardDTO {
	private int bid;
	private int btype;
	private int seq;
	private String btitle;
	private String bcontent;
	private String bregdate;
	private String pw;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getBtype() {
		return btype;
	}
	public void setBtype(int btype) {
		this.btype = btype;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getBregdate() {
		return bregdate;
	}
	public void setBregdate(String bregdate) {
		this.bregdate = bregdate;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	@Override
	public String toString() {
		String res = String.format("bid: %d / btype:%d / seq:%d\n %s / %s / %s\n", bid, btype, seq, btitle, bcontent, bregdate);
		return res;
	}
}
