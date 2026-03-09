/*
	1. 모든작물의 이름을 구하라.(crops)
*/
-- 중복을 제거한다.
SELECT DISTINCT cname FROM crops;

-- 관리하는 농작물의 총 종류수 구하기
SELECT COUNT(DISTINCT cname) FROM crops;


/*
	작물별 제공 농부수가 2명 이상인 작물을 구하라.  
*/
SELECT cname, COUNT(fid) farmers_count 
	FROM crops 
	GROUP BY cname 
	HAVING farmers_count >= 2;

/*
	3000원 이상하는 작물의 수를 구하라.  
*/
SELECT COUNT(cname) crops_count FROM crops WHERE cprice >= 3000; 

/*
	작물별로 3000원 이상하는 작물의 수가 2개 이상인 작물을 구하라.  
*/

SELECT cname, COUNT(cname) crops_count 
	FROM crops 
	WHERE cprice >= 3000
	GROUP BY cname
	HAVING crops_count >= 2;

/*
	작물별 가장 저렴한 제품의 가격을 구하라.  
*/
SELECT cname, MIN(cprice) FROM crops GROUP BY cname;

/*
	작물별 평균가격을 구하라
*/
SELECT cname, AVG(cprice) FROM crops GROUP BY cname;

/*
  이름이 4글자가 넘는 작물별 평균가격을 구하라.
*/
SELECT cname, AVG(cprice) 
	FROM crops
	WHERE CHAR_LENGTH(cname) > 4
	GROUP BY cname;
/*
  이름이 4글자가 넘는 작물별 평균가격을 구하라. 
  단, 평균가격이 1000원 이상
*/
SELECT cname, AVG(cprice) cprice_avg 
	FROM crops
	WHERE CHAR_LENGTH(cname) > 4
	GROUP BY cname
	HAVING cprice_avg > 1000;

/*
	각 농부별 재배작물의 총 가격을 구하라. 
*/

SELECT fid, SUM(cprice) total FROM crops GROUP BY fid;
SELECT fname FROM farmers;

-- sub-query
SELECT fname, c.total
	FROM farmers, 
		(SELECT fid, SUM(cprice) total FROM crops GROUP BY fid) c
	WHERE farmers.fid = c.fid;	

/*
	dainel이 재배하는 작물의 이름과 가격을 구하라.  
*/
SELECT cname, cprice FROM crops 
	WHERE fid = 
	(SELECT fid FROM farmers WHERE fname='daniel');
	
-- 최신(표준)
SELECT fname, cname, cprice 
	FROM crops INNER JOIN farmers	
	ON crops.fid = farmers.fid
	WHERE farmers.fname = 'daniel';

/*
	kiwi를 생산하는 농부의 이름과 전화번호를 구하라.	
*/	


SELECT fname, ftel FROM crops NATURAL JOIN farmers
	WHERE cname = 'kiwi';

/*
	가장 값이 싼 수박을 납품하는 농부의 이름과 주소를 구하라.
*/
		
SELECT fid FROM crops 
	WHERE 
		cname='watermelon' 
		AND
		cprice = (SELECT MIN(cprice) FROM crops WHERE cname='watermelon');
		
SELECT fname, faddr FROM farmers 
	WHERE
		fid = (
			SELECT fid FROM crops 
				WHERE 
					cname='watermelon' 
					AND
					cprice = (SELECT MIN(cprice) FROM crops WHERE cname='watermelon')
		
		
		);
		
SELECT fname, faddr		
	FROM 
		farmers NATURAL JOIN crops
	WHERE
		cname = 'watermelon'
		AND
		cprice = (
			SELECT MIN(cprice) 
				FROM crops 
				WHERE cname='watermelon'
		);
/*
	부산에서 재배되는 작물의 이름, 가격, 농부이름 구하라.
*/
SELECT cname, cprice, fname
	FROM crops NATURAL JOIN farmers
	WHERE faddr = 'busan';
/*
 	옥수수를 재배하지 않는 농부의 이름은? 	
 */

-- 1. 옥수수를 재배하는 농부아이디를 구한다.
SELECT fid FROM crops WHERE cname = 'corn';

-- 2. 1에 포함되지 않는 농부아이디를 구한다.
SELECT DISTINCT fid FROM crops WHERE 
	fid NOT IN (SELECT fid FROM crops WHERE cname = 'corn');

-- 3. 농부이름을 구한다.
SELECT fname FROM farmers
	WHERE fid IN (
		SELECT DISTINCT fid FROM crops WHERE 
			fid NOT IN	(
				SELECT fid FROM crops WHERE cname = 'corn'
			)	
	)

-- join
SELECT DISTINCT fname FROM farmers NATURAL JOIN crops
	WHERE farmers.fid NOT IN (
		SELECT fid FROM crops WHERE cname = 'corn'
	);

/*
	납품을 하지않는 농부의 이름을 구하라	
*/		
		
-- 작물을 재배하는 농부 아이디를 구한다.
SELECT DISTINCT fid FROM crops;
	
-- 1을 제외한 농부이름
SELECT fname FROM farmers WHERE fid NOT IN (
	SELECT DISTINCT fid FROM crops
);	
		
-- join
SELECT *
	FROM 
		farmers LEFT OUTER JOIN crops		
	ON 
		farmers.fid = crops.fid
	WHERE
		cid IS NULL;
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
		
