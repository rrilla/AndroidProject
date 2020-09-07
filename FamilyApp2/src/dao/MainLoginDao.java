package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MainLoginDao {
private static MainLoginDao instance = new MainLoginDao();
	
	private MainLoginDao() {}
		
	public static MainLoginDao getInstance() {
		return instance;
	}
	
	public String mainJoinJson(String userId) {
		JSONObject jsonMain = new JSONObject();
	 	JSONArray jArray = new JSONArray();
	 	JSONObject jObject = new JSONObject();
	 	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select f_code, f_name, location, motoo from family where f_code = (select f_code from member where id=?)";
		try {
			conn = DBconn.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			if(rs.next()){
				jObject.put("fCode", rs.getString(1));	//안드로이드 question 페이지로 보낼 메시지를 만듬(json)
				jObject.put("fName", rs.getString(2));
				jObject.put("location", rs.getString(3));
	   			jObject.put("motoo", rs.getString(4));
	   		    
	   			jArray.add(0, jObject);		// 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬
	   			jsonMain.put("mainJoinJson", jArray);	// 최종적으로 배열을 하나로 묶음
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBconn.close(conn, ps, rs);
		}
		return jsonMain.toJSONString();
	}
}
