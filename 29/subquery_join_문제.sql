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

-- 가장 비싼 제품 조회(product_name, price)

-- 제품명이 Laptop인 제품의  카테고리 이름을 구하라.(category_name)

-- 가장 비싼 제품의 카테고리(category_name)

-- 가격 합계가 1000 이상인 카테고리(category_name)

-- 카테고리 이름이 'Electronics' 가 아닌 제품들의 이름(product_name)

-- 제품별 카테고리이름(product_name, category_name)

-- 제품별 카테고리이름(카테고리가 없는 제품 포함) (product_name, category_name)

-- 카테고리와 이름.(제품이 없는 카테고리 포함)(category_name, product_name)

-- 가격이 100 이상인 제품의 카테고리(product_name, category_name)

-- 카테고리 이름에 'Tech'가 포함된 제품(product_name, category_name)

-- 카테고리별 가격 합계(category_name, total_price)

-- 카테고리별 제품 수(category_name, product_count)

-- 카테고리 이름순으로 정렬된 제품 목록(product_name, category_name)
