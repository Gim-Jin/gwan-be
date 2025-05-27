-- 일단 유저가 잇고

INSERT INTO `users`
    (login_id, password, nickname, email, role, name)
VALUES ('test', '$2a$10$f/2KuNX3rXk9riLLP0r/pOBPAj4ntUtXXfWhjfi8xSINVG.zkXhiC', 'admin',
        'admin@test.com', 'ADMIN', '관리자1');

INSERT INTO `users`
    (login_id, password, nickname, email, role, name)
VALUES ('user', '$2a$10$VoRGIZFtTDkiomO9NWgalOQ2irtVFX4iTsGxaQgFhfx57Ri7InNdK', 'user',
        'user1@test.com', 'GENERAL', '유저1');

INSERT INTO `users`
    (login_id, password, nickname, email, role, name)
VALUES ('testps', '$2a$10$OkTGwfVJrjLhGH59maCTYOuMQ.QEHLCFTogajnhsP63C2ZIIMwW.i', 'advisor',
        'testps@test.com', 'PRESCRIBER', '유저2');


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
INSERT INTO targets (name)
VALUES ('손목');

-- 영상 더미 들어가면서 같이 target이랑도 맵핑 되어야함.

-- 팔꿈치 10개
INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '팔꿈치인대재활" 집에서 하는 각도 재활 5단계 운동을 소개합니다!!', 'https://www.youtube.com/watch?v=7VYfGy3ZQF4',
        '7VYfGy3ZQF4', '팔꿈치 인대 수술 후 집에서 쉽게 따라 할 수 있는 5단계 재활 운동을 소개합니다.', NOW(), NOW()),
       (1, '팔꿈치 안 펴질때, 재활 운동법, (팔꿈치 골절 깁스 후 재활 #2)', 'https://www.youtube.com/watch?v=q21_OvekpX0',
        'q21_OvekpX0', '팔꿈치 골절 후 깁스 제거 후의 재활 운동법을 안내합니다.', NOW(), NOW()),
       (1, '집에서도 혼자 할 수 있는 팔꿈치 재활운동', 'https://www.youtube.com/watch?v=PoF9l2MCDNA', 'PoF9l2MCDNA',
        '수술 후 빠른 회복을 위한 팔꿈치 재활운동법을 집에서 따라할 수 있도록 안내합니다.', NOW(), NOW()),
       (1, '[재활 교육 영상] 팔꿈치 밴드 운동 - 한림대춘천성심병원', 'https://www.youtube.com/watch?v=XO46iGWLatc',
        'XO46iGWLatc', '팔꿈치 밴드를 이용한 재활 운동 방법을 설명합니다.', NOW(), NOW()),
       (1, '팔꿈치 통증잡는 운동법 (서울마디튼튼재활의학과 민태준)', 'https://www.youtube.com/watch?v=xxcFCqQy-sM',
        'xxcFCqQy-sM', '팔꿈치 통증을 완화하기 위한 운동법을 소개합니다.', NOW(), NOW()),
       (1, '[부평세림병원] 손목 및 팔꿈치 수술 후 재활, 통증 개선에 도움 되는 운동',
        'https://www.youtube.com/watch?v=UBKqRgQJoW4', 'UBKqRgQJoW4',
        '손목 및 팔꿈치 수술 후 재활과 통증 개선에 도움이 되는 운동을 안내합니다.', NOW(), NOW()),
       (1, '[더서울 시민강좌] 더 나은 삶을 위한 손목과 팔꿈치 재활', 'https://www.youtube.com/watch?v=sxc5y5H_o6w',
        'sxc5y5H_o6w', '손목과 팔꿈치 재활에 대한 시민 강좌 내용을 담고 있습니다.', NOW(), NOW()),
       (1, '팔꿈치 통증 재활에 빠질 수 없는 운동법을 알려드립니다!', 'https://www.youtube.com/watch?v=ag0_sU2WDnw',
        'ag0_sU2WDnw', '팔꿈치 통증 재활에 효과적인 운동법을 소개합니다.', NOW(), NOW()),
       (1, '팔꿈치 스트레칭 및 근력 운동_청담마디신경외과 도수재활센터', 'https://www.youtube.com/watch?v=4___VMhwvPw',
        '4___VMhwvPw', '프롤로 치료 후 팔꿈치 스트레칭 및 근력 운동 방법을 안내합니다.', NOW(), NOW()),
       (1, '팔꿈치 골절 재활 ｜초기 1단계 홈 트레이닝 영상!!!', 'https://www.youtube.com/watch?v=NupMgk8kAbk',
        'NupMgk8kAbk', '팔꿈치 골절 수술 후 초기 재활을 위한 홈 트레이닝 영상을 제공합니다.', NOW(), NOW());

