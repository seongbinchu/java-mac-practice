import java.util.Arrays;


class Some{
	private int num;
	
	public Some(int num){
		setNum(num);
	}
	public int getNum(){
		return num;
	}
	public void setNum(int num){
		this.num =num;
	}

	public String toStirng(){
		return "Some("+num+")";
	}
}

class ArrEx1{
	public static void changeSome(Some s){
		s.setNum(100);
	}
	public static void main(String[] args){
		Some[] arr = new Some[3];	//some객체가 들어가는 3칸배열 초기값 참조형이니 null
		System.out.println(Arrays.toString(arr));

		arr[0] = new Some(3);	//num값이 3인 some 객체 인덱스 0에 넣어라
		arr[1] = new Some(7);
		arr[2] = new Some(2);
		//arr[3] = new Some(8); ArrayIndexOutOfBoundsException : 배열의 인덱스 범위를 벗어나서 참조한 경우
		changeSome(arr[1]);
		System.out.println(changeSome(s));
	}
}