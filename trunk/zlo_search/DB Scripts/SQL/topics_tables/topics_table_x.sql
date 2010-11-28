

--USE zlo_storage;

-- create
CREATE TABLE x_topics (
  id INT NOT NULL /*AUTO_INCREMENT*/ PRIMARY KEY,
  name CHAR(50),
  isNew BOOL
  )
ENGINE=INNODB
DEFAULT CHARSET=cp1251;

-- index
-- ALTER TABLE x_topics
--  ADD INDEX (id);

-- insert
INSERT INTO x_topics (id, name, isNew) VALUES (0, '��� ����',         1);
INSERT INTO x_topics (id, name, isNew) VALUES (1, '�����',            1);
INSERT INTO x_topics (id, name, isNew) VALUES (2, '������',           1);
INSERT INTO x_topics (id, name, isNew) VALUES (3, '��������',         1);
INSERT INTO x_topics (id, name, isNew) VALUES (4, '����������',       1);
INSERT INTO x_topics (id, name, isNew) VALUES (5, '�������',          1);
INSERT INTO x_topics (id, name, isNew) VALUES (6, '�����',            1);
INSERT INTO x_topics (id, name, isNew) VALUES (7, '�����������',      1);
INSERT INTO x_topics (id, name, isNew) VALUES (8, '������ �����',     1);
INSERT INTO x_topics (id, name, isNew) VALUES (9, '����������������', 1);
INSERT INTO x_topics (id, name, isNew) VALUES (10, '�����',           1);
INSERT INTO x_topics (id, name, isNew) VALUES (11, '������',          1);
INSERT INTO x_topics (id, name, isNew) VALUES (12, '������',          1);
INSERT INTO x_topics (id, name, isNew) VALUES (13, 'Windows',         1);
INSERT INTO x_topics (id, name, isNew) VALUES (14, 'BSD/Linux',       1);
INSERT INTO x_topics (id, name, isNew) VALUES (15, '�������� ����',   1);
INSERT INTO x_topics (id, name, isNew) VALUES (16, '�����������',     1);
INSERT INTO x_topics (id, name, isNew) VALUES (17, '��������/�������',1);
INSERT INTO x_topics (id, name, isNew) VALUES (18, 'Temp',            1);
-- old
--INSERT INTO x_topics (id, name, isNew) VALUES (11, '������/�������',  0);