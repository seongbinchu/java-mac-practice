import java.util.Scanner;
class DiceGame{
	
	int diceFace;
	int userGuess;
	String result="������ϴ�";

	void rollDice(){
		diceFace = (int)(Math.random() * 6 ) + 1;
	}
	void getUserInput(){
		System.out.println("������ �Է��Ͻÿ�");
		Scanner input = new Scanner(System.in);
		userGuess = input.nextInt();
	}
	void checkUserGuess(){
		if(diceFace != userGuess){
				while(diceFace !=userGuess){
					result="Ʋ�Ƚ��ϴ�";
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

//���Խ� ������ ���� �����Ѵ� ���������� ��� �ּҰ��� �ϳ� �� ����°� ���������� �ϳ� �� ����°�?
//�޼ҵ带 �и��س����� ������ �� ����
//����� ǥ���ϱ� ���� �޼ҵ尡 �ƴ� �޼ҵ� 2���� 1.�ߺ��� �Ұ��ϴ� �޼ҵ� 2. ��� �з��ϴ� �޼ҵ�
//���Ǳ⸦ �ڵ�� ��������
	
//��� ���� ���������� Ư¡