package org.green.test.controller;

import org.green.test.dto.User;
import org.green.test.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminLogController {

    @Autowired
    private LogService logService;

    /**
     * 로그 조회 및 검색 페이지
     * URL: http://localhost:8080/master/logs
     */
    @GetMapping("/master/logs")
    public String viewLogs(@RequestParam(value = "search", required = false) String search, 
                           HttpSession session, Model model) {
        
        // 1. 보안 체크: 로그인 여부 및 MASTER 권한 확인
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null || !"MASTER".equals(loginUser.getUser_role())) {
            // 권한이 없으면 메인으로 튕겨냄
            return "redirect:/mainpage";
        }

        // 2. 검색어가 있을 경우 로그 파일 필터링 실행
        if (search != null && !search.trim().isEmpty()) {
            List<String> logList = logService.getFilteredLogs(search);
            model.addAttribute("logList", logList);
            model.addAttribute("search", search); // 검색창에 입력값 유지용
        } else {
            // 검색어가 없을 때는 빈 리스트를 보내거나 안내 메시지를 처리
            model.addAttribute("message", "검색어를 입력해주세요. (예: 사번, 시스템 장애, 로그인)");
        }

        // 3. 관리자 로그 뷰어 화면(HTML) 반환
        return "html/master-logs"; 
    }
}