package kr.ac.green;

import java.util.Scanner;

/*창 최대화 ctrl m
	줄삭제  ctrl d
	자동임포트 ctrl shift o
	alt shift s c 생성자 r게터세터
*/
public class Some {
	private int num;
	private String str;
	private boolean ok;
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
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public Some(int num, String str, boolean ok, Scanner scan) {
		this.num = num;
		this.str = str;
		this.ok = ok;
	}
	
}