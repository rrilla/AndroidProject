package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDaoTest {
	private static MemberDaoTest instance = new MemberDaoTest();
		
	private MemberDaoTest() {}
		
	public static MemberDaoTest getInstance() {
		return instance;
	}
	
	public String insert(String id, String pw) {
		String str = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from test_member where id=?";
		String sql2 = "insert into test_member(id,pw) values(?,?)";

		try {
			conn = DBconn.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				str = "이미 존재하는 아이디";
			} else
				ps = conn.prepareStatement(sql2);
			ps.setString(1, id);
			ps.setString(2, pw);
			int n = ps.executeUpdate();
			if (n == 1) {
				str = "회원가입 성공.";
			} else {
				str = "회원가입 실패";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBconn.close(conn, ps, rs);
		}
		return str;	
	}
	
	public String select() {
		String str = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from test_member";

		try {
			conn = DBconn.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				str +=rs.getString("id")+"\t"+rs.getString("pw")+"\t";
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBconn.close(conn, ps, rs);
		}
		return str;	
	}
}
