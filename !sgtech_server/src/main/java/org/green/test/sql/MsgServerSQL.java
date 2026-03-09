package org.green.test.sql;

import java.util.List;
import org.green.test.dto.Msg;

public interface MsgServerSQL {
	// [언제 사용]: 이재룡 님의 수신 쪽지함 목록을 날짜 역순으로 조회할 때 사용
	String SELECT_RECEIVED_MESSAGES = "SELECT m.*, e.emp_name AS sender_name, r.rnk_name " + "FROM msg m "
			+ "JOIN user u ON m.from_id = u.user_no " + "JOIN emp e ON u.emp_no = e.emp_no "
			+ "JOIN rnk r ON e.rnk_no = r.rnk_no " + "WHERE m.to_id = ? " + "ORDER BY m.msg_datetime DESC";

	// [언제 사용]: 특정 쪽지를 클릭하여 내용을 확인할 때 사용 (발신자/수신자 이름 포함)
	String SELECT_MESSAGE_DETAIL = "SELECT m.msg_no, m.msg_title, m.msg_content, m.msg_datetime, "
			+ "m.from_id, e1.emp_name AS sender_name, e2.emp_name AS receiver_name " + "FROM msg m "
			+ "JOIN user u1 ON m.from_id = u1.user_no " + "JOIN emp e1 ON u1.emp_no = e1.emp_no "
			+ "JOIN user u2 ON m.to_id = u2.user_no " + "JOIN emp e2 ON u2.emp_no = e2.emp_no " + "WHERE m.msg_no = ?";

	// [언제 사용]: 쪽지 답장을 보내거나 새 쪽지를 작성할 때 사용
	String INSERT_SEND_MESSAGE = "INSERT INTO msg (msg_title, from_id, to_id, msg_content, msg_datetime) VALUES (?, ?, ?, ?, NOW())";

	// 전송한 메시지
	String SELECT_SENT_MESSAGES = "SELECT m.*, e.emp_name AS receiver_name " + "FROM msg m "
			+ "JOIN user u ON m.to_id = u.user_no " + "JOIN emp e ON u.emp_no = e.emp_no " + "WHERE m.from_id = ? "
			+ "ORDER BY m.msg_datetime DESC";

	// 부서 목록 조회
	String SELECT_DEPT_LIST = "SELECT * FROM dept ORDER BY dept_no ASC";
	// 특정 부서의 사원 목록 조회
	String SELECT_USERS_BY_DEPT = "SELECT u.user_no, e.emp_name, e.emp_no, r.rnk_name " + "FROM user u "
			+ "JOIN emp e ON u.emp_no = e.emp_no " + "JOIN rnk r ON e.rnk_no = r.rnk_no " + "WHERE e.dept_no = ?";
	String SELECT_UNREAD_COUNT = "SELECT COUNT(*) FROM msg WHERE to_id = ? AND msg_is_read = 0";

	// [언제 사용]: 사용자가 쪽지 상세 내용을 확인하면 읽음 상태로 변경
	String UPDATE_MESSAGE_READ_STATUS = "UPDATE msg SET msg_is_read = 1 WHERE msg_no = ?";

	// 추상 메서드 정의[이부분은 통채로 복붙]
	List<Msg> getReceivedMessages(int user_no);

	Msg getMessageDetail(int msg_no);

	int sendMessage(Msg msg);

	int getUnreadMsgCount(int user_no);

	int updateReadStatus(int msg_no);

}