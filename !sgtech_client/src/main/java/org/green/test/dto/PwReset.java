package org.green.test.dto;

import java.time.LocalDateTime;

public class PwReset {
	private String emp_no;
	private String emp_name;
	private String emp_email;
	private int try_count;
	private LocalDateTime last_try;

	public PwReset() {
	}

	public PwReset(String emp_no, String emp_name, String emp_email, int try_count, LocalDateTime last_try) {
		this.emp_no = emp_no;
		this.emp_name = emp_name;
		this.emp_email = emp_email;
		this.try_count = try_count;
		this.last_try = last_try;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public int getTry_count() {
		return try_count;
	}

	public void setTry_count(int try_count) {
		this.try_count = try_count;
	}

	public LocalDateTime getLast_try() {
		return last_try;
	}

	public void setLast_try(LocalDateTime last_try) {
		this.last_try = last_try;
	}

}
