package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Counter extends JFrame implements ActionListener {

	private JLabel lblNum;
	private JButton btnPlus;
	
	public Counter() {
		lblNum = new JLabel("0", JLabel.CENTER);
		lblNum.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
		
		btnPlus = new JButton("plus");
		
		add(lblNum, BorderLayout.CENTER);
		add(btnPlus, BorderLayout.SOUTH);
		
		btnPlus.addActionListener(this);		
		
		setTitle("counter");
		setSize(300, 400);
		setLocation(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// ActionEvent발생 시 할 일 정의
		
		// 1. 레이블의 글자 가져오기
		String strNum = lblNum.getText();
		
		// 2. String -> int
		int num = Integer.parseInt(strNum);
		
		// 3. 1증가
		num++;
		
		// 4. int -> String
		strNum = String.valueOf(num);
		
		// 5. 레이블에 넣기
		lblNum.setText(strNum);
	}

	public static void main(String[] args) {
		new Counter();
	}
}
