package org.green.test.dto;

public class Report {
	private int rep_no;
	private String rep_reason;
	private int user_no;
	private int art_no;
	private int b_no;
	private String rep_is_read;
	
	private String b_name;
	private String emp_name;
	private String art_title;
	
	
	public String getRep_is_read() {
		return rep_is_read;
	}
	public void setRep_is_read(String rep_is_read) {
		this.rep_is_read = rep_is_read;
	}
	public int getRep_no() {
		return rep_no;
	}
	public void setRep_no(int rep_no) {
		this.rep_no = rep_no;
	}
	public String getRep_reason() {
		return rep_reason;
	}
	public void setRep_reason(String rep_reason) {
		this.rep_reason = rep_reason;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getArt_no() {
		return art_no;
	}
	public void setArt_no(int art_no) {
		this.art_no = art_no;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getArt_title() {
		return art_title;
	}
	public void setArt_title(String art_title) {
		this.art_title = art_title;
	}
	public Report(int rep_no, String rep_reason, int user_no, int art_no, int b_no, String b_name, String emp_name,
			String art_title) {
		super();
		this.rep_no = rep_no;
		this.rep_reason = rep_reason;
		this.user_no = user_no;
		this.art_no = art_no;
		this.b_no = b_no;
		this.b_name = b_name;
		this.emp_name = emp_name;
		this.art_title = art_title;
	}
	public Report(int rep_no, String rep_reason, int user_no, int art_no, int b_no) {
		super();
		this.rep_no = rep_no;
		this.rep_reason = rep_reason;
		this.user_no = user_no;
		this.art_no = art_no;
		this.b_no = b_no;
	}
	public Report() {
		super();
	}

}