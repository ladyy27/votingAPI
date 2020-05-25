INSERT INTO user (id, name, password) VALUES (1, 'user1', '$2a$04$GJ85Ihcglhbqac2zc3z3A.C3v55FMmN8.qGQ8FlNBCuyLtQ5/TyMO');
INSERT INTO user (id, name, password) VALUES (2, 'user2', '$2a$04$XWpgKkCQaVRuXjB5f1hzt.pDa2NAzntroH3bELICZy8R8Q0L0SShO');
INSERT INTO user (id, name, password) VALUES (3, 'user3', '$2a$04$bmmWXec6YkFIesIxv2gx7.NbxRcMJ4UoRKT9Qoqk09pKegfcr3Zla');

INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'EMPLOYEE');

INSERT INTO area (id, name) VALUES (1, 'Team player');
INSERT INTO area (id, name) VALUES (2, 'Technical referent');
INSERT INTO area (id, name) VALUES (3, 'Key Player');
INSERT INTO area (id, name) VALUES (4, 'Client satisfaction');
INSERT INTO area (id, name) VALUES (5, 'Motivation');
INSERT INTO area (id, name) VALUES (6, 'Fun');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 2);
