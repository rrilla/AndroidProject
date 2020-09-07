package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateEvery8 {
private static UpdateEvery8 instance = new UpdateEvery8();
	
	private UpdateEvery8() {}
		
	public static UpdateEvery8 getInstance() {
		return instance;
	}
	
	public String updateFamilyQno() {
		String result = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update family set q_no = q_no+1";
		try {
			conn = DBconn.getConnection();
			ps = conn.prepareStatement(sql);
			int n = ps.executeUpdate();
			if (n != 0) {
				result = n + "개의 가족들의 q_no가 수정됨.";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBconn.close(conn, ps);
		}
		return result;
	}
}