-- 허리 10개
INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '허리디스크 재활운동 10가지 중 자세히 1편 – 모커리 스케이트운동', 'https://www.youtube.com/watch?v=j10e_Tb_PXU',
        'j10e_Tb_PXU', '모커리한방병원에서 소개하는 허리디스크 재활운동 중 스케이트 운동입니다.', NOW(), NOW()),
       (1, '하루 5분! 맨몸 운동으로 허리 통증 탈출하세요! 맨몸 타바타 운동!', 'https://www.youtube.com/watch?v=YHC73-whRuw',
        'YHC73-whRuw', '세모핏 채널에서 소개하는 하루 5분 맨몸 타바타 운동으로 허리 통증을 완화하세요.', NOW(), NOW()),
       (1, '허리 좋아지려면 3하고 3마라! 정선근 교수가 알려주는 허리 운동법칙', 'https://www.youtube.com/watch?v=EaC9yC3NkRk',
        'EaC9yC3NkRk', '서울대병원 정선근 교수가 소개하는 허리 건강을 위한 운동법칙입니다.', NOW(), NOW()),
       (1, '2-4. 허리운동재활-스트레칭누워무릎굴곡몸통회전', 'https://www.youtube.com/watch?v=yQNf2znLvYw',
        'yQNf2znLvYw', '누워서 무릎을 굽히고 몸통을 회전시키는 스트레칭으로 허리 재활을 도와줍니다.', NOW(), NOW()),
       (1, '허리디스크재활운동, 척추강화운동의 의미! 디스크에 압력을 가하지 않는 운동법',
        'https://www.youtube.com/watch?v=ZFQllv-ziLE', 'ZFQllv-ziLE',
        '허리디스크 환자를 위한 척추 강화 운동의 중요성과 방법을 소개합니다.', NOW(), NOW()),
       (1, '세모핏 #145ㅣ뱃살빼기 허리둘레 맨몸운동 중년운동 스트레칭 기초근력 간단운동 다이어트',
        'https://www.youtube.com/watch?v=cRVAL41jnsY', 'cRVAL41jnsY',
        '세모핏 채널에서 소개하는 중년을 위한 허리둘레 감소 맨몸 운동입니다.', NOW(), NOW()),
       (1, '허리 통증을 없애주는 허리운동', 'https://www.youtube.com/watch?v=f-mgnsrDWHg', 'f-mgnsrDWHg',
        '재활센터에서 소개하는 허리 긴장을 풀어주는 스트레칭 운동입니다.', NOW(), NOW()),
       (1, '우리 몸의 대들보 허리가 아프다면? 요통 완화 셀프운동법 7가지', 'https://www.youtube.com/watch?v=TBfZwFHjyjs',
        'TBfZwFHjyjs', '일산백병원 근골격계재활치료실에서 소개하는 요통 완화를 위한 셀프 운동법입니다.', NOW(), NOW()),
       (1, '재활의학과 전문의가 알려드립니다! [닥터인사이드]', 'https://www.youtube.com/watch?v=joXbTbm3jB4',
        'joXbTbm3jB4', '재활의학과 전문의가 허리디스크 환자를 위한 재활운동을 소개합니다.', NOW(), NOW()),
       (1, '허리 디스크 재활운동 이것부터 알아야 합니다.', 'https://www.youtube.com/watch?v=semSFVNhkTw',
        'semSFVNhkTw', '허리디스크 환자가 재활 운동을 시작하기 전에 알아야 할 중요한 정보를 제공합니다.', NOW(), NOW());

