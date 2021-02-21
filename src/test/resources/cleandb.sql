DELETE FROM artist_engagement;
DELETE FROM artist;
DELETE FROM user;
INSERT INTO user VALUES (1,'jcoyne','test@test.test','supersecret1'),(2,'fhensen','test@test.test','supersecret2'),(3,'bcurry','test@test.test','supersecret3'),(4,'kmack','test@test.test','supersecret4'),(5,'dklein','test@test.test','supersecret5'),(6,'dtillman','test@test.test','supersecret6');
INSERT INTO artist VALUES (1, 'soundcloudId100', 'DJ Test', 'some location'), (2, 'soundcloudId200', 'Ipsum Lorem', 'some other location');
INSERT INTO artist_engagement VALUES (1, 1, '2020-01-01 10:10:10'), (2, 1, '2020-01-01 10:10:10');
