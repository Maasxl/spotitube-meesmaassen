--DATABASE SCRIPT
DROP DATABASE spotitube;
CREATE DATABASE spotitube;
USE spotitube;

CREATE TABLE account (
  user VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (user));

CREATE TABLE accountToken (
  user VARCHAR(255) NOT NULL,
  token VARCHAR(255) NOT NULL,
  expiry_date DATETIME NOT NULL,
  PRIMARY KEY (token, user),
  CONSTRAINT fk_accountToken_user FOREIGN KEY (user) REFERENCES account (user) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE playlist (
  id INT NOT NULL AUTO_INCREMENT,
  user VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id, user),
  CONSTRAINT fk_playlist_user FOREIGN KEY (user) REFERENCES account (user) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE track (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  performer VARCHAR(255) NOT NULL,
  duration INT NOT NULL,
  url VARCHAR(255) NOT NULL,
  playcount INT NULL,
  PRIMARY KEY (id));

CREATE TABLE song (
  track_id INT NOT NULL,
  album VARCHAR(255) NULL,
  PRIMARY KEY (track_id),
  CONSTRAINT fk_song_track FOREIGN KEY (track_id) REFERENCES track (id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE video (
  track_id INT NOT NULL,
  publication_date DATE NULL,
  description VARCHAR(255) NULL,
  PRIMARY KEY (track_id),
  CONSTRAINT fk_video_track FOREIGN KEY (track_id) REFERENCES track (id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE trackInPlaylist (
  playlist_id INT NOT NULL,
  track_id INT NOT NULL,
  offline_available TINYINT NOT NULL,
  PRIMARY KEY (playlist_id, track_id),
  CONSTRAINT fk_trackInPlaylist_playlist FOREIGN KEY (playlist_id) REFERENCES playlist (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_trackInPlaylist_track FOREIGN KEY (track_id) REFERENCES track (id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE VIEW song_view AS
  SELECT track.id,
  track.title,
  track.performer,
  track.duration,
  track.url,
  track.playcount,
  song.album
  FROM song LEFT JOIN track ON song.track_id = track.id;

CREATE VIEW video_view AS
  SELECT track.id,
  track.title,
  track.performer,
  track.duration,
  track.url,
  track.playcount,
  video.publication_date,
  video.description
  FROM video LEFT JOIN track ON video.track_id = track.id;

INSERT INTO account (user, password) VALUES ('mees' ,'meespass'), ('theo', 'theopass');

INSERT INTO track (title, performer, duration, url, playcount) VALUES
  ('Without Me', 'Halsey', 203, 'https://www.youtube.com/watch?v=aYr4fDuLhXg', 2),
  ('Taki Taki', 'DJ Snake', 231, 'https://www.youtube.com/watch?v=ixkoVwKQaJg', 55),
  ('Happier', 'Marshmello', 233, 'https://www.youtube.com/watch?v=m7Bc3pLyij0', 34),
  ('Check This Out', 'Marshmello', 192, 'https://www.youtube.com/watch?v=D8tA6KGgmJE', 87),
  ('Bohemian Rhapsody', 'Queen', 366, 'https://www.youtube.com/watch?v=fJ9rUzIMcZQ', 254),
  ('Dont Stop Me Now', 'Queen', 217, 'https://www.youtube.com/watch?v=HgzGwKwLmgM', 123),
  ('Start Me Up', 'The Rolling Stones', 222, 'https://www.youtube.com/watch?v=SGyOaCXr8Lw', 4),
  ('SICKO MODE', 'Travis Scott', 322, 'https://www.youtube.com/watch?v=6ONRf7h3Mdk', 43);

INSERT INTO playlist (user, name) VALUES
  ('mees', 'Top List'),
  ('theo', 'Rock');

INSERT INTO trackInPlaylist (playlist_id, track_id, offline_available) VALUES
  (1,1,0),
  (1,2,0),
  (1,3,0),
  (1,4,0),
  (1,8,0),
  (2,5,1),
  (2,6,1),
  (2,7,1);

INSERT INTO song (track_id, album) VALUES
  (5,'Rock'),
  (6,'Rock'),
  (7,'Rock');

INSERT INTO video (track_id, publication_date, description) VALUES
  (1, '2018-10-04', '"Without Me" available now on all platforms'),
  (2, '2018-10-09', 'Stream and download Taki Taki now!'),
  (3, '2018-09-24', 'Marshmello ft. Bastille - Happier (Official Music Video)'),
  (4, '2018-10-17', 'Marshmello - Check This Out (Official Music Video)'),
  (8, '2018-10-19', 'ASTROWORLD OUT NOW');