-- 발목 10개
INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '발목 염좌 재활운동 1단계', 'https://www.youtube.com/watch?v=hkEkMaY0YiE', 'hkEkMaY0YiE',
        '발목 염좌 후 초기 재활을 위한 스트레칭 운동입니다.', NOW(), NOW()),
       (1, '발목 재활운동 2단계 - 근력 강화', 'https://www.youtube.com/watch?v=3Yx1K0qz3lE', '3Yx1K0qz3lE',
        '발목 근력 강화를 위한 재활운동입니다.', NOW(), NOW()),
       (1, '발목 불안정성 개선 운동', 'https://www.youtube.com/watch?v=5d1kVxkYV2Q', '5d1kVxkYV2Q',
        '발목 불안정성을 개선하는 균형 및 근력 운동입니다.', NOW(), NOW()),
       (1, '발목 스트레칭과 강화 운동', 'https://www.youtube.com/watch?v=2jzFv4v7yZs', '2jzFv4v7yZs',
        '발목 유연성과 근력을 동시에 향상시키는 운동입니다.', NOW(), NOW()),
       (1, '발목 염좌 후 재활 운동 프로그램', 'https://www.youtube.com/watch?v=9xY0f5K3s8M', '9xY0f5K3s8M',
        '발목 염좌 후 단계별 재활 운동 프로그램을 소개합니다.', NOW(), NOW()),
       (1, '발목 안정성 향상을 위한 운동', 'https://www.youtube.com/watch?v=7lK1z3YvX9A', '7lK1z3YvX9A',
        '발목의 안정성을 높이기 위한 운동법입니다.', NOW(), NOW()),
       (1, '발목 통증 완화를 위한 스트레칭', 'https://www.youtube.com/watch?v=4n0xK3zYv5E', '4n0xK3zYv5E',
        '발목 통증 완화를 위한 스트레칭 운동입니다.', NOW(), NOW()),
       (1, '발목 재활을 위한 밴드 운동', 'https://www.youtube.com/watch?v=6yZ0f5K3s9B', '6yZ0f5K3s9B',
        '저항 밴드를 활용한 발목 재활 운동입니다.', NOW(), NOW()),
       (1, '발목 강화 및 유연성 향상 운동', 'https://www.youtube.com/watch?v=8xY1f5K3s7C', '8xY1f5K3s7C',
        '발목의 강화와 유연성 향상을 위한 종합 운동입니다.', NOW(), NOW()),
       (1, '발목 회복을 위한 균형 운동', 'https://www.youtube.com/watch?v=1lK2z3YvX8D', '1lK2z3YvX8D',
        '발목 회복을 위한 균형 감각 향상 운동입니다.', NOW(), NOW());

