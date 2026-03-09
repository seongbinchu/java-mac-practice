package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ex3 extends JFrame {
	
	private JLabel lblNum;
	private JButton btnPlus;
	private JButton btnMinus;
	
	public JLabel getLblNum() {
		return lblNum;
	}
	
	public JButton getBtnPlus() {
		return btnPlus;
	}
	
	public void setLblNum(JLabel lblNum) {
		this.lblNum = lblNum;
	}
	
	public void setBtnPlus(JButton btnPlus) {
		this.btnPlus = btnPlus;
	}
	
	public void plusOrMinus(ActionEvent e) {
		// ActionEvent 발생 시 할 일 정의
		
		// 1. 레이블의 글자 가져오기
		String strNum = lblNum.getText();
			
		// 2. String -> int
		int num = Integer.parseInt(strNum);
		
		// 3. 1 증가 or 감소 or 리셋 이벤트 소스 구분
		if(e.getSource() == btnPlus) {
			num++;
		} else if(e.getSource() == btnMinus){
			num--;
		} else {
			num = 0;
		}
		// 4. int -> String
		strNum = String.valueOf(num);
		
		// 5. 다시 레이블에 넣기
		lblNum.setText(strNum);
	}
	
	
	
	public Ex3() {
		lblNum = new JLabel("0"); // 라벨 X 레이블 O
		lblNum.setFont(new Font(Font.DIALOG, Font.BOLD, 40)); // 폰트 이름, 폰트 굵게, 폰트 크기
		
		btnPlus = new JButton("plus");
		btnMinus = new JButton("minus");
		
		btnPlus.addActionListener(new MyActionListener(this));
		btnMinus.addActionListener(new MyActionListener(this));
		
		JPanel pnlSouth = new JPanel(new GridLayout(1, 2));
		pnlSouth.add(btnPlus);
		pnlSouth.add(btnMinus);
		
		add(lblNum, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
		setTitle("counter");
		setSize(300, 400);
		setLocation(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ex3();
	}
}
