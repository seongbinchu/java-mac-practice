package ac.kr.green;

public class NormalProcess {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		for(int i =0;i<3000;i++) {
			System.out.print("-");
		}
		System.out.println(
				"소요기간 : "+(System.currentTimeMillis()-startTime)
		);
		for(int i =0;i<3000;i++) {
			System.out.print("|");
			
		}
		System.out.println(
				"소요기간2: "+(System.currentTimeMillis()-startTime)
		);
	}
}
