package kr.ac.green;

import java.io.Serializable;

public class Other implements Serializable{
	private double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Other(double value) {
		super();
		this.value = value;
	}
	
}
