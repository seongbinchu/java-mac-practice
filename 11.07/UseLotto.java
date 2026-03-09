import java.util.*;
class Lotto{ 
	private HashSet<Integer> winning;
	private int bonus;
	private Random r = new Random();

	public HashSet getWinning(){
		return winning;
	}
	public int getBonus(){
		return bonus;
	}
	
	public void setBonus(int bonus){
		this.bonus=bonus;
	}

	public HashSet<Integer> makeNum(){
		HashSet<Integer> set = new HashSet<Integer>();
		while(set.size() != 6){
			set.add(r.nextInt(45)+1);
		}
		return set;
	
	}

	public Lotto(){
		winning=makeNum();
		boolean flag = true;
		while(flag){
			int temp = r.nextInt(45)+1;
			if(winning.contains(temp)==false){
				bonus = temp;
				winning.add(bonus);
				flag = false;
			}
		}		
	}
	/*public HashSet<Integer> choice(){
		HashSet<Integer> choice = new HashSet<Integer>();
		while(choice.size() != 6){
			choice.add(r.nextInt(45)+1);
		}
		return choice;	
	}*/

	public int checkRank(HashSet<Integer> user){
		HashSet<Integer> temp = new HashSet<Integer>(user);
		temp.retainAll(winning);
		int count = temp.size();
		
		switch (count) {
			case 6:
				return 1;
			case 5:
				if (user.contains(bonus)) {
					return 2;
				} else {
					return 3;
 				}
 			case 4:
				return 4;
            	case 3:
				return 5;
			default:
				return 0;
		}
	}

	public void tryToMatch(){
		Scanner scan = new Scanner(System.in);
		System.out.print("¸î °³¸¦ »ì±î¿ä? : ");
		int num = scan.nextInt();

		System.out.println("\n´çÃ·¹øÈ£¸¦ ÃßÃ· ÇÕ´Ï´Ù.");
		System.out.println("´çÃ·¹øÈ£ : " + winning);

		int[] rankCounts = new int[6]; 

		for (int i = 0; i < num; i++) {
			HashSet<Integer> user = makeNum();
			int rank = checkRank(user);

			HashSet<Integer> matchedNumbers = new HashSet<Integer>(user);
			matchedNumbers.retainAll(winning);

            System.out.print(i + "È¸ °ÔÀÓ °á°ú : ");

            switch (rank) {
                case 1:
                    System.out.print("1µî");
                    rankCounts[1]++;
                    break;
                case 2:
                    System.out.print("2µî");
                    rankCounts[2]++;
                    break;
                case 3:
                    System.out.print("3µî");
                    rankCounts[3]++;
                    break;
                case 4:
                    System.out.print("4µî");
                    rankCounts[4]++;
                    break;
                case 5:
                    System.out.print("5µî");
                    rankCounts[5]++;
                    break;
                default:
                    System.out.print("²Î");
                    rankCounts[0]++;
                    break;
            }
            System.out.println("\t ¼±ÅÃ¹øÈ£ : " + user + "\t ÀÏÄ¡¹øÈ£ -> " + matchedNumbers);
        }
		System.out.println("\n<< " + num + "È¸ °ÔÀÓ °á°ú >>");
		System.out.println("1µî ´çÃ·È½¼ö : " + rankCounts[1]);
		System.out.println("2µî ´çÃ·È½¼ö : " + rankCounts[2]);
		System.out.println("3µî ´çÃ·È½¼ö : " + rankCounts[3]);
		System.out.println("4µî ´çÃ·È½¼ö : " + rankCounts[4]);
		System.out.println("5µî ´çÃ·È½¼ö : " + rankCounts[5]);
	}
}

class UseLotto{
	public static void main(String[] args){
		Lotto l1 = new Lotto();		
		l1.tryToMatch();
		
	}
}
