

USE velo_storage;

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
INSERT INTO topics (id, name, isNew) VALUES (1, '����',           1);
INSERT INTO topics (id, name, isNew) VALUES (2, '����������',           1);
INSERT INTO topics (id, name, isNew) VALUES (3, '�����',        1);
INSERT INTO topics (id, name, isNew) VALUES (4, '�����������',         1);
INSERT INTO topics (id, name, isNew) VALUES (5, '������',        1);
INSERT INTO topics (id, name, isNew) VALUES (6, 'F.A.Q.',      1);
INSERT INTO topics (id, name, isNew) VALUES (7, '����������',      1);
INSERT INTO topics (id, name, isNew) VALUES (8, '��������/�������',      1);
INSERT INTO topics (id, name, isNew) VALUES (9, '������',      1);

-- old