DROP TABLE IF EXISTS `likes`;
DROP TABLE IF EXISTS `routines_excercises`;
DROP TABLE IF EXISTS `exercise_videos`;
DROP TABLE IF EXISTS `routines`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `exercises`;
DROP TABLE IF EXISTS `targets`;
DROP TABLE IF EXISTS `gen_ai_repsponses`;
DROP TABLE IF EXISTS `comments`;
DROP TABLE IF EXISTS `exercise_targets`;
DROP TABLE IF EXISTS `video_targets`;

show tables;

CREATE TABLE `users` (
    `user_id` BIGINT NOT NULL AUTO_INCREMENT,
    `login_id` VARCHAR(10) NOT NULL,
    `password` TEXT NOT NULL COMMENT 'Bcrypt 해시 처리',
    `nickname` VARCHAR(10) NOT NULL,
    `email` VARCHAR(30) NOT NULL,
    `role` ENUM('GENERAL', 'ADMIN') NOT NULL DEFAULT 'GENERAL' COMMENT 'GENERAL, ADMIN',
    `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
    `name` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE `targets` (
    `target_id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`target_id`)
);

CREATE TABLE `exercises` (
    `exercise_id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `level` INT NOT NULL,
    PRIMARY KEY (`exercise_id`)
);

CREATE TABLE `exercise_videos` (
    `exercise_video_id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL COMMENT '영상URL 작성의 주체는?',
    `title` VARCHAR(50) NOT NULL,
    `url` VARCHAR(255) NOT NULL COMMENT 'URL파싱처리',
    `youtube_id` TEXT NOT NULL,
    `description` TEXT NULL COMMENT '영상에 대한 간단한 설명',
    `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
    `updated_at` TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`exercise_video_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `video_targets` (
    `video_target_id` BIGINT NOT NULL AUTO_INCREMENT,
    `exercise_video_id` BIGINT NOT NULL,
    `target_id` BIGINT NOT NULL,
    PRIMARY KEY (`video_target_id`),
    FOREIGN KEY (`exercise_video_id`) REFERENCES `exercise_videos` (`exercise_video_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`target_id`) REFERENCES `targets` (`target_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `exercise_targets` (
    `exercise_target_id` BIGINT NOT NULL AUTO_INCREMENT,
    `target_id` BIGINT NOT NULL,
    `exercise_id` BIGINT NOT NULL,
    PRIMARY KEY (`exercise_target_id`),
    FOREIGN KEY (`target_id`) REFERENCES `targets` (`target_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`exercise_id`) REFERENCES `exercises` (`exercise_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `comments` (
    `comment_id` BIGINT NOT NULL AUTO_INCREMENT,
    `exercise_video_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `content` TEXT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
    `updated_at` TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`comment_id`),
    FOREIGN KEY (`exercise_video_id`) REFERENCES `exercise_videos` (`exercise_video_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `gen_ai_repsponses` (
    `response_id` BIGINT NOT NULL AUTO_INCREMENT,
    `question` TEXT NOT NULL COMMENT '전체 문답내용',
    `answer` TEXT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`response_id`)
);

CREATE TABLE `routines` (
    `routine_id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `part` ENUM('NECK', 'SHOULDER', 'BACK', 'KNEE', 'ANKLE', 'WRIST', 'ELBO') NOT NULL DEFAULT 'NECK',
    `question` TEXT NOT NULL,
    `prescription` TEXT NOT NULL,
    `isdone` BOOLEAN NOT NULL DEFAULT false,
    `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
    `refresh_at` TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`routine_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `routines_excercises` (
    `routine_exercise_id` BIGINT NOT NULL AUTO_INCREMENT,
    `routine_id` BIGINT NOT NULL,
    `exercise_id` BIGINT NOT NULL,
    `sets` INT NOT NULL,
    `reps` INT NOT NULL,
    `rest_period` INT NOT NULL,
    `order` INT NOT NULL COMMENT '운동 수행 순서',
    PRIMARY KEY (`routine_exercise_id`),
    FOREIGN KEY (`routine_id`) REFERENCES `routines` (`routine_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`exercise_id`) REFERENCES `exercises` (`exercise_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `likes` (
    `like_id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `exercise_video_id` BIGINT NOT NULL,
    PRIMARY KEY (`like_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`exercise_video_id`) REFERENCES `exercise_videos` (`exercise_video_id`) ON DELETE CASCADE ON UPDATE CASCADE
);




-- users 더미
INSERT INTO users (login_id, password, nickname, email, role, created_at, name)
VALUES ('test', 'test', 'Tester', 'test@example.com', 'GENERAL', NOW(), '테스트 유저');

-- targets 더미
INSERT INTO targets (name)
VALUES ('SHOULDER');

-- exercises 더미
INSERT INTO exercises (name, level)
VALUES ('Shoulder Press', 2);

-- exercise_videos 더미
INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '어깨 재활 루틴', 'https://youtube.com/watch?v=abc123', 'abc123', '어깨 통증 완화를 위한 운동입니다.', NOW(), NOW());

-- video_targets 더미
INSERT INTO video_targets (exercise_video_id, target_id)
VALUES (1, 1);

-- exercise_targets 더미
INSERT INTO exercise_targets (target_id, exercise_id)
VALUES (1, 1);

-- comments 더미
INSERT INTO comments (exercise_video_id, user_id, content, created_at, updated_at)
VALUES (1, 1, '이 영상 너무 좋아요!', NOW(), NOW());

-- gen_ai_repsponses 더미
INSERT INTO gen_ai_repsponses (question, answer, created_at)
VALUES ('어깨 통증이 심해요', '어깨 스트레칭을 추천드립니다.', NOW());

-- routines 더미
INSERT INTO routines (user_id, part, question, prescription, isdone, created_at, refresh_at)
VALUES (1, 'SHOULDER', '어깨가 아파요', '어깨 스트레칭과 강화 운동을 하세요.', false, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY));

-- routines_excercises 더미
INSERT INTO routines_excercises (routine_id, exercise_id, sets, reps, rest_period, `order`)
VALUES (1, 1, 3, 15, 60, 1);

-- likes 더미
INSERT INTO likes (user_id, exercise_video_id)
VALUES (1, 1);

