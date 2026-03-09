package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 *	1.	직접 implements
 * 
 */
public class Ex2 extends JFrame implements ActionListener {
	
	private JLabel lblNum;
	private JButton btnPlus;
	
	public Ex2() {
		lblNum = new JLabel("0"); // 라벨 X 레이블 O
		lblNum.setFont(new Font(Font.DIALOG, Font.BOLD, 40)); // 폰트 이름, 폰트 굵게, 폰트 크기
		
		btnPlus = new JButton("plus");
		
		add(lblNum, BorderLayout.CENTER);
		add(btnPlus, BorderLayout.SOUTH);
		
		btnPlus.addActionListener(this); // 버튼이랑 연결을 해줘야 작동한다.
		
		setTitle("counter");
		setSize(300, 400);
		setLocation(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// ActionEvent 발생 시 할 일 정의
		
		// 1. 레이블의 글자 가져오기
		String strNum = lblNum.getText();
		
		// 2. String -> int
		int num = Integer.parseInt(strNum);
		
		// 3. 1 증가
		num++;
		
		// 4. int -> String
		strNum = String.valueOf(num);
		
		// 5. 다시 레이블에 넣기
		lblNum.setText(strNum);
	}
	
	public static void main(String[] args) {
		new Ex2();
	}
}