-- 무릎 10개
INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '하루 10분! 무릎이 거짓말처럼 편안해지는 무릎 강화 운동', 'https://www.youtube.com/watch?v=L9RsJMSs8y4',
        'L9RsJMSs8y4', '하루 10분으로 무릎 통증을 완화하고 강화하는 운동법을 소개합니다.', NOW(), NOW()),
       (1, '무릎 수술 후 더 중요한 무릎 운동! 무릎 재활 운동법', 'https://www.youtube.com/watch?v=O5WWtvI23fo',
        'O5WWtvI23fo', '무릎 수술 후 통증 완화와 근력 회복을 위한 재활 운동법을 안내합니다.', NOW(), NOW()),
       (1, '무릎인공관절수술 0~4주차 재활운동 이 영상만 보면 끝!', 'https://www.youtube.com/watch?v=t0WYCA4m3CA',
        't0WYCA4m3CA', '무릎 인공관절 수술 후 초기 0~4주차에 적합한 재활 운동을 소개합니다.', NOW(), NOW()),
       (1, '무릎 연골연화증 재활운동 [닥터인사이드]', 'https://www.youtube.com/watch?v=NoSJUzFxO1U', 'NoSJUzFxO1U',
        '무릎 연골연화증 환자를 위한 재활 운동법을 안내합니다.', NOW(), NOW()),
       (1, '무릎인공관절수술 후 재활운동, 이 영상 하나로 끝낸다', 'https://www.youtube.com/watch?v=XZvBtNnNGbc',
        'XZvBtNnNGbc', '무릎 인공관절 수술 후 재활 기간에 맞춘 저강도 운동을 소개합니다.', NOW(), NOW()),
       (1, '[무릎 재활 1주차] 의자에 앉아서 다리 펴기', 'https://www.youtube.com/watch?v=O-mLDG8d-Os',
        'O-mLDG8d-Os', '무릎 수술 후 1주차에 적합한 의자에 앉아서 하는 다리 펴기 운동입니다.', NOW(), NOW()),
       (1, '[무릎 인공관절 치환술] 후 빠르고 올바르게 걷게 만들어 주는 재활운동', 'https://www.youtube.com/watch?v=SSaOHhFJRP8',
        'SSaOHhFJRP8', '무릎 인공관절 치환술 후 빠른 회복을 위한 걷기 재활 운동을 안내합니다.', NOW(), NOW()),
       (1, '10만뷰 [마디마디] 집에서 할 수 있는 무릎 재활운동 7가지!!! ( 수술 후 )',
        'https://www.youtube.com/watch?v=uA152nsjPRg', 'uA152nsjPRg',
        '집에서 쉽게 따라할 수 있는 무릎 재활운동 7가지를 소개합니다.', NOW(), NOW()),
       (1, '[무릎수술 후 재활운동 영상] 무릎 전문의가 알려드리는 효과적인 운동', 'https://www.youtube.com/watch?v=m06ilKpj87g',
        'm06ilKpj87g', '무릎 전문의가 수술 후 효과적인 재활 운동 방법을 안내합니다.', NOW(), NOW()),
       (1, '무릎 수술 후 초기 재활 완전정복: 걷기 vs 자전거 운동', 'https://www.youtube.com/watch?v=FGMJjApGI-k',
        'FGMJjApGI-k', '무릎 수술 후 초기 재활 시 걷기와 자전거 운동의 비교를 통해 적절한 운동을 선택할 수 있도록 안내합니다.', NOW(), NOW());

-- 등 10개
INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '척추유합술 재활운동 목요일 l 척추 수술 시행 후 재활운동 [척추레시피]',
        'https://www.youtube.com/watch?v=VrFVjFMSdB4', 'VrFVjFMSdB4',
        '강남세브란스병원에서 제공하는 척추유합술 후 재활운동 가이드입니다.', NOW(), NOW()),
       (1, '재활운동법 - 11.뒤넙다리근스트레칭', 'https://www.youtube.com/watch?v=sisqAvf8eT4', 'sisqAvf8eT4',
        '어디서든 쉽게 따라할 수 있는 뒤넙다리근 스트레칭 재활운동입니다.', NOW(), NOW()),
       (1, '재활치료실과 함께하는 하지 스트레칭 & 운동법 / 5분만 따라 해보세요!',
        'https://www.youtube.com/watch?v=srXP_w0wI9A', 'srXP_w0wI9A',
        '재활치료실과 함께하는 하지 스트레칭 및 운동법을 소개합니다.', NOW(), NOW()),
       (1, '무릎 재활 2~4주차 누워서 뒤 허벅지 스트레칭', 'https://www.youtube.com/watch?v=DWVkwuQMXlI',
        'DWVkwuQMXlI', '무릎 수술 후 2~4주차에 적합한 누워서 하는 뒤 허벅지 스트레칭 운동입니다.', NOW(), NOW()),
       (1, '척추 수술 후 재활운동 가이드', 'https://www.youtube.com/watch?v=abcd1234efg', 'abcd1234efg',
        '척추 수술 후 효과적인 재활운동 방법을 안내합니다.', NOW(), NOW()),
       (1, '등 통증 완화를 위한 스트레칭 운동', 'https://www.youtube.com/watch?v=hijk5678lmn', 'hijk5678lmn',
        '등 통증을 완화하는 데 도움이 되는 스트레칭 운동을 소개합니다.', NOW(), NOW()),
       (1, '거북목 교정을 위한 등 근육 강화 운동', 'https://www.youtube.com/watch?v=opqr9012stu', 'opqr9012stu',
        '거북목 증상을 완화하기 위한 등 근육 강화 운동법입니다.', NOW(), NOW()),
       (1, '허리와 등의 유연성을 높이는 요가 동작', 'https://www.youtube.com/watch?v=vwxy3456zab', 'vwxy3456zab',
        '허리와 등의 유연성을 향상시키는 요가 동작을 안내합니다.', NOW(), NOW()),
       (1, '등 근육 강화로 자세 교정하기', 'https://www.youtube.com/watch?v=cdef7890ghi', 'cdef7890ghi',
        '등 근육 강화를 통해 자세를 교정하는 운동법을 소개합니다.', NOW(), NOW()),
       (1, '오십견 예방을 위한 등 스트레칭', 'https://www.youtube.com/watch?v=lmno1234pqr', 'lmno1234pqr',
        '오십견 예방에 도움이 되는 등 스트레칭 운동을 안내합니다.', NOW(), NOW());

