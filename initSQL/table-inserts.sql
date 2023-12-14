-- Insert data into Team table
INSERT INTO `words-API`.`Team` (`id`, `name`, `score`, `badge`) VALUES
(1, 'Team A', 100, 'badge1'),
(2, 'Team B', 80, 'badge2'),
(3, 'Team C', 120, 'badge3');

-- Insert data into Player table
INSERT INTO `words-API`.`Player` (`id`, `user`, `score`, `pfp`, `Team_id`) VALUES
(1, 'Player 1', 50, 'pfp1', 1),
(2, 'Player 2', 40, 'pfp2', 1),
(3, 'Player 3', 60, 'pfp3', 2);

-- Insert data into Game table
INSERT INTO `words-API`.`Game` (`id`, `Game_difficulty`, `max_tries`, `description`) VALUES
(1, 'easy', '10', 'Easy game'),
(2, 'medium', '8', 'Medium game'),
(3, 'hard', '6', 'Hard game');

-- Insert data into Match table
INSERT INTO `words-API`.`Match` (`id`, `word`, `tries`, `score`, `date`, `Player_id`, `Player_Team_id`, `Game_id`) VALUES
(1, 'Word 1', 8, 70, '2023-12-13', 1, 1, 1),
(2, 'Word 2', 6, 90, '2023-12-14', 2, 1, 2),
(3, 'Word 3', 10, 60, '2023-12-15', 3, 2, 3);

-- Insert data into Word table
INSERT INTO `words-API`.`Word` (`id`, `word`) VALUES
(1, 'Apple'),
(2, 'Banana'),
(3, 'Orange');

-- Insert data into Game_has_Word table
INSERT INTO `words-API`.`Game_has_Word` (`Game_id`, `Word_id`, `wordDifficulty`) VALUES
(1, 1, 'easy'),
(2, 2, 'medium'),
(3, 3, 'hard');
