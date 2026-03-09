package kr.ac.green;

import java.awt.Rectangle;
import java.io.Serializable;

public class CounterInfo implements Serializable {
	//위치 크기
	private Rectangle rectangle;
	//숫자
	private String num;
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public CounterInfo(Rectangle rectangle, String num) {
		this.rectangle = rectangle;
		this.num = num;
	}
	
	
	
	
}
