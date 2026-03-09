package org.green.test.repository;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// DTO 위치 확인
import org.green.test.dto.Article;
import org.green.test.dto.Board;
import org.green.test.dto.BoardAccess;
import org.green.test.dto.Dept;
import org.green.test.dto.Msg;
import org.green.test.dto.Prj;
import org.green.test.dto.PwReset;
import org.green.test.dto.Reply;
import org.green.test.dto.Report;
import org.green.test.dto.Schedule;
import org.green.test.dto.User;
import org.green.test.sql.ArticleServerSQL;
import org.green.test.sql.BoardServerSQL;
import org.green.test.sql.MsgServerSQL;
import org.green.test.sql.PagingServerSQL;
import org.green.test.sql.ScheduleServerSQL;
import org.green.test.sql.UserServerSQL;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ServerRepository implements ArticleServerSQL, BoardServerSQL, MsgServerSQL, UserServerSQL {
	private final JdbcTemplate tmp;

	public ServerRepository(JdbcTemplate tmp) {
		this.tmp = tmp;
	}

	// --- [Board 관련] ---
	public List<Board> getBoardList() {
		return tmp.query(BoardServerSQL.SELECT_BOARD_LIST, new BeanPropertyRowMapper<>(Board.class));
	}

	public Board getBoardInfo(int b_no) {
		try {
			return tmp.queryForObject(BoardServerSQL.SELECT_BOARD_BY_NO, new BeanPropertyRowMapper<>(Board.class),
					b_no);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Article> getArticlesByBoardNo(int b_no, int user_no) {
		return tmp.query(ArticleServerSQL.SELECT_ARTICLE_LIST_BY_BNO, (rs, rowNum) -> {
			Article a = new BeanPropertyRowMapper<>(Article.class).mapRow(rs, rowNum);
			String dbVal = rs.getString("isFav");
			a.setIsFav(dbVal);
			return a;
		}, user_no, b_no);
	}

	@Override
	public Article getArticleDetail(int art_no) {
		return tmp.queryForObject(ArticleServerSQL.SELECT_ARTICLE_DETAIL, new BeanPropertyRowMapper<>(Article.class),
				art_no);
	}

	public int insertArticle(Article article) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		tmp.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(ArticleServerSQL.INSERT_ARTICLE,
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, article.getB_no()); // 1. 게시판 번호
			ps.setInt(2, article.getUser_no()); // 2. 회원 번호
			ps.setString(3, article.getArt_title()); // 3. 제목
			ps.setString(4, article.getArt_content()); // 4. 내용

			// 5. 파일 데이터 & 6. 파일명
			if (article.getArt_file() != null) {
				ps.setBytes(5, article.getArt_file());
				ps.setString(6, article.getArt_file_name());
			} else {
				ps.setBytes(5, null);
				ps.setString(6, null);
			}

			ps.setString(7, article.getArt_is_private()); // 7. 비공개 여부
			ps.setString(8, article.getArt_tag()); // 8. 말머리 (태그)

			return ps;
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	public List<Article> getMyPosts(int emp_no) {
		return tmp.query(ArticleServerSQL.SELECT_ARTICLE_MYPOSTS, new BeanPropertyRowMapper<>(Article.class), emp_no);
	}

	// --- [Msg(쪽지) 관련] ---

	@Override
	public List<Msg> getReceivedMessages(int user_no) {
		return tmp.query(MsgServerSQL.SELECT_RECEIVED_MESSAGES, new BeanPropertyRowMapper<>(Msg.class), user_no);
	}

	@Override
	public Msg getMessageDetail(int msg_no) {
		return tmp.queryForObject(MsgServerSQL.SELECT_MESSAGE_DETAIL, new BeanPropertyRowMapper<>(Msg.class), msg_no);
	}

	@Override
	public int sendMessage(Msg msg) {
		// 기존 sender_name, receiver_name 필드에 맞춰 쿼리 파라미터 확인 필요
		return tmp.update(MsgServerSQL.INSERT_SEND_MESSAGE, msg.getMsg_title(), msg.getFrom_id(),
				msg.getReceiver_name(), msg.getMsg_content());
	}

	// 읽지 않은 쪽지 개수 조회할 때 사용
	@Override
	public int getUnreadMsgCount(int user_no) {
		return tmp.queryForObject(MsgServerSQL.SELECT_UNREAD_COUNT, Integer.class, user_no);
	}

	// 쪽지 읽음 상태 변경
	@Override
	public int updateReadStatus(int msg_no) {
		return tmp.update(MsgServerSQL.UPDATE_MESSAGE_READ_STATUS, msg_no);
	}

	// --[댓글관련]

	public List<Reply> getReplyByArticle(int art_no) {
		return tmp.query(ArticleServerSQL.SELECT_REPLY_LIST_BY_ARTNO, (rs, rowNum) -> {
			Reply reply = new Reply();
			reply.setRe_no(rs.getInt("re_no"));
			reply.setArt_no(rs.getInt("art_no"));
			reply.setUser_no(rs.getInt("user_no"));
			reply.setRe_content(rs.getString("re_content"));
			reply.setRe_date(rs.getString("re_date"));
			reply.setEmp_name(rs.getString("emp_name"));
			reply.setRnk_name(rs.getString("rnk_name"));
			return reply;
		}, art_no);
	}

	@Override
	public int insertReply(Reply reply) {
		return tmp.update(ArticleServerSQL.INSERT_REPLY, reply.getArt_no(), reply.getUser_no(), reply.getRe_content());
	}

	// --- [User & 즐겨찾기 관련] ---
	@Override
	public User getUserInfo(int user_no) {
		return tmp.queryForObject(UserServerSQL.SELECT_USER_INFO_WITH_DETAILS, new BeanPropertyRowMapper<>(User.class),
				user_no);
	}

	@Override
	public List<User> getUserList() {
		return tmp.query(UserServerSQL.SELECT_USER_LIST, new BeanPropertyRowMapper<>(User.class));
	}

	// 추가: 나를 제외한 유저 목록
	public List<User> getUserListExceptMe(int my_no) {
		return tmp.query(UserServerSQL.SELECT_USER_LIST_EXCEPT_ME, new BeanPropertyRowMapper<>(User.class), my_no);
	}

	// 추가: 즐겨찾기 관련
	public int checkFavorite(int user_no, int art_no) {
		return tmp.queryForObject(UserServerSQL.CHECK_FAVORITE, Integer.class, user_no, art_no);
	}

	public void addFavorite(int user_no, int art_no) {
		tmp.update(UserServerSQL.INSERT_FAVORITE, user_no, art_no);
	}

	public void removeFavorite(int user_no, int art_no) {
		tmp.update(UserServerSQL.DELETE_FAVORITE, user_no, art_no);
	}

	// ==부서 연관 유저 가져오는 메서드
	// 1. 부서 전체 목록 가져오기
	public List<Dept> getDeptList() {
		return tmp.query(MsgServerSQL.SELECT_DEPT_LIST, new BeanPropertyRowMapper<>(Dept.class));
	}

	// 2. 특정 부서 번호로 사원 리스트 가져오기
	public List<User> getUsersByDeptNo(int dept_no) {
		return tmp.query(MsgServerSQL.SELECT_USERS_BY_DEPT, new BeanPropertyRowMapper<>(User.class), dept_no);
	}

	public List<Article> getArticleList() {
		return tmp.query(ArticleServerSQL.SELECT_ARTICLE_ALL, new BeanPropertyRowMapper<>(Article.class));
	}


	public List<Article> getFavoriteArticles(int user_no) {
		return tmp.query(UserServerSQL.SELECT_FAVORITE, new BeanPropertyRowMapper<>(Article.class), user_no);
	}

	public void increaseHit(int art_no) {
		tmp.update(ArticleServerSQL.UPDATE_ART_COUNT, art_no);
	}

	public int increaseLike(int art_no) {
		return tmp.update(ArticleServerSQL.UPDATE_ART_LIKE, art_no);
	}

	public int insertReport(int user_no, int art_no, int b_no, String reason) {
		return tmp.update(ArticleServerSQL.INSERT_REPORT, user_no, art_no, b_no, reason);
	}

	public List<Report> getReports() {
		return tmp.query(ArticleServerSQL.SELECT_REPORT, new BeanPropertyRowMapper<>(Report.class));
	}

	public List<User> getUserListSorted(int user_no) {
		return tmp.query(UserServerSQL.SELECT_USER_LIST_SORTED, new BeanPropertyRowMapper<>(User.class), user_no);
	}

	public List<Article> getMyArticles(int user_no) {
		return tmp.query(ArticleServerSQL.SELECT_MY_ARTICLES, new BeanPropertyRowMapper<>(Article.class), user_no);
	}

	public List<Article> getArticlesWithMyReplies(int user_no) {
		return tmp.query(ArticleServerSQL.SELECT_MY_REPLIED_ARTICLES, new BeanPropertyRowMapper<>(Article.class),
				user_no);
	}

	public int updateArticle(Article article) {
		return tmp.update(ArticleServerSQL.UPDATE_ARTICLE, article.getArt_title(), article.getArt_content(),
				article.getArt_no());
	}

	//게시판삭제관리

	public List<Article> getArticleDeletedList(int b_no) {
		return tmp.query(ArticleServerSQL.MANAGE_ARTICLE_DELETE_GET, new BeanPropertyRowMapper<>(Article.class));
	}

	public int deleteArticle(int art_no) {
		return tmp.update(ArticleServerSQL.DELETE_ARTICLE, art_no);
	}

	public int articleRestoration(int art_no) {
		return tmp.update(ArticleServerSQL.MANAGE_ARTICLE_DELETE_RESTORATION, art_no);
	}

	public int manageDeleteArticle(int art_no) {
		return tmp.update(ArticleServerSQL.MANAGE_DELETE_ARTICLE, art_no);
	}

	// 댓글삭제관리 리스트

	public List<Reply> getReplyDeletedList(int artNo) {
		return tmp.query(ArticleServerSQL.DELETE_REPLY_LIST, new BeanPropertyRowMapper<>(Reply.class));
	}

	public int replyRestoration(int re_no) {
		return tmp.update(ArticleServerSQL.DELETE_RESTORATION_REPLY, re_no);
	}

	public int deleteReply(int re_no) {
		return tmp.update(ArticleServerSQL.DELETE_REPLY, re_no);
	}

	public int manageDeleteReply(int re_no) {
		return tmp.update(ArticleServerSQL.MANAGE_DELETE_REPLY, re_no);
	}

	@Override
	public int updateReply(Reply reply) {
		return tmp.update(ArticleServerSQL.UPDATE_REPLY, reply.getRe_content(), // 수정할 내용
				reply.getRe_no() // 기준이 될 댓글 번호
		);
	}

	public int insertUser(int emp_no, String user_pw) {
		return tmp.update(UserServerSQL.INSERT_USER, emp_no, emp_no, user_pw, emp_no);
	}

	public List<User> getUserProfileByEmpNo(int emp_no) {
		try {
			return tmp.query(UserServerSQL.SELECT_USER_INFO_BY_EMP_NO, new BeanPropertyRowMapper<>(User.class), emp_no);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<User> getEmpProfileByEmpNo(int emp_no) {
		try {
			return tmp.query(UserServerSQL.SELECT_EMP_INFO_BY_EMP_NO, new BeanPropertyRowMapper<>(User.class), emp_no);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int updateReportRead(int repNo) {
		return tmp.update(ArticleServerSQL.UPDATE_REPORT_READ, repNo);
	}

	public int deleteReport(int repNo) {
		return tmp.update(ArticleServerSQL.DELETE_REPORT, repNo);
	}

	public int updateUserState(String user_state, int emp_no) {
		return tmp.update(UserServerSQL.UPDATE_USER_STATE, user_state, emp_no);
	}

	public int insertBoard(String b_name, String b_cate) {
		try {
			return tmp.update(BoardServerSQL.INSERT_BOARD, b_cate, b_cate, b_cate, b_cate, b_cate, b_cate, b_cate,
					b_name, b_cate);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int deleteBoard(int b_no) {
		return tmp.update(DELETE_BOARD, b_no);
	}

	public int updateBoardName(int b_no, String b_name) {
		return tmp.update(UPDATE_BOARD_NAME, b_name, b_no);
	}

	// 미등록 사원 목록
	// 유저 등록
	public int updateUserProfile(User user) {
		return tmp.update(UserServerSQL.UPDATE_USER_PROFILE, user.getUser_email(), user.getUser_note(),
				user.getEmp_tel(), user.getUser_pw(), user.getUser_pw(), user.getUser_no());
	}

	// [유저 역할 일괄 변경]
	public int updateUserRoleBatch(List<Integer> userNos, String role) {
		if (userNos == null || userNos.isEmpty())
			return 0;

		// IN 절 만들기: (?, ?, ?)
		String inSql = String.join(",", Collections.nCopies(userNos.size(), "?"));

		String sql = "UPDATE user_info SET user_role = ? WHERE user_no IN (" + inSql + ")";

		// 파라미터 배열 만들기
		Object[] params = new Object[userNos.size() + 1];
		params[0] = role; // 첫 번째 물음표는 역할(USER/NONE)
		for (int i = 0; i < userNos.size(); i++) {
			params[i + 1] = userNos.get(i);
		}

		return tmp.update(sql, params);
	}

	public int countArticle(int b_no) {
		return tmp.queryForObject(ArticleServerSQL.COUNT_ARTICLE, Integer.class, b_no);
	}

	public int countByTitle(int b_no, String keyword) {
		return tmp.queryForObject(ArticleServerSQL.COUNT_BY_TITLE, Integer.class, b_no, keyword, keyword);
	}

	public int countByWriter(int b_no, String keyword) {
		return tmp.queryForObject(ArticleServerSQL.COUNT_BY_WRITER, Integer.class, b_no, keyword);
	}

	public List<Article> findArticleByTitleContent(int b_no, String art_title, String art_content, int offset,
			int pageSize, int user_no) {
		return tmp.query(ArticleServerSQL.SEARCH_ARTICLE_BY_TITLE_CONTENT, new BeanPropertyRowMapper<>(Article.class),
				user_no, b_no, art_title, art_content, offset, pageSize);
	}

	public List<Article> findArticleByWriter(int b_no, String emp_name, int offset, int pageSize, int user_no) {
		return tmp.query(ArticleServerSQL.SEARCH_ARTICLE_BY_WRITER, new BeanPropertyRowMapper<>(Article.class), user_no,
				b_no, emp_name, offset, pageSize);
	}

	public List<Map<String, Object>> getNoneUserList() {
		return tmp.queryForList(UserServerSQL.SELECT_EMP_NOT_USER);
	}

	public List<User> getUserListWithInfo() {
		return tmp.query(UserServerSQL.SELECT_USER_LIST_WITH_INFO, new BeanPropertyRowMapper<>(User.class));
	}

	public int registerUser(int emp_no) {
		return tmp.update(UserServerSQL.UPDATE_ROLE_TO_USER, emp_no);
	}

	public int removeUser(int user_no) {
		return tmp.update(UserServerSQL.UPDATE_ROLE_TO_NONE, user_no);
	}

	public int upsertAuthCode(int emp_no, String authCode) {
		return tmp.update(UserServerSQL.UPSERT_AUTH_CODE, emp_no, authCode);
	}

	public PwReset getAuthInfo(int emp_no, String authCode) {
		try {
			return tmp.queryForObject(UserServerSQL.SELECT_AUTH_INFO, new BeanPropertyRowMapper<>(PwReset.class),
					emp_no, authCode);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int setVerified(int emp_no) {
		return tmp.update(UserServerSQL.UPDATE_VERIFIED_STATUS, emp_no);
	}

	public int incrementTryCount(int emp_no) {
		return tmp.update(UserServerSQL.UPDATE_TRY_COUNT, emp_no);
	}

	public int checkIsVerified(int emp_no) {
		try {
			return tmp.queryForObject(UserServerSQL.CHECK_IS_VERIFIED, Integer.class, emp_no);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	public int updateUserPassword(int emp_no, String encodedPw) {
		String sql = "UPDATE user SET user_pw = ? WHERE emp_no = ?";
		return tmp.update(sql, encodedPw, emp_no);
	}

	public int clearAuthData(int emp_no) {
		return tmp.update(UserServerSQL.DELETE_AUTH_DATA, emp_no);
	}

	public int insertSchedule(Schedule sch) {
		return tmp.update(ScheduleServerSQL.INSERT_SCHEDULE, sch.getUser_no(), sch.getSch_date(), sch.getSch_content());
	}

	public List<Schedule> getScheduleList(int userNo, String yearMonth) {
		return tmp.query(ScheduleServerSQL.SELECT_MY_SCHEDULE, new BeanPropertyRowMapper<>(Schedule.class), userNo,
				yearMonth + "%");
	}

	public List<Article> findArticlesPerPage(int user_no, int b_no, int offset, int pageSize) {
		String sql = PagingServerSQL.addPaging(ArticleServerSQL.ARTICLES_PER_PAGE, offset, pageSize);
		return tmp.query(sql, new BeanPropertyRowMapper<>(Article.class), user_no, b_no);
	}

	public List<Board> getBoardList(int offset, int pageSize, String searchType, String keyword) {
		String sql = PagingServerSQL.addSearchType(BoardServerSQL.SELECT_BOARD_LIST, searchType);
		sql = PagingServerSQL.addPaging(sql, offset, pageSize);
		return tmp.query(sql, new BeanPropertyRowMapper<>(Board.class), "%" + keyword + "%");
	}

	public int getCountBoards(int offset, int pageSize, String searchType, String keyword) {

		String sql = PagingServerSQL.addSearchType(BoardServerSQL.SELECT_BOARD_LIST, searchType);
		sql = PagingServerSQL.getCount(sql);
		return tmp.queryForObject(sql, Integer.class, "%" + keyword + "%");
	}

	public List<Article> getArticlesByBoardNo(int b_no, int user_no, int offset, int pageSize, String searchType,
			String keyword) {

		String sql = PagingServerSQL.addSearchType(ArticleServerSQL.SELECT_ARTICLE_LIST_BY_BNO, searchType);
		sql = PagingServerSQL.addPaging(sql, offset, pageSize);
		return tmp.query(sql, (rs, rowNum) -> {
			Article a = new BeanPropertyRowMapper<>(Article.class).mapRow(rs, rowNum);
			String dbVal = rs.getString("isFav");
			a.setIsFav(dbVal);
			return a;
		}, user_no, b_no, "%" + keyword + "%");

	}

	public int updateArticleTag(int art_no, String art_tag) {
		try {
			return tmp.update(ArticleServerSQL.UPDATE_ARTICLE_TAG, art_tag, art_no);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// 게시판관리 - 관리자지정
	public int selectAdmin(int bNo, int empNo) {
		return tmp.update(UserServerSQL.ADMIN_ACCESS_BOARD, bNo, empNo);
	}

	// 1. 게시판 글쓰기 권한 부여
	public int grantAdmin(int bNo, int empNo) {
		return tmp.update(UserServerSQL.GRANT_BOARD_WRITE, bNo, empNo);
	}

	// 2. 특정 게시판 글쓰기 가능 목록 조회
	public List<BoardAccess> boardWriteRole(int bNo) {
		return tmp.query(UserServerSQL.BOARD_HAVE_WRITE_ROLE, (rs, rowNum) -> {
			BoardAccess ba = new BoardAccess();
			// DB 컬럼명과 DTO의 Setter를 1:1로 직접 연결합니다.
			ba.setBa_no(rs.getInt("ba_no"));
			ba.setRole(rs.getString("role"));
			ba.setEmp_no(rs.getInt("emp_no"));
			ba.setB_no(rs.getInt("b_no"));

			// 조인 컬럼들
			ba.setUser_no(rs.getInt("user_no"));
			ba.setEmp_name(rs.getString("emp_name"));
			ba.setDept_name(rs.getString("dept_name"));

			return ba;
		}, bNo);
	}

	// 3. 글쓰기 권한을 가진 게시판 리스트 조회 (0211 수정)
	public List<Board> userAdminBoardList(int empNo) {
		return tmp.query(UserServerSQL.USER_ADMIN_BOARD_LIST, (rs, rowNum) -> {
			Board b = new Board();
			// DB 컬럼명 "b_no"와 "b_name"을 DTO의 세터에 직접 연결
			b.setB_no(rs.getInt("b_no"));
			b.setB_name(rs.getString("b_name"));
			// 만약 Board DTO에 b_master 필드도 쿼리에서 가져온다면 추가:

			return b;
		}, empNo);
	}

	// 4. 글쓰기 권한 해제 (삭제)
	public int revokeAdmin(int bNo, int empNo) {
		return tmp.update(UserServerSQL.REVOKE_BOARD_ADMIN, bNo, empNo);
	}

	@Override
	public int checkPermission(int emp_no, int b_no) {
		return tmp.queryForObject(CHECK_WRITE_PERMISSION, Integer.class, emp_no, b_no);
	}

	public List<Article> findArticlesPerPage(int user_no, int b_no, int offset, int pageSize, String searchType,
			String keyword) {
		String sql = PagingServerSQL.addSearchType(ArticleServerSQL.ARTICLES_PER_PAGE, searchType);
		sql = PagingServerSQL.addPaging(sql, offset, pageSize);
		return tmp.query(sql, new BeanPropertyRowMapper<>(Article.class), user_no, b_no, "%" + keyword + "%");
	}

	public int getCountArticles(int b_no, int user_no, int offset, int pageSize, String searchType, String keyword) {
		String sql = PagingServerSQL.addSearchType(ArticleServerSQL.ARTICLES_PER_PAGE, searchType);
		sql = PagingServerSQL.getCount(sql);
		return tmp.queryForObject(sql, Integer.class, user_no, b_no, "%" + keyword + "%");
	}

	public List<Msg> getReceivedMessages(int user_no, int offset, int pageSize, String searchType, String keyword) {
		String sql = PagingServerSQL.addSearchType(MsgServerSQL.SELECT_RECEIVED_MESSAGES, searchType);
		sql = PagingServerSQL.addPaging(sql, offset, pageSize);
		return tmp.query(sql, new BeanPropertyRowMapper<>(Msg.class), user_no, "%" + keyword + "%");
	}

	public int getCountMessages(int user_no, int offset, int pageSize, String searchType, String keyword) {
		String sql = PagingServerSQL.addSearchType(MsgServerSQL.SELECT_RECEIVED_MESSAGES, searchType);
		sql = PagingServerSQL.getCount(sql);
		return tmp.queryForObject(sql, Integer.class, user_no, "%" + keyword + "%");
	}

	public List<Prj> getMyPrj(int user_no) {
		return tmp.query(BoardServerSQL.GET_MYPRJ, (rs, rowNum) -> {
			Prj p = new Prj();
			p.setPrj_board(rs.getInt("prj_board"));
			p.setPrj_name(rs.getString("prj_name"));
			p.setPrj_priority(rs.getInt("prj_priority"));
			if (rs.getDate("end_date") != null) {
				p.setEnd_date(rs.getDate("end_date").toString());
			}
			return p;
		}, user_no);
	}

	public List<Article> getArticleList(int offset, int pageSize, String searchType, String keyword) {
		// no searchType/keyword use
		String sql = ArticleServerSQL.SELECT_ARTICLE_ALL;
		sql = PagingServerSQL.addPaging(sql, offset, pageSize);
		return tmp.query(sql, new BeanPropertyRowMapper<>(Article.class));
	}

	public int getCountArticleList(int offset, int pageSize, String searchType, String keyword) {
		String sql = ArticleServerSQL.SELECT_ARTICLE_ALL;
		sql = PagingServerSQL.getCount(sql);
		return tmp.queryForObject(sql, Integer.class);
	}

	public List<Article> getArticleDeletedList(int b_no, int offset, int pageSize, String searchType, String keyword) {
		String sql = PagingServerSQL.addSearchType(ArticleServerSQL.MANAGE_ARTICLE_DELETE_GET, searchType);
		sql = PagingServerSQL.addPaging(sql, offset, pageSize);
		return tmp.query(sql, new BeanPropertyRowMapper<>(Article.class), "%" + keyword + "%");
	}

	public int getCountArticleDeletedList(int b_no, int offset, int pageSize, String searchType, String keyword) {
		String sql = PagingServerSQL.addSearchType(ArticleServerSQL.MANAGE_ARTICLE_DELETE_GET, searchType);
		sql = PagingServerSQL.getCount(sql);
		return tmp.queryForObject(sql, Integer.class, "%" + keyword + "%");
	}

	public List<Reply> getReplyDeletedList(int b_no, int offset, int pageSize, String searchType, String keyword) {
		String sql = PagingServerSQL.addSearchType(ArticleServerSQL.DELETE_REPLY_LIST, searchType);
		sql = PagingServerSQL.addPaging(sql, offset, pageSize);
		return tmp.query(sql, new BeanPropertyRowMapper<>(Reply.class), "%" + keyword + "%");
	}

	public int getCountReplyDeletedList(int b_no, int offset, int pageSize, String searchType, String keyword) {
		String sql = PagingServerSQL.addSearchType(ArticleServerSQL.DELETE_REPLY_LIST, searchType);
		sql = PagingServerSQL.getCount(sql);
		return tmp.queryForObject(sql, Integer.class, "%" + keyword + "%");
	}

	public List<Report> getReports(int offset, int pageSize, String searchType, String keyword) {
		String sql = PagingServerSQL.addSearchType(ArticleServerSQL.SELECT_REPORT, searchType);
		sql = PagingServerSQL.addPaging(sql, offset, pageSize);
		return tmp.query(sql, new BeanPropertyRowMapper<>(Report.class), "%" + keyword + "%");
	}

	public int getCountReports(int offset, int pageSize, String searchType, String keyword) {
		String sql = PagingServerSQL.addSearchType(ArticleServerSQL.SELECT_REPORT, searchType);
		sql = PagingServerSQL.getCount(sql);
		return tmp.queryForObject(sql, Integer.class, "%" + keyword + "%");
	}

	public int boardAdminRemove(int bNo) {
		return tmp.update(UserServerSQL.REMOVE_BOARD_ADMIN, bNo);
	}

	public int adminWriteRevoke(int bNo, int empNo) {
		return tmp.update(UserServerSQL.DELETE_BOARD_WRITE_REVOKE, bNo, empNo);
	}

	public int removeSchedule(int sch_no) {
		return tmp.update(ScheduleServerSQL.REMOVE_SCHEDULE, sch_no);
	}
}