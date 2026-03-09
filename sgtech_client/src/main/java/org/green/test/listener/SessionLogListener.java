package org.green.test.listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.green.test.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SessionLogListener implements HttpSessionListener {
    private static final Logger log = LoggerFactory.getLogger(SessionLogListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 세션이 생성될 때 (로그인 페이지 접속 등)
        log.info("새로운 세션 생성됨: ID = {}", se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 세션이 만료되거나(타임아웃) invalidate() 될 때 호출됨
        User loginUser = (User) se.getSession().getAttribute("loginUser");

        if (loginUser != null) {
            // 브라우저를 그냥 닫았더라도 세션 타임아웃이 되면 이 로그가 찍힙니다.
            log.info("사용자 접속 종료 (세션 만료): 사번={}, 이름={}, 부서={}", 
                     loginUser.getEmp_no(), loginUser.getEmp_name(), loginUser.getDept_name());
        } else {
            log.info("로그인하지 않은 세션 종료: ID = {}", se.getSession().getId());
        }
    }
}