package org.green.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// DTO 패키지명 확인 (Msg, User, Article 등)
import org.green.test.dto.Article;
import org.green.test.dto.Board;
import org.green.test.dto.BoardAccess;
import org.green.test.dto.Dept;
import org.green.test.dto.Msg;
import org.green.test.dto.Prj;
import org.green.test.dto.Reply;
import org.green.test.dto.Report;
import org.green.test.dto.Schedule;
import org.green.test.dto.User;
import org.green.test.paging.PageResponse;
import org.green.test.service.ServerService;
import org.green.test.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ServerController {

	private final ServerService serverService;
	@Autowired
	private JavaMailSender mailSender;

	public ServerController(ServerService serverService) {
		this.serverService = serverService;
	}

	// ================= [1. Board & Article] =================
	@GetMapping("/boards")
	public List<Board> boardList() {
		return serverService.getAllBoards();
	}

	@GetMapping("/users/{user_no}/favorites")
	public List<Article> getFavorites(@PathVariable int user_no) {
		return serverService.getFavoriteArticles(user_no);
	}

	@GetMapping("/boards/info/{b_no}")
	public Board getBoardInfo(@PathVariable("b_no") int b_no) {
		return serverService.getBoardInfo(b_no);
	}

	@PostMapping("/articles/like")
	public String likeArticle(@RequestParam("art_no") int artNo) {
		boolean result = serverService.likeArticle(artNo);
		return result ? "success" : "fail";
	}

	@PostMapping("/articles/report")
	public String reportArticle(@RequestParam("user_no") int userNo, @RequestParam("art_no") int artNo,
			@RequestParam("b_no") int bNo, @RequestParam("rep_reason") String reason) {

		boolean result = serverService.reportArticle(userNo, artNo, bNo, reason);
		return result ? "success" : "fail";
	}

	@GetMapping("/users/list/{user_no}")
	public List<User> getUserList(@PathVariable int user_no) {
		return serverService.getAllUsersSorted(user_no);
	}

	@GetMapping("/articles/{art_no}")
	public Article articleDetail(@PathVariable int art_no) {
		return serverService.getArticle(art_no);
	}

	@GetMapping("/articles")
	public List<Article> articleList() {
		return serverService.getArticleList();
	}

	@GetMapping("/myPosts")
	public List<Article> myPosts(int emp_no) {
		return serverService.getMyPosts(emp_no);
	}

	@PostMapping("/articles/register")
	public int registerArticle(@RequestPart("article") Article article,
			@RequestPart(value = "uploadFile", required = false) MultipartFile file) {
		return serverService.registerArticle(article, file);
	}

	// 파일 다운로드
	@GetMapping("/files/{art_no}")
	public ResponseEntity<byte[]> getFile(@PathVariable int art_no) {
		try {
			Article article = serverService.getArticle(art_no);
			if (article == null || article.getArt_file() == null) {
				return ResponseEntity.notFound().build();
			}
			String encodedFileName = java.net.URLEncoder.encode(article.getArt_file_name(), "UTF-8").replaceAll("\\+",
					"%20");
			return ResponseEntity.ok()
					.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + encodedFileName + "\"")
					.contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
					.body(article.getArt_file());

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/articles/delete")
	public String deleteArticle(@RequestParam("art_no") int art_no) {
		return serverService.deleteArticle(art_no) ? "success" : "fail";
	}

	// ================= [2. Note (Msg)] =================
	@GetMapping("/notes/received/{user_no}")
	public PageResponse messageList(@PathVariable int user_no, @RequestParam int page, @RequestParam int pageSize,
			@RequestParam String searchType, @RequestParam String keyword) {

		int offset = (page - 1) * pageSize;
		List<Msg> list = serverService.getMyMessages(user_no, offset, pageSize, searchType, keyword);
		int totalCount = serverService.getCountMessages(user_no, offset, pageSize, searchType, keyword);
		return new PageResponse(list, totalCount);
	}

	@GetMapping("/notes/detail/{msg_no}")
	public Msg messageDetail(@PathVariable int msg_no) {
		return serverService.getMessage(msg_no);
	}

	@PostMapping("/notes/send")
	public int sendMessage(@RequestBody Msg msg) {
		return serverService.sendMsg(msg) ? 1 : 0;
	}

	// 안 읽은 개수 조회
	@GetMapping("/notes/unread/count/{user_no}")
	public int getUnreadMsgCount(@PathVariable int user_no) {
		return serverService.getUnreadCount(user_no);
	}

	@PostMapping("/notes/read/{msg_no}")
	public int updateReadStatus(@PathVariable int msg_no) {
		return serverService.updateReadStatus(msg_no) ? 1 : 0;
	}

	// ================= [3. User & Favorites] =================
	@GetMapping("/users/all")
	public List<User> getUserList() {
		return serverService.getAllUsers();
	}

	@PostMapping("/users/validate")
	public String joinValidate(@RequestParam("emp_no") int emp_no, @RequestParam("emp_pw") String emp_pw) {

		int checkDup = serverService.getUserProfileByEmpNo(emp_no).size();

		if (checkDup > 0) {
			return "duplicated"; // 이미 존재함
		}

		User newUser = serverService.getEmpProfileByEmpNo(emp_no);

		if (newUser != null) {
			String right_pw = newUser.getEmp_pw();

			if (!emp_pw.equals(right_pw)) { // 비밀번호 불일치
				return "fail";
			}

			return "success"; // 사원 인증 성공
		} else {
			return "fail"; // 사원 인증 실패
		}

	}

	@PostMapping("/join")
	public String join(@RequestParam int emp_no, @RequestParam String user_pw) {
		boolean isSuccess = serverService.insertUser(emp_no, user_pw);

		return isSuccess ? "success" : "fail";
	}

	@PostMapping("/users/{user_no}/fav-toggle")
	public boolean toggleFavorite(@PathVariable int user_no, @RequestParam("artNo") int artNo) {
		return serverService.toggleFavorite(user_no, artNo);
	}

	// ================= [4. Reply (댓글)] =================

	@GetMapping("/articles/{art_no}/reply")
	public List<Reply> getRepliesByArticle(@PathVariable("art_no") int art_no) {
		return serverService.getReplyByArticle(art_no);
	}

	@GetMapping("/users/{user_no}/my-articles")
	public List<Article> getMyArticles(@PathVariable("user_no") int user_no) {
		return serverService.getMyArticles(user_no);
	}

	@GetMapping("/users/{user_no}/my-replied")

	public List<Article> getMyRepliedArticles(@PathVariable("user_no") int user_no) {
		return serverService.getArticlesWithMyReplies(user_no);
	}

	@PostMapping("/articles/update")
	@ResponseBody
	public String updateArticle(@RequestBody Article article, HttpSession session) {
		return serverService.updateArticle(article) ? "success" : "fail";
	}

	// ================== 관리자 페이지 =====================

	@GetMapping("/master/deletedReply/boards/{b_no}")
	public PageResponse masterDeletedReply(@PathVariable("b_no") int b_no, @RequestParam int page,
			@RequestParam int pageSize, @RequestParam String searchType, @RequestParam String keyword) {

		int offset = (page - 1) * pageSize;
		List<Reply> list = serverService.getReplyDeleted(b_no, offset, pageSize, searchType, keyword);
		int totalCount = serverService.getCountReplyDeleted(b_no, offset, pageSize, searchType, keyword);

		return new PageResponse(list, totalCount);

	}

	@GetMapping("/master/deletedReply/article/{art_no}")
	public List<Reply> masterDeletedReply2(@PathVariable("art_no") int artNo) {
		return serverService.getReplyDeleted(artNo);
	}

	@GetMapping("/master/deletedArticle/boards/{b_no}")
	public PageResponse masterDeletedArticle(@PathVariable int b_no, @RequestParam int page, @RequestParam int pageSize,
			@RequestParam String searchType, @RequestParam String keyword) {
		int offset = (page - 1) * pageSize;
		List<Article> list = serverService.getArticleDeleteManageList(b_no, offset, pageSize, searchType, keyword);
		int totalCount = serverService.getCountArticleDeleteManageList(b_no, offset, pageSize, searchType, keyword);

		return new PageResponse(list, totalCount);
	}

	@GetMapping("/master/boards")
	public PageResponse getMasterBoards(@RequestParam int page, @RequestParam int pageSize,
			@RequestParam String searchType, @RequestParam String keyword) {

		int offset = (page - 1) * pageSize;
		List<Board> list = serverService.getAllBoards(offset, pageSize, searchType, keyword);
		int totalCount = serverService.getCountBoards(offset, pageSize, searchType, keyword);

		return new PageResponse(list, totalCount);
	}

	@PostMapping("/master/restorationReply")
	@ResponseBody
	public String restorationReply(@RequestParam("re_no") int reNo) {
		int result = serverService.replyRestoration(reNo);

		return (result > 0) ? "success" : "fail";

	}

	@PostMapping("/master/restorationArticle")
	@ResponseBody
	public String restorationArticle(@RequestParam("art_no") int artNo) {
		int result = serverService.articleRestoration(artNo);
		return (result > 0) ? "success" : "fail";

	}

	@PostMapping("/master/deleteArticle")
	@ResponseBody
	public String manageDeleteArticle(@RequestParam("art_no") int artNo) {
		int result = serverService.manageRemoveArticle(artNo);
		return (result > 0) ? "success" : "fail";
	}

	@PostMapping("/master/deletedReply")
	@ResponseBody
	public String manageDeleteReply(@RequestParam("re_no") int reNo) {

		int result = serverService.manageRemoveReply(reNo);

		return (result > 0) ? "success" : "fail";

	}

	@PostMapping("/articles/reply/delete")
	@ResponseBody
	public int deleteReply(@RequestParam("re_no") int reNo) {
		return serverService.removeReply(reNo);
	}

	// 댓글 수정 0206 -두혁
	@PostMapping("/articles/reply/update")
	public String updateReply(@RequestBody Reply reply) {

		return serverService.updateReply(reply) ? "success" : "fail";
	}

	@GetMapping("/master/reports")
	public PageResponse getReports(@RequestParam int page, @RequestParam int pageSize, @RequestParam String searchType,
			@RequestParam String keyword) {
		int offset = (page - 1) * pageSize;
		List<Report> list = serverService.getReports(offset, pageSize, searchType, keyword);
		int totalCount = serverService.getCountReports(offset, pageSize, searchType, keyword);
		return new PageResponse(list, totalCount);

	}

	// [추가] 신고 읽음 처리 API
	@PostMapping("/master/readReport")
	public String readReport(@RequestParam("rep_no") int rep_no) {
		int result = serverService.updateReportRead(rep_no);
		return result > 0 ? "success" : "fail";
	}

	// [추가] 신고 삭제 API
	@PostMapping("/master/deleteReport")
	public String deleteReport(@RequestParam("rep_no") int rep_no) {
		int result = serverService.deleteReport(rep_no);
		return result > 0 ? "success" : "fail";
	}

	@GetMapping("/dept/list")
	public List<Dept> getDeptList() {
		return serverService.getAllDepts();
	}

	// 유저 찾기
	@GetMapping("/users/dept/{dept_no}")
	public List<User> getUsersByDept(@PathVariable("dept_no") int dept_no) {
		return serverService.getUsersByDept(dept_no);
	}

	@PostMapping("/users/{emp_no}/logout")
	public void userLogout(@PathVariable int emp_no, String user_state) {
		serverService.updateUserState(user_state, emp_no);
	}

	@PostMapping("/users/{emp_no}")
	public User userProfile(@PathVariable int emp_no, String user_pw) {
		List<User> userList = serverService.getUserProfileByEmpNo(emp_no);

		if (userList != null && !userList.isEmpty()) {
			User user = userList.get(0);

			// [중요] 사용자가 입력한 비번(user_pw)을 암호화합니다.
			String encryptedInput = EncryptUtil.hashPassword(user_pw);

			// 암호화된 결과끼리 비교합니다.
			if (encryptedInput.equals(user.getUser_pw())) {
				serverService.updateUserState("ONLINE", emp_no);
				return user; // 로그인 성공!
			}
		}
		return null; // 로그인 실패
	}

	@PostMapping("/master/createBoard")
	@ResponseBody
	public String createBoard(@RequestParam("b_name") String bName, @RequestParam("b_cate") String bCate) { // 1. 카테고리
																											// 파라미터 추가

		if (bName == null || bName.trim().isEmpty()) {
			return "fail";
		}

		try {
			// 2. 서비스 호출 시 bCate를 함께 전달
			int result = serverService.registeBoard(bName, bCate);

			return result > 0 ? "success" : "fail";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@PostMapping("/user/updateMyInfo")
	public String updateMyInfo(@RequestBody User user) {
		try {
			boolean isSuccess = serverService.updateUserProfile(user);
			return isSuccess ? "success" : "fail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@GetMapping("/list")
	public String listPage(@RequestParam(value = "b_no", required = false, defaultValue = "9999") int b_no, Model model,
			@RequestParam(defaultValue = "1", required = true) int page, HttpSession session) {

		User loginUser = (User) session.getAttribute("loginUser");
		int user_no = 0;

		if (loginUser != null) {
			user_no = loginUser.getUser_no();
		} else {
			return "redirect:/login";
		}

		boolean isMaster = "MASTER".equals(loginUser.getUser_role());

		// [수정] 일반 유저는 관리자 게시판(1001~1004) 접근 차단 -> 공지사항(9999)으로 리다이렉트
		if (!isMaster && (b_no >= 1001 && b_no <= 1004)) {
			return "redirect:/list?b_no=9999";
		}

		List<Board> allBoards = serverService.getAllBoards();

		// [수정] 사이드바: 관리자 게시판(1001~1004) 제외하고 표시
		List<Board> sidebarBoards = allBoards.stream().filter(b -> b.getB_no() < 1001 || b.getB_no() > 1004).toList();

		Board currentBoard = allBoards.stream().filter(b -> b.getB_no() == b_no).findFirst().orElse(null);

		if (currentBoard == null)
			return "redirect:/list?b_no=9999";

		// paging size(pageSize)는 20으로 결정났으므로, 20으로 반드시 바꿔야 함.
		int pageSize = 20;
		int offset = (page - 1) * pageSize;
		int totalCount = serverService.getCountArticle(b_no);

		int totalPage = (int) Math.ceil((double) totalCount / pageSize);

		List<Article> articles = serverService.getArticlesPerPage(user_no, b_no, offset, pageSize);

		// [수정] 8888(점심메뉴) API 공지 추가
		if (b_no == 8888) {
			Article apiNotice = new Article();
			apiNotice.setArt_no(0);
			apiNotice.setArt_title("🍴 랜덤 추천 메뉴 (API 연동 예정)");
			apiNotice.setEmp_name("관리자");
			apiNotice.setArt_tag("notice");

			articles.add(0, apiNotice);
		}

		model.addAttribute("boards", sidebarBoards);
		model.addAttribute("currentBoard", currentBoard);
		model.addAttribute("articles", articles);

		// 추가 된 내용.
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount", totalCount);
		return "list";
	}

	//검색
	@GetMapping("/board/list")
	public PageResponse findList(@RequestParam int user_no, @RequestParam int b_no, @RequestParam int page,
			@RequestParam int pageSize, @RequestParam String searchType, @RequestParam String keyword) {

		int offset = (page - 1) * pageSize;
		List<Article> articles = serverService.getArticlesPerPage(user_no, b_no, offset, pageSize, searchType, keyword);
		int totalCount = serverService.getCountArticles(b_no, user_no, offset, pageSize, searchType, keyword);

		return new PageResponse(articles, totalCount);
	}

	@GetMapping("/boards/list/totalCount")
	public int getTotalCount(int b_no, String searchType, String keyword, int page) {
		// 검색 내용이 작성자라면, 수행.
		if ("writer".equals(searchType)) {
			return serverService.countByWriter(b_no, keyword);
		} else if ("title_content".equals(searchType)) {
			// 검색 내용이 제목 + 내용이라면, 수행.
			return serverService.countByTitleContent(b_no, keyword);
			// 제목 + 게시글 내용이 4를 입력 결과, 2개 게시판 존재 확인. = 실제 2라고 출력.
		} else {
			System.out.println("전체 게시판 수: " + serverService.getCountArticle(b_no));
			return serverService.getCountArticle(b_no);
			// 경영 게시판에 6개 게시판 존재 확인. = 실제 6이라고 출력.
		}
	}

	@GetMapping("/users/none")
	public List<Map<String, Object>> getNoneUsers() {
		return serverService.getNoneUserList();
	}

	@GetMapping("/users/registered")
	public List<User> getRegisteredUsers() {
		return serverService.getRegisteredUsers();
	}

	@PostMapping("/master/user/add")
	public String addUser(@RequestParam("emp_no") int emp_no) {
		int result = serverService.registerUser(emp_no);
		return result > 0 ? "success" : "fail";
	}

	@PostMapping("/master/user/remove")
	public String removeUser(@RequestParam("user_no") int user_no) {
		int result = serverService.removeUser(user_no);
		return result > 0 ? "success" : "fail";
	}

	@PostMapping("/pw-reset/send-code")
	public String sendResetCode(@RequestParam("emp_no") int emp_no, @RequestParam("email") String email) {
		// 1. 6자리 랜덤 인증 코드 생성
		String authCode = String.valueOf((int) (Math.random() * 899999) + 100000);

		// 2. DB에 인증 코드 저장 (성공 시 메일 발송)
		if (serverService.registerAuthCode(emp_no, authCode)) {
			try {
				// 3. 실제 이메일 발송 로직
				org.springframework.mail.SimpleMailMessage message = new org.springframework.mail.SimpleMailMessage();
				message.setTo(email);
				message.setSubject("[GreenTest] 비밀번호 재설정 인증 코드입니다.");
				message.setText("인증 번호: [" + authCode + "] \n5분 이내에 입력해 주세요.");
				mailSender.send(message);

				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "mail_error";
			}
		}
		return "fail";
	}

	/**
	 * [비밀번호 찾기 2단계]: 사용자가 입력한 코드 검증
	 */
	@PostMapping("/pw-reset/verify")
	public String verifyCode(@RequestParam("emp_no") int emp_no, @RequestParam("auth_code") String authCode) {
		boolean isVerified = serverService.verifyAuthCode(emp_no, authCode);
		return isVerified ? "success" : "fail";
	}

	/**
	 * [비밀번호 찾기 3단계]: 새로운 비밀번호로 재설정
	 */
	@PostMapping("/pw-reset/complete")
	public String resetPassword(@RequestParam("emp_no") int emp_no, @RequestParam("new_pw") String newPw) {
		boolean result = serverService.resetUserPassword(emp_no, newPw);
		return result ? "success" : "fail";
	}

	@GetMapping("/users/email/{emp_no}")
	public String getUserEmail(@PathVariable("emp_no") int emp_no) {
		List<User> userList = serverService.getUserProfileByEmpNo(emp_no);

		if (userList != null && !userList.isEmpty()) {
			String email = userList.get(0).getUser_email();

			if (email != null && !email.trim().isEmpty()) {
				return email;
			}
		}

		// 데이터를 못 찾았을 경우
		return "not_found";
	}

	@PostMapping("/schedule/add")
	public String addSchedule(@RequestBody Schedule sch) {
		int result = serverService.insertSchedule(sch);
		return result > 0 ? "success" : "fail";
	}

	@GetMapping("/schedule/list")
	public List<Schedule> getSchedules(@RequestParam("user_no") int userNo, @RequestParam("month") String month) {
		return serverService.getScheduleList(userNo, month);
	}

	// 0211 jg
	@GetMapping("/boards/{b_no}/articles")
	public PageResponse articleList(@PathVariable int b_no,
			@RequestParam(value = "user_no", defaultValue = "0") int user_no,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "art_title") String searchType,
			@RequestParam(defaultValue = "") String keyword) {

		int offset = (page - 1) * pageSize;
		List<Article> list = serverService.getArticlesByBoard(b_no, user_no, offset, pageSize, searchType, keyword);
		int totalCount = serverService.getCountArticles(b_no, user_no, offset, pageSize, searchType, keyword);

		return new PageResponse(list, totalCount);
	}

	@PostMapping("/articles/{art_no}/reply")
	public String registerReply(@PathVariable("art_no") int art_no, @RequestBody Reply reply) {
		reply.setArt_no(art_no);
		boolean isSaved = serverService.registerReply(reply);
		if (isSaved) {
			serverService.updateArtTag(art_no, "답변완료");
		}

		return isSaved ? "success" : "fail";
	}

	//게시판관리 - 관리자 지정
	@PostMapping("/master/boards/grant")
	@ResponseBody
	public String selectAdmin(@RequestParam("b_no") int bNo, @RequestParam("user_no") int userNo) {
		// 서비스 호출 시 전달하는 값을 userNo로 명확히 함
		int result = serverService.selectAdmin(bNo, userNo);
		return (result > 0) ? "success" : "fail";
	}

	// 권한관리 - 글쓰기 권한 부여
	@PostMapping("/master/admins/write")
	@ResponseBody
	public String grantAdmin(@RequestParam("b_no") int bNo, @RequestParam("emp_no") int empNo) {

		int result = serverService.grantAdmin(bNo, empNo);

		return (result > 0) ? "success" : "fail";
	}

	/**
	 * 특정 게시판의 글쓰기 목록 조회
	 */
	@GetMapping("/master/boards/{bNo}/admins")
	public List<BoardAccess> getBoardAdmins(@PathVariable("bNo") int bNo) {
		return serverService.boardWriteRoleList(bNo);
	}

	/**
	 * 특정 사원이 글쓰기 권한을 가진 게시판 리스트 조회
	 */
	@GetMapping("/master/emp/{empNo}/admin-boards")
	public List<Board> getEmpAdminBoards(@PathVariable("empNo") int empNo) {
		// SQL: USER_ADMIN_BOARD_LIST 호출
		return serverService.userAdminBoardList(empNo);
	}

	// == 게시판 추가
	// ServerController 클래스 내부의 메서드로 추가
	@PostMapping("/master/boards/add") // 최종 주소는 /api/master/boards/add 가 됩니다.
	@ResponseBody
	public String addBoard(@RequestParam("b_name") String b_name, @RequestParam("b_cate") String b_cate) {
		try {

			// 이미 주입되어 있는 serverService를 사용합니다.
			// 기존에 있는 registeBoard 메서드를 활용하거나, 새로 만드신 insertBoard를 호출하세요.
			int result = serverService.registeBoard(b_name, b_cate);

			if (result > 0) {
				// 게시판 생성 성공 시, 나의 쪽지 DTO(Msg)를 활용해 마스터에게 알림을 보낼 수도 있습니다.
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error: " + e.getMessage();
		}
	}

	// 게시판 이름 수정
	@PostMapping("/master/boards/updateBoardName")
	@ResponseBody
	public String updateBoardName(int b_no, String b_name) {

		int result = serverService.modifyBoardNmae(b_no, b_name);
		return result > 0 ? "success" : "fail";
	}

	// 게시판 완전삭제
	@PostMapping("/master/boards/delete")
	@ResponseBody
	public String deleteBoard(@RequestParam("b_no") int b_no) {
		int result = serverService.removeBoard(b_no);

		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	/**
	 * 특정 게시판의 글쓰기 권한 해제
	 */
	@PostMapping("/master/boards/revoke")
	@ResponseBody
	public String boardAdminRevoke(@RequestParam("b_no") int bNo, @RequestParam("emp_no") int empNo) {
		int result = serverService.adminBoardRevoke(bNo, empNo);
		return (result > 0) ? "success" : "fail";
	}

	@GetMapping("/boards/check-permission")
	public boolean checkBoardPermission(@RequestParam("emp_no") int emp_no, @RequestParam("b_no") int b_no) {
		return serverService.hasWritePermission(emp_no, b_no);
	}

	@GetMapping("/users/myProject")
	public PageResponse getMyProject(@RequestParam("user_no") int user_no) {
		List<Prj> myPrj = serverService.getMyPrj(user_no);
		return new PageResponse(myPrj, myPrj.size());
	}

	@GetMapping("/search/integrated")
	public List<Map<String, Object>> searchIntegrated(@RequestParam("keyword") String keyword) {

		// 1. 모든 데이터 가져오기
		List<Article> articles = serverService.getArticleList();
		List<Board> boards = serverService.getAllBoards();
		List<User> users = serverService.getAllUsers();

		List<Map<String, Object>> resultList = new ArrayList<>();

		// 2. 검색어가 없으면 빈 리스트 리턴
		if (keyword == null || keyword.trim().isEmpty())
			return resultList;

		for (Article art : articles) {

			if ("Y".equals(art.getArt_is_private())) {
				continue;
			}

			// ---------------------------------------------------------

			String writerName = "알수없음";
			String rankName = "";
			if (users != null) {
				for (User u : users) {
					if (u.getUser_no() == art.getUser_no()) {
						writerName = u.getEmp_name();
						rankName = u.getRnk_name();
						break;
					}
				}
			}

			boolean isMatch = (art.getArt_title() != null && art.getArt_title().contains(keyword))
					|| (art.getArt_content() != null && art.getArt_content().contains(keyword))
					|| (writerName.contains(keyword));

			if (isMatch) {
				// 게시판 이름 찾기 (b_no로 b_name 찾기)
				String boardName = "기타";
				if (boards != null) {
					for (Board b : boards) {
						if (b.getB_no() == art.getB_no()) {
							boardName = b.getB_name();
							break;
						}
					}
				}

				Map<String, Object> map = new HashMap<>();
				map.put("art_no", art.getArt_no());
				map.put("art_title", art.getArt_title());
				map.put("art_date", art.getArt_date());
				map.put("b_name", boardName); // 게시판 이름
				map.put("emp_name", writerName); // 작성자 이름
				map.put("rnk_name", rankName); // 직급

				resultList.add(map);
			}
		}
		return resultList;
	}

	@PostMapping("/master/board/adminRemove")
	@ResponseBody
	public String boardAdminRemove(@RequestParam("b_no") int bNo) {
		int result = serverService.boardAdminRemove(bNo);
		return (result > 0) ? "success" : "fail";
	}

	// 특정 게시판 글쓰기 권한 해제
	@PostMapping("/master/boards/revoke1") // 최종: /api/master/boards/revoke1
	@ResponseBody
	public String boardAdminRevokeOne( @RequestParam("b_no") int bNo,
			@RequestParam("emp_no") int empNo) {

		// 실제 DB에서 삭제를 수행하는 서비스 호출
		int result = serverService.adminWriteRevoke(bNo, empNo);

		if (result > 0) {

			return "success";
		} else {
			return "fail";
		}
	}

	@GetMapping("/schedule/remove/{sch_no}")
	public String removeSchedule(@PathVariable int sch_no) {
		int result = serverService.removeSchedule(sch_no);
		return result > 0 ? "success" : "fail";
	}

}