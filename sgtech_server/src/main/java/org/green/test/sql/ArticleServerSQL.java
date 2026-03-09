package org.green.test.sql;

import java.util.List;

import org.green.test.dto.Article;
import org.green.test.dto.Reply;

public interface ArticleServerSQL {
	// 모든 article
	String SELECT_ARTICLE_ALL = "SELECT * FROM article";
	
	// 1. 특정 게시판의 글 목록 조회 (작성자 상세 정보 포함)
	String SELECT_ARTICLE_LIST_BY_BNO = "SELECT a.*, e.emp_name, r.rnk_name, d.dept_name, "
			+ "(CASE WHEN EXISTS (SELECT 1 FROM art_favorite f WHERE f.art_no = a.art_no AND f.user_no = ?) THEN 'Y' ELSE 'N' END) AS isFav "
			+ "FROM article a " + "JOIN user u ON a.user_no = u.user_no " + "JOIN emp e ON u.emp_no = e.emp_no "
			+ "JOIN rnk r ON e.rnk_no = r.rnk_no " + "JOIN dept d ON e.dept_no = d.dept_no " + "WHERE a.b_no = ? "
			+ "and (art_tag not in('삭제글') OR art_tag IS NULL) " + "ORDER BY a.art_no DESC";

	// 2. 게시글 상세 조회
	String SELECT_ARTICLE_DETAIL = "SELECT a.*, e.emp_name, r.rnk_name, d.dept_name " + "FROM article a "
			+ "JOIN user u ON a.user_no = u.user_no " + "JOIN emp e ON u.emp_no = e.emp_no "
			+ "LEFT JOIN rnk r ON e.rnk_no = r.rnk_no " + 
			"LEFT JOIN dept d ON e.dept_no = d.dept_no " + "WHERE a.art_no = ?";

	// 3. 검색 기능
	String SEARCH_ARTICLE = "SELECT * FROM article WHERE art_title LIKE ? OR art_content LIKE ?";

	// [추가] 4. 게시글 등록 (ServerRepository.insertArticle 에서 사용)
	public static final String INSERT_ARTICLE = "INSERT INTO article (b_no, user_no, art_title, art_content, art_file, art_file_name, art_date, art_is_private, art_tag) "
			+ "VALUES (?, ?, ?, ?, ?, ?, NOW(), ?, ?)";

	// 5. 게시글 사용자 삭제 (ServerRepository.deleteArticle 에서 사용)
	String DELETE_ARTICLE = "UPDATE article SET art_tag = '삭제글' WHERE art_no = ?";

	// 6. 게시글 관리자 완전 삭제
	String MANAGE_DELETE_ARTICLE = "DELETE FROM article WHERE art_no = ?";

	// 7. 게시글 복구 - 다빈
	String MANAGE_ARTICLE_DELETE_RESTORATION = "UPDATE article SET art_tag='null' WHERE art_no=?";

	// 8. 게시글 삭제관리 페이지 List
	String MANAGE_ARTICLE_DELETE_GET = "SELECT a.*, e.emp_name, r.rnk_name, d.dept_name, b.b_name " + "FROM article a "
			+ "JOIN user u ON a.user_no = u.user_no " + "JOIN emp e ON u.emp_no = e.emp_no "
			+ "JOIN rnk r ON e.rnk_no = r.rnk_no " + "JOIN dept d ON e.dept_no = d.dept_no "
			+ "JOIN board b ON b.b_no = a.b_no "
			+ "WHERE a.art_tag IN ('삭제글') " + "ORDER BY a.art_no DESC";
	//추천 및 조회수

	String UPDATE_ART_COUNT = "UPDATE article SET art_count = art_count + 1 WHERE art_no = ?";

	String UPDATE_ART_LIKE = "UPDATE article SET art_like = art_like + 1 WHERE art_no = ?";

	String INSERT_REPORT = "INSERT INTO report (user_no, art_no, b_no, rep_reason) VALUES (?, ?, ?, ?)";

	String SELECT_REPORT = "SELECT r.rep_no,a.art_title,e.emp_name,b.b_name,r.rep_is_read,rep_reason,a.art_no,b.b_no FROM report r JOIN article a ON r.art_no = a.art_no\r\n"
			+ "	JOIN user u ON u.user_no = r.user_no \r\n" + "	JOIN emp e ON u.emp_no = e.emp_no\r\n"
			+ "	JOIN board b ON b.b_no = r.b_no ";

