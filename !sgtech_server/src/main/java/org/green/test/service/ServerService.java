package org.green.test.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// 패키지명(dto 또는 model) 확인 필요
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
import org.green.test.repository.ServerRepository;
import org.green.test.util.EncryptUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServerService {
	private final ServerRepository serverRepository;

	public ServerService(ServerRepository serverRepository) {
		this.serverRepository = serverRepository;
	}

	public List<Article> getArticlesPerPage(int user_no, int b_no, int offset, int pageSize, String searchType,
			String keyword) {
		return serverRepository.findArticlesPerPage(user_no, b_no, offset, pageSize, searchType, keyword);
	}

	public List<Msg> getMyMessages(int user_no, int offset, int pageSize, String searchType, String keyword) {
		return serverRepository.getReceivedMessages(user_no, offset, pageSize, searchType, keyword);
	}

	public int getCountMessages(int user_no, int offset, int pageSize, String searchType, String keyword) {
		return serverRepository.getCountMessages(user_no, offset, pageSize, searchType, keyword);
	}

	// --- [1. 게시판 & 게시글] ---
	public List<Board> getAllBoards() {
		return serverRepository.getBoardList();
	}

	public List<Article> getFavoriteArticles(int user_no) {
		return serverRepository.getFavoriteArticles(user_no);
	}

	public Article getArticle(int art_no) {
		serverRepository.increaseHit(art_no);
		return serverRepository.getArticleDetail(art_no);
	}

	public boolean likeArticle(int art_no) {
		return serverRepository.increaseLike(art_no) > 0;
	}

	public boolean reportArticle(int user_no, int art_no, int b_no, String reason) {
		return serverRepository.insertReport(user_no, art_no, b_no, reason) > 0;
	}

	public List<Report> getReports() {
		return serverRepository.getReports();
	}

	public List<User> getAllUsersSorted(int my_no) {
		return serverRepository.getUserListSorted(my_no);
	}

	public int registerArticle(Article article, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			try {
				article.setArt_file(file.getBytes());
				article.setArt_file_name(file.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return serverRepository.insertArticle(article);
	}

	public Board getBoardInfo(int b_no) {
		return serverRepository.getBoardInfo(b_no);
	}

	public List<Article> getArticlesByBoard(int b_no, int user_no) {
		return serverRepository.getArticlesByBoardNo(b_no, user_no);
	}

	public List<Article> getArticleList() {
		return serverRepository.getArticleList();
	}

	public List<Article> getMyPosts(int emp_no) {
		return serverRepository.getMyPosts(emp_no);
	}

	//게시글 삭제
	public boolean deleteArticle(int art_no) {
		return serverRepository.deleteArticle(art_no) > 0;
	}

	// --- [2. 쪽지(Msg)] ---
	public List<Msg> getMyMessages(int user_no) {
		return serverRepository.getReceivedMessages(user_no);
	}

	public boolean updateReadStatus(int msg_no) {
		return serverRepository.updateReadStatus(msg_no) > 0;
	}

	public boolean sendMsg(Msg msg) {
		return serverRepository.sendMessage(msg) > 0;
	}

	public Msg getMessage(int msg_no) {
		serverRepository.updateReadStatus(msg_no);
		return serverRepository.getMessageDetail(msg_no);
	}

	// 추가: 안 읽은 쪽지 개수 조회
	public int getUnreadCount(int user_no) {
		return serverRepository.getUnreadMsgCount(user_no);
	}

	// --- [3. 유저 & 즐겨찾기] ---
	public User getUserProfile(int user_no) {
		return serverRepository.getUserInfo(user_no);
	}

	public List<User> getAllUsers() {
		return serverRepository.getUserList();
	}

	// 추가: 나를 제외한 유저 목록 (클라이언트 mainpage용)
	public List<User> getAllUsersExcept(int my_no) {
		return serverRepository.getUserListExceptMe(my_no);
	}

	// 추가: 즐겨찾기 토글
	public boolean toggleFavorite(int user_no, int art_no) {
		// 기존에 즐겨찾기가 있는지 확인 후 insert 또는 delete 수행
		int count = serverRepository.checkFavorite(user_no, art_no);
		if (count > 0) {
			serverRepository.removeFavorite(user_no, art_no);
			return false; // 삭제됨
		} else {
			serverRepository.addFavorite(user_no, art_no);
			return true; // 추가됨
		}
	}

	// ----[4.댓글]
	// 특정 게시글의 댓글 목록 가져오기
	public List<Reply> getReplyByArticle(int art_no) {
		return serverRepository.getReplyByArticle(art_no);
	}

	// 댓글 등록
	public boolean registerReply(Reply reply) {
		// 성공 시 1이 반환되므로 0보다 크면 true
		return serverRepository.insertReply(reply) > 0;
	}

	// 댓글 삭제 (필요 시)
	public boolean deleteReply(int reply_no) {
		return serverRepository.deleteReply(reply_no) > 0;
	}

// ================= [5. 부서 및 부서별 유저 추가] =================

	// 부서 전체 목록 가져오기
	public List<Dept> getAllDepts() {
		return serverRepository.getDeptList();
	}

	// 특정 부서의 사원 목록 가져오기
	public List<User> getUsersByDept(int dept_no) {
		return serverRepository.getUsersByDeptNo(dept_no);
	}

	public List<Article> getMyArticles(int user_no) {
		return serverRepository.getMyArticles(user_no);
	}

	public List<Article> getArticlesWithMyReplies(int user_no) {
		return serverRepository.getArticlesWithMyReplies(user_no);
	}

	public boolean updateArticle(Article article) {
		return serverRepository.updateArticle(article) > 0;
	}

	// 삭제관리 게시글
	public List<Article> getArticleDeleteManageList(int b_no) {
		return serverRepository.getArticleDeletedList(b_no);
	}

	public int articleRestoration(int art_no) {
		return serverRepository.articleRestoration(art_no);
	}

	public int manageRemoveArticle(int art_no) {
		return serverRepository.manageDeleteArticle(art_no);
	}

	// 댓글
	public List<Reply> getReplyDeleted(int b_no) {
		return serverRepository.getReplyDeletedList(b_no);
	}

	public int replyRestoration(int re_no) {
		return serverRepository.replyRestoration(re_no);
	}

	public int manageRemoveReply(int re_no) {
		return serverRepository.manageDeleteReply(re_no);
	}

	public int removeReply(int re_no) {
		return serverRepository.deleteReply(re_no);
	}

	// 댓글 수정
	public boolean updateReply(Reply reply) {
		return serverRepository.updateReply(reply) > 0;
	}

	public User getEmpProfileByEmpNo(int emp_no) {
		List<User> userList = serverRepository.getEmpProfileByEmpNo(emp_no);

		if (userList.isEmpty()) {
			return null;
		} else {
			return userList.get(0);
		}
	}

	public int updateReportRead(int rep_no) {
		return serverRepository.updateReportRead(rep_no);
	}

	public int deleteReport(int rep_no) {
		return serverRepository.deleteReport(rep_no);
	}

	// 암호화
	public boolean insertUser(int emp_no, String user_pw) {
		// 1. EncryptUtil을 사용해서 평문 비밀번호를 암호화(해싱)합니다.
		String encryptedPw = EncryptUtil.hashPassword(user_pw);

		// 2. 암호화된 비밀번호를 레포지토리에 전달합니다.
		// UserServerSQL.INSERT_USER의 두 번째 ? 자리에 encryptedPw가 들어갑니다.
		return serverRepository.insertUser(emp_no, encryptedPw) > 0;
	}

	public List<User> getUserProfileByEmpNo(int emp_no) {
		// TODO Auto-generated method stub
		return serverRepository.getUserProfileByEmpNo(emp_no);
	}

	public int updateUserState(String user_state, int emp_no) {
		return serverRepository.updateUserState(user_state, emp_no);
	}

	public int registeBoard(String b_name, String b_cate) throws IOException {
		return serverRepository.insertBoard(b_name, b_cate);
	}

	public int removeBoard(int b_no) {
		return serverRepository.deleteBoard(b_no);
	}

	public int modifyBoardNmae(int b_no, String b_name) {
		return serverRepository.updateBoardName(b_no, b_name);
	}

	public boolean updateUserProfile(User user) {
		if (user.getUser_pw() != null && !user.getUser_pw().isEmpty()) {
			String encryptedPw = EncryptUtil.hashPassword(user.getUser_pw());
			user.setUser_pw(encryptedPw);
		} else {
			user.setUser_pw("");
		}
		return serverRepository.updateUserProfile(user) > 0;
	}

	public int getCountArticle(int b_no) {
		return serverRepository.countArticle(b_no);
	}

	public List<Article> getArticlesPerPage(int user_no, int b_no, int offset, int pageSize) {
		return serverRepository.findArticlesPerPage(user_no, b_no, offset, pageSize);
	}

	public int countByTitleContent(int b_no, String keyword) {
		return serverRepository.countByTitle(b_no, keyword);
	}

	public int countByWriter(int b_no, String keyword) {
		return serverRepository.countByWriter(b_no, keyword);
	}

	public List<Article> getArticleByTitleContent(int b_no, String art_title, String art_content, int offset,
			int pageSize, int user_no) {
		return serverRepository.findArticleByTitleContent(b_no, art_title, art_content, offset, pageSize, user_no);
	}

	public List<Article> getArticleByWriter(int b_no, String emp_name, int offset, int pageSize, int user_no) {
		return serverRepository.findArticleByWriter(b_no, emp_name, offset, pageSize, user_no);
	}

	public List<Map<String, Object>> getNoneUserList() {
		return serverRepository.getNoneUserList();
	}

	public List<User> getRegisteredUsers() {
		return serverRepository.getUserListWithInfo();
	}

	public int registerUser(int emp_no) {
		return serverRepository.registerUser(emp_no);
	}

	public int removeUser(int user_no) {
		return serverRepository.removeUser(user_no);
	}

	public boolean registerAuthCode(int emp_no, String authCode) {
		return serverRepository.upsertAuthCode(emp_no, authCode) > 0;
	}

	// 2. 인증 코드 검증 로직
	public boolean verifyAuthCode(int emp_no, String inputCode) {
		// 시도 횟수 먼저 증가
		serverRepository.incrementTryCount(emp_no);

		// 코드 일치 및 만료 시간 확인
		PwReset authInfo = serverRepository.getAuthInfo(emp_no, inputCode);

		if (authInfo != null) {
			// 일치하면 인증 완료(is_verified = 1) 처리
			return serverRepository.setVerified(emp_no) > 0;
		}
		return false;
	}

	// 3. 최종 비밀번호 변경
	public boolean resetUserPassword(int emp_no, String newPw) {
		// 인증이 완료된 유저인지 확인
		if (serverRepository.checkIsVerified(emp_no) > 0) {
			// 암호화 후 업데이트
			String encodedPw = EncryptUtil.hashPassword(newPw);
			boolean isUpdated = serverRepository.updateUserPassword(emp_no, encodedPw) > 0;

			if (isUpdated) {
				// 변경 성공 시 보안을 위해 인증 데이터 삭제
				serverRepository.clearAuthData(emp_no);
				return true;
			}
		}
		return false;
	}

	public int insertSchedule(Schedule sch) {
		return serverRepository.insertSchedule(sch);
	}

	public List<Schedule> getScheduleList(int user_no, String yearMonth) {
		return serverRepository.getScheduleList(user_no, yearMonth);
	}

	public List<Board> getAllBoards(int offset, int pageSize, String searchType, String keyword) {
		return serverRepository.getBoardList(offset, pageSize, searchType, keyword);
	}

	public int getCountBoards(int offset, int pageSize, String searchType, String keyword) {
		return serverRepository.getCountBoards(offset, pageSize, searchType, keyword);
	}

	public List<Article> getArticlesByBoard(int b_no, int user_no, int offset, int pageSize, String searchType,
			String keyword) {
		return serverRepository.getArticlesByBoardNo(b_no, user_no, offset, pageSize, searchType, keyword);
	}

	public int getCountArticles(int b_no, int user_no, int offset, int pageSize, String searchType, String keyword) {
		return serverRepository.getCountArticles(b_no, user_no, offset, pageSize, searchType, keyword);
	}

	public void updateArtTag(int art_no, String art_tag) {
		serverRepository.updateArticleTag(art_no, art_tag);
	}

	// 글쓰기 권한 부여된 목록 유저 목록조회
	public List<BoardAccess> boardWriteRoleList(int bNo) {
		return serverRepository.boardWriteRole(bNo);
	}

	// 유저가 가지고 있는 글쓰기 권한의 게시판 목록조회
	public List<Board> userAdminBoardList(int empNo) {
		return serverRepository.userAdminBoardList(empNo);
	}

	// 게시판관리 - 게시판 관리자 지정
	public int selectAdmin(int bNo, int empNo) {
		return serverRepository.selectAdmin(bNo, empNo);
	}

	/**
	 * 권한관리글쓰기 권한 부여
	 */
	public int grantAdmin(int bNo, int empNo) {
		return serverRepository.grantAdmin(bNo, empNo);
	}

	/**
	 * 특정 게시판에서 특정 사원의 관리자 권한 박탈 (삭제) (새로 추가)
	 */
	public int adminBoardRevoke(int bNo, int empNo) {
		return serverRepository.revokeAdmin(bNo, empNo);
	}

	public boolean hasWritePermission(int emp_no, int b_no) {
		int count = serverRepository.checkPermission(emp_no, b_no);
		return count > 0;
	}

	public List<Prj> getMyPrj(int user_no) {
		return serverRepository.getMyPrj(user_no);
	}

	public List<Article> getArticleList(int offset, int pageSize, String searchType, String keyword) {
		return serverRepository.getArticleList(offset, pageSize, searchType, keyword);
	}

	public int getCountArticleList(int offset, int pageSize, String searchType, String keyword) {
		return serverRepository.getCountArticleList(offset, pageSize, searchType, keyword);
	}

	public List<Article> getArticleDeleteManageList(int b_no, int offset, int pageSize, String searchType,
			String keyword) {
		return serverRepository.getArticleDeletedList(b_no, offset, pageSize, searchType, keyword);
	}

	public int getCountArticleDeleteManageList(int b_no, int offset, int pageSize, String searchType, String keyword) {
		return serverRepository.getCountArticleDeletedList(b_no, offset, pageSize, searchType, keyword);
	}

	public List<Reply> getReplyDeleted(int b_no, int offset, int pageSize, String searchType, String keyword) {
		return serverRepository.getReplyDeletedList(b_no, offset, pageSize, searchType, keyword);
	}

	public int getCountReplyDeleted(int b_no, int offset, int pageSize, String searchType, String keyword) {
		return serverRepository.getCountReplyDeletedList(b_no, offset, pageSize, searchType, keyword);
	}

	public List<Report> getReports(int offset, int pageSize, String searchType, String keyword) {
		return serverRepository.getReports(offset, pageSize, searchType, keyword);
	}

	public int getCountReports(int offset, int pageSize, String searchType, String keyword) {
		return serverRepository.getCountReports(offset, pageSize, searchType, keyword);
	}

	public int boardAdminRemove(int bNo) {
		return serverRepository.boardAdminRemove(bNo);
	}

	public int adminWriteRevoke(int bNo, int empNo) {
		return serverRepository.adminWriteRevoke(bNo ,empNo);
	}

	public int removeSchedule(int sch_no) {
		return serverRepository.removeSchedule(sch_no);
	}
}
