package kr.ac.green;

import java.net.*;
import javax.swing.*;

public class NSLookup {
	public static void main(String[] args) {
		String domain = JOptionPane.showInputDialog("도메인을 입력하세요");
		InetAddress inetaddr[] = null;
		try {
			inetaddr = InetAddress.getAllByName(domain);
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		for(int i =0; i<inetaddr.length;i++) {
			System.out.println(inetaddr[i].getHostName());
			System.out.println(inetaddr[i].getHostAddress());
			System.out.println(inetaddr[i].toString());
			System.out.println("--------------------");
		}
	}
}
/*
InetAddress => ip[디바이스 식별자]를 나타내는 객체
포트[프로세스 식별자(논리적 식별자)] 
naver.com => DNS[domain name server] naver.com=>xxx.xxx.xxx = > naver 
 = > 바뀐 ip 사용자 알필요 없고 바꿔도 접근 가능
 
 TCP[Transmission Control Protocol]
 	- 연결형 통신방식 [like 전화]
 	- 인터넷
 	- 신뢰성 있는 데이터 보장
 UDP[User Control Protocol]
 	- 비 연결형	[like 편지]
 	- 송수신 신호 확인 절차 없음
 	- 안정성 낮음 / 속도에서 이득
 
 HTTP[Hype Text Transfer Protocol]
 	-선형[진입점과 끝점 존재]vs비선형(아날로그vs디지털 나누는 기준)
 	-HTML[Hype Text Markup language]
 [1]
*/