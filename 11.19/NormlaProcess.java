package ac.kr.green;

import javax.swing.JOptionPane;

public class NormlaProcess {
	public static void main(String[] args) {
		String input = JOptionPane.showInputDialog("아무값이나입력하세요.");
		System.out.println("입력하신값은 "+input+"입니다");
		for(int i = 10 ; i>0; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			}catch(Exception e) {}
		}
	}
}
