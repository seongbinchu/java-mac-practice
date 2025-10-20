class Girl{
	private String tel = "01012341234";
	private boolean feelSoGood = false;
	
	public String getTel(){
		if(feelSoGood){
			return tel;
		}else{
			return "112";
		}
	}
	public boolean isFeelSoGood(){
		return feelSoGood;
	}

	public void setTel(String newTel){
		newTel = tel;
		
	}
	public void setFeelSoGood(int tall){
		if(tall>180){
			feelSoGood = true;
		}
	}
}
class UseGirl{
	public static void main(String[] agrs){
		Girl g1 = new Girl();
		g1.setFeelSoGood(180);
		System.out.println(g1.getTel());
	}
}