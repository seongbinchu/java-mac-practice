package kr.ac.green;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

//Dialog 창
public class IOEx1 {
	public static void fileCopy() {
		JFileChooser ch = new JFileChooser("."); // 파일 선택할 수 있는 기능 추가해줌

		int resultOpen = ch.showOpenDialog(null); // 사용자가 뭐했는지에 대해 int값 리턴
													// event-driven방식
													// 이벤트가 프로그램의 흐름을 결정한다

		if (resultOpen == JFileChooser.APPROVE_OPTION) {
			// 열기버튼을 승인했다
			File targetFile = ch.getSelectedFile();
			
			int resultSave = ch.showSaveDialog(null);
			
			if (resultSave == JFileChooser.APPROVE_OPTION) {
				// 저장을 승인했다
				File copiedFile = ch.getSelectedFile();

				FileInputStream fis = null;
				FileOutputStream fos = null;

				try {
					fis = new FileInputStream(targetFile);
					fos = new FileOutputStream(copiedFile);

					int count = -1;
					byte[] buf = new byte[10000];				//char연산에선 잘안씀 char연산 => txt파일 같은건 수백 MB잘 없음
					while ((count = fis.read(buf)) != -1) {		// data = read(); => 하나씩 읽어서 data에 대입
						fos.write(buf, 0, count);				// count = fis.read(); => count 몇개 읽엇냐
					}											// fis.read(); 읽어서 배열에 대입
																// fos.write(buf,0,count);
																// 0시작인덱스 count 갯수
					// abcdefg =? [a][b][c] count 3=> [d][e][f] count 3= > [g][e][f] count 1
					// 속도 빨라진이유 => 7번읽고 7번씀 = > 3번읽고 3번씀
					fos.flush();
					JOptionPane.showMessageDialog(null, "복사완료");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// 자원해재 부터 작성할 것 !!!
					try {
						fis.close();
					} catch (Exception e) {
					}
					try {
						fos.close();
					} catch (Exception e) {
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		fileCopy();
	}
}