import java.util.Arrays;

class subject{
	private String name;
	private int point;
	
	public subject(String name , int point){
		
	}
}


class Student{
	private String name;
	private String num;
	private int[] score;
	
	public Student(String name,String num,int... score){
		setName(name);
		setNum(num);
		setScore(score);
	}
	public String getName(){
		return name;
	}
	public String getNum(){
		return num;
	}
	public int[] getScore(){
		return score;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setNum(String num){
		this.num = num;
	}
	public void setScore(int... score){
		this.score = score;
	}
	public String toString(){
		String info = "name : "+name+"\n";
		info+= "학번 : "+num+"\n";
		info+= "성적 : "+ Arrays.toString(score);
		return info;
	}
}

class Ban{
	private String banName;
	private Student[] students;
	public Ban(String banName,Student... students){
		setBanName(banName);
		setStudents(students);
	}
	public String getBanName(){
		return banName;
	}
	public Student[] getStudents(){
		return students;
	}
	public void setBanName(String banName){
		this.banName=banName;
	}
	public void setStudents(Student...students){
		this.students=students;
	}
	public String sum(Student temp){
		int[] sum1 = temp.getScore();
		int lastAvg = (sum1[0]+sum1[1]+sum1[2])/3;
		int lastSum = sum1[0]+sum1[1]+sum1[2];
		int[] result = new int[]{lastAvg,lastSum};
		return Arrays.toString(result);
	}
	public String test(){
		int getScore
	}
}
class UseStudent{
	public static void main(String[] args){
		
		Student s1 = new Student("추성빈","1615483",70,80,70);
		Student s2 = new Student("준성","121383",65,40,90);
		Student s3 = new Student("다빈","125483",55,70,20);
		Ban b1 = new Ban("1반",s1,s2,s3);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(b1.sum(s1));
		System.out.println(b1.sum(s2));
		System.out.println(b1.sum(s3));
		
		
	}
}





// 과목
	[국어] [80,70,90]
	[수학] [80,70,90]
	[영어] [80,70,90]
// 학생
	추성빈 국어[0],수학[0],영어[0]
	성빈 국어[1],수학[1],영어[1]
	빈 국어[2],수학[2],영어[2]
// 반