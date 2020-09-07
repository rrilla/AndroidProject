package vo;

import java.util.Date;

public class Post {
	private String m_id;
	private String f_code;
	private int q_no;
	private String question;
	private Date answer_date;
	private String data;
	
	public Post() {
		super();
	}
	
	public Post(String m_id, String f_code, int q_no, String question, String data) {
		super();
		this.m_id = m_id;
		this.f_code = f_code;
		this.q_no = q_no;
		this.question = question;
		this.data = data;
	}

	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getF_code() {
		return f_code;
	}
	public void setF_code(String f_code) {
		this.f_code = f_code;
	}
	public int getQ_no() {
		return q_no;
	}
	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Date getAnswer_date() {
		return answer_date;
	}
	public void setAnswer_date(Date answer_date) {
		this.answer_date = answer_date;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Post [m_id=" + m_id + ", f_code=" + f_code + ", q_no=" + q_no + ", question=" + question
				+ ", answer_date=" + answer_date + ", data=" + data + "]";
	}
	
}
