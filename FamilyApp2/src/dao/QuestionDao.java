package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class QuestionDao {
	private static QuestionDao instance = new QuestionDao();
	
	private QuestionDao() {}
		
	public static QuestionDao getInstance() {
		return instance;
	}
	
	public String questionJson(String userId) {
		JSONObject jsonMain = new JSONObject();
	 	JSONArray jArray = new JSONArray();
	 	JSONObject jObject = new JSONObject();
	 	
		int familyQno = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select m.f_code, m.position, f.q_no from member m, family f where m.f_code=f.f_code and id=?";
		String sql2 = "select p.content, c.content from question_p p, question_c c where p.no=c.no and p.no=?";
		try {
			conn = DBconn.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			rs.next();
			jObject.put("userFcode", rs.getString(1));	//안드로이드 question 페이지로 보낼 메시지를 만듬(json)
			jObject.put("userPosition", rs.getString(2));
			familyQno = rs.getInt(3);
   			jObject.put("familyQno", familyQno);
			
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, familyQno);
			rs = ps.executeQuery();
			rs.next();
			jObject.put("question_p", rs.getString(1));
   			jObject.put("question_c", rs.getString(2));
   		    
   			jArray.add(0, jObject);		// 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬
   			jsonMain.put("questionJson", jArray);	// 최종적으로 배열을 하나로 묶음
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBconn.close(conn, ps, rs);
		}
		return jsonMain.toJSONString();
	}
	
//	public String addJson(int familyQno) {
//		JSONObject jsonMain = new JSONObject();
//	 	JSONArray jArray = new JSONArray();
//	 	JSONObject jObject = new JSONObject();
//		String question_p = null;
//		String question_c = null;
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		String sql = "select p.content, c.content from question_p p, question_c c where p.no=c.no and p.no=?";
//		try {
//			conn = DBconn.getConnection();
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, familyQno);
//			rs = ps.executeQuery();
//			if (rs.next()) {
//				question_p = rs.getString(1);
//				question_c = rs.getString(2);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			DBconn.close(conn, ps, rs);
//		}
//		return userFcode;
//	}
	
	
}
