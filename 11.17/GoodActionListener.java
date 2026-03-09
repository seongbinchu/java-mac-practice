package kr.ac.green;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoodActionListener implements ActionListener {

	private IDoAction owner;
	
	public GoodActionListener(IDoAction owner) {
		this.owner = owner;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		owner.doAction(e);
	}
}
