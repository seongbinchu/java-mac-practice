package kr.ac.green;

public class User {
	private String uid;
	private String upw;
	private String uname;
	private String unick;
	private String ugender;
	
	public User(String uid) {
		setuid(uid);
	}
	
	public User(
		String uid, String upw, String uname,
		String unick, String ugender){
			setuid(uid);
			setUpw(upw);
			setUname(uname);
			setUnick(unick);
			setUgender(ugender);
			
		
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", upw=" + upw + ", uname=" + uname + ", unick=" + unick + ", ugender=" + ugender
				+ "]";
	}

	public String getUid() {
		return uid;
	}

	public void setuid(String uid) {
		this.uid = uid;
	}

	public String getUpw() {
		return upw;
	}

	public void setUpw(String upw) {
		this.upw = upw;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUnick() {
		return unick;
	}

	public void setUnick(String unick) {
		this.unick = unick;
	}

	public String getUgender() {
		return ugender;
	}

	public void setUgender(String ugender) {
		this.ugender = ugender;
	}
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof User)) {
			return false;
		}
		User user = (User)o;
		return uid.equals(user.getUid());
	}
}
