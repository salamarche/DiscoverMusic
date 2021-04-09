DELETE FROM city;
DELETE FROM region;
DELETE FROM country;

DELETE FROM artist_engagement;
DELETE FROM artist;
DELETE FROM user;


INSERT INTO country VALUES (1, 'United States', 'USA');
INSERT INTO country VALUES (2, 'Canada', 'CAN');
INSERT INTO region VALUES (1, 'Wisconsin', 1);
INSERT INTO region VALUES (2, 'New York', 1);
INSERT INTO region VALUES (3, 'Quebec', 2);
INSERT INTO city VALUES (1, 'Montreal', 'latcoord', 'lngcoord', 3);
INSERT INTO city VALUES (2, 'Madison', 'latcoord', 'longcoord', 1);


INSERT INTO user VALUES (1,'jcoyne','test@test.test','user'),(2,'fhensen','test@test.test','user'),(3,'bcurry','test@test.test', 'user'),(4,'kmack','test@test.test','user'),(5,'dklein','test@test.test','supersecret5','user'),(6,'dtillman','test@test.test','supersecret6','user');
INSERT INTO artist VALUES (1, 'soundcloudId100','DJ Test',1,'photo-url','artist description'), (2, 'soundcloudId200', 'Ipsum Lorem', 2,'photo-url','artist description');
INSERT INTO artist_engagement VALUES (1, 1, '2020-01-01 10:10:10'), (2, 1, '2020-01-01 10:10:10'), (1, 2, '2020-01-01 10:10:10'), (1, 3, '2020-01-01 10:10:10');
