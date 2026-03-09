DROP TABLE farmers;
DROP TABLE crops; 

CREATE TABLE farmers (
	fid			INT				PRIMARY KEY		AUTO_INCREMENT,
	fname		VARCHAR(50)		NOT NULL,
	faddr		VARCHAR(50)		NOT NULL,	
	ftel		CHAR(13)		NOT NULL
);

CREATE TABLE crops(
	cid			INT				PRIMARY KEY		AUTO_INCREMENT,
	cname		VARCHAR(50)		NOT NULL,
	cprice		INT				NOT NULL,
	fid			INT				NOT NULL
);

INSERT INTO farmers (fname, faddr, ftel) VALUES
('smith', 'busan', '010-1234-0001'),
('jason', 'seoul', '010-4567-1111'),
('john', 'busan', '010-2234-3451'),
('kevin', 'ulsan', '010-5678-4321'),
('daniel', 'busan', '010-9090-1010'),
('austin', 'busan', '010-5858-1004'),
('foo', 'deagu', '010-9876-1250'),
('bar', 'masna', '010-1001-1122')


INSERT INTO crops (cname, cprice, fid) VALUES
-- smith
('corn', 1500, 1),
('grape', 2000, 1),
('watermelon', 12000, 1),
('mango', 4000, 1),
('potato', 1500, 1),
-- jason
('bean', 100, 2),
('grape', 2000, 2),
('watermelon', 13000, 2),
('apple', 2000, 2),
-- john
('corn', 1500, 3),
('grape', 2000, 3),
('onion', 1300, 3),
('garlic', 400, 3),
('peach', 2100, 3),
-- kevin
('tomato', 900, 4),
('pear', 3000, 4),
('watermelon', 11000, 4),
('apple', 2300, 4),
('potato', 1200, 4),
-- daniel
('corn', 1500, 5),
('kiwi', 1500, 5),
('mango', 4000, 5),
('garlic', 900, 5);

SELECT * FROM farmers;
SELECT * FROM crops;

DESC farmers;
DESC crops;



















