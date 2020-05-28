INSERT INTO user (id, name, password) VALUES (1, 'user1', '$2a$04$GJ85Ihcglhbqac2zc3z3A.C3v55FMmN8.qGQ8FlNBCuyLtQ5/TyMO');
INSERT INTO user (id, name, password) VALUES (2, 'user2', '$2a$04$XWpgKkCQaVRuXjB5f1hzt.pDa2NAzntroH3bELICZy8R8Q0L0SShO');
INSERT INTO user (id, name, password) VALUES (3, 'user3', '$2a$04$bmmWXec6YkFIesIxv2gx7.NbxRcMJ4UoRKT9Qoqk09pKegfcr3Zla');
INSERT INTO user (id, name, password) VALUES (4, 'user4', '$2a$10$c7u7NqSxIEPobDAYfJiM.ueUWuDDk5lSkvxWi87UznOKOFV.mmp2C');
INSERT INTO user (id, name, password) VALUES (5, 'user5', '$2a$10$xoTjYxnlR3upOAn/h30F1u9gVjIHH0C8zmCMhsKlToncJpyY.rEcu');
INSERT INTO user (id, name, password) VALUES (6, 'user6', '$2a$10$I6.VJuMdnjnh7VsNgPMZfeGc810pjVFD6qUCkKaNy5u.DosrCS6yG');
INSERT INTO user (id, name, password) VALUES (7, 'user7', '$2a$10$tPAe7Gg7dh7EgBksPlalY.jsQpxzpS1XbdiIW62O6vuN2zO3wpxYi');

INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'EMPLOYEE');

INSERT INTO area (id, name) VALUES (1, 'Team player');
INSERT INTO area (id, name) VALUES (2, 'Technical referent');
INSERT INTO area (id, name) VALUES (3, 'Key Player');
INSERT INTO area (id, name) VALUES (4, 'Client satisfaction');
INSERT INTO area (id, name) VALUES (5, 'Motivation');
INSERT INTO area (id, name) VALUES (6, 'Fun');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (6, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (7, 2);

INSERT INTO vote (id, issuer_id, recipient_id, area_id, date) VALUES (1, 2, 4, 1, '2020-05-20');
INSERT INTO vote (id, issuer_id, recipient_id, area_id, date) VALUES (2, 3, 4, 1, '2020-04-20');
INSERT INTO vote (id, issuer_id, recipient_id, area_id, date) VALUES (3, 4, 2, 1, '2020-04-20');
INSERT INTO vote (id, issuer_id, recipient_id, area_id, date) VALUES (4, 5, 4, 1, '2020-05-20');
INSERT INTO vote (id, issuer_id, recipient_id, area_id, date) VALUES (5, 6, 2, 1, '2020-05-20')
INSERT INTO vote (id, issuer_id, recipient_id, area_id, date) VALUES (6, 7, 4, 1, '2020-04-20');
INSERT INTO vote (id, issuer_id, recipient_id, area_id, date) VALUES (7, 3, 4, 2, '2020-04-20');
INSERT INTO vote (id, issuer_id, recipient_id, area_id, date) VALUES (8, 3, 5, 3, '2020-05-20');
INSERT INTO vote (id, issuer_id, recipient_id, area_id, date) VALUES (9, 4, 2, 3, '2020-05-20');
INSERT INTO vote (id, issuer_id, recipient_id, area_id, date) VALUES (10, 5, 2, 3, '2020-05-20');