-- 어깨 10개
INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '회전근개 파열에 좋은 어깨 재활운동 매일 10분만 따라 하세요', 'https://www.youtube.com/watch?v=INluHydZS-k',
        'INluHydZS-k', '회전근개 파열 후 통증 완화를 위한 10분 재활 운동입니다.', NOW(), NOW()),
       (1, '초간단 하루 5분 어깨 재활운동 가이드 라인', 'https://www.youtube.com/watch?v=pwDJGr3tJzQ', 'pwDJGr3tJzQ',
        '하루 5분으로 어깨 수술 후 재활을 돕는 간단한 운동 가이드입니다.', NOW(), NOW()),
       (1, '어깨 재활운동 총정리 - 스트레칭 운동편 (오십견, 회전근개 파열)', 'https://www.youtube.com/watch?v=K7tFUtbRSr4',
        'K7tFUtbRSr4', '오십견과 회전근개 파열 환자를 위한 스트레칭 중심의 재활 운동 총정리입니다.', NOW(), NOW()),
       (1, '연세사랑병원 어깨수술후 재활운동, 근력회복 및 강화에 도움되는 운동', 'https://www.youtube.com/watch?v=wNv9Td8QKbs',
        'wNv9Td8QKbs', '어깨 수술 후 근력 회복과 강화를 위한 재활 운동을 소개합니다.', NOW(), NOW()),
       (1, '어깨 명의의 아주 간단한 어깨 재활운동 - 회전근개 맨손 운동', 'https://www.youtube.com/watch?v=VvkfAn60jEI',
        'VvkfAn60jEI', '전문가가 소개하는 회전근개 손상 후 맨손으로 할 수 있는 간단한 재활 운동입니다.', NOW(), NOW()),
       (1, '어깨 재활 스트레칭 운동 - 효과적인 방법으로 해야 합니다', 'https://www.youtube.com/watch?v=zgVxGBRS0V8',
        'zgVxGBRS0V8', '어깨 통증 완화를 위한 효과적인 스트레칭 재활 운동 방법을 안내합니다.', NOW(), NOW()),
       (1, '이해가 쏙쏙 되는 어깨 질환별 재활운동! 어깨충돌증후군 편', 'https://www.youtube.com/watch?v=mEbbs-B8dxs',
        'mEbbs-B8dxs', '어깨충돌증후군 환자를 위한 맞춤형 재활 운동을 소개합니다.', NOW(), NOW()),
       (1, '어깨 재활 운동 - 도르래 운동 (pulley exercise)', 'https://www.youtube.com/watch?v=zag94rM-FUk',
        'zag94rM-FUk', '도르래를 활용한 어깨 가동 범위 회복을 위한 재활 운동입니다.', NOW(), NOW()),
       (1, '어깨 재활 운동 - 막대 운동 (T-bar exercise)', 'https://www.youtube.com/watch?v=cd4M-3dFufo',
        'cd4M-3dFufo', '막대를 이용한 어깨 가동 범위 회복을 위한 재활 운동입니다.', NOW(), NOW()),
       (1, '재활운동 어깨 수술 후 똑똑한 운동법', 'https://www.youtube.com/watch?v=rgwBAGX87IQ', 'rgwBAGX87IQ',
        '어깨 수술 후 효과적인 재활을 위한 운동법을 안내합니다.', NOW(), NOW());

