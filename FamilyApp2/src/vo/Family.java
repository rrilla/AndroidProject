package vo;

public class Family {
	private String f_code;
	private String f_name;
	private String motoo;
	private String f_img;
	private String join_date;
	
	public Family(String f_code) {
		super();
		this.f_code = f_code;
	}
	
	public String getF_code() {
		return f_code;
	}
	public void setF_code(String f_code) {
		this.f_code = f_code;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getMotoo() {
		return motoo;
	}
	public void setMotoo(String motoo) {
		this.motoo = motoo;
	}
	public String getF_img() {
		return f_img;
	}
	public void setF_img(String f_img) {
		this.f_img = f_img;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}

	@Override
	public String toString() {
		return "Family [f_code=" + f_code + ", f_name=" + f_name + ", motoo=" + motoo + ", f_img=" + f_img
				+ ", join_date=" + join_date + "]";
	}
	
}
