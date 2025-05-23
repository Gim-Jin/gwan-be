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