	// -----------댓글------------------
	// 1. 특정 게시글의 댓글 목록 조회 (작성자 정보 포함 - 필요시 JOIN)
	String SELECT_REPLY_LIST_BY_ARTNO = "SELECT r.re_no, r.art_no, r.user_no, r.re_content, r.re_date, "
			+ "       e.emp_name, rk.rnk_name, d.dept_name " + "FROM reply r "
			+ "LEFT JOIN user u ON r.user_no = u.user_no " + "LEFT JOIN emp e ON u.emp_no = e.emp_no "
			+ "LEFT JOIN rnk rk ON e.rnk_no = rk.rnk_no " + "LEFT JOIN dept d ON e.dept_no = d.dept_no "
			+ "WHERE r.art_no = ? " + "AND (r.re_isdeleted = 0 OR r.re_isdeleted IS NULL) " + "ORDER BY r.re_no ASC";

	// 2. 댓글 등록
	// DB의 컬럼명이 reply_date인지 reply_datetime인지 확인이 필요합니다.
	String INSERT_REPLY = "INSERT INTO reply (art_no, user_no, re_content, re_date) " + "VALUES (?, ?, ?, NOW())";

	// 3. 댓글 사용자삭제
	String DELETE_REPLY = "UPDATE reply SET re_isdeleted=1 WHERE re_no = ?";

	// 4. 댓글 관리자 완전삭제
	String MANAGE_DELETE_REPLY = "DELETE FROM reply WHERE re_no = ?";

	// 5. 댓글 관리자 복구
	String DELETE_RESTORATION_REPLY = "UPDATE reply SET re_isdeleted=null WHERE re_no = ?";

	// 6 댓글 수정
	String UPDATE_REPLY = "UPDATE reply SET re_content = ?, re_date = NOW() WHERE re_no = ?";

	// 7. 댓글 삭제관리 페이지 List
	String DELETE_REPLY_LIST = "SELECT\r\n" + "    r.re_no,\r\n" + "    r.art_no,\r\n" + "    r.user_no,\r\n"
			+ "    e.emp_name,\r\n" + "    r.re_date,\r\n" + "    r.re_content,\r\n" + "    rnk.rnk_name,\r\n"+ " b.b_name\r\n "
			+ "    \r\n" + "FROM reply r\r\n" + "JOIN emp e\r\n" + "  ON e.emp_no = r.user_no\r\n" + "JOIN rnk\r\n"
			+ "  ON rnk.rnk_no = e.rnk_no\r\n" + 
			"LEFT JOIN article a ON a.art_no = r.art_no " +
			"LEFT JOIN board b ON b.b_no = a.b_no "   +
			"WHERE (r.re_isdeleted = 1)" + "ORDER BY r.re_no DESC";

	// --- 추상 메서드 ---
	List<Reply> getReplyByArticle(int art_no);

	int insertReply(Reply reply);

	int deleteReply(int reply_no);

	int updateReply(Reply reply);

	// 추상 메서드
	List<Article> getArticlesByBoardNo(int b_no, int user_no);

	Article getArticleDetail(int art_no);

	// 내가쓴글
	String SELECT_ARTICLE_MYPOSTS = "SELECT * FROM ARTICLE where emp_no = ?";

	String SELECT_MY_ARTICLES = "SELECT a.*, e.emp_name, r.rnk_name " + "FROM article a "
			+ "JOIN user u ON a.user_no = ? and a.user_no = u.user_no " + "JOIN emp e ON u.emp_no = e.emp_no "
			+ "LEFT JOIN rnk r ON e.rnk_no = r.rnk_no " + "WHERE art_tag not in('삭제글') "
			+ "or art_tag is null " + "ORDER BY a.art_no DESC";

