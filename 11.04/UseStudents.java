/*
	학생 만들고
	스코어에 과목별 점수 배열 3개
	학생 총점 / 평균
	과목 총점 / 평균
*/
class Score{
	private String title;
	private int score;

	public Score(String title,int score){
		setTitle(title);
		setScore(score);
	}
	public String getTitle(){
		return title;
	}
	public int getScore(){
		return score;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setScore(int score){
		this.score = score;
	}
	@Override
	public String toString(){
		return title+": \n"+score;
		
	}
}
class TotalScore{
	private Score[] scores;
	private int total;

	public TotalScore(int total,Score... scores){
		setTotal(total);
		setScores(scores);
	}

	public Score[] getScores(){
		return scores;
	}
	public int getTotal(){
		return total;
	}
	public void setTotal(int total){
		this.total = total;
	}
	public void setScores(Score... scores){
		this.scores = scores;
	}

	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer();
		for(Score temp : scores){
			buf.append(scores.toString() + "\n");
		}
		buf.append("총점 : "+total+"\n");
		return buf.toString();

	}
}

class Students{
	private String name;
	private String num;
	private TotalScore[] totalScores;

	public String getName(){
		return name;
	}	
	public String getNum(){
		return num;
	}
	public TotalScore[] getTotalScores(){
		return totalScores;
	}
	public void setTotalScores(TotalScore... totalScores){
		this.totalScores=totalScores;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setNum(String num){
		this.num=num;
	}

	public Students(String name,String num,TotalScore... totalScores){
		setName(name);
		setNum(num);
		setTotalScores(totalScores);
	}
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer();
		buf.append("이름 : "+name+"\n");
		buf.append("학번 : "+num+"\n");
		for(TotalScore temp : totalScores){
			buf.append(temp.toString() + "\n");
		}
		return buf.toString();
	}

	public void sum(S)
}
class UseStudents{
	public static void main(String[] args){

		Students s1 = new Students("추성빈","1615483",
			new TotalScore(200,
			new Score("국어",80),
			new Score("수학",70),
			new Score("과학",90)
			)
			);
		Students s2 = new Students("***","456432",
			new TotalScore(100,
			new Score("국어",50),
			new Score("수학",60),
			new Score("과학",70)
			)
			);
		Students s3 = new Students("+++","535434",
			new TotalScore(300,
			new Score("국어",100),
			new Score("수학",80),
			new Score("과학",90)
			)
			);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
	}
}
