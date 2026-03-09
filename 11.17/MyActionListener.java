package kr.ac.green;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MyActionListener implements ActionListener {
	
	private Ex4 owner;
	
	public MyActionListener(Ex4 owner) {
		this.owner = owner;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		owner.plusOrMinusOrReset(e);
	}
}
