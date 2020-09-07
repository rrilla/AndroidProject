package servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AnswerDao;
import dao.FmodifyDao;
import dao.JoinDao;
import dao.MainLoginDao;
import dao.MemberDao;
import dao.QuestionDao;
import dao.UpdateEvery8;
import vo.Member;
import vo.Post;

@WebServlet("*.do")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Servlet() {
        super();
    }
	
	public void init(ServletConfig config) throws ServletException {
		//every8run();	//매일 8시 실행되는 스케줄러 실행
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
				 String name = request.getParameter("name");
				 String phone = request.getParameter("phoneNum");
				 String bDay = request.getParameter("bDay");
				 String nickname = request.getParameter("nickname");
				 String role = request.getParameter("role");
				 String f_Code = request.getParameter("fCode");
				 String fAlready = request.getParameter("fAlready");
				 boolean flag = true;
				 //기존가족등록 미체크시 가족코드 생성
				 if(fAlready.equals("0")) {
					 flag = JoinDao.getInstance().addFcode(f_Code, name);
				 }
				 System.out.println("fcode되고되나");
				 if(flag) {
					 //회원가입
					 Member member = new Member(id, pw, f_Code, name, nickname, phone, bDay, role);
					 flag = JoinDao.getInstance().insert(member);
					 if(flag) {
						 out.print("1");
					 }else {
						 out.print("회원가입 실패.");
					 }
				}else {
						out.print("가족 등록 실패.");
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
				String cName = null;
				cName = JoinDao.getInstance().CheckFcode(fCode);
				if(cName!=null) {
					out.print(cName);	
				}else {
					out.print("0");
				}
				
				
			}else if(action.equals("login.do")) {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				int n = MemberDao.getInstance().login(id, pw);
				out.print(n);
				//1성공, 0비번에러, -1 아이디 에러
				
			}else if(action.equals("question_load.do")) {
				String userId = request.getParameter("userId");
				String questionJson = null;
				questionJson = QuestionDao.getInstance().questionJson(userId);
				if(questionJson==null) {
					out.print("서버 에러");
				}else {
					out.print(questionJson);
				}
				
			}else if(action.equals("answer_post.do")) {
				String id = request.getParameter("id");
				String fCode = request.getParameter("fCode");
				int familyQno = Integer.parseInt(request.getParameter("familyQno"));
				String question = request.getParameter("question");
				String answer = request.getParameter("answer");
				Post post = new Post(id,fCode,familyQno,question,answer);
				boolean flag = AnswerDao.getInstance().insertPost(post);
				if(flag) {
					out.print("1");
				}else {
					out.print("2");
				}
				
			}else if(action.equals("fModify.do")) {
				String id = request.getParameter("id");
				String fName = request.getParameter("fName");
				String motoo = request.getParameter("motoo");
				String location = request.getParameter("location");
				boolean flag = FmodifyDao.getInstance().modify(id, fName, location, motoo);
				if(flag) {
					out.print("1");
				}else {
					out.print("0");
				}
				
			}else if(action.equals("mainLogin_load.do")) {
				String id = request.getParameter("id");
				String mainJoinJson = null;
				mainJoinJson = MainLoginDao.getInstance().mainJoinJson(id);
				if(mainJoinJson==null) {
					out.print("서버 에러");
				}else {
					out.print(mainJoinJson);
				}
				System.out.println(mainJoinJson + "오긴옴");
			}else if(action.equals("audio.do")) {
				
			}else if(action.equals("storage.do")) {
				
				
				
				
			}else if(action.equals("imgTest.do")) {
				String imgStr = request.getParameter("imgStr");
				String img = new String(Base64.getDecoder().decode(imgStr.getBytes()));
				byte[] imgByte = imgStr.getBytes();
				System.out.println(img);
				out.print("왓따");
				
				//writeToFile("zzzz", imgByte);
//				ByteArrayInputStream input_stream = new ByteArrayInputStream(imgByte);
//
//				BufferedImage final_buffered_image = ImageIO.read(input_stream);
//
//				ImageIO.write(final_buffered_image , "png", new File("./temp.png") );

				
			}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	//매일 8시 실행할 작업
	private void every8run() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				String msg = UpdateEvery8.getInstance().updateFamilyQno();
				System.out.println(msg);
			}
		};
		
		Timer timer = new Timer();
		long firstTime = calcTaskTime(8);	//시작시간입력(ex 17)
		long Period = (60*60*24)*100;	//실행주기
		timer.scheduleAtFixedRate(task, firstTime, Period);
	}
	
	//시작시간까지 대기해야할 시간
	private long calcTaskTime(int startTime) {
	    if(startTime > 23 || startTime < 0){
	        return 0;
	    }
	    Calendar calendar = new GregorianCalendar(Locale.KOREA);
	    calendar.set(Calendar.HOUR_OF_DAY, startTime);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);

	    long nowDate = new Date().getTime();

	    if (nowDate > calendar.getTime().getTime()) {
	        calendar.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    long waiting = (calendar.getTime().getTime() - nowDate)/1000;
	    System.out.println(("family`s q_no Schedule Start Time : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime())));
	    System.out.println(("Waiting : " + waiting+" sec"));

	    return (int)waiting;
	}
	
	
	
	
	
}
