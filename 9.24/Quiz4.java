class Quiz4{
	public static void main(String args[]){
		
		
		for(int num=2;num<101;num++){

			boolean flag = true;

			for(int a=2;a<=num/2;a++){
				if(num%a==0){
					flag = false;
				}

			}if(flag){
				System.out.println(num);
			}
		}
	}
}

//�Ҽ����ƴѼ� false�� ���
// true�� ������ �Ҽ�
// flag&& a*a <num;
//num/2�� ���� ��� �����ؾ���
//�Ҽ��� ������ ����� ���ؾ��ϴ� �ݴ�Ǵ� ���� ����



