package org.green.test.dto;

public class BoardAccess {
	private int ba_no;
	private String role;
	private int emp_no;
	private int b_no;
	
	//조인 컬럼
	private int user_no;
	private String emp_name;
	private String dept_name;
	
	
	
	
	public BoardAccess(int ba_no, String role, int emp_no, int b_no, int user_no, String emp_name, String dept_name) {
		super();
		this.ba_no = ba_no;
		this.role = role;
		this.emp_no = emp_no;
		this.b_no = b_no;
		this.user_no = user_no;
		this.emp_name = emp_name;
		this.dept_name = dept_name;
	}

	public BoardAccess() {}
	
	public int getBa_no() {
		return ba_no;
	}
	public void setBa_no(int ba_no) {
		this.ba_no = ba_no;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	
	
	
	
}
