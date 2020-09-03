package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import dao.MemberDaoTest;

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
				 String phoneNum = request.getParameter("phoneNum");
				 String bDay = request.getParameter("bDay");
				 String nickname = request.getParameter("nickname");
				 String role = request.getParameter("role");
				 String fCode = request.getParameter("fCode");
				 System.out.println(id+pw+pwConfirm+name+phoneNum+bDay);
				 String str = MemberDaoTest.getInstance().insert(id,pw);
				 out.print(str);
				 System.out.println("성공"+str);
				 
			}else if(action.equals("join_IdCheck.do")) {
				String id = request.getParameter("id");
				boolean flag = MemberDao.getInstance().overappedId(id);
				if(flag) {
					out.print("사용 불가능");
				}else {
					out.print("사용 가능");
				}
				
			}else if(action.equals("join_FcodeCheck.do")) {
				String fCode = request.getParameter("fCode");
				boolean flag = MemberDao.getInstance().overappedFcode(fCode);
				if(flag) {
					out.print("사용 불가능");	
				}else {
					out.print("사용 가능");
				}
				
			}else if(action.equals("login.do")) {
				String str = MemberDaoTest.getInstance().select();
				out.print(str);
				System.out.println(str);
				
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
