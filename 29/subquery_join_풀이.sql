DROP TABLE categories;
DROP TABLE products;


CREATE TABLE categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL
);

CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    category_id INT,
    price DECIMAL(10, 2) NOT NULL
);

INSERT INTO categories (category_name) VALUES
('Electronics'),
('Furniture'),
('Clothing'),
('Books'),
('Toys'),
('etc');


INSERT INTO products (product_name, category_id, price) VALUES
('Laptop', 1, 999.99),
('Mouse', 1, 29.99),
('Desk', 2, 149.50),
('Chair', 2, 89.00),
('T-Shirt', 3, 19.99),
('Jeans', 3, 49.99),
('Novel', 4, 12.50),
('Textbook', NULL, 45.00),
('Action Figure', 5, 15.99),
('Puzzle', 5, 9.99),
('Monitor', 1, 199.99),
('Sofa', 2, 399.00),
('Jacket', 3, 79.99),
('Comic Book', 4, 5.99),
('Board Game', NULL, 24.99);


-- 평균 가격보다 비싼 제품 조회.(product_name, price)
SELECT product_name, price
FROM products
WHERE price > (SELECT AVG(price) FROM products);

-- 가장 비싼 제품 조회(product_name, price)
SELECT product_name, price
FROM products
WHERE price = (SELECT MAX(price) FROM products);


-- 제품명이 Laptop인 제품의  카테고리 이름을 구하라.(category_name)
SELECT category_name
FROM categories
WHERE category_id = (SELECT category_id FROM products WHERE product_name = 'Laptop');

	-- 복수행의 가능성이 있는 경우 IN으로 검색
SELECT category_name
FROM categories
WHERE category_id IN (SELECT category_id FROM products WHERE product_name = 'Laptop');

-- 가장 비싼 제품의 카테고리(category_name)
SELECT category_name
FROM categories
WHERE category_id = (SELECT category_id FROM products ORDER BY price DESC LIMIT 1);


-- 가격 합계가 1000 이상인 카테고리(category_name)
SELECT category_name
FROM categories
WHERE category_id IN (SELECT category_id FROM products GROUP BY category_id HAVING SUM(price) > 1000);

-- 카테고리 이름이 'Electronics' 가 아닌 제품들의 이름(product_name)
SELECT product_name
FROM products
WHERE category_id NOT IN (SELECT category_id FROM categories WHERE category_name = 'Electronics');


-- join

-- 제품별 카테고리이름(product_name, category_name)
SELECT p.product_name, c.category_name
FROM products p INNER JOIN categories c 
ON p.category_id = c.category_id;

-- 제품별 카테고리이름(카테고리가 없는 제품 포함) (product_name, category_name)
SELECT p.product_name, c.category_name
FROM products p LEFT OUTER JOIN categories c 
ON p.category_id = c.category_id;

-- 카테고리와 이름.(제품이 없는 카테고리 포함)(category_name, product_name)
SELECT c.category_name, p.product_name
FROM categories c LEFT OUTER JOIN products p 
ON c.category_id = p.category_id;


-- 가격이 100 이상인 제품의 카테고리(product_name, category_name)
SELECT p.product_name, c.category_name
FROM products p
JOIN categories c ON p.category_id = c.category_id
WHERE p.price > 100;


-- 카테고리 이름에 'Tech'가 포함된 제품(product_name, category_name)
SELECT p.product_name, c.category_name
FROM products p
JOIN categories c ON p.category_id = c.category_id
WHERE c.category_name LIKE '%Tech%';

-- 카테고리별 가격 합계(category_name, total_price)
SELECT c.category_name, SUM(p.price) AS total_price
FROM products p
JOIN categories c ON p.category_id = c.category_id
GROUP BY c.category_name;


-- 카테고리별 제품 수(category_name, product_count)
	-- COUNT(열이름) -> NULL일때 카운팅 하지 않음
	-- COUNT(*) -> NULL일지라도 카운팅 한다.
SELECT c.category_name, COUNT(p.product_id) AS product_count
FROM categories c
LEFT JOIN products p ON c.category_id = p.category_id
GROUP BY c.category_name;


-- 카테고리 이름순으로 정렬된 제품 목록(product_name, category_name)
SELECT p.product_name, c.category_name
FROM products p
JOIN categories c ON p.category_id = c.category_id
ORDER BY c.category_name ASC;