-- 목 10
INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '목디스크, 거북목 재활운동 베스트3! - 재활의학과 전문의가 추천하는 운동',
        'https://www.youtube.com/watch?v=Z8Ohtus-a_I', 'Z8Ohtus-a_I',
        '재활의학과 전문의가 추천하는 목디스크와 거북목에 효과적인 재활운동 3가지입니다.', NOW(), NOW()),
       (1, '목디스크에 좋은 5가지 운동 완벽정리!', 'https://www.youtube.com/watch?v=0p1b5cfdxhU', '0p1b5cfdxhU',
        '목디스크 환자를 위한 5가지 효과적인 운동을 정리한 영상입니다.', NOW(), NOW()),
       (1, '함께 해요! 목 디스크 재활 운동', 'https://www.youtube.com/watch?v=c1a1N7MS6MA', 'c1a1N7MS6MA',
        '목 디스크 환자를 위한 재활 운동을 함께 따라하는 영상입니다.', NOW(), NOW()),
       (1, '잘못된 목 스트레칭 STOP! 돈 안 들이고 어디서든 할 수 있는 올바른 목 스트레칭',
        'https://www.youtube.com/watch?v=PVwP8VaM9M4', 'PVwP8VaM9M4',
        '잘못된 목 스트레칭을 피하고 올바른 방법으로 목을 스트레칭하는 방법을 안내합니다.', NOW(), NOW()),
       (1, '목디스크 수술 후 집에서 하는 재활운동 - 초급편(수술 후 3달 미만)', 'https://www.youtube.com/watch?v=cobFpl8qYkk',
        'cobFpl8qYkk', '목디스크 수술 후 초기 3개월 이내에 집에서 할 수 있는 재활운동을 소개합니다.', NOW(), NOW()),
       (1, '목통증운동 이거 하나로 끝! 목디스크통증 환자가 따라하면 좋은 운동', 'https://www.youtube.com/watch?v=E0rugjm7gkY',
        'E0rugjm7gkY', '목통증과 목디스크 환자를 위한 효과적인 운동법을 안내합니다.', NOW(), NOW()),
       (1, '목디스크 운동, 이거 하나로 끝낸다! - 도리도리 운동', 'https://www.youtube.com/watch?v=eReIEoieQsQ',
        'eReIEoieQsQ', '도리도리 운동으로 목디스크를 예방하고 치료하는 방법을 소개합니다.', NOW(), NOW()),
       (1, '[1분홈트] 깍지 끼고 팔 올리기 #목스트레칭 #목재활운동법 | 바로선병원',
        'https://www.youtube.com/watch?v=__5DQJ0iU8s', '__5DQJ0iU8s',
        '바로선병원에서 소개하는 1분 홈트레이닝으로 목 스트레칭과 재활운동을 할 수 있습니다.', NOW(), NOW()),
       (1, '재활의학과 교수가 보증하는 초간단 거북목 스트레칭법! (feat. 수건)',
        'https://www.youtube.com/watch?v=-nOgOs4r6Ys', '-nOgOs4r6Ys',
        '재활의학과 교수가 추천하는 수건을 이용한 간단한 거북목 스트레칭 방법입니다.', NOW(), NOW()),
       (1, '척추의 신 정선근 교수가 알려주는 목디스크 치료 운동 어디서든 쉽게 따라하세요!',
        'https://www.youtube.com/watch?v=cSwm7ziLQu4', 'cSwm7ziLQu4',
        '정선근 교수가 소개하는 목디스크 치료를 위한 간단한 운동법입니다.', NOW(), NOW());

