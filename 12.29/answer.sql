--부산에 사는 고객의 직업을 구하라.
SELECT 
	job
FROM 
	customers NATURAL JOIN job_list 
WHERE 
	c_addr = 'pusan';

--이름이 kim인 사람의 직업은?
SELECT 
	job
FROM 
	customers NATURAL JOIN job_list 
WHERE 
	c_name = 'kim';

--가장 가격이 싼 제품의 제품명은?
SELECT 
	p_name 
FROM 
	product  
WHERE 
	p_price = (
		SELECT MIN(p_price)
		FROM product
	)

--고객별 구매한 제품의 이름,수량, 구매일자를 구하시오. 단, 최신 구매순으로 정렬한다.
SELECT 
	c_name, p_name, o_count, o_date 
FROM 
	customers  
	NATURAL JOIN 
	order_list  
	NATURAL JOIN 
	products  
ORDER BY o_date DESC;

--가장 비싼 제품을 구입한 고객의 이름을 구하시오.
SELECT 
	DISTINCT c_name 
FROM 
	customers 
	NATURAL JOIN 
	order_list
	NATURAL JOIN 
	products 
WHERE
	p_price = (
		SELECT MAX(p_price) FROM products
	);

--DVD나 TV를 구매한 고객의 이름과 주소, 제품명을 구하시오.
SELECT 
	c_name, c_addr, p_name 
FROM 
	customers 
	NATURAL JOIN 
	order_list 
	NATURAL JOIN 
	products 
WHERE 
	p_name IN ('DVD', 'TV');

--doo가 구매한 가격의 총액을 구하시오.
SELECT 
	SUM(p_price*o_count) 
FROM 
	customers 
	NATURAL JOIN 
	order_list 
	NATURAL JOIN 
	products 
WHERE 
	c_name = 'doo';

--직업이 teacher인 사람이 구매한 제품목록을 구하시오.
SELECT 
	DISTINCT p_name
FROM 
	job_list 
	NATURAL JOIN 
	customers 
	NATURAL JOIN 
	order_list 
	NATURAL JOIN 
	products
WHERE 
	job = 'teacher';

--직업이 student인 고객의 이름과 구매총액을 구하시오.
SELECT 
	c_name, SUM(p_price * o_count)
FROM 
	job_list 
	NATURAL JOIN 
	customers 
	NATURAL JOIN 
	order_list 
	NATURAL JOIN 
	products
WHERE 
	job = 'student‘
GROUP BY
	c_id;

--한번도 구매하지 않은 사람의 직업을 구하시오.
SELECT 
	c_name, job
FROM
	job_list 
	NATURAL JOIN 
	customers 
	LEFT OUTER JOIN 
	order_list 
	NATURAL JOIN products
ON
	customers.c_id = order_list.c_id
WHERE
	o_id IS NULL;

