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

public class Ex6 extends JFrame {

	private JLabel lblNum;
	private JButton btnPlus;
	private JButton btnMinus;
	private JButton btnReset;
	
	public Ex6() {
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
		
		// anonymous-inner-class // 익명 클래스 매우많이쓰인다. 이름X 재사용X 한번만
		ActionListener listener = new ActionListener() {
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
		};
				
				
		btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblNum.setText(String.valueOf(Integer.parseInt(lblNum.getText()) + 1));
			}
		});
		btnMinus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblNum.setText(String.valueOf(Integer.parseInt(lblNum.getText()) - 1));
			}
		});
//		btnReset.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				lblNum.setText("0");
//			}
//		});
		
		// 람다식 - @Functional Interface : 추상 메서드를 1개 가지는 인터페이스만 사용 가능
		// JDK 1.8~
		btnReset.addActionListener(e -> lblNum.setText("0"));
		
		
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
	
	public static void main(String[] args) {
		new Ex6();
	}
}