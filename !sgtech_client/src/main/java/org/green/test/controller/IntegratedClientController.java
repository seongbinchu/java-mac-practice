package org.green.test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.green.test.dto.Article;
import org.green.test.dto.Board;
import org.green.test.dto.BoardAccess;
import org.green.test.dto.Dept;
import org.green.test.dto.Msg;
import org.green.test.dto.Reply;
import org.green.test.dto.Report;
import org.green.test.dto.Schedule;
import org.green.test.dto.User;
import org.green.test.enums.FreeBoardEnum;
import org.green.test.paging.PageResponse;
import org.green.test.paging.PagingConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class IntegratedClientController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private JavaMailSender mailSender;

	private static final Logger log = LoggerFactory.getLogger(IntegratedClientController.class);
	// 9090 서버의 기본 API 경로
	//private final String API_URL = "http://192.168.0.146:9090/api/";
	private final String API_URL = "http://localhost:9090/api/";

	// ================= [1. 인증 및 메인] =================

	@GetMapping("/")
	public String start() {
		return "html/login";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "html/login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			log.info("사용자 활동 - 로그아웃 시도: 사번={}", loginUser.getEmp_no());
			try {
				MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
				params.add("user_state", "OFFLINE");
				restTemplate.postForObject(API_URL + "/users/" + loginUser.getEmp_no() + "/logout", params, User.class);
			} catch (Exception e) {
				log.error("시스템 장애 - 로그아웃 처리 중 API 오류: 사번={}, 사유={}", loginUser.getEmp_no(), e.getMessage());
			}
		}
		session.invalidate();
		return "html/login";
	}

	@GetMapping("/join")
	public String joinPage(User user) {
		return "html/join";
	}

	@PostMapping("/join/validate")
	@ResponseBody
	public String joinValidate(int emp_no, String emp_pw) {
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("emp_no", emp_no);
		params.add("emp_pw", emp_pw);
		return restTemplate.postForEntity(API_URL + "/users/validate", params, String.class).getBody();
	}

	@PostMapping("/join")
	public String join(User user, RedirectAttributes rttr) {
		log.info("회원가입 요청: 사번={}", user.getEmp_no());

		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("emp_no", user.getEmp_no());
		params.add("user_pw", user.getUser_pw());

		try {
			ResponseEntity<String> result = restTemplate.postForEntity(API_URL + "/join", params, String.class);

			if ("success".equals(result.getBody())) {
				log.info("회원가입 성공: 사번={}", user.getEmp_no());
				// FlashAttribute는 리다이렉트 직후 한 번만 유지되는 세션 기반 데이터입니다.
				rttr.addFlashAttribute("msg", "회원가입이 완료되었습니다. 로그인해 주세요.");
				return "redirect:/login"; // 로그인 매핑 주소로 리다이렉트
			} else {
				log.warn("회원가입 실패: 사번={}", user.getEmp_no());
				rttr.addFlashAttribute("msg", "회원가입에 실패했습니다. 사번 또는 정보를 확인하세요.");
				return "redirect:/join"; // 다시 가입 페이지로
			}
		} catch (Exception e) {
			log.error("API 서버 통신 에러: {}", e.getMessage());
			rttr.addFlashAttribute("msg", "서버 연결 오류가 발생했습니다.");
			return "redirect:/join";
		}
	}

	@PostMapping("/login")
	public String login(@RequestParam("emp_no") int emp_no, @RequestParam("user_pw") String user_pw,
			HttpSession session) {
		log.info("사용자 활동 - 로그인 시도: 사번={}", emp_no);
		try {
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
			params.add("user_pw", user_pw);
			User loginUser = restTemplate.postForObject(API_URL + "/users/" + emp_no, params, User.class);

			if (loginUser != null) {
				if ("NONE".equals(loginUser.getUser_role())) {
					log.warn("사용자 활동 - 로그인 제한(승인대기): 사번={}", emp_no);
					return "redirect:/login?status=pending";
				}
				session.setAttribute("loginUser", loginUser);
				log.info("사용자 활동 - 로그인 성공: 사번={}, 권한={}", loginUser.getEmp_no(), loginUser.getUser_role());
				return "redirect:/mainpage";
			} else {
				log.warn("사용자 활동 - 로그인 실패: 사번={}", emp_no);
				return "redirect:/login?status=fail";
			}
		} catch (Exception e) {
			// [보안] 상세 경로(StackTrace) 노출 차단
			log.error("시스템 장애 - 로그인 처리 오류: 사번={}, 사유={}", emp_no, e.getMessage());
			return "redirect:/login?status=error";
		}
	}

	@GetMapping("/view")
	public String viewPage(@RequestParam("art_no") int art_no, Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		// 1. 비로그인 사용자 차단 로그
		if (loginUser == null) {
			log.warn("비로그인 사용자의 게시글 접근 차단: art_no={}", art_no);
			return "redirect:/login";
		}

		// 게시글 정보 가져오기
		Article article = restTemplate.getForObject(API_URL + "/articles/" + art_no, Article.class);

		// 2. 게시글 존재 여부 확인 (null 체크 로깅)
		if (article == null) {
			log.error("존재하지 않는 게시글 접근 시도: art_no={}, user_no={}", art_no, loginUser.getUser_no());
			return "redirect:/list?error=notfound";
		}

		// 3. 비공개글 권한 체크 및 로그
		if (article.getB_no() == 7777 && "Y".equals(article.getArt_is_private())) {
			boolean isAuthor = (article.getUser_no() == loginUser.getUser_no());
			boolean isMaster = "MASTER".equals(loginUser.getUser_role());
			boolean isAdmin = "ADMIN".equals(loginUser.getUser_role());

			if (!isAuthor && !isMaster && !isAdmin) {
				// 권한 없는 사용자가 비공개글에 접근했을 때 (경고 수준)
				log.warn("비공개 게시글 접근 거부: art_no={}, user_no={}, role={}", art_no, loginUser.getUser_no(),
						loginUser.getUser_role());
				return "redirect:/list?b_no=" + article.getB_no() + "&error=unauthorized";
			}
			log.info("비공개 게시글 특수 권한 열람: art_no={}, user_no={}, role={}", art_no, loginUser.getUser_no(),
					loginUser.getUser_role());
		}

		// 정상적인 열람 로그 (일반 게시글 포함)
		log.info("게시글 열람: art_no={}, b_no={}, user_no={}", art_no, article.getB_no(), loginUser.getUser_no());

		// 댓글 및 게시판 정보 조회
		Reply[] replyArray = restTemplate.getForObject(API_URL + "/articles/" + art_no + "/reply", Reply[].class);
		List<Reply> replyList = (replyArray != null) ? Arrays.asList(replyArray) : new ArrayList<>();
		Board currentBoard = restTemplate.getForObject(API_URL + "/boards/info/" + article.getB_no(), Board.class);

		model.addAttribute("article", article);
		model.addAttribute("replyList", replyList);
		model.addAttribute("currentBoard", currentBoard);

		if (article.getB_no() == 7777) {
			return "html/view-qna";
		}
		return "html/view";
	}

	@GetMapping("/write")
	public String writePage(@RequestParam(value = "b_no", defaultValue = "11") int b_no, Model model,
			HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			log.warn("비로그인 사용자의 글쓰기 페이지 접근 시도 - b_no: {}", b_no);
			return "redirect:/login";
		}

		// 권한 체크 로깅
		if (!"MASTER".equals(loginUser.getUser_role())) {
			String url = API_URL + "/boards/check-permission?emp_no=" + loginUser.getEmp_no() + "&b_no=" + b_no;
			Boolean canWrite = restTemplate.getForObject(url, Boolean.class);

			if ((canWrite == null || !canWrite) && (!FreeBoardEnum.contains(b_no))) { // 6666 - 자유게시판
				log.warn("글쓰기 권한 거부: 사번={}, 게시판={}", loginUser.getEmp_no(), b_no);
				return "redirect:/list?b_no=" + b_no + "&error=unauthorized";
			}
		}

		log.info("글쓰기 페이지 진입: 사번={}, 게시판={}", loginUser.getEmp_no(), b_no);
		Board currentBoard = restTemplate.getForObject(API_URL + "/boards/info/" + b_no, Board.class);
		model.addAttribute("selectedBno", b_no);
		model.addAttribute("currentBoard", currentBoard);
		return "html/write";
	}

	@PostMapping("/write")
	public String registerArticle(@ModelAttribute Article article, @RequestParam("uploadFile") MultipartFile file,
			HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null)
			return "redirect:/login";

		// 저장 전 최종 권한 확인 로깅
		if (!"MASTER".equals(loginUser.getUser_role())) {
			String url = API_URL + "/boards/check-permission?emp_no=" + loginUser.getEmp_no() + "&b_no="
					+ article.getB_no();
			Boolean canWrite = restTemplate.getForObject(url, Boolean.class);

			if ((canWrite == null || !canWrite) && (!FreeBoardEnum.contains(article.getB_no()))) { // 6666 - 자유게시판
				log.error("불법적인 게시글 등록 시도 차단: 사번={}, 게시판={}", loginUser.getEmp_no(), article.getB_no());
				return "redirect:/list?b_no=" + article.getB_no() + "&error=unauthorized_action";
			}
		}

		log.info("게시글 등록 시작: 사번={}, 제목={}, 파일유무={}", loginUser.getEmp_no(), article.getArt_title(), !file.isEmpty());

		article.setUser_no(loginUser.getUser_no());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("article", article);
		if (!file.isEmpty()) {
			body.add("uploadFile", file.getResource());
		}

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		int newArtNo = restTemplate.postForObject(API_URL + "/articles/register", requestEntity, Integer.class);

		if (newArtNo > 0) {
			log.info("게시글 등록 성공: art_no={}, 작성자={}", newArtNo, loginUser.getEmp_no());
			return "redirect:/view?art_no=" + newArtNo;
		} else {
			log.error("게시글 등록 실패: 사번={}", loginUser.getEmp_no());
			return "redirect:/list?b_no=" + article.getB_no();
		}
	}

	@PostMapping("/delete")
	@ResponseBody
	public String deleteArticle(@RequestParam("art_no") int art_no, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		// 삭제는 매우 중요한 작업이므로 사번을 반드시 남깁니다.
		String userIdentifier = (loginUser != null) ? String.valueOf(loginUser.getEmp_no()) : "Unknown";

		log.info("게시글 삭제 요청: art_no={}, 실행자 사번={}", art_no, userIdentifier);

		String result = restTemplate.postForObject(API_URL + "/articles/delete?art_no=" + art_no, null, String.class);
		log.info("게시글 삭제 결과: art_no={}, 결과={}", art_no, result);

		return result;
	}

	@GetMapping("/file/{art_no}")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(@PathVariable int art_no, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		int empNo = (loginUser != null) ? loginUser.getEmp_no() : 0;

		log.info("파일 다운로드 시도: art_no={}, 요청자={}", art_no, empNo);

		return restTemplate.getForEntity(API_URL + "/files/" + art_no, byte[].class);
	}

	// ====================== 추천 및 신고 ======================

	@PostMapping("/api/like")
	@ResponseBody
	public String toggleLike(@RequestParam("art_no") int artNo, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");

		if (user == null) {
			log.warn("비로그인 사용자의 좋아요 시도 차단: art_no={}", artNo);
			return "login_required";
		}

		try {
			log.info("좋아요 토글 요청: 사번={}, art_no={}", user.getEmp_no(), artNo);

			String result = restTemplate.postForObject(API_URL + "/articles/like?art_no=" + artNo, null, String.class);

			log.info("좋아요 결과: art_no={}, 결과={}", artNo, result);
			return result;
		} catch (Exception e) {
			log.error("좋아요 처리 중 통신 에러: art_no={}, 사번={}, error={}", artNo, user.getEmp_no(), e.getMessage());
			return "fail";
		}
	}

	@PostMapping("/api/report")
	@ResponseBody
	public String reportArticle(@RequestParam("art_no") int artNo, @RequestParam("b_no") int bNo,
			@RequestParam("reason") String reason, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");

		if (user == null) {
			log.warn("비로그인 사용자의 신고 시도 차단: art_no={}", artNo);
			return "login_required";
		}

		log.info("게시글 신고 접수: 신고자={}, art_no={}, 사유={}", user.getEmp_no(), artNo, reason);

		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("user_no", user.getUser_no());
		params.add("art_no", artNo);
		params.add("b_no", bNo);
		params.add("rep_reason", reason);

		try {
			String result = restTemplate.postForObject(API_URL + "/articles/report", params, String.class);

			if ("success".equals(result)) {
				log.info("신고 등록 완료: art_no={}, 신고자={}", artNo, user.getEmp_no());
			} else {
				log.warn("신고 등록 실패(서버 응답): art_no={}, 결과={}", artNo, result);
			}
			return result;
		} catch (Exception e) {
			log.error("신고 처리 중 시스템 에러: art_no={}, 신고자={}, error={}", artNo, user.getEmp_no(), e.getMessage());
			return "fail";
		}
	}

	// ================= [3. 쪽지 기능] =================
	@GetMapping("/note")
	public String showMyNotes(HttpSession session, Model model,
			@RequestParam(defaultValue = "msg_title", required = false) String searchType,
			@RequestParam(defaultValue = "", required = true) String keyword,
			@RequestParam(defaultValue = "1", required = true) int page,
			@RequestParam(defaultValue = "10", required = true) int pageSize) {

		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			log.warn("[보안] 비로그인 사용자의 쪽지함 접근 시도");
			return "redirect:/login";
		}

		int userNo = loginUser.getUser_no();
		log.info("[조회] 쪽지함 요청: user_no={}, page={}, keyword='{}', searchType='{}'", userNo, page, keyword, searchType);

		try {
			// 페이징 파라미터 생성
			String params = PagingConstants.getParams(page, pageSize, searchType, keyword);
			String fullUrl = API_URL + "/notes/received/" + userNo + params;

			log.debug("ㄴ API 요청 URL: {}", fullUrl);

			// API 호출 및 결과 파싱
			PageResponse result = restTemplate.getForObject(fullUrl, PageResponse.class);

			if (result == null) {
				log.warn("ㄴ API 응답 결과가 null입니다.");
				model.addAttribute("articles", new ArrayList<>());
				return "html/note";
			}

			List myNotes = result.getContent();
			int totalCount = result.getTotalCount();
			int totalPage = PagingConstants.getTotalPage(totalCount, pageSize);

			log.info("ㄴ 조회 성공: 전체 쪽지 수={}, 현재 페이지 결과={}건", totalCount, myNotes.size());

			// Model 데이터 바인딩
			model.addAttribute("articles", myNotes);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("page", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("keyword", keyword);
			model.addAttribute("searchType", searchType);

		} catch (Exception e) {
			log.error("[장애] 쪽지 목록 로드 중 오류 발생: user_no={}, 사유={}", userNo, e.getMessage());
			model.addAttribute("articles", new ArrayList<>());
		}

		return "html/note";
	}

	@GetMapping("/writenote")
	public String noteWritePage(Model model) {
		log.info("[페이지] 쪽지 작성 페이지 진입");
		try {
			List<Dept> deptList = restTemplate.getForObject(API_URL + "/dept/list", List.class);
			model.addAttribute("deptList", deptList);
			log.info("ㄴ 부서 목록 로드 완료: {}개 부서", (deptList != null ? deptList.size() : 0));
		} catch (Exception e) {
			log.error("ㄴ 부서 목록 로드 실패: {}", e.getMessage());
		}
		return "html/writenote";
	}

	@GetMapping("/replyNote")
	public String noteReplyPage(@RequestParam("receiver_id") String receiver_id, @RequestParam("name") String name,
			Model model) {
		log.info("[페이지] 답장 페이지 진입: 수신 대상자ID={}, 이름={}", receiver_id, name);

		model.addAttribute("targetName", name);
		model.addAttribute("targetId", receiver_id);

		return "html/replynote";
	}

	// [추가]: 모든 페이지에서 공통으로 안 읽은 쪽지 개수를 사용할 수 있도록 설정
	@ModelAttribute("unreadCount")
	public int getUnreadNoteCount(HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null)
			return 0;

		try {
			Integer count = restTemplate.getForObject(API_URL + "/notes/unread/count/" + loginUser.getUser_no(),
					Integer.class);
			return (count != null) ? count : 0;
		} catch (Exception e) {
			// System.err 대신 log.error를 사용하여 파일에 기록합니다.
			log.error("안 읽은 쪽지 개수 조회 실패: user_no={}, error={}", loginUser.getUser_no(), e.getMessage());
			return 0;
		}
	}

	// 읽음 기능 수정
	@GetMapping("/note/view")
	public String noteViewPage(@RequestParam("msg_no") int msg_no, Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		// 1. 쪽지 상세 내용 가져오기
		Msg msg = restTemplate.getForObject(API_URL + "/notes/detail/" + msg_no, Msg.class);

		if (msg != null && loginUser != null) {
			log.info("쪽지 열람: msg_no={}, 수신자={}, 발신자={}", msg_no, loginUser.getEmp_name(), msg.getSender_name());

			// 2. 읽음 상태 업데이트 요청
			try {
				restTemplate.postForObject(API_URL + "/notes/read/" + msg_no, null, String.class);
			} catch (Exception e) {
				log.warn("쪽지 읽음 처리 실패: msg_no={}, error={}", msg_no, e.getMessage());
			}
		}

		model.addAttribute("msg", msg);
		return "html/noteview";
	}

	@PostMapping("/note/send")
	public String sendNote(Msg msg, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			msg.setFrom_id(loginUser.getUser_no());
			msg.setSender_name(loginUser.getEmp_name());

			// 추가: 수신자 이름 필드에 담긴 ID(숫자문자열)를 실제 to_id(int)에 할당
			if (msg.getReceiver_name() != null && !msg.getReceiver_name().isEmpty()) {
				try {
					msg.setTo_id(Integer.parseInt(msg.getReceiver_name()));
				} catch (NumberFormatException e) {
					log.error("수신자 ID 변환 실패: {}", msg.getReceiver_name());
				}
			}
		}

		log.info("쪽지 발송: 발신자={}, 수신자(ID)={}, 제목={}", loginUser.getEmp_name(), msg.getTo_id(), msg.getMsg_title());

		restTemplate.postForEntity(API_URL + "/notes/send", msg, Integer.class);
		return "redirect:/note";
	}

	@PostMapping("/note/sendReply")
	public String sendReply(Msg msg, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			msg.setFrom_id(loginUser.getUser_no());
			msg.setSender_name(loginUser.getEmp_name());

			// 추가: 답장 시에도 수신자 ID 세팅 (receiver_name 필드 활용 시)
			if (msg.getReceiver_name() != null && !msg.getReceiver_name().isEmpty()) {
				try {
					msg.setTo_id(Integer.parseInt(msg.getReceiver_name()));
				} catch (NumberFormatException e) {
					log.error("답장 수신자 ID 변환 실패: {}", msg.getReceiver_name());
				}
			}
		}

		log.info("답장 발송: 발신자={}, 수신자(ID)={}, 제목={}", loginUser.getEmp_name(), msg.getTo_id(), msg.getMsg_title());

		restTemplate.postForEntity(API_URL + "/notes/send", msg, Integer.class);
		return "redirect:/note";
	}

	@GetMapping("/api/users/dept/{dept_no}")
	@ResponseBody
	public List<User> getUsersByDept(@PathVariable("dept_no") int dept_no) {
		User[] users = restTemplate.getForObject(API_URL + "/users/dept/" + dept_no, User[].class);
		return (users != null) ? Arrays.asList(users) : new ArrayList<>();
	}
	// ================= [4. 즐겨찾기 및 마이페이지] =================

	@GetMapping("/favorites")
	public String favorites(Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		// 1. 보안 체크
		if (loginUser == null) {
			log.warn("[보안] 비로그인 사용자의 즐겨찾기 접근 시도");
			return "redirect:/login";
		}

		int userNo = loginUser.getUser_no();
		log.info("[조회] 즐겨찾기 목록 요청: user_no={}, 이름={}", userNo, loginUser.getEmp_name());

		try {
			// 2. API 호출
			String url = API_URL + "/users/" + userNo + "/favorites";
			Article[] articlesArray = restTemplate.getForObject(url, Article[].class);
			List<Article> articles = (articlesArray != null) ? Arrays.asList(articlesArray) : new ArrayList<>();

			log.info("ㄴ 즐겨찾기 로드 완료: {}건", articles.size());

			// 3. [중요] html/list 템플릿에서 요구하는 모든 변수 세팅
			model.addAttribute("articles", articles);

			// 타이틀 표시용
			Board currentBoard = new Board();
			currentBoard.setB_name("⭐ 즐겨찾기 목록");
			model.addAttribute("currentBoard", currentBoard);

			// 페이징 영역 에러 방지용 기본값 (이게 없으면 에러 납니다!)
			model.addAttribute("totalCount", articles.size());
			model.addAttribute("totalPage", 1);
			model.addAttribute("page", 1);
			model.addAttribute("pageSize", 10);
			model.addAttribute("keyword", "");
			model.addAttribute("searchType", "art_title");

		} catch (Exception e) {
			log.error("[장애] 즐겨찾기 조회 실패: {}", e.getMessage());
			model.addAttribute("articles", new ArrayList<>());
			model.addAttribute("totalCount", 0);
			model.addAttribute("totalPage", 0);
		}

		return "html/favorites";
	}

	@PostMapping("/api/fav-toggle")
	@ResponseBody
	public boolean toggleFav(@RequestParam("artNo") int artNo, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			log.warn("비로그인 사용자의 즐겨찾기 토글 시도: artNo={}", artNo);
			return false;
		}

		boolean result = restTemplate.postForObject(
				API_URL + "/users/" + user.getUser_no() + "/fav-toggle?artNo=" + artNo, null, Boolean.class);

		log.info("즐겨찾기 토글: 사번={}, artNo={}, 결과={}", user.getEmp_no(), artNo, result);
		return result;
	}

	@GetMapping("/myPosts")
	public String myPosts(Model model, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		if (user == null)
			return "redirect:/login";

		log.info("내 작성글/댓글 단 글 조회: 사번={}", user.getEmp_no());

		List myArticles = restTemplate.getForObject(API_URL + "/users/" + user.getUser_no() + "/my-articles",
				List.class);
		List myRepliedArticles = restTemplate.getForObject(API_URL + "/users/" + user.getUser_no() + "/my-replied",
				List.class);

		model.addAttribute("articles", myArticles);
		model.addAttribute("myRepliedArticles", myRepliedArticles);
		return "html/myPosts";
	}

	@GetMapping("/edit")
	public String editForm(@RequestParam("art_no") int art_no, Model model, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		log.info("게시글 수정 페이지 진입: art_no={}, 요청자={}", art_no, (user != null ? user.getEmp_no() : "Unknown"));

		Article article = restTemplate.getForObject(API_URL + "/articles/" + art_no, Article.class);
		model.addAttribute("article", article);
		return "html/edit";
	}

	@PostMapping("/articles/update")
	@ResponseBody
	public String updateArticle(@RequestParam("art_no") int artNo, @RequestParam("art_title") String title,
			@RequestParam("art_content") String content, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");

		try {
			log.info("게시글 수정 시도: art_no={}, 수정자={}, 제목={}", artNo, (user != null ? user.getEmp_no() : "Unknown"),
					title);

			Article article = new Article();
			article.setArt_no(artNo);
			article.setArt_title(title);
			article.setArt_content(content);

			restTemplate.postForObject(API_URL + "/articles/update", article, String.class);

			log.info("게시글 수정 완료: art_no={}", artNo);
			return "success";
		} catch (Exception e) {
			log.error("게시글 수정 실패: art_no={}, 사유={}", artNo, e.getMessage());
			return "fail";
		}
	}

	@GetMapping("/master/users")
	public String masterUserList(@RequestParam(value = "b_no", defaultValue = "1002") int b_no, Model model,
			HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		// 권한 부족 접근 시도 기록 (보안상 매우 중요)
		if (loginUser == null || !"MASTER".equals(loginUser.getUser_role())) {
			String attemptUser = (loginUser != null) ? String.valueOf(loginUser.getEmp_no()) : "Anonymous";
			log.warn("관리자 페이지 접근 거부(회원관리): 접근시도자={}", attemptUser);
			return "redirect:/mainpage";
		}

		log.info("관리자 - 회원 관리 페이지 진입: 사번={}", loginUser.getEmp_no());

		Board currentBoard = restTemplate.getForObject(API_URL + "/boards/info/" + b_no, Board.class);
		List userList = restTemplate.getForObject(API_URL + "/users/registered", List.class);
		List noneUserList = restTemplate.getForObject(API_URL + "/users/none", List.class);

		model.addAttribute("userList", userList);
		model.addAttribute("currentBoard", currentBoard);
		model.addAttribute("noneUserList", noneUserList);
		return "html/master-users";
	}

	// 1. [등록] 사원 -> 유저 (오른쪽으로 이동)
	@PostMapping("/master/user/add")
	@ResponseBody
	public String addUser(@RequestParam("emp_no") int empNo, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		// 실행 주체 파악
		String executor = (loginUser != null) ? String.valueOf(loginUser.getEmp_no()) : "Unknown";
		log.info("관리자 - 사용자 계정 생성 시도: 대상 emp_no={}, 실행자 사번={}", empNo, executor);

		try {
			// [수정 포인트] 삭제 기능과 동일하게 MultiValueMap 구성
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("emp_no", String.valueOf(empNo));

			// API 서버(9090)로 데이터 전송
			String result = restTemplate.postForObject(API_URL + "/master/user/add", params, String.class);

			if ("success".equals(result)) {
				log.info("관리자 - 사용자 계정 생성 완료: 대상 emp_no={}", empNo);
			} else {
				log.warn("관리자 - 사용자 계정 생성 실패(서버 응답): 대상 emp_no={}, 결과={}", empNo, result);
			}

			return result;
		} catch (Exception e) {
			log.error("관리자 - 사용자 계정 생성 중 시스템 에러: 대상 emp_no={}, 사유={}", empNo, e.getMessage());
			return "fail";
		}
	}

	@GetMapping("/master/admins")
	public String masterAdmins(@RequestParam(value = "b_no", defaultValue = "1005") int b_no, Model model,
			HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		// loginUser가 없거나, (역할이 MASTER가 아니고 AND ADMIN도 아닐 때) 차단
		if (loginUser == null
				|| (!"MASTER".equals(loginUser.getUser_role()) && !"ADMIN".equals(loginUser.getUser_role()))) {

			log.warn("관리자 권한 없음 - 게시판 설정 접근 차단: 사번={}", (loginUser != null ? loginUser.getEmp_no() : "Guest"));
			return "redirect:/mainpage";
		}

		log.info("관리자 - 권한 관리 페이지 진입: 사번={}", loginUser.getEmp_no());

		Board currentBoard = restTemplate.getForObject(API_URL + "/boards/info/" + b_no, Board.class);
		List userList = restTemplate.getForObject(API_URL + "/users/all", List.class);
		List boardList = restTemplate.getForObject(API_URL + "/boards", List.class);

		model.addAttribute("boardList", boardList);
		model.addAttribute("userList", userList);
		model.addAttribute("currentBoard", currentBoard);
		return "html/master-admins";
	}

	@GetMapping("/master/reports")
	public String masterReportsList(Model model, HttpSession session,
			@RequestParam(defaultValue = "art_title", required = false) String searchType,
			@RequestParam(defaultValue = "", required = true) String keyword,
			@RequestParam(defaultValue = "1", required = true) int page,
			@RequestParam(defaultValue = "10", required = true) int pageSize) {

		User loginUser = (User) session.getAttribute("loginUser");

		// 1. 권한 체크: MASTER도 아니고 ADMIN도 아닐 경우 차단
		if (loginUser == null
				|| (!"MASTER".equals(loginUser.getUser_role()) && !"ADMIN".equals(loginUser.getUser_role()))) {

			log.warn("관리자 페이지 접근 거부(신고관리): 접근시도자={}", (loginUser != null ? loginUser.getEmp_no() : "Anonymous"));
			return "redirect:/mainpage";
		}

		// 2. 진입 로그 (권한 표시)
		log.info("관리자 - 신고 내역 페이지 진입: 사번={}, 권한={}", loginUser.getEmp_no(), loginUser.getUser_role());

		try {
			// 페이징 및 검색 파라미터 생성
			String params = PagingConstants.getParams(page, pageSize, searchType, keyword);

			// API 서버로부터 신고 내역 조회
			// (참고: API 서버 내부에서도 ADMIN일 경우 본인 게시판 신고만 필터링하는 로직이 있으면 좋습니다)
			PageResponse result = restTemplate.getForObject(API_URL + "/master/reports" + params, PageResponse.class);

			List reports = (result != null) ? result.getContent() : new ArrayList();
			int totalCount = (result != null) ? result.getTotalCount() : 0;

			// 페이지네이션 처리
			int totalPage = PagingConstants.getTotalPage(totalCount, pageSize);

			// View로 데이터 전달
			model.addAttribute("reports", reports);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("page", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("keyword", keyword);
			model.addAttribute("searchType", searchType);

		} catch (Exception e) {
			log.error("신고 내역 조회 중 에러 발생: {}", e.getMessage());
			model.addAttribute("reports", new ArrayList<Report>());
		}

		return "html/master-reports";
	}

	@GetMapping("/master/deletedReply")
	public String masterDeletedReply(@RequestParam(value = "b_no", defaultValue = "1005") int b_no,
			@RequestParam(value = "artNo", defaultValue = "0") int artNo, Model model, HttpSession session,
			@RequestParam(defaultValue = "re_content", required = false) String searchType,
			@RequestParam(defaultValue = "", required = true) String keyword,
			@RequestParam(defaultValue = "1", required = true) int page,
			@RequestParam(defaultValue = "10", required = true) int pageSize) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null || !"MASTER".equals(loginUser.getUser_role())) {
			log.warn("관리자 권한 없음 - 삭제된 댓글 목록 접근 거부: 사번={}", (loginUser != null ? loginUser.getEmp_no() : "Anonymous"));
			return "redirect:/mainpage";
		}

		log.info("관리자 - 삭제된 댓글 관리 페이지 진입: 사번={}, artNo={}", loginUser.getEmp_no(), artNo);

		String params = PagingConstants.getParams(page, pageSize, searchType, keyword);

		List boards = restTemplate
				.getForObject(API_URL + "/master/deletedReply/boards/" + b_no + params, PageResponse.class)
				.getContent();

		PageResponse result = restTemplate.getForObject(API_URL + "/master/deletedReply/boards/" + artNo + params,
				PageResponse.class);
		List replies = result.getContent();
		int totalCount = result.getTotalCount();

		int totalPage = PagingConstants.getTotalPage(totalCount, pageSize);

		model.addAttribute("boards", boards);
		model.addAttribute("replies", replies);

		// pagination
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("keyword", keyword);
		model.addAttribute("searchType", searchType);

		return "html/master-deletedReply";
	}

	@GetMapping("/master/deletedArticle")
	public String masterDeletedArticle(@RequestParam(defaultValue = "1004") int b_no,
			@RequestParam(value = "artNo", defaultValue = "0") int artNo, Model model, HttpSession session,
			@RequestParam(defaultValue = "art_title", required = false) String searchType,
			@RequestParam(defaultValue = "", required = true) String keyword,
			@RequestParam(defaultValue = "1", required = true) int page,
			@RequestParam(defaultValue = "10", required = true) int pageSize) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null || !"MASTER".equals(loginUser.getUser_role())) {
			log.warn("관리자 권한 없음 - 삭제된 게시글 목록 접근 거부: 사번={}", (loginUser != null ? loginUser.getEmp_no() : "Anonymous"));
			return "redirect:/mainpage";
		}

		log.info("관리자 - 삭제된 게시글 관리 페이지 진입: 사번={}, b_no={}", loginUser.getEmp_no(), b_no);
		String params = PagingConstants.getParams(page, pageSize, searchType, keyword);

		List boards = restTemplate
				.getForObject(API_URL + "/master/deletedArticle/boards/" + b_no + params, PageResponse.class)
				.getContent();

		PageResponse result = restTemplate.getForObject(API_URL + "/master/deletedArticle/boards/" + artNo + params,
				PageResponse.class);
		List articles = result.getContent();
		int totalCount = result.getTotalCount();

		int totalPage = PagingConstants.getTotalPage(totalCount, pageSize);

		// pagination
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("keyword", keyword);

		model.addAttribute("boards", boards);
		model.addAttribute("articles", articles);

		return "html/master-deletedArticle";
	}

	@PostMapping("/master/restorationReply")
	@ResponseBody
	public String restorationReply(@RequestParam("re_no") int reNo, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		log.info("관리자 - 댓글 복구 시도: re_no={}, 실행자={}", reNo, (loginUser != null ? loginUser.getEmp_no() : "Unknown"));

		String result = restTemplate.postForObject(API_URL + "/master/restorationReply?re_no=" + reNo, null,
				String.class);

		if ("success".equals(result)) {
			log.info("댓글 복구 완료: re_no={}", reNo);
			return "success";
		} else {
			log.error("댓글 복구 실패: re_no={}, 결과={}", reNo, result);
			return "fail";
		}
	}

	@PostMapping("/master/restorationArticle")
	@ResponseBody
	public String restorationArticle(@RequestParam("art_no") int artNo, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		log.info("관리자 - 게시글 복구 시도: art_no={}, 실행자={}", artNo, (loginUser != null ? loginUser.getEmp_no() : "Unknown"));

		String result = restTemplate.postForObject(API_URL + "/master/restorationArticle?art_no=" + artNo, null,
				String.class);

		if ("success".equals(result)) {
			log.info("게시글 복구 완료: art_no={}", artNo);
			return "success";
		} else {
			log.error("게시글 복구 실패: art_no={}, 결과={}", artNo, result);
			return "fail";
		}
	}

	@PostMapping("/master/deleteArticle")
	@ResponseBody
	public String manageDeleteArticle(@RequestParam("art_no") int artNo, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		log.info("관리자 - 게시글 강제 삭제 실행: art_no={}, 실행자={}", artNo,
				(loginUser != null ? loginUser.getEmp_no() : "Unknown"));

		String result = restTemplate.postForObject(API_URL + "/master/deleteArticle?art_no=" + artNo, null,
				String.class);

		if ("success".equals(result)) {
			log.info("게시글 강제 삭제 완료: art_no={}", artNo);
			return "success";
		} else {
			log.error("게시글 강제 삭제 실패: art_no={}, 결과={}", artNo, result);
			return "fail";
		}
	}

	@PostMapping("/master/deleteReply")
	@ResponseBody
	public String manageDeleteReply(@RequestParam("re_no") int reNo, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		log.info("관리자 - 댓글 강제 삭제 실행: re_no={}, 실행자={}", reNo, (loginUser != null ? loginUser.getEmp_no() : "Unknown"));

		String result = restTemplate.postForObject(API_URL + "/master/deletedReply?re_no=" + reNo, null, String.class);

		if ("success".equals(result)) {
			log.info("댓글 강제 삭제 완료: re_no={}", reNo);
			return "success";
		} else {
			log.error("댓글 강제 삭제 실패: re_no={}, 결과={}", reNo, result);
			return "fail";
		}
	}

	// ================= [추가] 관리자 신고 읽음 처리 및 삭제 =================

	@PostMapping("/master/readReport")
	@ResponseBody
	public String readReport(@RequestParam("rep_no") int rep_no, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		try {
			// [로그 기록] 어떤 관리자가 어떤 신고를 확인(읽음) 처리했는지 기록
			log.info("관리자 - 신고 읽음 처리: rep_no={}, 실행자={}", rep_no,
					(loginUser != null ? loginUser.getEmp_no() : "Unknown"));

			String result = restTemplate.postForObject(API_URL + "/master/readReport?rep_no=" + rep_no, null,
					String.class);

			if ("success".equals(result)) {
				return "success";
			} else {
				log.warn("신고 읽음 처리 실패(서버 응답): rep_no={}, 결과={}", rep_no, result);
				return "fail";
			}
		} catch (Exception e) {
			log.error("신고 읽음 처리 중 에러 발생: rep_no={}, 사유={}", rep_no, e.getMessage());
			return "fail";
		}
	}

	@PostMapping("/master/deleteReport")
	@ResponseBody
	public String deleteReport(@RequestParam("rep_no") int repNo, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		try {
			// [로그 기록] 신고 내역 삭제는 데이터가 사라지는 작업이므로 상세히 기록
			log.info("관리자 - 신고 내역 삭제 실행: rep_no={}, 실행자={}", repNo,
					(loginUser != null ? loginUser.getEmp_no() : "Unknown"));

			String result = restTemplate.postForObject(API_URL + "/master/deleteReport?rep_no=" + repNo, null,
					String.class);

			if ("success".equals(result)) {
				log.info("신고 내역 삭제 완료: rep_no={}", repNo);
				return "success";
			} else {
				log.warn("신고 내역 삭제 실패(서버 응답): rep_no={}, 결과={}", repNo, result);
				return "fail";
			}
		} catch (Exception e) {
			log.error("신고 내역 삭제 중 에러 발생: rep_no={}, 사유={}", repNo, e.getMessage());
			return "fail";
		}
	}

	// ================= [댓글 기능 (수정/삭제 Fix)] =================
	@PostMapping("/reply/write")
	@ResponseBody
	public String replyWrite(@RequestParam("art_no") int artNo, @RequestParam("re_content") String content,
			HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			log.warn("비로그인 사용자의 댓글 작성 시도 차단: art_no={}", artNo);
			return "login_required";
		}

		Reply reply = new Reply();
		reply.setArt_no(artNo);
		reply.setRe_content(content);
		reply.setUser_no(loginUser.getUser_no());
		reply.setEmp_name(loginUser.getEmp_name());

		try {
			log.info("댓글 등록 시도: art_no={}, 작성자={}", artNo, loginUser.getEmp_no());

			ResponseEntity<String> response = restTemplate.postForEntity(API_URL + "/articles/" + artNo + "/reply",
					reply, String.class);

			if ("success".equals(response.getBody())) {
				log.info("댓글 등록 성공: art_no={}, 작성자={}", artNo, loginUser.getEmp_no());
				return "success";
			} else {
				log.warn("댓글 등록 실패(서버 응답): art_no={}, 결과={}", artNo, response.getBody());
				return "fail";
			}
		} catch (Exception e) {
			log.error("댓글 등록 중 에러 발생: art_no={}, 사유={}", artNo, e.getMessage());
			return "fail";
		}
	}

	@PostMapping("/reply/update")
	@ResponseBody
	public String replyUpdate(@RequestParam("re_no") int reNo, @RequestParam("re_content") String content,
			HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		try {
			log.info("댓글 수정 시도: re_no={}, 수정자={}", reNo, (loginUser != null ? loginUser.getEmp_no() : "Unknown"));

			String targetUrl = API_URL + "/articles/reply/update";
			Reply reply = new Reply();
			reply.setRe_no(reNo);
			reply.setRe_content(content);
			// DTO 필드 세팅 (필요 시)
			reply.setArt_no(0);
			reply.setUser_no(0);

			ResponseEntity<String> response = restTemplate.postForEntity(targetUrl, reply, String.class);

			log.info("댓글 수정 결과: re_no={}, 결과={}", reNo, response.getBody());
			return response.getBody();
		} catch (Exception e) {
			log.error("댓글 수정 중 에러 발생: re_no={}, 사유={}", reNo, e.getMessage());
			return "fail";
		}
	}

	@PostMapping("/reply/delete")
	@ResponseBody
	public String replyDelete(@RequestParam("re_no") int reNo, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		try {
			log.info("댓글 삭제 요청: re_no={}, 삭제실행자={}", reNo, (loginUser != null ? loginUser.getEmp_no() : "Unknown"));

			restTemplate.postForEntity(API_URL + "/articles/reply/delete?re_no=" + reNo, null, String.class);

			log.info("댓글 삭제 완료: re_no={}", reNo);
			return "success";
		} catch (Exception e) {
			log.error("댓글 삭제 중 에러 발생: re_no={}, 사유={}", reNo, e.getMessage());
			return "fail";
		}
	}

	// ================= [점심메뉴] =================

	@GetMapping("/foodwrite")
	public String foodWriteForm(HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			log.warn("비로그인 사용자의 식단 작성 페이지 접근 차단");
			return "redirect:/login";
		}
		log.info("식단 작성 페이지 진입: 사번={}", loginUser.getEmp_no());
		return "html/foodwrite";
	}

	@PostMapping("/foodwrite")
	public String foodWritePro(@ModelAttribute Article article,
			@RequestParam(value = "uploadFile", required = false) MultipartFile file, HttpSession session)
			throws IOException {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null)
			return "redirect:/login";

		article.setUser_no(loginUser.getUser_no());
		article.setB_no(8888); // 식단 게시판 번호

		log.info("식단 게시글 등록 시도: 작성자={}, 제목={}, 파일유무={}", loginUser.getEmp_no(), article.getArt_title(),
				(file != null && !file.isEmpty()));

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("article", article);
		if (file != null && !file.isEmpty()) {
			body.add("uploadFile", file.getResource());
		}

		try {
			restTemplate.postForEntity(API_URL + "/articles/register", body, String.class);
			log.info("식단 게시글 등록 성공: 작성자={}", loginUser.getEmp_no());
		} catch (Exception e) {
			log.error("식단 게시글 등록 에러: 작성자={}, 사유={}", loginUser.getEmp_no(), e.getMessage());
		}

		return "redirect:/foodlist";
	}

	@GetMapping("/foodview")
	public String foodDetail(@RequestParam("art_no") int art_no, Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		log.info("식단 상세 보기: art_no={}, 조회자={}", art_no, (loginUser != null ? loginUser.getEmp_no() : "Guest"));

		String articleUrl = API_URL + "/articles/" + art_no;
		Article article = restTemplate.getForObject(articleUrl, Article.class);
		model.addAttribute("article", article);

		try {
			String replyUrl = API_URL + "/articles/" + art_no + "/reply";
			Reply[] replyArray = restTemplate.getForObject(replyUrl, Reply[].class);
			model.addAttribute("replyList", replyArray != null ? Arrays.asList(replyArray) : new ArrayList<>());
		} catch (Exception e) {
			log.error("식단 댓글 조회 실패: art_no={}, 사유={}", art_no, e.getMessage());
			model.addAttribute("replyList", new ArrayList<Reply>());
		}
		return "html/foodview";
	}

	@GetMapping("/foodrandomview")
	public String foodRandomView(Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		try {
			int foodBoardNo = 8888;
			String targetUrl = API_URL + "/boards/" + foodBoardNo + "/articles";
			ResponseEntity<Article[]> response = restTemplate.getForEntity(targetUrl, Article[].class);

			if (response.getBody() != null) {
				List<Article> articleList = new ArrayList<>(Arrays.asList(response.getBody()));
				if (!articleList.isEmpty()) {
					Collections.shuffle(articleList);
					Article selectedFood = articleList.get(0);

					// [로그 기록] 어떤 메뉴가 추천되었는지 기록
					log.info("식단 랜덤 추천 실행: 사용자={}, 추천된메뉴={}", (loginUser != null ? loginUser.getEmp_no() : "Guest"),
							selectedFood.getArt_title());

					model.addAttribute("food", selectedFood);
				}
			}
		} catch (Exception e) {
			log.error("식단 랜덤 추천 중 오류 발생: {}", e.getMessage());
		}
		return "html/foodrandomview";
	}

	@ModelAttribute("boards")
	public List<Board> getBoards(HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null)
			return new ArrayList<>();
		try {
			// 모든 페이지에서 공통으로 쓰이는 데이터는 로그를 남기면 양이 너무 많아지므로
			// 에러 상황에 대해서만 기록합니다.
			return restTemplate.getForObject(API_URL + "/boards", List.class);
		} catch (Exception e) {
			log.error("게시판 목록 로드 실패: {}", e.getMessage());
			return new ArrayList<>();
		}
	}

	@ModelAttribute("userList")
	public List<User> getUserList(HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null)
			return new ArrayList<>();
		try {
			User[] userArr = restTemplate.getForObject(API_URL + "/users/list/" + loginUser.getUser_no(), User[].class);
			return (userArr != null) ? Arrays.asList(userArr) : new ArrayList<>();
		} catch (Exception e) {
			log.error("유저 목록 로드 실패 (user_no={}): {}", loginUser.getUser_no(), e.getMessage());
			return new ArrayList<>();
		}
	}

	// Board 영역
	@PostMapping("/master/createBoard")
	@ResponseBody
	public String createBoard(@RequestParam("b_name") String bName, @RequestParam("b_cate") String bCate, // 1. 카테고리
																											// 파라미터 추가
			HttpSession session) {

		User loginUser = (User) session.getAttribute("loginUser");

		if (bName == null || bName.trim().isEmpty()) {
			log.warn("관리자 - 빈 이름의 게시판 생성 시도 차단: 실행자={}", (loginUser != null ? loginUser.getEmp_no() : "Unknown"));
			return "fail";
		}

		try {
			log.info("관리자 - 새 게시판 생성 시도: 이름={}, 카테고리={}, 실행자={}", bName, bCate,
					(loginUser != null ? loginUser.getEmp_no() : "Unknown"));

			String url = API_URL + "/master/createBoard?b_name={bName}&b_cate={bCate}";

			String result = restTemplate.postForObject(url, null, String.class, bName, bCate);

			log.info("관리자 - 게시판 생성 결과: {}", result);
			return result;
		} catch (Exception e) {
			log.error("관리자 - 게시판 생성 중 에러: {}", e.getMessage());
			return "error";
		}
	}

	@PostMapping("/master/updateBoardName")
	@ResponseBody
	public String updateBoardName(@RequestParam("b_no") int b_no, @RequestParam("b_name") String b_name,
			HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		try {
			log.info("관리자 - 게시판 이름 변경 시도: b_no={}, 새 이름={}, 실행자={}", b_no, b_name,
					(loginUser != null ? loginUser.getEmp_no() : "Unknown"));

			String url = API_URL + "/master/updateBoardName?b_no={b_no}&b_name={b_name}";
			String result = restTemplate.postForObject(url, null, String.class, b_no, b_name);

			log.info("관리자 - 게시판 이름 변경 결과: {}", result);
			return result;
		} catch (Exception e) {
			log.error("관리자 - 게시판 이름 변경 중 에러: {}", e.getMessage());
			return "fail";
		}
	}

	@PostMapping("/clientApi/updateMyInfo")
	@ResponseBody
	public String updateMyInfoProc(@RequestBody User user, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		// 1. 비로그인 접근 차단 로그
		if (loginUser == null) {
			log.warn("비로그인 사용자의 정보 수정 시도 차단");
			return "fail";
		}

		String url = API_URL + "/user/updateMyInfo";
		log.info("내 정보 수정 시도: 사번={}, 변경내용(이메일={}, 전화={})", loginUser.getEmp_no(), user.getUser_email(),
				user.getEmp_tel());

		try {
			ResponseEntity<String> response = restTemplate.postForEntity(url, user, String.class);

			if ("success".equals(response.getBody())) {
				// 2. 세션 정보 동기화 로그
				loginUser.setUser_email(user.getUser_email());
				loginUser.setUser_note(user.getUser_note());
				loginUser.setEmp_tel(user.getEmp_tel());

				log.info("내 정보 수정 및 세션 갱신 완료: 사번={}", loginUser.getEmp_no());
			} else {
				log.warn("내 정보 수정 실패(서버 응답): 사번={}, 결과={}", loginUser.getEmp_no(), response.getBody());
			}
			return response.getBody();

		} catch (Exception e) {
			log.error("내 정보 수정 중 통신 에러: 사번={}, 사유={}", loginUser.getEmp_no(), e.getMessage());
			return "fail";
		}
	}

	// ======================
	@GetMapping("/list")
	public String listPage(@RequestParam(value = "b_no", defaultValue = "11") int b_no, Model model,
			HttpSession session, @RequestParam(defaultValue = "art_title", required = false) String searchType,
			@RequestParam(defaultValue = "", required = true) String keyword,
			@RequestParam(defaultValue = "1", required = true) int page,
			@RequestParam(defaultValue = "10", required = true) int pageSize) {

		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			log.warn("[보안] 비로그인 사용자의 게시판 접근 시도 (b_no: {})", b_no);
			return "redirect:/login";
		}

		int user_no = loginUser.getUser_no();
		log.info("[조회] 게시판 목록 요청: b_no={}, user_no={}, page={}, keyword='{}'", b_no, user_no, page, keyword);

		try {
			// 1. 페이징 및 검색 파라미터 생성
			String params = PagingConstants.getParams(page, pageSize, searchType, keyword);
			String listUrl = API_URL + "/board/list" + params + "&user_no=" + user_no + "&b_no=" + b_no;
			log.debug("ㄴ 게시글 목록 API 요청: {}", listUrl);

			// 2. 게시글 데이터 로드
			PageResponse result = restTemplate.getForObject(listUrl, PageResponse.class);

			List articles = new ArrayList<>();
			int totalCount = 0;

			if (result != null) {
				articles = result.getContent();
				totalCount = result.getTotalCount();
				log.info("ㄴ 게시글 로드 성공: 전체 {}건, 현재 페이지 {}건", totalCount, articles.size());
			} else {
				log.warn("ㄴ 게시글 데이터 로드 실패 (API 응답 null)");
			}

			// 3. 게시판 정보(제목 등) 로드
			Object currentBoard = restTemplate.getForObject(API_URL + "/boards/info/" + b_no, Object.class);
			log.debug("ㄴ 게시판 정보 로드 완료: {}", currentBoard);

			// 4. 모델 데이터 바인딩
			int totalPage = PagingConstants.getTotalPage(totalCount, pageSize);

			model.addAttribute("currentBoard", currentBoard);
			model.addAttribute("b_no", b_no);
			model.addAttribute("articles", articles);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("page", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("keyword", keyword);
			model.addAttribute("searchType", searchType);

		} catch (Exception e) {
			log.error("[장애] 게시판 목록 조회 중 오류 발생: b_no={}, 사유={}", b_no, e.getMessage());
			// 장애 발생 시 기본값 세팅으로 페이지 깨짐 방지
			model.addAttribute("articles", new ArrayList<>());
			model.addAttribute("totalCount", 0);
		}

		return "html/list";
	}

	@PostMapping("/master/users/changeRole")
	@ResponseBody
	public String changeUserRole(@RequestBody Map<String, Object> payload, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		// 3. 권한 변경 로그 (매우 중요)
		// payload 내용: 보통 {user_no: 123, role: 'ADMIN'} 식의 데이터
		log.info("관리자 - 사용자 권한 변경 시도: 실행자={}, 변경내역={}", (loginUser != null ? loginUser.getEmp_no() : "Unknown"),
				payload.toString());

		try {
			String result = restTemplate.postForObject(API_URL + "/users/role/update", payload, String.class);

			if ("success".equals(result)) {
				log.info("관리자 - 사용자 권한 변경 완료: 대상 데이터={}", payload.toString());
			} else {
				log.warn("관리자 - 사용자 권한 변경 실패(서버 응답): 결과={}", result);
			}
			return result;
		} catch (Exception e) {
			log.error("관리자 - 사용자 권한 변경 중 시스템 에러: 사유={}", e.getMessage());
			return "fail";
		}
	}

	// 2. [해제] 유저 -> 사원 (왼쪽으로 이동)
	@PostMapping("/master/user/remove")
	@ResponseBody
	public String removeUser(@RequestParam("user_no") int userNo, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		// 1. 실행 주체 파악 (보안 감사용)
		String executor = (loginUser != null) ? String.valueOf(loginUser.getEmp_no()) : "Unknown";
		log.info("관리자 - 사용자 계정 삭제 시도: 대상 user_no={}, 실행자 사번={}", userNo, executor);

		try {
			// 서버(9090)로 user_no 전송
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("user_no", String.valueOf(userNo));

			String result = restTemplate.postForObject(API_URL + "/master/user/remove", params, String.class);

			// 2. 실행 결과 기록
			if ("success".equals(result)) {
				log.info("관리자 - 사용자 계정 삭제 완료: 대상 user_no={}", userNo);
			} else {
				log.warn("관리자 - 사용자 계정 삭제 실패(서버 응답): 대상 user_no={}, 결과={}", userNo, result);
			}

			return result;
		} catch (Exception e) {
			// 3. 시스템 에러 기록
			log.error("관리자 - 사용자 계정 삭제 중 시스템 에러: 대상 user_no={}, 사유={}", userNo, e.getMessage());
			return "fail";
		}
	}

	@GetMapping("/pw-reset/get-email/{emp_no}")
	@ResponseBody
	public String getEmail(@PathVariable int emp_no) {
		String url = API_URL + "users/email/" + emp_no;
		try {
			log.info("비밀번호 재설정 - 이메일 조회 시도: 사번={}", emp_no);
			String email = restTemplate.getForObject(url, String.class);

			if (email == null || email.isEmpty()) {
				log.warn("비밀번호 재설정 - 이메일 조회 실패(미등록 사번): 사번={}", emp_no);
				return "not_found";
			}
			return email;
		} catch (Exception e) {
			log.error("비밀번호 재설정 - 이메일 조회 중 에러: 사번={}, 사유={}", emp_no, e.getMessage());
			return "not_found";
		}
	}

	@PostMapping("/pw-reset/complete")
	@ResponseBody
	public String resetPassword(@RequestParam("emp_no") int emp_no, @RequestParam("new_pw") String newPw) {
		log.info("비밀번호 최종 변경 시도: 사번={}", emp_no);

		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("emp_no", emp_no);
		params.add("new_pw", newPw);

		try {
			String result = restTemplate.postForObject(API_URL + "pw-reset/complete", params, String.class);

			if ("success".equals(result)) {
				log.info("비밀번호 최종 변경 성공: 사번={}", emp_no);
			} else {
				log.warn("비밀번호 최종 변경 실패: 사번={}, 결과={}", emp_no, result);
			}
			return result;
		} catch (Exception e) {
			log.error("비밀번호 최종 변경 에러: 사번={}, 사유={}", emp_no, e.getMessage());
			return "fail";
		}
	}

	@GetMapping("/pw-reset")
	public String pwResetPage() {
		return "html/pw-reset";
	}

	@PostMapping("/pw-reset/send-code")
	@ResponseBody
	public String sendResetCode(@RequestParam("emp_no") int emp_no, @RequestParam("email") String email) {
		log.info("비밀번호 재설정 인증코드 발송 요청: 사번={}, 이메일={}", emp_no, email);

		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("emp_no", emp_no);
		params.add("email", email);

		try {
			String result = restTemplate.postForObject(API_URL + "pw-reset/send-code", params, String.class);
			log.info("비밀번호 인증코드 발송 결과: 사번={}, 결과={}", emp_no, result);
			return result;
		} catch (Exception e) {
			log.error("비밀번호 인증코드 발송 에러: 사번={}, 사유={}", emp_no, e.getMessage());
			return "fail";
		}
	}

	@PostMapping("/pw-reset/verify")
	@ResponseBody
	public String verifyCode(@RequestParam("emp_no") int emp_no, @RequestParam("auth_code") String authCode) {
		log.info("비밀번호 인증코드 검증 시도: 사번={}, 입력코드={}", emp_no, authCode);

		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("emp_no", emp_no);
		params.add("auth_code", authCode);

		try {
			String result = restTemplate.postForObject(API_URL + "pw-reset/verify", params, String.class);

			if ("success".equals(result)) {
				log.info("비밀번호 인증코드 검증 성공: 사번={}", emp_no);
			} else {
				log.warn("비밀번호 인증코드 검증 실패: 사번={}, 입력코드={}", emp_no, authCode);
			}
			return result;
		} catch (Exception e) {
			log.error("비밀번호 인증코드 검증 에러: 사번={}, 사유={}", emp_no, e.getMessage());
			return "fail";
		}
	}
	// ================= [스케쥴] =================

	@PostMapping("/schedule/add")
	@ResponseBody
	public String addSchedule(@RequestParam("date") String date, @RequestParam("content") String content,
			HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		// 1. 비로그인 접근 차단 로그
		if (loginUser == null) {
			log.warn("비로그인 사용자의 일정 추가 시도 차단: date={}", date);
			return "fail";
		}

		log.info("일정 추가 시도: 사번={}, 날짜={}, 내용={}", loginUser.getEmp_no(), date, content);

		Schedule sch = new Schedule();
		sch.setUser_no(loginUser.getUser_no());
		sch.setSch_date(date);
		sch.setSch_content(content);

		try {
			String result = restTemplate.postForObject(API_URL + "/schedule/add", sch, String.class);

			if ("success".equals(result)) {
				log.info("일정 추가 성공: 사번={}, 날짜={}", loginUser.getEmp_no(), date);
			} else {
				log.warn("일정 추가 실패(서버 응답): 사번={}, 결과={}", loginUser.getEmp_no(), result);
			}
			return result;
		} catch (Exception e) {
			log.error("일정 추가 중 통신 에러: 사번={}, 사유={}", loginUser.getEmp_no(), e.getMessage());
			return "fail";
		}
	}

	@GetMapping("/schedule/my")
	@ResponseBody
	public List<Schedule> getMySchedules(@RequestParam("yearMonth") String yearMonth, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			return new ArrayList<>();
		}
		log.info("개인 일정 조회: 사번={}, 조회월={}", loginUser.getEmp_no(), yearMonth);
		try {
			Schedule[] arr = restTemplate.getForObject(
					API_URL + "/schedule/list?user_no=" + loginUser.getUser_no() + "&month=" + yearMonth,
					Schedule[].class);
			return (arr != null) ? Arrays.asList(arr) : new ArrayList<>();
		} catch (Exception e) {
			log.error("일정 조회 중 시스템 에러: 사번={}, 조회월={}, 사유={}", loginUser.getEmp_no(), yearMonth, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	
	//mainpage load
	@GetMapping("/mainpage")
	public String mainPage(Model model, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			log.warn("[보안] 비로그인 사용자의 메인페이지 접근 시도 - 리다이렉트 수행");
			return "redirect:/login";
		}
		int userNo = loginUser.getUser_no();
		String userRole = loginUser.getUser_role();
		log.info("[접속] 메인페이지 진입: 사번={}, 이름={}, 권한={}", loginUser.getEmp_no(), loginUser.getEmp_name(), userRole);
		if (loginUser.getEmp_photo() != null && loginUser.getEmp_photo().length > 0) {
			String base64Photo = Base64.getEncoder().encodeToString(loginUser.getEmp_photo());
			model.addAttribute("userPhoto", "data:image/png;base64," + base64Photo);
			log.debug("로그인 유저 프로필 이미지 로드 완료 (Base64)");
		} else {
			model.addAttribute("userPhoto", "/images/default_profile2.png");
			log.debug("로그인 유저 프로필 이미지 없음 - 기본 이미지 적용");
		}
		try {
			User[] userArr = restTemplate.getForObject(API_URL + "/users/list/" + userNo, User[].class);
			List<User> userList = (userArr != null) ? Arrays.asList(userArr) : new ArrayList<>();
			for (User u : userList) {
				if (u.getEmp_photo() != null && u.getEmp_photo().length > 0) {
					String encoded = Base64.getEncoder().encodeToString(u.getEmp_photo());
					u.setBase64Photo("data:image/png;base64," + encoded);
				} else if (u.getEmp_photo_name() != null && !u.getEmp_photo_name().trim().isEmpty()) {
					u.setBase64Photo("/images/" + u.getEmp_photo_name());
				} else {
					u.setBase64Photo("/images/default_profile2.png");
				}
			}
			log.info("[데이터] 사원 목록 로드 및 사진 변환 완료: {}명", userList.size());
			PageResponse noticeRes = restTemplate.getForObject(API_URL + "/boards/9999/articles", PageResponse.class);
			List noticeList = (noticeRes != null) ? noticeRes.getContent() : new ArrayList<>();
			System.out.println(noticeRes.getContent().size());
			List noticeListTop3 = noticeList.subList(0, Math.min(3, noticeList.size()));
			log.info("[데이터] 공지사항 로드 완료: 전체 {}건 중 상위 3건 가공", noticeList.size());
			String qnaUrl = API_URL + "/boards/7777/articles?user_no=" + userNo;
			if ("ADMIN".equals(userRole) || "MASTER".equals(userRole)) {
				qnaUrl = API_URL + "/boards/7777/articles";
				log.info("[권한] 관리자급 권한 확인: 전체 Q&A 데이터에 접근합니다.");
			}
			PageResponse qnaRes = restTemplate.getForObject(qnaUrl, PageResponse.class);
			List questionList = (qnaRes != null) ? qnaRes.getContent() : new ArrayList<>();
			List questionListTop3 = questionList.subList(0, Math.min(3, questionList.size()));
			PageResponse articleRes = restTemplate.getForObject(API_URL + "/boards/11/articles?user_no=" + userNo,
					PageResponse.class);
			List articleList = (articleRes != null) ? articleRes.getContent() : new ArrayList<>();
			PageResponse prjRes = restTemplate.getForObject(API_URL + "/users/myProject?user_no=" + userNo,
					PageResponse.class);
			List myProject = (prjRes != null) ? prjRes.getContent() : new ArrayList<>();
			List myProjectTop3 = myProject.subList(0, Math.min(3, myProject.size()));
			log.info("[데이터] 메인 대시보드 구성 완료 (일반:{}건, 프로젝트:{}건)", articleList.size(), myProject.size());
			model.addAttribute("userList", userList);
			model.addAttribute("noticeList", noticeListTop3);
			model.addAttribute("questionList", questionListTop3);
			model.addAttribute("articleList", articleList);
			model.addAttribute("MyProject", myProjectTop3);
		} catch (Exception e) {
			log.error("[장애] 메인페이지 데이터 로딩 중 서버 통신 오류 발생!");
			log.error("ㄴ 사용자: {} (No: {}), 사유: {}", loginUser.getEmp_name(), userNo, e.getMessage());
			model.addAttribute("userList", new ArrayList<>());
			model.addAttribute("noticeList", new ArrayList<>());
			model.addAttribute("questionList", new ArrayList<>());
			model.addAttribute("articleList", new ArrayList<>());
			model.addAttribute("MyProject", new ArrayList<>());
		}
		return "html/mainPage";
	}

	@GetMapping("/master/boards")
	public String masterBoardList(@RequestParam(value = "b_no", defaultValue = "1001") int b_no, Model model,
			HttpSession session, @RequestParam(defaultValue = "b_name", required = false) String searchType,
			@RequestParam(defaultValue = "", required = true) String keyword,
			@RequestParam(defaultValue = "1", required = true) int page,
			@RequestParam(defaultValue = "10", required = true) int pageSize) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null
				|| (!"MASTER".equals(loginUser.getUser_role()) && !"ADMIN".equals(loginUser.getUser_role()))) {
			log.warn("관리자 권한 없음 - 게시판 설정 접근 차단: 사번={}", (loginUser != null ? loginUser.getEmp_no() : "Guest"));
			return "redirect:/mainpage";
		}
		if ("ADMIN".equals(loginUser.getUser_role())) {
			log.info("관리자(ADMIN) 권한 확인 - 관리자 전용 페이지로 이동: 사번={}", loginUser.getEmp_no());
			return "redirect:/master/admins";
		}
		log.info("최고관리자(MASTER) - 게시판 설정 페이지 진입: 사번={}, 페이지={}, 검색어={}", loginUser.getEmp_no(), page, keyword);
		try {
			String params = PagingConstants.getParams(page, pageSize, searchType, keyword);
			PageResponse result = restTemplate.getForObject(API_URL + "/master/boards" + params, PageResponse.class);
			List boards = (result != null) ? result.getContent() : new ArrayList();
			int totalCount = (result != null) ? result.getTotalCount() : 0;
			int totalPage = (int) Math.ceil((double) totalCount / pageSize);
			Board currentBoard = restTemplate.getForObject(API_URL + "/boards/info/" + b_no, Board.class);
			List deptList = restTemplate.getForObject(API_URL + "/dept/list", List.class);
			List userList = restTemplate.getForObject(API_URL + "/users/list/" + loginUser.getUser_no(), List.class);
			model.addAttribute("boards", boards);
			model.addAttribute("currentBoard", currentBoard);
			model.addAttribute("deptList", deptList);
			model.addAttribute("userList", userList);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("page", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("keyword", keyword);
			model.addAttribute("searchType", searchType);
			log.info("최고관리자 - 게시판 목록 로드 완료: 총 개수={}", totalCount);
		} catch (Exception e) {
			log.error("최고관리자 - 게시판 목록 로딩 중 에러 발생: {}", e.getMessage());
		}
		return "html/master-boards";
	}

	@GetMapping("/foodlist")
	public String foodlistPage(@RequestParam(value = "b_no", defaultValue = "8888") int b_no, Model model,
			@RequestParam(defaultValue = "art_title", required = false) String searchType,
			@RequestParam(defaultValue = "", required = true) String keyword,
			@RequestParam(defaultValue = "1", required = true) int page,
			@RequestParam(defaultValue = "10", required = true) int pageSize, HttpSession session) {

		User loginUser = (User) session.getAttribute("loginUser");

		// 1. 접근 로그 기록
		if (keyword != null && !keyword.trim().isEmpty()) {
			log.info("식단 목록 검색: 사번={}, 키워드={}, 페이지={}", (loginUser != null ? loginUser.getEmp_no() : "Guest"), keyword,
					page);
		} else {
			log.info("식단 목록 조회: 사번={}, 페이지={}", (loginUser != null ? loginUser.getEmp_no() : "Guest"), page);
		}

		try {
			String params = PagingConstants.getParams(page, pageSize, searchType, keyword);

			PageResponse result = restTemplate.getForObject(API_URL + "/boards/" + b_no + "/articles" + params,
					PageResponse.class);

			if (result == null) {
				log.warn("식단 목록 데이터 없음: b_no={}", b_no);
				return "html/foodlist";
			}

			List articles = result.getContent();
			int totalCount = result.getTotalCount();
			int totalPage = PagingConstants.getTotalPage(totalCount, pageSize);

			Board currentBoard = restTemplate.getForObject(API_URL + "/boards/info/" + b_no, Board.class);

			model.addAttribute("articles", articles);
			model.addAttribute("currentBoard", currentBoard);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("page", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("keyword", keyword);
			model.addAttribute("searchType", searchType);

			log.info("식단 목록 로딩 완료: b_no={}, 조회된 데이터 수={}", b_no, articles.size());

		} catch (Exception e) {
			log.error("식단 목록 조회 중 시스템 장애: 사유={}", e.getMessage());
		}

		return "html/foodlist";
	}

	// ========= 게시판 추가

	@PostMapping("/master/boards/add")
	@ResponseBody
	public String addBoard(@RequestParam("b_name") String b_name, @RequestParam("b_cate") String b_cate) {
		try {
			log.info("게시판 추가 시도: 이름={}, 카테고리={}", b_name, b_cate);

			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("b_name", b_name);
			params.add("b_cate", b_cate);

			String response = restTemplate.postForObject(API_URL + "/master/boards/add", params, String.class);

			return response;

		} catch (Exception e) {
			log.error("게시판 추가 중 API 통신 에러 발생: {}", e.getMessage());
			return "fail";
		}
	}


	/**
	 * 특정 게시판의 관리자(ADMIN) 목록 조회 JS에서 게시판 선택 시 해당 게시판의 관리자 명단을 가져올 때 사용
	 */
	@GetMapping("/master/boards/{bNo}/admins")
	@ResponseBody
	public List<BoardAccess> getBoardAdmins(@PathVariable("bNo") int bNo, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		log.info("관리자 - 게시판 관리자 목록 조회: 게시판번호(bNo)={}, 조회자={}", bNo,
				(loginUser != null ? loginUser.getEmp_no() : "Unknown"));

		try {
			String url = API_URL + "/master/boards/" + bNo + "/admins";
			BoardAccess[] admins = restTemplate.getForObject(url, BoardAccess[].class);

			List<BoardAccess> adminList = (admins != null) ? Arrays.asList(admins) : new ArrayList<>();

			log.info("관리자 - 게시판 관리자 조회 완료: bNo={}, 관리자 수={}", bNo, adminList.size());

			return adminList;

		} catch (Exception e) {
			log.error("관리자 - 게시판 관리자 조회 중 시스템 에러: bNo={}, 사유={}", bNo, e.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * 특정 사원이 관리 중인 게시판 리스트 조회 사원 리스트에서 특정 사원 클릭 시 관리 중인 게시판을 띄울 때 사용
	 */
	@GetMapping("/master/emp/{empNo}/admin-boards")
	@ResponseBody
	public List<Board> getEmpAdminBoards(@PathVariable("empNo") int empNo, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		log.info("관리자 - 직원별 권한 조회 시도: 대상사번(empNo)={}, 실행자={}", empNo,
				(loginUser != null ? loginUser.getEmp_no() : "Unknown"));

		try {
			String url = API_URL + "/master/emp/" + empNo + "/admin-boards";
			Board[] boards = restTemplate.getForObject(url, Board[].class);

			List<Board> adminBoards = (boards != null) ? Arrays.asList(boards) : new ArrayList<>();

			log.info("관리자 - 직원별 권한 조회 완료: 대상사번={}, 관리게시판 수={}", empNo, adminBoards.size());

			return adminBoards;

		} catch (Exception e) {
			log.error("관리자 - 직원별 권한 조회 중 장애 발생: 대상사번={}, 사유={}", empNo, e.getMessage());
			return new ArrayList<>();
		}
	}

	// 특정 게시판글쓰기 권한 해제
	@PostMapping("/master/boards/revoke1") 
	@ResponseBody
	public String revokeAdminOne(@RequestParam("b_no") int bNo,
			@RequestParam("emp_no") int empNo) {

		log.info("[수정된 경로] 관리자 권한 해제 시도 - 게시판: {}, 사번: {}", empNo);

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("b_no", String.valueOf(bNo));
			params.add("emp_no", String.valueOf(empNo));

			// API 서버에도 고유한 경로(/api/master/boards/revoke1)로 요청
			String response = restTemplate.postForObject(API_URL + "/master/boards/revoke1", params, String.class);

			return response;
		} catch (Exception e) {
			log.error("권한 해제 통신 중 에러 발생: {}", e.getMessage());
			return "fail";
		}
	}

	/**
	 * 특정 게시판의 특정 사원 관리자 권한 해제
	 */
	@PostMapping("/master/boards/revoke")
	@ResponseBody
	public String boardAdminRevoke(@RequestParam("b_no") int bNo, @RequestParam("emp_no") int empNo,
			HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		// 1. 보안 로그: 권한 해지를 시도하는 마스터와 대상자 기록
		log.info("관리자 - 게시판 권한 해지 시도: 실행자={}, 대상사번={}, 게시판번호={}",
				(loginUser != null ? loginUser.getEmp_no() : "Unknown"), empNo, bNo);

		try {
			// 9090 서버의 @RequestParam 구조에 맞게 호출
			String url = API_URL + "/master/boards/revoke?b_no={b_no}&emp_no={emp_no}";

			String result = restTemplate.postForObject(url, null, String.class, bNo, empNo);

			// 2. 실행 결과 확인 및 기록
			if ("success".equals(result)) {
				log.info("관리자 - 게시판 권한 해지 완료: 대상사번={}, 게시판번호={}", empNo, bNo);
				return "success";
			} else {
				log.warn("관리자 - 게시판 권한 해지 실패(서버 응답): 결과={}, 대상사번={}", result, empNo);
				return "fail";
			}
		} catch (Exception e) {
			// 3. 에러 발생 시 상세 정보 기록
			log.error("관리자 - 게시판 권한 해지 중 시스템 장애: 대상사번={}, 사유={}", empNo, e.getMessage());
			return "error";
		}
	}

	@GetMapping("/search")
	public String searchPage(@RequestParam(value = "keyword", required = false) String keyword, HttpSession session,
			Model model) {

		User loginUser = (User) session.getAttribute("loginUser");
		int empNo = (loginUser != null) ? loginUser.getEmp_no() : 0;
		List<Map<String, Object>> results = new ArrayList<>();

		if (keyword != null && !keyword.trim().isEmpty()) {
			// 1. 검색 활동 로그 기록 (누가, 어떤 키워드로 검색했는지)
			log.info("사용자 활동 - 통합 검색 실행: 사번={}, 키워드='{}'", empNo, keyword);

			try {
				String url = API_URL + "/search/integrated?keyword=" + keyword;
				List response = restTemplate.getForObject(url, List.class);

				if (response != null) {
					results = response;
					// 2. 검색 결과 수 기록
					log.info("사용자 활동 - 검색 완료: 키워드='{}', 결과 수={}", keyword, results.size());
				}
			} catch (Exception e) {
				// 3. [보안] printStackTrace 삭제 및 e.getMessage()만 기록
				log.error("시스템 장애 - 통합 검색 API 통신 에러: 사번={}, 사유={}", empNo, e.getMessage());
			}
		}

		model.addAttribute("keyword", keyword);
		model.addAttribute("searchResults", results);

		return "html/search";
	}

	@PostMapping("/master/boards/grant")
	@ResponseBody
	public String selectAdmin(@RequestParam("b_no") int bNo, @RequestParam("user_no") int userNo, // HTML에서 넘어오는 대상
																									// 사번(emp_no)
			HttpSession session) {

		User loginUser = (User) session.getAttribute("loginUser");

		// 로그 확인용
		log.info("관리자 권한 부여 시도: 실행자={}, 대상사번={}, 게시판번호={}", (loginUser != null ? loginUser.getEmp_no() : "Unknown"),
				userNo, bNo);

		/**
		 * [에러 해결의 핵심] Rest 서버(9090)의 컨트롤러가 @RequestParam("user_no")를 기다리고 있다면 아래 형식을
		 * 유지하고, 만약 Rest 서버가 @RequestParam("emp_no")를 기다리고 있다면 user_no={user_no} 부분을
		 * emp_no={user_no}로 바꿔야 합니다. 로그상 9090에서 'user_no'가 없다고 했으므로, 명시적으로 파라미터를 구성합니다.
		 */
		String url = API_URL + "/master/boards/grant?b_no={b_no}&user_no={user_no}";

		try {
			// postForObject를 호출할 때 마지막 인자들(bNo, userNo)이
			// 순서대로 URL의 {b_no}, {user_no}에 대입됩니다.
			String result = restTemplate.postForObject(url, null, String.class, bNo, userNo);

			log.info("관리자 권한 부여 최종 결과: {}", result);
			return result;
		} catch (org.springframework.web.client.HttpClientErrorException e) {
			// 400 에러 발생 시 상세 로그 출력
			log.error("Rest 서버 응답 에러 (400): {}", e.getResponseBodyAsString());
			return "fail";
		} catch (Exception e) {
			log.error("권한 부여 중 일반 에러 발생: {}", e.getMessage());
			return "error";
		}
	}

	// 0213 게시판 권한 관리 - 글쓰기 권한 부여
	@PostMapping("/master/admins/write")
	@ResponseBody
	public String grantAdmin(@RequestParam("b_no") int bNo, @RequestParam("emp_no") int empNo, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");

		// [로그 기록] 어떤 마스터가 누구에게 어떤 게시판 권한을 주었는지 기록
		// 보안 감사(Audit) 목적 및 디버깅을 위해 실행자 정보를 남김
		log.info("관리자 - 게시판 권한 부여 시도: 실행자={}, 대상사번={}, 게시판번호={}",
				(loginUser != null ? loginUser.getEmp_no() : "Unknown"), empNo, bNo);

		// 서버 API 호출 URL (파라미터 바인딩 방식 사용)
		String url = API_URL + "/master/admins/write?b_no={b_no}&emp_no={emp_no}";

		try {
			// postForObject를 사용하여 서버로 요청 전송
			// 2번째 인자(requestBody)는 null (쿼리 파라미터로 전송하므로)
			// 3번째 인자: 반환 타입(String)
			// 4번째 이후 가변 인자: URL의 {b_no}, {emp_no}에 들어갈 값
			String result = restTemplate.postForObject(url, null, String.class, bNo, empNo);

			log.info("관리자 - 게시판 권한 부여 결과: {}", result);
			return result;

		} catch (Exception e) {
			log.error("관리자 - 게시판 권한 부여 중 에러 발생: {}", e.getMessage());
			return "error";
		}
	}

	@PostMapping("/master/board/adminRemove")
	@ResponseBody
	public String boardAdminRemove(@RequestParam("b_no") int bNo) {
		String url = API_URL + "/master/board/adminRemove?b_no={b_no}";
		String result = restTemplate.postForObject(url, null, String.class, bNo);

		return "success".equals(result) ? "success" : "fail";
	}

	// 게시판 삭제
	@PostMapping("/master/boards/delete")
	@ResponseBody
	public String deleteBoard(@RequestParam("b_no") int bNo) {
		String url = API_URL + "/master/boards/delete?b_no={b_no}";
		String result = restTemplate.postForObject(url, null, String.class, bNo);

		return "success".equals(result) ? "success" : "fail";
	}

	// 게시판 이름 수정
	@PostMapping("/master/boards/updateName")
	@ResponseBody
	public String updateBoardName(@RequestParam("b_no") int bNo, @RequestParam("b_name") String bName) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("b_no", String.valueOf(bNo));
		body.add("b_name", bName);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

		String result = restTemplate.postForObject(API_URL + "/master/boards/updateBoardName", requestEntity,
				String.class);

		return "success".equals(result) ? "success" : "fail";
	}

	@GetMapping("/schedule/remove/{sch_no}")
	public String removeSchedule(@PathVariable int sch_no, HttpSession session, Model model) {

		User loginUser = (User) session.getAttribute("loginUser");

		// 1. 비로그인 접근 차단 로그
		if (loginUser == null) {
			log.warn("비로그인 사용자의 일정 삭제 시도 차단: sch_no={}");
			// return "fail";
		}

		log.info("일정 삭제 시도: 사번={}, 날짜={}, 내용={}", loginUser.getEmp_no());

		try {
			String result = restTemplate.getForObject(API_URL + "/schedule/remove/" + sch_no, String.class);

			if ("success".equals(result)) {
				log.info("일정 삭제 성공: 사번={}, 결과={}", loginUser.getEmp_no(), result);
			} else {
				log.warn("일정 추가 실패(서버 응답): 사번={}, 결과={}", loginUser.getEmp_no(), result);
			}

			return "redirect:/mainpage?scheduleRemove=" + result;
		} catch (Exception e) {
			log.error("일정 추가 중 통신 에러: 사번={}, 사유={}", loginUser.getEmp_no(), e.getMessage());
			// return "fail";
		}

		return "redirect:/mainpage";
	}

}