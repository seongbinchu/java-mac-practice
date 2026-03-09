package kr.ac.green;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ex8 extends JFrame {
	
	public Ex8() {
		super("MyFrame");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(
					Ex8.this,
					"종료하시겠습니까?",
					"종료",
					JOptionPane.YES_NO_OPTION, // 버튼 종류 결정
					JOptionPane.QUESTION_MESSAGE
				);
				if(result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
//		int result = JOptionPane.showConfirmDialog(
//			this,
//			"종료하시겠습니까?",
//			"종료",
//		//	JOptionPane.OK_CANCEL_OPTION, // 버튼 종류 결정
//			JOptionPane.YES_NO_OPTION, // 버튼 종류 결정
//			JOptionPane.QUESTION_MESSAGE	// 이것도 순서가 있네?
//		//	JOptionPane.INFORMATION_MESSAGE
//		//	JOptionPane.YES_NO_CANCEL_OPTION, // 버튼 종류 결정
//		//	JOptionPane.WARNING_MESSAGE
//		//	JOptionPane.PLAIN_MESSAGE
//		//	JOptionPane.ERROR_MESSAGE
//		);
//		
//		if(result == JOptionPane.YES_OPTION) {
//			System.exit(0);
//		}
	
		setSize(500, 400);
		setLocation(100, 100);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ex8();
	}
}
