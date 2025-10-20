class Girl{
	private String tel = "01012341234";
	private boolean feelSoGood = false;
	
	public String getTel(){
		return tel;
	}
	public boolean isFeelSoGood(){
		return feelSoGood;
	}

	public Stinrg setTel(String newTel){
		tel = newTel;
	}
	public boolean setFeelSoGood(boolean newFeelSoGood){
		feelSoGood = newFeelSoGood;
	}
}
class UseGirl{
	public static void main(String[] agrs){
		Girl g1 = new Girl();

		System.out.println(g1.getTel());
	}
}