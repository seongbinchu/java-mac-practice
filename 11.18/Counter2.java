package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Counter2 extends JFrame implements ActionListener {

	private JLabel lblNum;
	private JButton btnPlus;
	
	public Counter2() {
		lblNum = new JLabel("0", JLabel.CENTER);
		lblNum.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
		
		btnPlus = new JButton("plus");
		
		add(lblNum, BorderLayout.CENTER);
		add(btnPlus, BorderLayout.SOUTH);
		
		btnPlus.addActionListener(this);		
		
		setTitle("counter");
		setSize(300, 400);
		setLocation(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public Counter2(CounterInfo info) {
		lblNum = new JLabel(info.getNum(), JLabel.CENTER);
		lblNum.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
		
		btnPlus = new JButton("plus");
		
		add(lblNum, BorderLayout.CENTER);
		add(btnPlus, BorderLayout.SOUTH);
		
		btnPlus.addActionListener(this);		
		
		setTitle("counter");
		Rectangle rectangle = info.getRectangle();
		setSize(rectangle.width,rectangle.height);
		setLocation(rectangle.x,rectangle.y);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public CounterInfo getInfo() {
		return new CounterInfo(
				new Rectangle(getLocation(), getSize()),
			lblNum.getText()	
		);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String strNum = lblNum.getText();
		int num = Integer.parseInt(strNum);
		num++;
		strNum = String.valueOf(num);
		lblNum.setText(strNum);
	}	
}
