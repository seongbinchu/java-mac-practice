package org.green.test.dto;

public class Article{
	private int art_no;
	private String art_title;
	private String art_date;
	private String art_tag;
	private String art_content;
	private byte[] art_file;;
	private int art_like;
	private String art_is_private;
	private int art_count;
	private String art_map;
	private int user_no;
	private int b_no;
	private String emp_name;  
    private String rnk_name;  
    private String dept_name; 
    private String art_file_name;
    private String isFav;
    private String b_name;
    
    public Article() {
    	super();
    }
    
    
	public Article(int art_no, String art_title, String art_date, String art_tag, String art_content, byte[] art_file,
			int art_like, String art_is_private, int art_count, String art_map, int user_no, int b_no, String emp_name,
			String rnk_name, String dept_name, String art_file_name, String isFav, String b_name) {
		super();
		this.art_no = art_no;
		this.art_title = art_title;
		this.art_date = art_date;
		this.art_tag = art_tag;
		this.art_content = art_content;
		this.art_file = art_file;
		this.art_like = art_like;
		this.art_is_private = art_is_private;
		this.art_count = art_count;
		this.art_map = art_map;
		this.user_no = user_no;
		this.b_no = b_no;
		this.emp_name = emp_name;
		this.rnk_name = rnk_name;
		this.dept_name = dept_name;
		this.art_file_name = art_file_name;
		this.isFav = isFav;
		this.b_name = b_name;
	}
	public int getArt_no() {
		return art_no;
	}
	public void setArt_no(int art_no) {
		this.art_no = art_no;
	}
	public String getArt_title() {
		return art_title;
	}
	public void setArt_title(String art_title) {
		this.art_title = art_title;
	}
	public String getArt_date() {
		return art_date;
	}
	public void setArt_date(String art_date) {
		this.art_date = art_date;
	}
	public String getArt_tag() {
		return art_tag;
	}
	public void setArt_tag(String art_tag) {
		this.art_tag = art_tag;
	}
	public String getArt_content() {
		return art_content;
	}
	public void setArt_content(String art_content) {
		this.art_content = art_content;
	}
	public byte[] getArt_file() {
		return art_file;
	}
	public void setArt_file(byte[] art_file) {
		this.art_file = art_file;
	}
	public int getArt_like() {
		return art_like;
	}
	public void setArt_like(int art_like) {
		this.art_like = art_like;
	}
	public String getArt_is_private() {
		return art_is_private;
	}
	public void setArt_is_private(String art_is_private) {
		this.art_is_private = art_is_private;
	}
	public int getArt_count() {
		return art_count;
	}
	public void setArt_count(int art_count) {
		this.art_count = art_count;
	}
	public String getArt_map() {
		return art_map;
	}
	public void setArt_map(String art_map) {
		this.art_map = art_map;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
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
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getArt_file_name() {
		return art_file_name;
	}
	public void setArt_file_name(String art_file_name) {
		this.art_file_name = art_file_name;
	}
	public String getIsFav() {
		return isFav;
	}
	public void setIsFav(String isFav) {
		this.isFav = isFav;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	
    
}