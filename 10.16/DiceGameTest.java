import java.util.Scanner;
class DiceGame{
	
	int diceFace;
	int userGuess;
	String result="맞췄습니다";

	void rollDice(){
		diceFace = (int)(Math.random() * 6 ) + 1;
	}
	void getUserInput(){
		System.out.println("예상값을 입력하시오");
		Scanner input = new Scanner(System.in);
		userGuess = input.nextInt();
	}
	void checkUserGuess(){
		if(diceFace != userGuess){
				while(diceFace !=userGuess){
					result="틀렸습니다";
					getUserInput();
				}
		}System.out.println(result);
	}
	void startPlaying(){
		rollDice();
		getUserInput();
		checkUserGuess();
		
	}
}
class DiceGameTest{
	public static void main(String[] args){
		DiceGame game = new DiceGame();
		game.startPlaying();
		game.rollDice() = game.rollDice()+3;
	}
}

//대입시 변수의 값을 복사한다 참조변수의 경우 주소값이 하나 더 생기는것 참조변수가 하나 더 생기는것?
//메소드를 분리해놓으면 수정할 때 편함
//기능을 표현하기 위한 메소드가 아닌 메소드 2가지 1.중복을 소거하는 메소드 2. 기능 분류하는 메소드
//자판기를 코드로 만들어오기
	
//퀴즈를 통해 참조변수의 특징