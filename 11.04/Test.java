
/* 과목
	[국어] [80,70,90]
	[수학] [80,70,90]
	[영어] [80,70,90]
// 학생
	추성빈 국어[0],수학[0],영어[0]
	성빈 국어[1],수학[1],영어[1]
	빈 국어[2],수학[2],영어[2]
// 반*/
import java.util.Arrays;

class Subject{
	private String title;
	private int score;
	
	public String getTitle(){
		return title;
	}
	public int getScore(){
		return score;
	}
	public void setTitle(String title){
		this.title =title;
	}
	public void setScore(int score){
		this.score=score;
	}
	public Subject(String title , int score){
		setTitle(title);
		setScore(score);
	}
}

class Student{
	private String name;
	private Subject[] subjects;

	public String getName(){
		return name;
	}
	public Subject[] getSubjects(){
		return subjects;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setSubjects(Subject... subjects){
		this.subjects=subjects;
	}

	public Student(String name,Subject... subjects){
		setName(name);
	}
}

class Ban{
	private String banName;
	private Student[] students;

	public String getBanName(){
		return banName;
	}
	public Student[] getStudents(){
		return students;
	}
	public void setBanName(String banName){
		this.banName=banName;
	}
	public void setStudents(Student... students){
		this.students=students;
	}

	public Ban(String banName,Student... Students){
		setBanName(banName);
		setStudents(students);
	}

	public void test(Student temp){
		Subject[] test = temp.getSubjects();
		System.out.println
	}
}



class Test{
	public static void main(String[] args){
		
	}
}
