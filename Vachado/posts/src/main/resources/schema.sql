CREATE TABLE IF NOT EXISTS `post` (
  `post_number` int AUTO_INCREMENT  PRIMARY KEY,
  `title` varchar(100) NOT NULL,
  `content` varchar(256) NOT NULL,
  `likes` int DEFAULT NULL,
  `account_number` int DEFAULT NULL ,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `post_likers` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    post_number INT,
    account_number INT,
    CONSTRAINT unique_post_account_number UNIQUE (post_number, account_number)
);




