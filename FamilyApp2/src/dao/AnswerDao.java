package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.Member;
import vo.Post;

public class AnswerDao {
private static AnswerDao instance = new AnswerDao();
	
	private AnswerDao() {}
		
	public static AnswerDao getInstance() {
		return instance;
	}
	
	public boolean insertPost(Post post) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into post(m_id, f_code, q_no, question, data) values(?,?,?,?,?)";
		try {
			conn = DBconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, post.getM_id());
			ps.setString(2, post.getF_code());
			ps.setInt(3, post.getQ_no());
			ps.setString(4, post.getQuestion());
			ps.setString(5, post.getData());
			int n = ps.executeUpdate();
			if (n == 1) {
				flag = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBconn.close(conn, ps);
		}
		return flag;
	}
}
