package org.green.test.dto;

public class Schedule {
	private int sch_no;
	private int user_no;
	private String sch_date;
	private String reg_date;
	private String sch_content;
	public int getSch_no() {
		return sch_no;
	}
	
	public int getUser_no() {
		return user_no;
	}
 
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public void setSch_no(int sch_no) {
		this.sch_no = sch_no;
	}
	public String getSch_date() {
		return sch_date;
	}
	public void setSch_date(String sch_date) {
		this.sch_date = sch_date;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getSch_content() {
		return sch_content;
	}
	public void setSch_content(String sch_content) {
		this.sch_content = sch_content;
	}
	public Schedule(int sch_no, String sch_date, String reg_date, String sch_content,int user_no) {
		super();
		this.sch_no = sch_no;
		this.sch_date = sch_date;
		this.reg_date = reg_date;
		this.sch_content = sch_content;
		this.user_no=user_no;
	}
	public Schedule() {
		super();
	}
	@Override
	public String toString() {
		return "MySchedule [sch_no=" + sch_no + ", sch_date=" + sch_date + ", reg_date=" + reg_date + ", sch_content="
				+ sch_content + "]";
	}
	
	
}
