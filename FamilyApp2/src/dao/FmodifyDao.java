package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Member;

public class FmodifyDao {
	private static FmodifyDao instance = new FmodifyDao();
	
	private FmodifyDao() {}
		
	public static FmodifyDao getInstance() {
		return instance;
	}
	
	public boolean modify(String id, String fName, String location, String motoo) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update family f set f.f_name = (?), "
				+ "f.location = (?), "
				+ "f.motoo = (?) "
				+ "where f.f_code = (select f_code from member where id=?)";
		try {
			conn = DBconn.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, fName);
			ps.setString(2, location);
			ps.setString(3, motoo);
			ps.setString(4, id);
			int n = ps.executeUpdate();
			if (n == 1) {
				flag = true;
			}
		} catch (Exception ex) {
			System.out.println("여기는");
			ex.printStackTrace();
		} finally {
			DBconn.close(conn, ps);
		}
		return flag;
	}
}
