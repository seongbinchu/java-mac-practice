package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ex5 extends JFrame {

	private JLabel lblNum;
	private JButton btnPlus;
	private JButton btnMinus;
	private JButton btnReset;
	
	public Ex5() {
		lblNum = new JLabel("0", JLabel.CENTER);
		lblNum.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
		
		btnPlus = new JButton("plus");
		btnMinus = new JButton("minus");
		btnReset = new JButton("reset");
		
		JPanel pnlSouth = new JPanel(new GridLayout(1, 2));
		pnlSouth.add(btnPlus);
		pnlSouth.add(btnMinus);
		
		
		add(btnReset, BorderLayout.NORTH);
		add(lblNum, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
		
		ActionListener listener = new InnerListener();
		btnPlus.addActionListener(listener);
		btnMinus.addActionListener(listener);
		btnReset.addActionListener(listener);
		
		
		setTitle("counter");
		setSize(300, 400);
		setLocation(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void doAction(ActionEvent e) {
	
	}
	
	public static void main(String[] args) {
		new Ex5();
	}

	private class InnerListener implements ActionListener {
		// alt + shift + s -> v 오버라이드
		@Override
		public void actionPerformed(ActionEvent e) {
			String strNum = "0";
			Object o = e.getSource();
			if(o == btnPlus || o == btnMinus) {			
				strNum = lblNum.getText();
				int num = Integer.parseInt(strNum);
				if(o == btnPlus) {
					num++;
				} else {
					num--;
				}
				strNum = String.valueOf(num);
			}
			lblNum.setText(strNum);
		}
	}
}