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

public class Ex4 extends JFrame {
	
	private JLabel lblNum;
	private JButton btnPlus;
	private JButton btnMinus;
	private JButton btnReset;
	
	public JLabel getLblNum() {
		return lblNum;
	}
	
	public JButton getBtnPlus() {
		return btnPlus;
	}
	
	public Ex4() {
		lblNum = new JLabel("0"); // 라벨 X 레이블 O
		lblNum.setFont(new Font(Font.DIALOG, Font.BOLD, 40)); // 폰트 이름, 폰트 굵게, 폰트 크기
		
		btnPlus = new JButton("plus");
		btnMinus = new JButton("minus");
		btnReset = new JButton("reset");
		
		JPanel pnlSouth = new JPanel(new GridLayout(1, 2));
		pnlSouth.add(btnPlus);
		pnlSouth.add(btnMinus);
	
		ActionListener listener = new Ex4Listener(this);
		btnPlus.addActionListener(listener);
		btnMinus.addActionListener(listener);
		btnReset.addActionListener(listener);
		
		add(btnReset, BorderLayout.NORTH);
		add(lblNum, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
		setTitle("counter");
		setSize(300, 400);
		setLocation(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void doAction(ActionEvent e) {
		String strNum = "0";
		Object o = e.getSource();
		if(o == btnPlus || o == btnMinus) {
			StrNum = lblNum.getText();
			int num = Integer.parseInt(strNum);
			if(o == btnPlus) {
				num++;
			} else if(o == btnMinus){
				num--;
			}
			strNum = String.valueOf(num);
		}
		lblNum.setText(strNum);
	}

	
	public static void main(String[] args) {
		new Ex4();
	}
}
