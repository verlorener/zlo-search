

USE zlo_storage;

-- create
CREATE TABLE topics (id INT UNIQUE PRIMARY KEY,
                        name CHAR(50)
                    );

-- insert
INSERT INTO topics (id, name) VALUES (0, '��� ����');
INSERT INTO topics (id, name) VALUES (1, '�����');
INSERT INTO topics (id, name) VALUES (2, '������');
INSERT INTO topics (id, name) VALUES (3, '��������');
INSERT INTO topics (id, name) VALUES (4, '����������');
INSERT INTO topics (id, name) VALUES (5, '�������');
INSERT INTO topics (id, name) VALUES (6, '�����');
INSERT INTO topics (id, name) VALUES (7, '�����������');
INSERT INTO topics (id, name) VALUES (8, '������ �����');
INSERT INTO topics (id, name) VALUES (9, '����������������');
INSERT INTO topics (id, name) VALUES (10, '�����');
INSERT INTO topics (id, name) VALUES (11, '������');
INSERT INTO topics (id, name) VALUES (12, '������');
INSERT INTO topics (id, name) VALUES (13, 'Windows');
INSERT INTO topics (id, name) VALUES (14, 'BSD/Linux');
INSERT INTO topics (id, name) VALUES (15, '�������� ����');
INSERT INTO topics (id, name) VALUES (16, '�����������');
INSERT INTO topics (id, name) VALUES (17, '��������/�������');
INSERT INTO topics (id, name) VALUES (18, 'Temp');