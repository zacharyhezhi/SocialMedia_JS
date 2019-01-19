INSERT INTO admin_profile VALUES ('root', 'root', 'unswbook.server@gmail.com');

INSERT INTO user_profile (username) VALUES ('');

INSERT INTO post (content) VALUES ('');

INSERT INTO operation (description) VALUES ('JOIN');
INSERT INTO operation (description) VALUES ('REQUEST');
INSERT INTO operation (description) VALUES ('FRIEND');
INSERT INTO operation (description) VALUES ('POST');
INSERT INTO operation (description) VALUES ('LIKE');
INSERT INTO operation (description) VALUES ('BULLY');

INSERT INTO entity_store VALUES (1, 'type', 'bidirectionalLink');
INSERT INTO entity_store VALUES (1, 'class', 'edge');
INSERT INTO entity_store VALUES (1, 'label', 'friendOf');
INSERT INTO entity_store VALUES (2, 'type', 'unidirectionalLink');
INSERT INTO entity_store VALUES (2, 'class', 'edge');
INSERT INTO entity_store VALUES (2, 'label', 'posted');
INSERT INTO entity_store VALUES (3, 'type', 'unidirectionalLink');
INSERT INTO entity_store VALUES (3, 'class', 'edge');
INSERT INTO entity_store VALUES (3, 'label', 'liked');
