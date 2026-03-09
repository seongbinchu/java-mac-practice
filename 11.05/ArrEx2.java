import java.util.Arrays;
class Some implements Comparable<Some>{
	private int num;

	public Some(int num){
		setNum(num);
	}
	public int getNum(){
		return num;
	}
	public void setNum(int num){
		this.num = num;
	}
	@Override
	public String toString(){
		return "Some("+num+")";
	}

	// compareTo 파라미터랑 비교하여 파라미터가 크면 음수 작으면 양수 같으면 0
	// Human 이름으로 정렬한다 => natural ordering
	@Override
	public int compareTo(Some other){
		//비교 기준을 설정해줌
		int otherNum = other.getNum();
		if(num<otherNum){
			return -1;
		}else if(num>otherNum){
			return 1;
		}else{
			return 0;
		}
		// return num-otherNum; 같음 음수양수0
		// return (num-otherNum)* -1; 내림차순
	}
}
class ArrEx2{
	public static void main(String[] args){
		Some[] arr = {
			new Some(2),
			new Some(5),
			new Some(1),
			new Some(8),
			new Some(3),
			new Some(9)
		};
		System.out.println("before: "+Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println("before: "+Arrays.toString(arr));
	}
}



/*
compareTo 학습
generics
some객체들 크기 비교할 기준이 없음=>Comparable로 형변환=>기준(같은값들과 비교할)을 만듬 
sort 정렬 알고리즘을 통해 정렬해주는 기능 기준은 없음 => comparable 구현必
Object.equals 적용되야할 이유?

compareTo가 처리 못하는 정렬
기준이 한번에 여러개 필요할 때
비교할 대상의 소유자가 아닐 때ex.String
*/