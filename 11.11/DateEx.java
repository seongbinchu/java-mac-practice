import java.util.Date;//현재시간
import java.text.SimpleDateFormat;
import java.text.ParseException;
class DateEx{
	public static void main(String[] args){
		Date d = new Date();

		System.out.println(d.getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		String strDate = sdf.format(d);
		System.out.println(strDate);

		String someday = "2020-05-03 오전 09:20"; // 정각의미 0분0초
		try{
			Date somedayObj = sdf.parse(someday);//String -> Date

			System.out.println(somedayObj.getTime());
		}catch(ParseException e){
			e.printStackTrace();
		}
		
	}
}