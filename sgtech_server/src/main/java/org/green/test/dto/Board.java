package org.green.test.dto;

public class Board {
	private int b_no;
	private String b_name;
	private String b_master;
	private String b_cate;
	private Integer emp_no;
	
	public Board() {}

	public Board(int b_no, String b_name, String b_master, String b_cate, Integer emp_no) {
		this.b_no = b_no;
		this.b_name = b_name;
		this.b_master = b_master;
		this.b_cate = b_cate;
		this.emp_no = emp_no;
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

	public String getB_master() {
		return b_master;
	}

	public void setB_master(String b_master) {
		this.b_master = b_master;
	}

	public String getB_cate() {
		return b_cate;
	}

	public void setB_cate(String b_cate) {
		this.b_cate = b_cate;
	}

	public Integer getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}

	
	
}

	