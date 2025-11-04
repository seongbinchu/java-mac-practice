class Student{
	private String name;
	private	String num;
	private String hakkwa;
	private int year;
	private double score;

	public Student(String name,String num,String hakkwa,int year,double score){
		setName(name);
		setNum(num);
		setHakkwa(hakkwa);
		setYear(year);
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
	public int getYear(){
		return year;
	}
	public double getScore(){
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
	public void setYear(int year){
		this.year=year;
	}
	public void setScore(double score){
		this.score=score;
	}

	public String toString(){
		String info="name : "+name+"\n";
		info+="num : "+num+"\n";
		info+="hakkwa : "+hakkwa+"\n";
		info+="year : "+year+"\n";
		info+="score : "+score+"\n";
		return info;
	}
}

class UnderGraduate extends Student{
	private String dongari;

	public UnderGraduate(String name,String num,String hakkwa,int year,double score,String dongari){
		super(name,num,hakkwa,year,score);
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
class Graduate extends Student{
	private double good;
	private String type;

	public Graduate(String name,String num,String hakkwa,int year,double score,String type){
		super(name,num,hakkwa,year,score);
		this.good =Math.random();
		setType(type);
	}
	public double getGood(){
		return good;
	}
	public String getType(){
		return type;
	}
	public void setGood(double good){
		this.good=good;
	}
	public void setType(String type){
		this.type=type;
	}
	public String toString(){
		String info =super.toString();
		info+= "good : "+ good+"\n";
		info+= "type : "+type+"\n";
		return info;
	}
}


class UseStudent{
	public static void main(String[] args){
		UnderGraduate u1 = new UnderGraduate("추","1615483","경영학과",4,4.5,"방구석");
		System.out.println(u1);
		double random = Math.random();
		Graduate g1 = new Graduate("성","3845161","회계학과",3,4.0,"교육");
		System.out.println(g1);
		Graduate g2 = new Graduate("빈","8888888","수학과",1,2.0,"교육");
		System.out.println(g2);
	}
}