-- joe나 jonadan 의 담당교수 이름을 구하라.
SELECT pname FROM professor WHERE pid IN (SELECT pid FROM student WHERE sname = 'joe' OR sname = 'jonadan');

-- 각 교수별 담당 학생을 구하라.
SELECT  pname, sname FROM professor INNER JOIN student ON professor.pid = student.pid;

-- 각 교수별 담당 학생을 구하라. 담당학생이 없는 교수도 결과에 포함한다.
SELECT  pname, sname FROM professor LEFT OUTER JOIN student ON professor.pid = student.pid;


-- 각 교수별 담당학생 수 를 구하라. 담당학생이 없는 교수도 결과에 포함한다.
SELECT  pname, count(sname) 
	FROM professor LEFT OUTER JOIN student 
	ON professor.pid = student.pid 
	GROUP BY pname

-- 담당 학생이 없는 교수를 구하라.
SELECT  pname FROM professor LEFT OUTER JOIN student ON professor.pid = student.pid WHERE sname IS NULL;
SELECT pname FROM professor WHERE pid NOT IN ( SELECT pid FROM student GROUP BY pid );
SELECT pname FROM professor WHERE pid NOT IN ( SELECT DISTINCT pid FROM student );

-- computer 학과 학생을 구하라.
SELECT sname FROM student NATURAL JOIN professor WHERE pmajor = 'computer';

-- 각 모든 학과별 학생 수를 구하라.
SELECT  pmajor, count(sname) 
	FROM professor LEFT OUTER JOIN student 
	ON professor.pid = student.pid 
	GROUP BY pmajor


	
	
	
	
	
	
	