-- 손목 10
INSERT INTO exercise_videos (user_id, title, url, youtube_id, description, created_at, updated_at)
VALUES (1, '다친 손목을 회복시키는 손목 재활운동', 'https://www.youtube.com/watch?v=HoFFKY583S8', 'HoFFKY583S8',
        '일산백병원 근골격계 재활치료실에서 소개하는 손목 회복을 위한 재활운동입니다.', NOW(), NOW()),
       (1, '손목 골절 수술 후 손목 재활운동(0~6주)', 'https://www.youtube.com/watch?v=Ab9MiEh20xY', 'Ab9MiEh20xY',
        '일산백병원에서 제공하는 손목 골절 수술 후 0~6주차에 적합한 재활운동입니다.', NOW(), NOW()),
       (1, '손목 수술 후 재활운동', 'https://www.youtube.com/watch?v=isUEVlJUKmU', 'isUEVlJUKmU',
        '손가락 및 손목 손상 환자를 위한 자가 운동치료 동영상입니다.', NOW(), NOW()),
       (1, '집에서 할 수 있는 손목 골절 후 운동법 7가지', 'https://www.youtube.com/watch?v=B7STAq6LjgA',
        'B7STAq6LjgA', '손목 수술 후 재활과정에서 도움이 되는 7가지 운동법을 소개합니다.', NOW(), NOW()),
       (1, '손목 골절 수술 후 손목 재활운동(6~12주)', 'https://www.youtube.com/watch?v=4qYA8hzAF8w',
        '4qYA8hzAF8w', '일산백병원에서 제공하는 손목 골절 수술 후 6~12주차에 적합한 재활운동입니다.', NOW(), NOW()),
       (1, '손목 골절 수술 후 재활운동 2 - 손목 스트레칭', 'https://www.youtube.com/shorts/bH4wqYlKAzU',
        'bH4wqYlKAzU', '더서울병원에서 소개하는 손목 골절 수술 후 손목 스트레칭 재활운동입니다.', NOW(), NOW()),
       (1, '손목 수술 후 재활운동 - 이렇게 할 수 있습니다!', 'https://www.youtube.com/watch?v=mMuvVZxRWtM',
        'mMuvVZxRWtM', '손목 수술 후 재활 운동을 하는 방법을 안내하는 동영상입니다.', NOW(), NOW()),
       (1, '손목 재활 운동 - 수건을 이용한 잡기 운동', 'https://www.youtube.com/watch?v=example1', 'example1',
        '성모윌병원에서 소개하는 수건을 이용한 손목 재활 운동입니다.', NOW(), NOW()),
       (1, '손목 재활 운동 - 고무줄을 이용한 강화 운동', 'https://www.youtube.com/watch?v=example2', 'example2',
        '성모윌병원에서 소개하는 고무줄을 이용한 손목 강화 운동입니다.', NOW(), NOW()),
       (1, '손목 재활 운동 - 손목 근육 강화 운동', 'https://www.youtube.com/watch?v=example3', 'example3',
        '성모윌병원에서 소개하는 손목 근육 강화 운동입니다.', NOW(), NOW());


-- 타겟 맵핑 쿼리
INSERT INTO video_targets (exercise_video_id, target_id)
VALUES (1, 7),
       (2, 7),
       (3, 7),
       (4, 7),
       (5, 7),
       (6, 7),
       (7, 7),
       (8, 7),
       (9, 7),
       (10, 7),
       (11, 6),
       (12, 6),
       (13, 6),
       (14, 6),
       (15, 6),
       (16, 6),
       (17, 6),
       (18, 6),
       (19, 6),
       (20, 6),
       (21, 5),
       (22, 5),
       (23, 5),
       (24, 5),
       (25, 5),
       (26, 5),
       (27, 5),
       (28, 5),
       (29, 5),
       (30, 5),
       (31, 4),
       (32, 4),
       (33, 4),
       (34, 4),
       (35, 4),
       (36, 4),
       (37, 4),
       (38, 4),
       (39, 4),
       (40, 4),
       (41, 3),
       (42, 3),
       (43, 3),
       (44, 3),
       (45, 3),
       (46, 3),
       (47, 3),
       (48, 3),
       (49, 3),
       (50, 3),
       (51, 2),
       (52, 2),
       (53, 2),
       (54, 2),
       (55, 2),
       (56, 2),
       (57, 2),
       (58, 2),
       (59, 2),
       (60, 2),
       (61, 1),
       (62, 1),
       (63, 1),
       (64, 1),
       (65, 1),
       (66, 1),
       (67, 1),
       (68, 1),
       (69, 1),
       (70, 1),
       (71, 8),
       (72, 8),
       (73, 8),
       (74, 8),
       (75, 8),
       (76, 8),
       (77, 8),
       (78, 8),
       (79, 8),
       (80, 8);

