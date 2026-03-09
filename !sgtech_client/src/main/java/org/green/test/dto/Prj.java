package org.green.test.dto;

public class Prj {
	private int prj_no;
	private int prj_priority;
	private String prj_name;
	private String description;
	private String end_date;
	private int prj_board;
	public int getPrj_no() {
		return prj_no;
	}
	public void setPrj_no(int prj_no) {
		this.prj_no = prj_no;
	}
	public int getPrj_priority() {
		return prj_priority;
	}
	public void setPrj_priority(int prj_priority) {
		this.prj_priority = prj_priority;
	}
	public String getPrj_name() {
		return prj_name;
	}
	public void setPrj_name(String prj_name) {
		this.prj_name = prj_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getPrj_board() {
		return prj_board;
	}
	public void setPrj_board(int prj_board) {
		this.prj_board = prj_board;
	}
	@Override
	public String toString() {
		return "Prj [prj_no=" + prj_no + ", prj_priority=" + prj_priority + ", prj_name=" + prj_name + ", description="
				+ description + ", end_date=" + end_date + ", prj_board=" + prj_board + "]";
	}
	public Prj(int prj_no, int prj_priority, String prj_name, String description, String end_date, int prj_board) {
		super();
		this.prj_no = prj_no;
		this.prj_priority = prj_priority;
		this.prj_name = prj_name;
		this.description = description;
		this.end_date = end_date;
		this.prj_board = prj_board;
	}
	public Prj() {
		super();
	}
	
	
}
