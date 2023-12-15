CREATE TABLE Team (
                      team_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      team_name VARCHAR(20),
                      badge VARCHAR(50),
                      teamScore INT
);

CREATE TABLE Partidas (
                         match_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                         word VARCHAR(20),
                         matchScore INT,
                         max_tries INT,
                         matchDate TIMESTAMP
);

CREATE TABLE Word (
                      word_id INT PRIMARY KEY AUTO_INCREMENT,
                      word VARCHAR(30) NOT NULL
);

CREATE TABLE Game (
                      game_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      difficulty VARCHAR(10) CHECK (difficulty IN ('Easy', 'Normal', 'Hard')),
                      description VARCHAR(255),
                      max_Tries INTEGER CHECK (max_Tries BETWEEN 0 AND 5)
);

CREATE TABLE Game_Has_Word (
                           game_id INT NOT NULL,
                           word_id INT NOT NULL,
                           word_difficulty VARCHAR(10) CHECK (word_difficulty IN ('Easy', 'Normal', 'Hard')),
                           PRIMARY KEY (game_id, word_id),
                           FOREIGN KEY (game_id) REFERENCES Game(game_id),
                           FOREIGN KEY (word_id) REFERENCES Word(word_id)
);

CREATE TABLE Player (
                        player_id INT PRIMARY KEY AUTO_INCREMENT,
                        score INT,
                        pfp VARCHAR(100),
                        match_id INT NOT NULL,
                        team_id INT NOT NULL,
                        FOREIGN KEY (match_id) REFERENCES Partidas(match_id),
                        FOREIGN KEY (team_id) REFERENCES Team(team_id)
);
INSERT INTO Team (team_name, badge, teamScore)
VALUES
  ('Team A', 'badge_a.png', 0),
  ('Team B', 'badge_b.png', 0);

INSERT INTO Partidas (word, matchScore, max_tries, matchDate)
VALUES
  ('example1', 1000, 3, CURRENT_TIMESTAMP),
  ('example2', 1, 4, CURRENT_TIMESTAMP);

INSERT INTO Word (word)
VALUES
  ('apple'),
  ('banana'),
  ('orange'),
  ('grape'),
  ('strawberry');

INSERT INTO Game (difficulty, description, max_Tries)
VALUES
  ('Easy', 'Easy game description', 3),
  ('Normal', 'Normal game description', 4);

INSERT INTO Game_Has_Word (game_id, word_id, word_difficulty)
VALUES
  (1, 1, 'Easy'),
  (2, 2, 'Normal');

INSERT INTO Player (score, pfp, match_id, team_id)
VALUES
  (0, 'player1.png', 1, 1),
  (0, 'player2.png', 2, 2);

-- Update Player table with the sum of match scores for each player
UPDATE Player
SET score = (
  SELECT COALESCE(SUM(matchScore), 0)
  FROM Partidas
  WHERE Partidas.match_id = Player.match_id
)
WHERE Player.player_id IN (SELECT DISTINCT player_id FROM Partidas);

-- Update Team table with the sum of player scores for each team
UPDATE Team
SET teamScore = (
  SELECT SUM(score)
  FROM Player
  WHERE Player.team_id = Team.team_id
)
WHERE Team.team_id IN (SELECT DISTINCT team_id FROM Player);