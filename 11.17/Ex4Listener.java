package kr.ac.green;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ex4Listener implements ActionListener {
	private Ex4 owner;
	
	public Ex4Listener(Ex4 owner) {
		this.owner = owner;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		owner.doAction(e);
	}
}
