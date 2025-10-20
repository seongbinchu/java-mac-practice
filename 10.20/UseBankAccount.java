class BankAccount{
	private String accountNum;
	private int balance;

	public String getAccountNum(){	//get this.�ʿ����
		return this.accountNum;
	}
	public int getBalance(){
		return this.balance;
	}

	public void setAccountNum(String accountNum){
		this.accountNum = accountNum;
	}
	public void setBalance(int balance){
		this.balance = balance;
	}

	public void Withdrawal(int amount){ // �빮��
		if(balance>=amount){	//*
			balance-=amount;	//*
		}else{
			printInsufficientBalance();
		}
	}

	public void deposit(int amount){
		balance += amount;
	}

	public void transfer(int amount,BankAccount other){
		if(this.balance>=amount){	//*
			this.balance-=amount;	//* �ߺ������ؾ���
			other.balance+=amount;
		}else{
			printInsufficientBalance();
		}
	}

	public String toString(){
		String info = "���¹�ȣ : " + accountNum + "\n";
		info += "����ݾ� : "+balance+"\n";
		return info;
	}

	private void printInsufficientBalance(){
		System.out.println("�ܾ��� �����մϴ�");
	}
}

class UseBankAccount{
	public static void main(String[] args){
		BankAccount a1 = new BankAccount();
		BankAccount other = new BankAccount();

		a1.setAccountNum("110-518-809435");
		a1.setBalance(82213500);
		other.setAccountNum("112-2098-6571-07");
		other.setBalance(500000);

		other.transfer(400000,a1);
		System.out.println(a1);
		System.out.println(other);

		other.transfer(500000,a1);
		System.out.println(a1);
		System.out.println(other);

		a1.transfer(80000000,other);
		System.out.println(a1);
		System.out.println(other);

		a1.transfer(2613501,other);
		System.out.println(a1);
		System.out.println(other);

		a1.transfer(2613500,other);
		System.out.println(a1);
		System.out.println(other);
	}
}