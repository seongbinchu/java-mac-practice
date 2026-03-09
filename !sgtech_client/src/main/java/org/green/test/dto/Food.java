package org.green.test.dto;

public class Food {
	private String main_title;	//MAIN_TITLE
	private String gu_name;		//GUGUN_NM
	private double lat;			//LAT
	private double lng;			//LNG
	private String addr;		//ADDR1
	private String open_time;	//USAGE_DAY_WEEK_AND_TIME
	private String signature;	//RPRSNTV_MENU
	
	public Food() {}

	public Food(String main_title, String gu_name, double lat, double lng, String addr, String open_time,
			String signature) {
		this.main_title = main_title;
		this.gu_name = gu_name;
		this.lat = lat;
		this.lng = lng;
		this.addr = addr;
		this.open_time = open_time;
		this.signature = signature;
	}

	public String getMain_title() {
		return main_title;
	}

	public void setMain_title(String main_title) {
		this.main_title = main_title;
	}

	public String getGu_name() {
		return gu_name;
	}

	public void setGu_name(String gu_name) {
		this.gu_name = gu_name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "Food [식당명=" + main_title + ", 구=" + gu_name + ", lat=" + lat + ", lng=" + lng + ", 주소="
				+ addr + ", 운영시간=" + open_time + ", 대표메뉴=" + signature + "]";
	}
	
	
	
}	
	
	
