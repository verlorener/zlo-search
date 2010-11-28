

USE zlo_storage;

-- create
CREATE TABLE topics (id INT,
                        name CHAR(50),
                        isNew BOOL
                    );

-- index
ALTER TABLE topics
  ADD INDEX (id);

-- insert
INSERT INTO topics (id, name, isNew) VALUES (0, '��� ����',         1);
INSERT INTO topics (id, name, isNew) VALUES (1, '�����',            1);
INSERT INTO topics (id, name, isNew) VALUES (2, '������',           1);
INSERT INTO topics (id, name, isNew) VALUES (3, '��������',         1);
INSERT INTO topics (id, name, isNew) VALUES (4, '����������',       1);
INSERT INTO topics (id, name, isNew) VALUES (5, '�������',          1);
INSERT INTO topics (id, name, isNew) VALUES (6, '�����',            1);
INSERT INTO topics (id, name, isNew) VALUES (7, '�����������',      1);
INSERT INTO topics (id, name, isNew) VALUES (8, '������ �����',     1);
INSERT INTO topics (id, name, isNew) VALUES (9, '����������������', 1);
INSERT INTO topics (id, name, isNew) VALUES (10, '�����',           1);
INSERT INTO topics (id, name, isNew) VALUES (11, '������',          1);
INSERT INTO topics (id, name, isNew) VALUES (12, '������',          1);
INSERT INTO topics (id, name, isNew) VALUES (13, 'Windows',         1);
INSERT INTO topics (id, name, isNew) VALUES (14, 'BSD/Linux',       1);
INSERT INTO topics (id, name, isNew) VALUES (15, '�������� ����',   1);
INSERT INTO topics (id, name, isNew) VALUES (16, '�����������',     1);
INSERT INTO topics (id, name, isNew) VALUES (17, '��������/�������',1);
INSERT INTO topics (id, name, isNew) VALUES (18, 'Temp',            1);
-- old
INSERT INTO topics (id, name, isNew) VALUES (11, '������/�������',  0);