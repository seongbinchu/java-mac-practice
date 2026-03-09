
CREATE DATABASE IF NOT EXISTS `sg` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sg`;


CREATE TABLE IF NOT EXISTS `article` (
  `art_no` int NOT NULL AUTO_INCREMENT,
  `art_title` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_no` int NOT NULL,
  `art_date` date NOT NULL,
  `art_tag` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `art_content` text,
  `art_file` longblob,
  `art_like` int NOT NULL DEFAULT '0',
  `art_is_private` char(1) DEFAULT 'N',
  `art_count` int DEFAULT '0',
  `art_map` text,
  `b_no` int NOT NULL,
  `art_file_name` varchar(255) DEFAULT NULL,
  `art_fav_no` int DEFAULT NULL,
  PRIMARY KEY (`art_no`)
) ENGINE=InnoDB AUTO_INCREMENT=603 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `art_favorite` (
  `art_fav_no` int NOT NULL AUTO_INCREMENT,
  `user_no` int NOT NULL,
  `art_no` int NOT NULL,
  `fav_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`art_fav_no`),
  UNIQUE KEY `unique_fav` (`user_no`,`art_no`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `board` (
  `b_no` int NOT NULL AUTO_INCREMENT,
  `b_name` text NOT NULL,
  `b_cate` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`b_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1000014 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `board_access` (
  `ba_no` int NOT NULL AUTO_INCREMENT,
  `b_no` int DEFAULT NULL,
  `emp_no` int DEFAULT NULL,
  `ROLE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ba_no`),
  UNIQUE KEY `uk_board_emp` (`b_no`,`emp_no`),
  UNIQUE KEY `idx_board_emp` (`b_no`,`emp_no`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `dept` (
  `dept_no` int NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(10) NOT NULL,
  PRIMARY KEY (`dept_no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `emp` (
  `emp_no` int NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(20) NOT NULL,
  `emp_email` char(32) NOT NULL,
  `emp_tel` char(20) NOT NULL,
  `emp_photo` mediumblob,
  `emp_photo_name` char(20) DEFAULT NULL,
  `dept_no` int DEFAULT NULL,
  `rnk_no` int DEFAULT NULL,
  `emp_pw` varchar(255) NOT NULL,
  PRIMARY KEY (`emp_no`)
) ENGINE=InnoDB AUTO_INCREMENT=20000008 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `emp_prj` (
  `emp_no` int NOT NULL,
  `prj_no` int NOT NULL,
  `prj_role` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `msg` (
  `msg_no` int NOT NULL AUTO_INCREMENT,
  `msg_title` char(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `from_id` int DEFAULT NULL,
  `to_id` int DEFAULT NULL,
  `msg_content` text,
  `msg_datetime` date DEFAULT NULL,
  `msg_is_read` tinyint DEFAULT '0',
  PRIMARY KEY (`msg_no`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `prj` (
  `prj_no` int NOT NULL AUTO_INCREMENT,
  `prj_name` varchar(20) NOT NULL,
  `description` char(32) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `prj_priority` int DEFAULT NULL,
  `prj_board` int DEFAULT NULL,
  PRIMARY KEY (`prj_no`)
) ENGINE=InnoDB AUTO_INCREMENT=100006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `pw_reset` (
  `emp_no` varchar(20) NOT NULL,
  `try_count` int DEFAULT '0',
  `last_try` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `auth_code` varchar(10) DEFAULT NULL,
  `expiry_time` datetime DEFAULT NULL,
  `is_verified` char(1) DEFAULT 'N',
  PRIMARY KEY (`emp_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `reply` (
  `re_no` int NOT NULL AUTO_INCREMENT,
  `art_no` int NOT NULL,
  `user_no` int NOT NULL,
  `re_date` date DEFAULT NULL,
  `re_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `re_isdeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`re_no`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `report` (
  `rep_no` int NOT NULL AUTO_INCREMENT,
  `rep_reason` text,
  `user_no` int DEFAULT NULL,
  `art_no` int DEFAULT NULL,
  `b_no` int DEFAULT NULL,
  `rep_is_read` tinyint DEFAULT '0',
  PRIMARY KEY (`rep_no`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `rnk` (
  `rnk_no` int NOT NULL AUTO_INCREMENT,
  `rnk_name` varchar(10) NOT NULL,
  PRIMARY KEY (`rnk_no`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




CREATE TABLE IF NOT EXISTS `schedule` (
  `sch_no` int NOT NULL AUTO_INCREMENT,
  `user_no` int NOT NULL,
  `sch_date` date NOT NULL,
  `sch_content` varchar(255) DEFAULT NULL,
  `reg_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sch_no`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DELIMITER //
CREATE PROCEDURE `sp_assign_board_admin`(
    IN p_b_no INT,
    IN p_user_no INT
)
BEGIN
    -- 1. [핵심 추가] 해당 게시판의 기존 관리자들을 모두 일반 유저 권한(NULL)으로 변경
    -- 이렇게 해야 UI에서 기존 담당자 이름이 사라집니다.
    UPDATE board_access 
    SET ROLE = NULL 
    WHERE b_no = p_b_no AND ROLE = 'ADMIN';

    -- 2. 새로운 관리자 지정 (중복 방지 및 업데이트)
    INSERT INTO board_access (b_no, emp_no, ROLE)
    SELECT p_b_no, u.emp_no, 'ADMIN'
    FROM user u
    WHERE u.user_no = p_user_no
    ON DUPLICATE KEY UPDATE ROLE = 'ADMIN';

    -- 3. 해당 유저의 전역 권한을 ADMIN으로 승격
    UPDATE user 
    SET user_role = 'ADMIN' 
    WHERE user_no = p_user_no;
    
    -- 4. [선택사항] 권한이 박탈된 이전 관리자가 다른 게시판 관리직도 없다면 
    -- 전역 user_role을 'USER'로 내려주는 로직을 넣을 수도 있습니다.
END//
DELIMITER ;

-- 프로시저 sg.sp_revoke_board_admin 구조 내보내기
DELIMITER //
CREATE PROCEDURE `sp_revoke_board_admin`(
    IN p_b_no INT,
    IN p_user_no INT
)
BEGIN
    -- 1. board_access 테이블의 ROLE을 NULL로 변경 (기록은 유지)
    UPDATE board_access 
    SET ROLE = NULL 
    WHERE b_no = p_b_no 
      AND emp_no = (SELECT u.emp_no FROM user u WHERE u.user_no = p_user_no);

    -- 2. 해당 유저가 '다른 게시판'에서도 더 이상 관리자(ADMIN)가 아닌지 확인
    -- 만약 어디에도 ADMIN 권한이 남아있지 않다면 일반 유저(USER)로 변경
    IF NOT EXISTS (
        SELECT 1 FROM board_access ba 
        JOIN user u ON ba.emp_no = u.emp_no 
        WHERE u.user_no = p_user_no AND ba.ROLE = 'ADMIN'
    ) THEN
        UPDATE user SET user_role = 'USER' WHERE user_no = p_user_no;
    END IF;
END//
DELIMITER ;


CREATE TABLE IF NOT EXISTS `user` (
  `user_no` int NOT NULL AUTO_INCREMENT,
  `user_state` enum('ONLINE','OFFLINE','AWAY') DEFAULT 'OFFLINE',
  `user_role` enum('MASTER','ADMIN','USER','NONE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `user_email` varchar(32) DEFAULT NULL,
  `user_note` text,
  `user_pw` varchar(255) DEFAULT NULL,
  `emp_no` int DEFAULT NULL,
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=20000008 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


