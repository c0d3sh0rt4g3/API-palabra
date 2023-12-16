-- Drop the tables
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS game_has_word;
DROP TABLE IF EXISTS matches;
DROP TABLE IF EXISTS word;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS team;

CREATE TABLE team (
                      team_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      team_name VARCHAR(20),
                      badge VARCHAR(50),
                      team_score INT
);

CREATE TABLE matches (
                         match_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                         word VARCHAR(20),
                         match_score INT,
                         max_tries INT,
                         match_date TIMESTAMP
);

CREATE TABLE word (
                      word_id INT PRIMARY KEY AUTO_INCREMENT,
                      word VARCHAR(30) NOT NULL
);

CREATE TABLE game (
                      game_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      game_name VARCHAR(10),
                      difficulty VARCHAR(10) CHECK (difficulty IN ('Easy', 'Normal', 'Hard')),
                      description VARCHAR(255),
                      max_Tries INTEGER CHECK (max_Tries BETWEEN 0 AND 5)
);

CREATE TABLE game_has_word (
                           game_id INT NOT NULL,
                           word_id INT NOT NULL,
                           word_difficulty VARCHAR(10) CHECK (word_difficulty IN ('Easy', 'Normal', 'Hard')),
                           PRIMARY KEY (game_id, word_id),
                           FOREIGN KEY (game_id) REFERENCES game(game_id),
                           FOREIGN KEY (word_id) REFERENCES word(word_id)
);

CREATE TABLE player (
                        player_id INT PRIMARY KEY AUTO_INCREMENT,
                        score INT,
                        player_name VARCHAR(100),
                        pfp VARCHAR(100),
                        match_id INT NOT NULL,
                        team_id INT NOT NULL,
                        FOREIGN KEY (match_id) REFERENCES matches(match_id),
                        FOREIGN KEY (team_id) REFERENCES team(team_id)
);
INSERT INTO team (team_name, badge, team_score)
VALUES
  ('team A', 'badge_a.png', 0),
  ('team B', 'badge_b.png', 0);

INSERT INTO matches (word, match_score, max_tries, match_date)
VALUES
  ('example1', 1000, 3, CURRENT_TIMESTAMP),
  ('example2', 1, 4, CURRENT_TIMESTAMP);

INSERT INTO word (word)
VALUES
  ('apple'),
  ('banana'),
  ('orange'),
  ('grape'),
  ('strawberry');

INSERT INTO game (game_name, difficulty, description, max_Tries)
VALUES
  ('Wordle','Easy', 'Easy game description', 3),
  ('Hang man','Normal', 'Normal game description', 4);

INSERT INTO game_has_word (game_id, word_id, word_difficulty)
VALUES
  (1, 1, 'Easy'),
  (2, 2, 'Normal');

INSERT INTO player (score, player_name, pfp, match_id, team_id)
VALUES
  (0, 'player1', 'player1.png', 1, 1),
  (0, 'player2', 'player2.png', 2, 2);

-- Update player table with the sum of match scores for each player
UPDATE player
SET score = (
  SELECT COALESCE(SUM(match_score), 0)
  FROM matches
  WHERE matches.match_id = player.match_id
)
WHERE player.player_id IN (SELECT DISTINCT player_id FROM matches);

-- Update team table with the sum of player scores for each team
UPDATE team
SET team_score = (
  SELECT SUM(score)
  FROM player
  WHERE player.team_id = team.team_id
)
WHERE team.team_id IN (SELECT DISTINCT team_id FROM player);