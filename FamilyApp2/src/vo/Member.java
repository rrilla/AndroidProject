package vo;

public class Member {
	private String id;
	private String pw;
	private String f_code;
	private String name;
	private String nickname;
	private String phone;
	private String birthday;
	private String position;
	private String join_date;
	
	public String getId() {
		return id;
	}
	
	public Member(String id, String pw, String f_code, String name, String nickname, String phone, String birthday,
			String position) {
		super();
		this.id = id;
		this.pw = pw;
		this.f_code = f_code;
		this.name = name;
		this.nickname = nickname;
		this.phone = phone;
		this.birthday = birthday;
		this.position = position;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getF_code() {
		return f_code;
	}
	public void setF_code(String f_code) {
		this.f_code = f_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", f_code=" + f_code + ", name=" + name + ", nickname=" + nickname
				+ ", phone=" + phone + ", birthday=" + birthday + ", position=" + position + ", join_date=" + join_date
				+ "]";
	}
	
}
