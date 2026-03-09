package org.green.test.dto;

public class User {

	private int user_no;
	private String user_state;
	private String user_role;
	private String user_email;
	private String user_note;
	private String user_access;
	private String user_pw;
	private int emp_no;
	private String emp_pw;
	private String emp_name;
	private String emp_email;
	private String emp_tel;
	private byte[] emp_photo;
	private String base64Photo;
	private String emp_photo_name;
	private String dept_name;
	private String rnk_name;
	private int admin_b_no;
	
	
	public User() {}


	public User(int user_no, String user_state, String user_role, String user_email, String user_note,
			String user_access, String user_pw, int emp_no, String emp_pw, String emp_name, String emp_email,
			String emp_tel, byte[] emp_photo, String base64Photo, String emp_photo_name, String dept_name,
			String rnk_name) {
		this.user_no = user_no;
		this.user_state = user_state;
		this.user_role = user_role;
		this.user_email = user_email;
		this.user_note = user_note;
		this.user_access = user_access;
		this.user_pw = user_pw;
		this.emp_no = emp_no;
		this.emp_pw = emp_pw;
		this.emp_name = emp_name;
		this.emp_email = emp_email;
		this.emp_tel = emp_tel;
		this.emp_photo = emp_photo;
		this.base64Photo = base64Photo;
		this.emp_photo_name = emp_photo_name;
		this.dept_name = dept_name;
		this.rnk_name = rnk_name;
	}
	
	


	public User(int user_no, String user_state, String user_role, String user_email, String user_note,
			String user_access, String user_pw, int emp_no, String emp_pw, String emp_name, String emp_email,
			String emp_tel, byte[] emp_photo, String base64Photo, String emp_photo_name, String dept_name,
			String rnk_name, int admin_b_no) {
		super();
		this.user_no = user_no;
		this.user_state = user_state;
		this.user_role = user_role;
		this.user_email = user_email;
		this.user_note = user_note;
		this.user_access = user_access;
		this.user_pw = user_pw;
		this.emp_no = emp_no;
		this.emp_pw = emp_pw;
		this.emp_name = emp_name;
		this.emp_email = emp_email;
		this.emp_tel = emp_tel;
		this.emp_photo = emp_photo;
		this.base64Photo = base64Photo;
		this.emp_photo_name = emp_photo_name;
		this.dept_name = dept_name;
		this.rnk_name = rnk_name;
		this.admin_b_no = admin_b_no;
	}


	public int getUser_no() {
		return user_no;
	}


	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}


	public String getUser_state() {
		return user_state;
	}


	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}


	public String getUser_role() {
		return user_role;
	}


	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getUser_note() {
		return user_note;
	}


	public void setUser_note(String user_note) {
		this.user_note = user_note;
	}


	public String getUser_access() {
		return user_access;
	}


	public void setUser_access(String user_access) {
		this.user_access = user_access;
	}


	public String getUser_pw() {
		return user_pw;
	}


	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}


	public int getEmp_no() {
		return emp_no;
	}


	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}


	public String getEmp_pw() {
		return emp_pw;
	}


	public void setEmp_pw(String emp_pw) {
		this.emp_pw = emp_pw;
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


	public String getEmp_tel() {
		return emp_tel;
	}


	public void setEmp_tel(String emp_tel) {
		this.emp_tel = emp_tel;
	}


	public byte[] getEmp_photo() {
		return emp_photo;
	}


	public void setEmp_photo(byte[] emp_photo) {
		this.emp_photo = emp_photo;
	}


	public String getBase64Photo() {
		return base64Photo;
	}


	public void setBase64Photo(String base64Photo) {
		this.base64Photo = base64Photo;
	}


	public String getEmp_photo_name() {
		return emp_photo_name;
	}


	public void setEmp_photo_name(String emp_photo_name) {
		this.emp_photo_name = emp_photo_name;
	}


	public String getDept_name() {
		return dept_name;
	}


	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}


	public String getRnk_name() {
		return rnk_name;
	}


	public void setRnk_name(String rnk_name) {
		this.rnk_name = rnk_name;
	}


	public int getAdmin_b_no() {
		return admin_b_no;
	}


	public void setAdmin_b_no(int admin_b_no) {
		this.admin_b_no = admin_b_no;
	}
	
	
	
	
	
		
}