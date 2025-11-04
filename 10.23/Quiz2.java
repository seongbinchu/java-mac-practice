class Student{
	private String name;
	private	String num;
	private String hakkwa;
	private double score;

	public Student(String name,String num,String hakkwa,double score){
		setName(name);
		setNum(num);
		setHakkwa(hakkwa);
		setScore(score);
	}

	public String getName(){
		return name;
	}
	public String getNum(){
		return num;
	}
	public String getHakwa(){
		return hakkwa;
	}
	public double getScore(double score){
		return score;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setNum(String num){
		this.num = num;
	}
	public void setHakkwa(String hakkwa){
		this.hakkwa=hakkwa;
	}
	public void setScore(double score){
		this.score=score;
	}

	public String toString(){
		String info="name : "+name+"\n";
		info+="num : "+num+"\n";
		info+="hakkwa : "+hakkwa+"\n";
		info+="score : "+score+"\n";
		return info;
	}
}

class UnderGraduate extends Student{
	private String dongari;

	public UnderGraduate(String name,String num,String hakkwa,double score,String dongari){
		super(name,num,hakkwa,score);
		setDongari(dongari);
	}
	public String getDongari(){
		return dongari;
	}
	public void setDongari(String dongari){
		this.dongari=dongari;
	}
	public String toString(){
		String info =super.toString();
		info+="dongari : "+dongari+"\n";
		return info;
	}
}
class Edu extends Student{
	private double good;
	public Edu(String name,String num,String hakkwa,double score,double good){
		super(name,num,hakkwa,score);
		setGood(good);
	}
	public double setGood(){
		return good;
	}
	public void setGood(double good){
		this.good=good;
	}
	public String toString(){
		String info =super.toString();
		info+= "good : "+ good+"\n";
		return info;
	}
}

class Resurch extends Student{
	private double good;
	public Resurch(String name,String num,String hakkwa,double score,double good){
		super(name,num,hakkwa,score);
		setGood(good);
	}
	public double setGood(){
		return good;
	}
	public void setGood(double good){
		this.good=good;
	}
	public String toString(){
		String info =super.toString();
		info+= "good : "+ good+"\n";
		return info;
	}
}
class Quiz2{
	public static void main(String[] args){
		UnderGraduate u1 = new UnderGraduate("추성빈","1615483","경영학과",4.5,"방구석");
		System.out.println(u1);
		double random = Math.random()+1;
		Edu e1 = new Edu("추성빈","1615483","경영학과",4.5,random);
		System.out.println(e1);
		double random2 = Math.random()+1;
		Resurch r1 = new Resurch("추성빈","1615483","경영학과",4.5,random2);
		System.out.println(r1);
	}
}