-- 유저는 댓글도 달아둠.
INSERT INTO comments (user_id, exercise_video_id, content)
VALUES (2, 1, '헥헥 너무 힘들어요 🥵'),
       (3, 1, 'ㅋㅋ 그거 님이 저질 체력이라 그럼 ㅋ'),
       (3, 1, 'ㅋ 긁혔죠?');



INSERT INTO articles (user_id, title, content)
VALUES (3, '거북목 환자분들 꼭 읽으세요.',
        '미안하다 이거 보여주려고 어그로끌었다.. 나루토 사스케 싸움수준 ㄹㅇ실화냐? 진짜 세계관최강자들의 싸움이다.. 그찐따같던 나루토가 맞나? 진짜 나루토는 전설이다..진짜옛날에 맨날나루토봘는데 왕같은존재인 호카게 되서 세계최강 전설적인 영웅이된나루토보면 진짜내가다 감격스럽고 나루토 노래부터 명장면까지 가슴울리는장면들이 뇌리에 스치면서 가슴이 웅장해진다.. 그리고 극장판 에 카카시앞에 운석날라오는 거대한 걸 사스케가 갑자기 순식간에 나타나서 부숴버리곤 개간지나게 나루토가 없다면 마을을 지킬 자는 나밖에 없다 라며 바람처럼 사라진장면은 진짜 나루토처음부터 본사람이면 안울수가없더라 진짜 너무 감격스럽고 보루토를 최근에 알았는데 미안하다.. 지금20화보는데 진짜 나루토세대나와서 너무 감격스럽고 모두어엿하게 큰거보니 내가 다 뭔가 알수없는 추억이라해야되나 그런감정이 이상하게 얽혀있다.. 시노는 말이많아진거같다 좋은선생이고..그리고 보루토왜욕하냐 귀여운데 나루토를보는것같다 성격도 닮았어 그리고버루토에 나루토사스케 둘이싸워도 이기는 신같은존재 나온다는게 사실임?? 그리고인터닛에 쳐봣는디 이거 ㄹㅇㄹㅇ 진짜팩트냐?? 저적이 보루토에 나오는 신급괴물임?ㅡ 나루토사스케 합체한거봐라 진짜 ㅆㅂ 이거보고 개충격먹어가지고 와 소리 저절로 나오더라 ;; 진짜 저건 개오지는데.. 저게 ㄹㅇ이면 진짜 꼭봐야돼 진짜 세계도 파괴시키는거아니야 .. 와 진짜 나루토사스케가 저렇게 되다니 진짜 눈물나려고했다.. 버루토그라서 계속보는중인데 저거 ㄹㅇ이냐..? 하.. ㅆㅂ 사스케 보고싶다..  진짜언제 이렇게 신급 최강들이 되었을까 옛날생각나고 나 중딩때생각나고 뭔가 슬프기도하고 좋기도하고 감격도하고 여러가지감정이 복잡하네.. 아무튼 나루토는 진짜 애니중최거명작임..');

-- 유저 댓글 작성~
INSERT INTO reviews (article_id, user_id, content, created_at, updated_at)
VALUES (1, 2, '선생님...여기서 이러시면 안됩니다..', NOW(), NOW());



