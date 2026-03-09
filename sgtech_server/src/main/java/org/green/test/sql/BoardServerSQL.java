package org.green.test.sql;

import java.util.List;
import org.green.test.dto.Board;

public interface BoardServerSQL {
	// 1. 사이드바용 전체 게시판 목록 조회
	String SELECT_BOARD_LIST = 
		    "SELECT b.*, e.emp_name AS b_master, e.emp_no " +
		    "FROM board b " +
		    "LEFT JOIN board_access ba ON b.b_no = ba.b_no AND ba.role = 'ADMIN' " +
		    "LEFT JOIN emp e ON ba.emp_no = e.emp_no " +
		    "ORDER BY b.b_no ASC";

	// [추가] 2. 특정 게시판 정보 조회 (현재 게시판 이름 출력용)
	String SELECT_BOARD_INFO = "SELECT * FROM board WHERE b_no = ?";

	String SELECT_BOARD_BY_NO = "SELECT * FROM board WHERE b_no = ?";

	// 수정: b_cate를 받아 섹터별 MAX+1 번호를 생성하는 쿼리
	String INSERT_BOARD = "INSERT INTO board (b_no, b_name, b_cate) " + "VALUES (" + "  (SELECT CASE "
			+ "    WHEN ? = '프로젝트' THEN IFNULL(MAX(b_no), 100) + 1 "
			+ "    WHEN ? = '관리자' THEN IFNULL(MAX(b_no), 1000) + 1 "
			+ "    WHEN ? = '부서별' THEN IFNULL(MAX(b_no), 2000) + 1 " + "    WHEN ? = '자유' THEN 6666 "
			+ "    WHEN ? = '문의' THEN 7777 " + "    WHEN ? = '공지사항' THEN 9999 " + "    ELSE 0 "
			+ "  END FROM board b WHERE b_cate = ?), " + "  ?, ?)";
	
	
	String DELETE_BOARD = "DELETE FROM board WHERE b_no = ?";
	String UPDATE_BOARD_NAME = "UPDATE board SET b_name = ? WHERE b_no = ?";

	String CHECK_WRITE_PERMISSION = "SELECT COUNT(*) FROM board " + "NATURAL JOIN board_access " + "NATURAL JOIN emp "
			+ "WHERE emp_no = ? AND b_no = ?";

	String GET_MYPRJ = "SELECT * from prj NATURAL JOIN emp NATURAL JOIN emp_prj natural join user WHERE user_no = ?";
	
	// 추상 메서드 정의
	List<Board> getBoardList();

	int checkPermission(int emp_no, int b_no);

}