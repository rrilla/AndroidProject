package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JoinDao;
import dao.MemberDao;
import vo.Member;

@WebServlet("*.do")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Servlet() {
        super();
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		   response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();
			String action = requestURI.substring(contextPath.length()+1);
			
			if(action.equals("join.do")) {
				 String id = request.getParameter("id");
				 String pw = request.getParameter("pw");
				 String pwConfirm = request.getParameter("pwConfirm");
				 String name = request.getParameter("name");
				 String phone = request.getParameter("phoneNum");
				 String bDay = request.getParameter("bDay");
				 String nickname = request.getParameter("nickname");
				 String role = request.getParameter("role");
				 String f_Code = request.getParameter("fCode");
				 System.out.println(id+pw+pwConfirm+name+phone+bDay);
				 Member member = new Member(id, pw, f_Code, name, nickname, phone, bDay, role);
				 boolean flag = JoinDao.getInstance().insert(member);
				 if(flag) {
					 out.print("가입 성공");
				 }else {
					 out.print("가입 실패");
				 }
				 
			}else if(action.equals("join_CheckId.do")) {
				String id = request.getParameter("id");
				boolean flag = JoinDao.getInstance().overappedId(id);
				if(flag) {
					out.print("사용 불가능");
				}else {
					out.print("사용 가능");
				}
						
			}else if(action.equals("join_OverlapFcode.do")) {
				String fCode = request.getParameter("fCode");
				boolean flag = JoinDao.getInstance().overappedFcode(fCode);
				if(flag) {
					out.print("사용 불가능");	
				}else {
					out.print("사용 가능");
				}
				
			}else if(action.equals("join_CheckFcode.do")) {
				String fCode = request.getParameter("fCode");
				boolean flag = JoinDao.getInstance().CheckFcode(fCode);
				if(flag) {
					out.print("가족코드있음(가족이름?)");	
				}else {
					out.print("존재하지 않는 가족코드 입니다.");
				}
				
			}else if(action.equals("question.do")) {
				
			}else if(action.equals("login.do")) {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				int n = MemberDao.getInstance().login(id, pw);
				out.print(n);
				//1성공, 0비번에러, -1 아이디 에러
			}else if(action.equals("question.do")) {
				
			}else if(action.equals("answer.do")) {
				
			}else if(action.equals("post.do")) {
				
			}else if(action.equals("photo.do")) {
				
			}else if(action.equals("audio.do")) {
				
			}else if(action.equals("storage.do")) {
				
			}
			
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
