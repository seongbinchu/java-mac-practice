package org.green.test.dto;

public class Reply {
    private int re_no;
    private int art_no;
    private int user_no;
    private String re_date;
    private String re_content;
    private int re_isdeleted; 
    private String emp_name;
    private String rnk_name;
    private String art_tag;
    private String b_name;

    
    public Reply() {}

    



	public Reply(int re_no, int art_no, int user_no, String re_date, String re_content, int re_isdeleted,
			String emp_name, String rnk_name, String art_tag, String b_name) {
		super();
		this.re_no = re_no;
		this.art_no = art_no;
		this.user_no = user_no;
		this.re_date = re_date;
		this.re_content = re_content;
		this.re_isdeleted = re_isdeleted;
		this.emp_name = emp_name;
		this.rnk_name = rnk_name;
		this.art_tag = art_tag;
		this.b_name = b_name;
	}





	public String getArt_tag() {
		return art_tag;
	}

	public void setArt_tag(String art_tag) {
		this.art_tag = art_tag;
	}

	public int getRe_no() {
		return re_no;
	}

	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}

	public int getArt_no() {
		return art_no;
	}

	public void setArt_no(int art_no) {
		this.art_no = art_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getRe_date() {
		return re_date;
	}

	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}

	public String getRe_content() {
		return re_content;
	}

	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}

	public int getRe_isdeleted() {
		return re_isdeleted;
	}

	public void setRe_isdeleted(int re_isdeleted) {
		this.re_isdeleted = re_isdeleted;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getRnk_name() {
		return rnk_name;
	}

	public void setRnk_name(String rnk_name) {
		this.rnk_name = rnk_name;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	


    
    
    
    
}