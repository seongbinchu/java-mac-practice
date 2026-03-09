package kr.ac.green;

import java.io.Serializable;

public class Some implements Serializable{
	private int num;
	private String str;
	private Other other;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Other getOther() {
		return other;
	}
	@Override
	public String toString() {
		return "Some [num=" + num + ", str=" + str + ", other=" + other + "]";
	}
	public Some(int num, String str, Other other) {
		super();
		this.num = num;
		this.str = str;
		this.other = other;
	}
	public void setOther(Other other) {
		this.other = other;
	}
	
}
