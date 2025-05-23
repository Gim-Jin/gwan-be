-- 일단 유저가 잇고

INSERT INTO `users`
    (login_id, password, nickname, email, role, name)
VALUES ('admin', 'admin', 'admin', 'admin@admin.com', 'ADMIN', '관리자1');

INSERT INTO `users`
    (login_id, password, nickname, email, role, name)
VALUES ('user1', 'user1', 'user1', 'user1@user.com', 'GENERAL', '유저1');

INSERT INTO `users`
    (login_id, password, nickname, email, role, name)
VALUES ('user2', 'user2', 'user2', 'user2@user.com', 'GENERAL', '유저2');

INSERT INTO `users`
    (login_id, password, nickname, email, role, name)
VALUES ('user3', 'user3', 'user3', 'user3@user.com', 'GENERAL', '유저3');

INSERT INTO `users`
    (login_id, password, nickname, email, role, name)
VALUES ('user4', 'user4', 'user4', 'user4@user.com', 'GENERAL', '유저4');

-- 영자는 영상을 추가함. 그 전에 target이라는 카테고리 중 하나 또는 여러개를 고를 수 이쓰~

-- targets 더미
INSERT INTO targets (name)
VALUES ('목');
INSERT INTO targets (name)
VALUES ('어깨');
INSERT INTO targets (name)
VALUES ('등');
INSERT INTO targets (name)
VALUES ('무릎');
INSERT INTO targets (name)
VALUES ('발목');
INSERT INTO targets (name)
VALUES ('허리');
INSERT INTO targets (name)
VALUES ('팔꿈치');

-- 영상 더미 들어가면서 같이 target이랑도 맵핑 되어야함.
INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]', 'https://www.youtube.com/embed/gMaB-fG4u4g',
        'gMaB-fG4u4g', 'ThankyouBUBU 채널의 전신 운동 영상입니다.', NOW(), NOW()),
       (1, '하루 15분! 전신 칼로리 불태우는 다이어트 운동', 'https://www.youtube.com/embed/swRNeYw1JkY',
        'swRNeYw1JkY', 'ThankyouBUBU 채널의 전신 운동 영상입니다.', NOW(), NOW()),
       (1, '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]', 'https://www.youtube.com/embed/54tTYO-vU2E',
        '54tTYO-vU2E', 'ThankyouBUBU 채널의 상체 운동 영상입니다.', NOW(), NOW()),
       (1, '상체비만 다이어트 최고의 운동 [상체 핵매운맛]', 'https://www.youtube.com/embed/QqqZH3j_vH0', 'QqqZH3j_vH0',
        'ThankyouBUBU 채널의 상체 운동 영상입니다.', NOW(), NOW()),
       (1, '하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]', 'https://www.youtube.com/embed/tzN6ypk6Sps',
        'tzN6ypk6Sps', '김강민 채널의 하체 운동 영상입니다.', NOW(), NOW()),
       (1, '저는 하체 식주의자 입니다', 'https://www.youtube.com/embed/u5OgcZdNbMo', 'u5OgcZdNbMo',
        'GYM종국 채널의 하체 운동 영상입니다.', NOW(), NOW()),
       (1, '11자복근 복부 최고의 운동 [복근 핵매운맛]', 'https://www.youtube.com/embed/PjGcOP-TQPE', 'PjGcOP-TQPE',
        'ThankyouBUBU 채널의 복부 운동 영상입니다.', NOW(), NOW()),
       (1, '(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)', 'https://www.youtube.com/embed/7TLk7pscICk',
        '7TLk7pscICk', 'SomiFit 채널의 복부 운동 영상입니다.', NOW(), NOW());

-- 타겟 맵핑 쿼리
INSERT INTO video_targets (exercise_video_id, target_id)
VALUES (1, 7),
       (1, 6),
       (1, 5),
       (1, 4),
       (1, 3),
       (1, 2),
       (1, 1),
       (2, 7),
       (2, 6),
       (2, 5),
       (2, 4),
       (2, 3),
       (2, 2),
       (2, 1),
       (3, 7),
       (3, 6),
       (3, 3),
       (3, 2),
       (4, 7),
       (4, 6),
       (4, 3),
       (4, 2),
       (5, 5),
       (5, 4),
       (6, 5),
       (6, 4),
       (7, 6),
       (8, 6);

-- 이제 유저는 영상을 보면서 좋아요를 누르겄지..
INSERT INTO likes (user_id, exercise_video_id)
VALUES (2, 1),
       (2, 2),
       (2, 5),
       (2, 6),
       (3, 5),
       (3, 1),
       (3, 7),
       (3, 4),
       (4, 1),
       (4, 2),
       (4, 3),
       (4, 4),
       (4, 5),
       (4, 6),
       (4, 7),
       (4, 8),
       (5, 2),
       (5, 6),
       (5, 2);


-- 유저는 댓글도 달아둠.
INSERT INTO comments (user_id, exercise_video_id, content)
VALUES (2, 1, '헥헥 너무 힘들어요 🥵'),
       (3, 1, 'ㅋㅋ 그거 님이 저질 체력이라 그럼 ㅋ'),
       (2, 1, '? 왜 갑자기 시비? 님 부모가 그리 가르침?'),
       (3, 1, 'ㅋ 긁혔죠?'),
       (4, 1, '심심한 @ㅗ빠들 내 프로필 당장 클릭해 ㅂr 🥵');

-- 이제 유저는 운동 프로그램을 처방 받을 수 있음.
INSERT INTO rehab_programs (user_id, part, question, prescription)
VALUES (2, 'KNEE', '무릎이 자주 아파요', '스쿼트와 무릎 안정화 운동 처방'),
       (3, 'WRIST', '손목이 자주 뻐근해요', '손목 회전 운동과 스트레칭'),
       (4, 'NECK', '장시간 컴퓨터 사용 후 목 통증', '거북목 교정 운동과 자세 개선');

