package org.green.test.sql;

public class ScheduleServerSQL {
	public static final String INSERT_SCHEDULE = "INSERT INTO schedule (user_no, sch_date, sch_content) VALUES (?, ?, ?)";

	public static final String SELECT_MY_SCHEDULE = "SELECT * FROM schedule WHERE user_no = ? AND sch_date LIKE ? ORDER BY sch_date ASC";

	public static final String REMOVE_SCHEDULE = "DELETE FROM schedule where sch_no=?";

}