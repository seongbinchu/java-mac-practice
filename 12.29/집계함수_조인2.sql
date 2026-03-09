DROP TABLE professor;
DROP TABLE student;

CREATE TABLE professor(
	pid		INT		PRIMARY KEY		AUTO_INCREMENT,
	pname	VARCHAR(20)				NOT NULL,
	pmajor	VARCHAR(20)				NOT NULL
);

CREATE TABLE student (
	sid			INT		PRIMARY KEY		AUTO_INCREMENT,
	sname		VARCHAR(20)		NOT NULL,
	sgrade		INT		NOT NULL,
	pid			INT		NOT NULL
);


INSERT INTO professor(pname, pmajor) VALUES 
	('james', 'computer'),
	('jhone', 'math'),
	('jane', 'english'),
	('jason', 'kor');
	
INSERT INTO student(sname, sgrade, pid) VALUES
	('smith', 3, 1),
	('clock', 1, 2),
	('jonadan', 2, 3),
	('mike', 4, 1),
	('brown', 2, 2),
	('joe', 2, 3);


-- joe나 jonadan 의 담당교수 이름을 구하라.
-- 각 교수별 담당 학생을 구하라.
-- 각 교수별 담당 학생을 구하라. 담당학생이 없는 교수도 결과에 포함한다.
-- 각 교수별 담당학생 수 를 구하라. 담당학생이 없는 교수도 결과에 포함한다.
-- 담당 학생이 없는 교수를 구하라.
-- computer 학과 학생을 구하라.
-- 각 모든 학과별 학생 수를 구하라.