	String SELECT_MY_REPLIED_ARTICLES = "SELECT DISTINCT a.*, e.emp_name, r.rnk_name " + "FROM article a "
			+ "JOIN reply rep ON rep.user_no = ? AND a.art_no = rep.art_no " + "JOIN user u ON a.user_no = u.user_no " + // 원글 작성자 정보
			"JOIN emp e ON u.emp_no = e.emp_no " + "LEFT JOIN rnk r ON e.rnk_no = r.rnk_no " + "WHERE "
			+ "(rep.re_isdeleted = 0 OR rep.re_isdeleted IS NULL) " + "ORDER BY a.art_no DESC";

	String UPDATE_ARTICLE = "UPDATE article SET art_title = ?, art_content = ? WHERE art_no = ?";
	
	//관리자 신고 관리 SQL
	public static final String UPDATE_REPORT_READ = "UPDATE report SET rep_is_read = 1 WHERE rep_no = ?";
	public static final String DELETE_REPORT = "DELETE FROM report WHERE rep_no = ?";

	String COUNT_ARTICLE = "SELECT COUNT(*) FROM article " + "WHERE b_no = ?";

	String ARTICLES_PER_PAGE = "SELECT a.*, e.emp_name, r.rnk_name, "
			+ "CASE WHEN EXISTS (SELECT 1 FROM art_favorite f WHERE f.art_no = a.art_no AND f.user_no = ?) "
			+ "THEN 'Y' ELSE 'N' END AS isFav " + "FROM article a " + "JOIN user u ON a.user_no = u.user_no "
			+ "JOIN emp e ON u.emp_no = e.emp_no " + "NATURAL JOIN rnk r " + "WHERE a.b_no = ? "
			+ "AND (art_tag != '삭제글' OR art_tag IS NULL) " + "ORDER BY a.art_no DESC ";

	String COUNT_BY_TITLE = "SELECT COUNT(*) FROM article a " + "WHERE b_no = ? "
			+ "AND (art_title LIKE CONCAT('%', ?, '%') " + "OR art_content LIKE CONCAT('%', ?, '%')) "
			+ "AND (art_tag != '삭제글' OR art_tag IS NULL) " + "ORDER BY a.art_no DESC";

	String COUNT_BY_WRITER = "SELECT COUNT(*) FROM article a " + "JOIN user u ON a.user_no = u.user_no "
			+ "JOIN emp e ON u.user_no = e.emp_no " + "WHERE b_no = ? " + "AND (emp_name LIKE CONCAT('%', ?, '%')) "
			+ "AND (art_tag != '삭제글' OR art_tag IS NULL) " + "ORDER BY a.art_no DESC";

	String SEARCH_ARTICLE_BY_TITLE_CONTENT = "SELECT a.*, e.emp_name, r.rnk_name, "
			+ "CASE WHEN EXISTS (SELECT 1 FROM art_favorite f WHERE f.art_no = a.art_no AND f.user_no = ?) THEN 'Y' ELSE 'N' END AS isFav "
			+ "FROM article a " + "JOIN user u ON a.user_no = u.user_no " + "NATURAL JOIN emp e "
			+ "NATURAL JOIN rnk r " + "NATURAL JOIN dept d " + "WHERE a.b_no = ? "
			+ "AND ((art_title LIKE CONCAT('%', ?, '%')) " + "OR (art_content LIKE CONCAT('%', ?, '%'))) "
			+ "AND (art_tag != '삭제글' OR art_tag IS NULL) " + "ORDER BY a.art_no DESC " + "LIMIT ?, ?";

	String SEARCH_ARTICLE_BY_WRITER = "String COUNT_BY_WRITER = \"SELECT COUNT(*) FROM article a \"\r\n"
			+ "  			+ \"JOIN user u ON a.user_no = u.user_no \"\r\n"
			+ "  			+ \"JOIN emp e ON u.emp_no = e.emp_no \"\r\n" + "  			+ \"WHERE b_no = ? \"\r\n"
			+ "  			+ \"AND (emp_name LIKE CONCAT('%', ?, '%')) \"\r\n"
			+ "  			+ \"AND (art_tag != '삭제글' OR art_tag IS NULL) \"\r\n"
			+ "  			+ \"ORDER BY a.art_no DESC";

	String UPDATE_ARTICLE_TAG = "UPDATE article SET art_tag = ? WHERE art_no = ?";

	// 추상메서드 구역
	int updateArticleTag(int art_no, String art_tag);
}
