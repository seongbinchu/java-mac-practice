/*
class Quiz1{
	public static void main(String agrs[]){

		int sum=0;
		while(sum <50){
			int a=1;
			sum +=a;
			a++;
		}System.out.println(sum);
	}
}
*/

class Quiz2{
	public static void main(String args[]){
		
		int sum=0;
		for(int a=1;a>101;a++){
			sum+=a;
			if(sum>50){
				System.out.println(sum);
			}
		}
	}
}

/*������� 
boolean isContinue = true;
for(int num=1 ; isContinue && num<=100; num++){
			sum+=int;
			if(sum>=50){
			System.out.println(sum);
			isContinue = false;
			}
		}
�̷��� �������� �߰��ߴµ� ������ ����?*/