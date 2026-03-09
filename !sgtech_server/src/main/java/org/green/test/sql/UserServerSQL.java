package org.green.test.sql;

import java.util.List;
import org.green.test.dto.User;

public interface UserServerSQL {
	// [언제 사용]: 접속 시 세션에 담을 유저 정보를 조회할 때 사용
	String SELECT_USER_BY_NO = "SELECT * FROM user WHERE user_no = ?";

	// [언제 사용]: 마이페이지나 유저 상세 정보 조회 시 사원/직급/부서 정보를 JOIN하여 가져올 때 사용
	String SELECT_USER_INFO_WITH_DETAILS = "SELECT u.*, e.emp_name, r.rnk_name, d.dept_name " + "FROM user u "
			+ "JOIN emp e ON u.emp_no = e.emp_no " + "JOIN rnk r ON e.rnk_no = r.rnk_no "
			+ "JOIN dept d ON e.dept_no = d.dept_no " + "WHERE u.user_no = ?";

	String SELECT_EMP_INFO_BY_EMP_NO = "SELECT * FROM emp e WHERE e.emp_no=?";
	String SELECT_USER_INFO_BY_EMP_NO = "SELECT u.*, e.emp_name, r.rnk_name, d.dept_name, IFNULL(ba_admin.b_no,-1) as admin_b_no " + "FROM user u "
			+ "JOIN emp e ON u.emp_no = e.emp_no " + "JOIN rnk r ON e.rnk_no = r.rnk_no "
			+ "JOIN dept d ON e.dept_no = d.dept_no " 
			+ "LEFT JOIN  (SELECT * FROM board_access ba NATURAL JOIN emp WHERE ba.ROLE='ADMIN') AS ba_admin ON ba_admin.emp_no=u.emp_no "
			+ "WHERE u.emp_no = ?";

	// [언제 사용]: 주소록 등에서 전체 사원 목록을 조회할 때 사용
	String SELECT_USER_LIST = "SELECT u.*, e.emp_name, e.emp_tel, e.emp_email, r.rnk_name, d.dept_name "
			+ "FROM user u " + "JOIN emp e ON u.emp_no = e.emp_no " + "JOIN rnk r ON e.rnk_no = r.rnk_no "
			+ "JOIN dept d ON e.dept_no = d.dept_no " + "ORDER BY e.emp_name ASC";

	// [추가 1]: 나를 제외한 전체 사원 목록 (클라이언트 mainpage 유저 리스트용)
	String SELECT_USER_LIST_EXCEPT_ME = "SELECT u.*, e.emp_name, r.rnk_name, d.dept_name " + "FROM user u "
			+ "JOIN emp e ON u.emp_no = e.emp_no " + "JOIN rnk r ON e.rnk_no = r.rnk_no "
			+ "JOIN dept d ON e.dept_no = d.dept_no " + "WHERE u.user_no != ? " + "ORDER BY e.emp_name ASC";

	// [추가 2]: 즐겨찾기 체크 및 토글
	String CHECK_FAVORITE = "SELECT COUNT(*) FROM art_favorite WHERE user_no = ? AND art_no = ?";
	String INSERT_FAVORITE = "INSERT INTO art_favorite (user_no, art_no) VALUES (?, ?)";
	String DELETE_FAVORITE = "DELETE FROM art_favorite WHERE user_no = ? AND art_no = ?";

	// 유저아닌 emp 찾기
	String SELECT_NONE_USER_EMP = "SELECT e.emp_no, e.emp_name, d.dept_name, r.rnk_name FROM emp e \r\n"
			+ "			JOIN dept d ON e.dept_no = d.dept_no JOIN rnk r ON e.rnk_no = r.rnk_no\r\n"
			+ "			JOIN user u ON e.emp_no = u.emp_no \r\n"
			+ "			WHERE u.user_role ='NONE' ORDER BY e.emp_no ASC;";

	// 즐찾글찾기
	String SELECT_FAVORITE = "SELECT a.*, e.emp_name, 'Y' AS isFav " + "FROM article a "
			+ "JOIN art_favorite f ON a.art_no = f.art_no " + "JOIN user u ON a.user_no = u.user_no "
			+ "JOIN emp e ON u.emp_no = e.emp_no " + "WHERE f.user_no = ? " + "AND a.art_tag not in('삭제글') "
			+ "OR a.art_tag is null " + "ORDER BY a.art_no DESC";
	String SELECT_USER_LIST_SORTED = "SELECT u.*, e.*, r.rnk_name, d.dept_name " + "FROM user u "
			+ "JOIN emp e ON u.emp_no = e.emp_no " + "LEFT JOIN rnk r ON e.rnk_no = r.rnk_no "
			+ "LEFT JOIN dept d ON e.dept_no = d.dept_no " + "ORDER BY "
			+ " (CASE WHEN u.user_no = ? THEN 1 ELSE 2 END) ASC, " + " (CASE WHEN u.user_state = 'ONLINE' THEN 1 "
			+ "       WHEN u.user_state = 'AWAY' THEN 2 " + "       ELSE 3 END) ASC, " + " r.rnk_no ASC, "
			+ " e.emp_name ASC";
	// 신규 게시판 유저 생성 - email은 사내 이메일 그대로, 유저 권한은 'NONE'으로 생성
	// 수정된 쿼리 (user를 `user`로 변경)
	String INSERT_USER = "INSERT INTO `user` (user_no, user_state, user_role, user_email, user_pw, emp_no) "
	                   + "VALUES (?, 'OFFLINE', 'NONE', (SELECT emp_email FROM emp WHERE emp_no = ?), ?, ?)";
	// 전체 부서 번호, 부서명 검색
	String SELECT_DEPT_LIST = "SELECT dept_no, dept_name FROM dept";

