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
DROP TABLE IF EXISTS `refresh_token`;

show
tables;

CREATE TABLE `users`
(
    `user_id`    BIGINT      NOT NULL AUTO_INCREMENT,
    `login_id`   VARCHAR(10) NOT NULL,
    `password`   TEXT        NOT NULL COMMENT 'Bcrypt 해시 처리',
    `nickname`   VARCHAR(10) NOT NULL,
    `email`      VARCHAR(30) NOT NULL,
    `role`       ENUM('GENERAL', 'ADMIN') NOT NULL DEFAULT 'GENERAL' COMMENT 'GENERAL, ADMIN',
    `created_at` TIMESTAMP   NOT NULL DEFAULT NOW(),
    `name`       VARCHAR(20) NOT NULL,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE `targets`
(
    `target_id` BIGINT       NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(255) NOT NULL,
    PRIMARY KEY (`target_id`)
);

CREATE TABLE `exercises`
(
    `exercise_id` BIGINT       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(200) NOT NULL,
    `level`       INT          NOT NULL,
    PRIMARY KEY (`exercise_id`)
);

CREATE TABLE `exercise_videos`
(
    `exercise_video_id` BIGINT       NOT NULL AUTO_INCREMENT,
    `user_id`           BIGINT       NOT NULL COMMENT '영상URL 작성의 주체는?',
    `title`             VARCHAR(50)  NOT NULL,
    `url`               VARCHAR(255) NOT NULL COMMENT 'URL파싱처리',
    `youtube_id`        TEXT         NOT NULL,
    `description`       TEXT NULL COMMENT '영상에 대한 간단한 설명',
    `created_at`        TIMESTAMP    NOT NULL DEFAULT NOW(),
    `updated_at`        TIMESTAMP    NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`exercise_video_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `video_targets`
(
    `video_target_id`   BIGINT NOT NULL AUTO_INCREMENT,
    `exercise_video_id` BIGINT NOT NULL,
    `target_id`         BIGINT NOT NULL,
    PRIMARY KEY (`video_target_id`),
    FOREIGN KEY (`exercise_video_id`) REFERENCES `exercise_videos` (`exercise_video_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`target_id`) REFERENCES `targets` (`target_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `exercise_targets`
(
    `exercise_target_id` BIGINT NOT NULL AUTO_INCREMENT,
    `target_id`          BIGINT NOT NULL,
    `exercise_id`        BIGINT NOT NULL,
    PRIMARY KEY (`exercise_target_id`),
    FOREIGN KEY (`target_id`) REFERENCES `targets` (`target_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`exercise_id`) REFERENCES `exercises` (`exercise_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `comments`
(
    `comment_id`        BIGINT    NOT NULL AUTO_INCREMENT,
    `exercise_video_id` BIGINT    NOT NULL,
    `user_id`           BIGINT    NOT NULL,
    `content`           TEXT      NOT NULL,
    `created_at`        TIMESTAMP NOT NULL DEFAULT NOW(),
    `updated_at`        TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`comment_id`),
    FOREIGN KEY (`exercise_video_id`) REFERENCES `exercise_videos` (`exercise_video_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `gen_ai_repsponses`
(
    `response_id` BIGINT    NOT NULL AUTO_INCREMENT,
    `question`    TEXT      NOT NULL COMMENT '전체 문답내용',
    `answer`      TEXT      NOT NULL,
    `created_at`  TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`response_id`)
);

CREATE TABLE `rehab_programs`
(
    `rehab_program_id`   BIGINT    NOT NULL AUTO_INCREMENT,
    `user_id`      BIGINT    NOT NULL,
    `part`         ENUM('NECK', 'SHOULDER', 'BACK', 'KNEE', 'ANKLE', 'WRIST', 'ELBO') NOT NULL DEFAULT 'NECK',
    `question`     TEXT      NOT NULL,
    `prescription` TEXT      NOT NULL,
    `isdone`       BOOLEAN   NOT NULL DEFAULT false,
    `created_at`   TIMESTAMP NOT NULL DEFAULT NOW(),
    `refresh_at`   TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`rehab_program_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `rehab_program_excercises`
(
    `rehab_program_exercise_id` BIGINT NOT NULL AUTO_INCREMENT,
    `rehab_program_id`          BIGINT NOT NULL,
    `exercise_id`         BIGINT NOT NULL,
    `sets`                INT    NOT NULL,
    `reps`                INT    NOT NULL,
    `rest_period`         INT    NOT NULL,
    `order`               INT    NOT NULL COMMENT '운동 수행 순서',
    PRIMARY KEY (`rehab_program_exercise_id`),
    FOREIGN KEY (`rehab_program_id`) REFERENCES `rehab_programs` (`rehab_program_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`exercise_id`) REFERENCES `exercises` (`exercise_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `likes`
(
    `like_id`           BIGINT NOT NULL AUTO_INCREMENT,
    `user_id`           BIGINT NOT NULL,
    `exercise_video_id` BIGINT NOT NULL,
    PRIMARY KEY (`like_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`exercise_video_id`) REFERENCES `exercise_videos` (`exercise_video_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE `refresh_tokens`
(
    `refresh_token_id` BIGINT       NOT NULL AUTO_INCREMENT,
    `user_id`          BIGINT       NOT NULL,
    `refresh_token`    VARCHAR(512) NOT NULL,
    `expires_at`       TIMESTAMP    NOT NULL,
    PRIMARY KEY (`refresh_token_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);


-- users 더미
INSERT INTO users (login_id, password, nickname, email, role, created_at, name)
VALUES ('test', 'test', 'Tester', 'test@example.com', 'GENERAL', NOW(), '테스트 유저');


INSERT INTO users (login_id, password, nickname, email, role, created_at, name)
VALUES ('test2', 'test2', 'Tester2', 'test@example.com', 'GENERAL', NOW(), '테스트 유저2');

-- targets 더미
INSERT INTO targets (name)
VALUES ('SHOULDER');

INSERT INTO targets (name) VALUES
                               ('Neck'),
                               ('Ankle'),
                               ('Wrist'),
                               ('Elbow'),
                               ('Hip');

-- exercises 더미
INSERT INTO exercises (name, level)
VALUES ('Shoulder Press', 2);

-- exercise_videos 더미
INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '어깨 재활 루틴', 'https://youtube.com/watch?v=abc123', 'abc123', '어깨 통증 완화를 위한 운동입니다.', NOW(),
        NOW());

INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '팔꿈치 재활 루틴', 'https://youtube.com/watch?v=abc123', 'abc123', '팔꿈치 통증 완화를 위한 운동입니다.', NOW(),
        NOW());

INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '발목 재활 루틴', 'https://youtube.com/watch?v=abc123', 'abc123', '발목 통증 완화를 위한 운동입니다.', NOW(),
        NOW());

INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1,'무릎 재활 루틴', 'https://youtube.com/watch?v=abc123', 'abc123', '무릎 통증 완화를 위한 운동입니다.', NOW(),
        NOW());

INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '목 재활 루틴', 'https://youtube.com/watch?v=abc123', 'abc123', '목 통증 완화를 위한 운동입니다.', NOW(),
        NOW());


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
INSERT INTO rehab_programs (user_id, part, question, prescription, isdone, created_at, refresh_at)
VALUES (1, 'SHOULDER', '어깨가 아파요', '어깨 스트레칭과 강화 운동을 하세요.', false, NOW(),
        DATE_ADD(NOW(), INTERVAL 7 DAY));

-- routines_excercises 더미
INSERT INTO rehab_program_excercises (rehab_program_id, exercise_id, sets, reps, rest_period, `order`)
VALUES (1, 1, 3, 15, 60, 1);

-- likes 더미
INSERT INTO likes (user_id, exercise_video_id)
VALUES (2, 1);

INSERT INTO likes (user_id, exercise_video_id)
VALUES (2, 2);



-- 사용자 추가
INSERT INTO users (login_id, password, nickname, email, role, name) VALUES
                                                                        ('user03', 'hashed_pw_04', 'Nick03', 'user03@example.com', 'GENERAL', 'User Three'),
                                                                        ('user04', 'hashed_pw_05', 'Nick04', 'user04@example.com', 'GENERAL', 'User Four'),
                                                                        ('user05', 'hashed_pw_06', 'Nick05', 'user05@example.com', 'GENERAL', 'User Five');

-- 운동 영상 (다양한 유저)
INSERT INTO exercise_videos (user_id, title, url, youtube_id, description) VALUES
                                                                               (3, 'Knee Pain Relief', 'http://youtube.com/vid3', 'ghi789', 'Help relieve knee pain'),
                                                                               (4, 'Stretching Basics', 'http://youtube.com/vid4', 'jkl012', 'Everyday stretching routine'),
                                                                               (5, 'Wrist Mobility', 'http://youtube.com/vid5', 'mno345', 'Improve wrist flexibility');

-- 영상 대상 매핑
INSERT INTO video_targets (exercise_video_id, target_id) VALUES
                                                             (3, 3), (4, 1), (5, 2);

-- 재활 프로그램
INSERT INTO rehab_programs (user_id, part, question, prescription) VALUES
                                                                       (3, 'KNEE', '무릎이 자주 아파요', '스쿼트와 무릎 안정화 운동 처방'),
                                                                       (4, 'WRIST', '손목이 자주 뻐근해요', '손목 회전 운동과 스트레칭'),
                                                                       (5, 'NECK', '장시간 컴퓨터 사용 후 목 통증', '거북목 교정 운동과 자세 개선');

-- 좋아요
INSERT INTO likes (user_id, exercise_video_id) VALUES
                                                   (3, 1), (4, 3), (5, 5);









