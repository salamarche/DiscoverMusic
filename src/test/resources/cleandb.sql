DELETE FROM artist_location;
DELETE FROM artist_engagement;
DELETE FROM artist;
DELETE FROM user;
DELETE FROM city;
DELETE FROM region;
DELETE FROM country;
INSERT INTO country VALUES (1, 'United States', 'USA');
INSERT INTO country VALUES (2, 'Canada', 'CAN');
INSERT INTO region VALUES (1, 'Wisconsin', 1);
INSERT INTO region VALUES (2, 'New York', 1);
INSERT INTO region VALUES (3, 'Quebec', 2);
INSERT INTO city VALUES (1, 'Montreal', 'latcoord', 'lngcoord', 3);
INSERT INTO city VALUES (2, 'Madison', 'latcoord', 'longcoord', 1);
INSERT INTO user VALUES (1,'jcoyne','test@test.test','user');
INSERT INTO user VALUES (2,'fhensen','test@test.test','user');
INSERT INTO user VALUES (3,'bcurry','test@test.test', 'user');
INSERT INTO user VALUES (4,'kmack','test@test.test','user');
INSERT INTO user VALUES (5,'dklein','test@test.test','user');
INSERT INTO user VALUES (6,'dtillman','test@test.test','user');
INSERT INTO artist VALUES (1, 'id1','DJ Test', 'photo-url','artist description', 'href');
INSERT INTO artist VALUES (2, 'id2', 'Ipsum Lorem', 'photo-url','artist description', 'href');
INSERT INTO artist_location VALUES (1, 1);
INSERT INTO artist_location VALUES (1, 2);
INSERT INTO artist_location VALUES (2, 2);
INSERT INTO artist_engagement VALUES (1, 1, 1, '2020-01-01 10:10:10');
INSERT INTO artist_engagement VALUES (2, 2, 1, '2020-01-01 10:10:10');
INSERT INTO artist_engagement VALUES (3, 1, 2, '2020-01-01 10:10:10');
INSERT INTO artist_engagement VALUES (4, 1, 3, '2020-01-01 10:10:10');