	// 부서별 직원 검색
	String SELECT_EMP_BY_DEPT = "SELECT u.*, " + "       e.emp_name, r.rnk_name, d.dept_name, e.emp_email "
			+ "FROM user u " + "JOIN emp e ON u.emp_no = e.emp_no " + "JOIN dept d ON e.dept_no = d.dept_no "
			+ "JOIN rnk r ON e.rnk_no = r.rnk_no " + "WHERE e.dept_no = ?";

	String UPDATE_USER_STATE = "UPDATE user SET user_state=? WHERE emp_no=?";

	String UPDATE_USER_PROFILE = "UPDATE user u " + "JOIN emp e ON u.emp_no = e.emp_no " + "SET u.user_email = ?, "
			+ "u.user_note = ?, " + "e.emp_tel = ?, " + "u.user_pw = CASE WHEN ? = '' THEN u.user_pw ELSE ? END "
			+ "WHERE u.user_no = ?";

	public static final String SELECT_EMP_NOT_USER = "SELECT u.user_no, u.emp_no, e.emp_name, d.dept_name, r.rnk_name "
			+ "FROM user u " + "JOIN emp e ON u.emp_no = e.emp_no " + "LEFT JOIN dept d ON e.dept_no = d.dept_no "
			+ "LEFT JOIN rnk r ON e.rnk_no = r.rnk_no " + "WHERE u.user_role = 'NONE' " + "ORDER BY e.emp_name ASC";

	public static final String SELECT_USER_LIST_WITH_INFO = "SELECT u.user_no, e.emp_name, u.user_role, d.dept_name, r.rnk_name \r\n"
			+ "			FROM user u JOIN emp e ON u.emp_no = e.emp_no LEFT JOIN dept d ON e.dept_no = d.dept_no \r\n"
			+ "			LEFT JOIN rnk r ON e.rnk_no = r.rnk_no WHERE u.user_role = 'USER'\r\n"
			+ "			ORDER BY u.user_no ASC";

	public static final String UPDATE_ROLE_TO_USER = "UPDATE user SET user_role = 'USER' WHERE emp_no = ?";

	public static final String UPDATE_ROLE_TO_NONE = "UPDATE user SET user_role = 'NONE' WHERE user_no = ?";

	String UPSERT_AUTH_CODE = "INSERT INTO pw_reset (emp_no, auth_code, expiry_time, try_count, is_verified) "
			+ "VALUES (?, ?, DATE_ADD(NOW(), INTERVAL 5 MINUTE), 0, 0) "
			+ "ON DUPLICATE KEY UPDATE auth_code=VALUES(auth_code), expiry_time=VALUES(expiry_time), try_count=0, is_verified=0";

	// 인증 코드 일치 여부 및 만료 시간 확인
	String SELECT_AUTH_INFO = "SELECT * FROM pw_reset WHERE emp_no = ? AND auth_code = ? AND expiry_time > NOW()";

	// 인증 성공 시 상태 변경
	String UPDATE_VERIFIED_STATUS = "UPDATE pw_reset SET is_verified = 1 WHERE emp_no = ?";

	// 인증 시도 시 횟수 증가 및 시도 시간 업데이트
	String UPDATE_TRY_COUNT = "UPDATE pw_reset SET try_count = try_count + 1, last_try = NOW() WHERE emp_no = ?";

	// 최종 비밀번호 변경 시 인증 완료 여부 확인용
	String CHECK_IS_VERIFIED = "SELECT is_verified FROM pw_reset WHERE emp_no = ? AND is_verified = 1";

	// 비밀번호 변경 완료 후 인증 데이터 삭제 (재사용 방지)
	String DELETE_AUTH_DATA = "DELETE FROM pw_reset WHERE emp_no = ?";

	
	
	//게시판당 글쓰기 권한 가진 유저 리스트 조회
			String BOARD_HAVE_WRITE_ROLE = 
				    "SELECT ba.*, u.user_no, e.emp_name, d.dept_name " +
				    "FROM board_access ba " +
				    "JOIN emp e ON ba.emp_no = e.emp_no " +  
				    "JOIN user u ON e.emp_no = u.emp_no " + 
				    "JOIN dept d ON e.dept_no = d.dept_no " +
				    "WHERE ba.b_no = ? ";
			
			// 사용자가 글쓰기 권한을 가진 게시판 리스트
			 String USER_ADMIN_BOARD_LIST = 
				    "SELECT b.b_no, b.b_name " + 
				    "FROM board b " +
				    "JOIN board_access ba ON b.b_no = ba.b_no " +
				    "WHERE ba.emp_no = ?";
				
			 String ADMIN_ACCESS_BOARD = "CALL sp_assign_board_admin(?, ?)";
			
			// 1. 특정 게시판에 특정 글쓰기 권한 부여 (Insert)
			String GRANT_BOARD_WRITE = "INSERT IGNORE INTO board_access (b_no, emp_no) VALUES (?, ?)";

			
			
			// 1. 특정 유저 권한 박탈 (프로시저 호출)
			String REVOKE_BOARD_ADMIN = "CALL sp_revoke_board_admin(?, ?)";
			
			
			// 게시판 전체 관리자 해제 (NULL로 변경)
			String REMOVE_BOARD_ADMIN = "CALL sp_remove_all_board_admins(?)";
	
	
			
			
			
			
			String DELETE_BOARD_WRITE_REVOKE = "DELETE FROM board_access WHERE b_no =? and emp_no  = ?";
	
	// 추상 메서드 정의
	User getUserInfo(int user_no);

	List<User> getUserList();
}