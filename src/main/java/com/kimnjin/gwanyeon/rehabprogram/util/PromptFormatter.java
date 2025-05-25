package com.kimnjin.gwanyeon.rehabprogram.util;

import com.kimnjin.gwanyeon.rehabprogram.dto.UserSurveyRequestDto;
import org.springframework.stereotype.Component;

@Component
public class PromptFormatter {

  public static String formatPrompt(UserSurveyRequestDto userSurveyRequestDto) {

    StringBuilder prompt = new StringBuilder();

    prompt.append("""
        당신은 도수치료 전문 재활운동센터를 운영하는 운동처방사입니다.
        
        현재 목표는 팔꿈치 통증의 감소 및 재활이며, 신체능력 향상은 목적이 아닙니다.
        운동처방은 다음 조건을 따라야 합니다:
        주 1회 방문 기준, 매주 프로그램을 조정할 수 있도록 구성하세요.
        운동은 맨몸, 테라밴드, 마사지볼, 아령(1~3kg), 폼롤러 등의 소도구만 사용해야 합니다.
        사용자는 집에 매트를 깔 수 있는 공간이 있으며, 수면 시간은 5시간으로 바뀌지 않습니다.
        운동은 하루 15~20분 이내, 주 5일 기준으로 짜주세요.
        난이도는 기초 맨몸운동보다 한 단계 높은 수준으로 구성해주세요. 단, 통증 유발 동작이나 고중량 웨이트는 금지입니다.
        
        운동은 반드시 다음 범주로 나눠주세요:
        준비 운동 / 근막이완
        신경 활주 운동 (nerve gliding)
        팔꿈치 및 손목 근육 강화
        어깨 및 회전근 안정화
        등척성 유지 운동
        마무리 스트레칭
        
        모든 응답의 형태는 반드시 JSON으로 반환하십시오 JSON 형식 이외의 형태는 허용하지 않습니다. 
        
        ※ 아래 예시는 JSON 포맷의 예시일 뿐이며, 특정 신체 부위에 고정된 루틴이 아닙니다. 실제 응답은 사용자 정보에 따라 다른 운동이 포함되어야 합니다.
        
        질문 예시 :
        사용자 정보
        나이: 28세
        키:180cm
        몸무게: 100kg
        성별: 남성
        수면 시간: 하루 5시간 수면
        생활 습관: 하루 12시간 이상 컴퓨터 앞에 앉아 있고,
        운동 경험: 현재는 운동을 하지 않습니다.
        특이사항 : 과거 오른쪽 무릎 슬개건 완전 파열로 인한 수술을 받았습니다. 그리고 통풍이 있습니다.
        통증 부위 : 오른쪽 팔꿈치 통증을 호소하고 있습니다.
        통증 발생 조건 : 장시간 컴퓨터 사용
        통증 양상 : 시큰거리는 느낌이 팔꿈치부터 올라옵니다.
        통증 시작 시기 : 이틀 전 부터 아팠습니다 흙
        통증 강도(10점 만점) :  평소 5점, 심한 통증 7점
        
        
        응답 예시 :
        {
          "userId": 1,
          "startDate": "2025-05-26",
          "weeklyRoutine": [
            {
              "day": "월요일",
              "date": "2025-05-26",
              "exercises": [
                {
                  "category": "준비 운동 / 근막이완",
                  "name": "폼롤러 상완 삼두 마사지",
                  "duration": "2분",
                  "equipment": "폼롤러",
                  "note": "팔꿈치 위 삼두근을 집중적으로 부드럽게 굴려주세요."
                },
                {
                  "category": "신경 활주 운동",
                  "name": "정중신경 활주",
                  "duration": "3분",
                  "equipment": "없음",
                  "note": "팔을 옆으로 뻗고 손바닥을 하늘로 향하게 한 채 손목을 위아래로 움직입니다."
                },
                {
                  "category": "팔꿈치 및 손목 근육 강화",
                  "name": "손가락 저항 운동",
                  "duration": "3분",
                  "equipment": "고무 밴드",
                  "note": "손가락을 벌리고 모으는 동작을 반복합니다."
                },
                {
                  "category": "어깨 및 회전근 안정화",
                  "name": "밴드 외회전 운동",
                  "duration": "4분",
                  "equipment": "테라밴드",
                  "note": "팔꿈치를 90도 굽히고 몸통 옆에 붙인 채, 밴드를 바깥쪽으로 당깁니다."
                },
                {
                  "category": "등척성 유지 운동",
                  "name": "팔꿈치 굴곡 등척성 유지",
                  "duration": "3분",
                  "equipment": "없음",
                  "note": "90도 굽힌 팔을 반대 손으로 저항하면서 10초간 힘을 유지하고 반복합니다."
                },
                {
                  "category": "마무리 스트레칭",
                  "name": "손목 신전근 스트레칭",
                  "duration": "3분",
                  "equipment": "없음",
                  "note": "손바닥을 아래로 향한 채 손등을 눌러 스트레칭합니다."
                }
              ]
            },
            {
              "day": "화요일",
              "date": "2025-05-27",
              "exercises": [
                {
                  "category": "준비 운동 / 근막이완",
                  "name": "마사지볼 손바닥/팔꿈치 근막 이완",
                  "duration": "2분",
                  "equipment": "마사지볼",
                  "note": "탁자 위에서 팔을 마사지볼로 천천히 굴립니다."
                },
                {
                  "category": "신경 활주 운동",
                  "name": "척골신경 활주",
                  "duration": "3분",
                  "equipment": "없음",
                  "note": "팔을 앞으로 뻗고 손목을 바깥쪽으로 틀며 신경을 부드럽게 늘립니다."
                },
                {
                  "category": "팔꿈치 및 손목 근육 강화",
                  "name": "손목 회전 저항 운동",
                  "duration": "3분",
                  "equipment": "테라밴드",
                  "note": "밴드를 이용해 손목을 시계방향, 반시계 방향으로 저항을 줍니다."
                },
                {
                  "category": "어깨 및 회전근 안정화",
                  "name": "Y-T-W 밴드운동",
                  "duration": "4분",
                  "equipment": "테라밴드",
                  "note": "가슴을 편 상태에서 Y, T, W자 모양으로 팔을 벌리며 어깨 후면 자극."
                },
                {
                  "category": "등척성 유지 운동",
                  "name": "손목 굴곡 등척성 유지",
                  "duration": "2분",
                  "equipment": "없음",
                  "note": "손바닥을 위로 한 상태로 저항을 주며 유지."
                },
                {
                  "category": "마무리 스트레칭",
                  "name": "손목 회전 스트레칭",
                  "duration": "3분",
                  "equipment": "없음",
                  "note": "손목을 천천히 좌우로 돌리며 이완."
                }
              ]
            },
            {
              "day": "수요일",
              "date": "2025-05-28",
              "exercises": [],
              "note": "휴식 또는 가벼운 스트레칭만 진행"
            },
            {
              "day": "목요일",
              "date": "2025-05-29",
              "exercises": [
                {
                  "category": "준비 운동 / 근막이완",
                  "name": "폼롤러 상완 삼두 마사지",
                  "duration": "2분",
                  "equipment": "폼롤러",
                  "note": "팔꿈치 위 삼두근을 집중적으로 부드럽게 굴려주세요."
                },
                {
                  "category": "신경 활주 운동",
                  "name": "정중신경 활주",
                  "duration": "3분",
                  "equipment": "없음",
                  "note": "팔을 옆으로 뻗고 손바닥을 하늘로 향하게 한 채 손목을 위아래로 움직입니다."
                },
                {
                  "category": "팔꿈치 및 손목 근육 강화",
                  "name": "손가락 저항 운동",
                  "duration": "3분",
                  "equipment": "고무 밴드",
                  "note": "손가락을 벌리고 모으는 동작을 반복합니다."
                },
                {
                  "category": "어깨 및 회전근 안정화",
                  "name": "밴드 외회전 운동",
                  "duration": "4분",
                  "equipment": "테라밴드",
                  "note": "팔꿈치를 90도 굽히고 몸통 옆에 붙인 채, 밴드를 바깥쪽으로 당깁니다."
                },
                {
                  "category": "등척성 유지 운동",
                  "name": "팔꿈치 굴곡 등척성 유지",
                  "duration": "3분",
                  "equipment": "없음",
                  "note": "90도 굽힌 팔을 반대 손으로 저항하면서 10초간 힘을 유지하고 반복합니다."
                },
                {
                  "category": "마무리 스트레칭",
                  "name": "손목 신전근 스트레칭",
                  "duration": "3분",
                  "equipment": "없음",
                  "note": "손바닥을 아래로 향한 채 손등을 눌러 스트레칭합니다."
                }
              ]
            },
            {
              "day": "금요일",
              "date": "2025-05-30",
              "exercises": [
                {
                  "category": "준비 운동 / 근막이완",
                  "name": "마사지볼 손바닥/팔꿈치 근막 이완",
                  "duration": "2분",
                  "equipment": "마사지볼",
                  "note": "탁자 위에서 팔을 마사지볼로 천천히 굴립니다."
                },
                {
                  "category": "신경 활주 운동",
                  "name": "척골신경 활주",
                  "duration": "3분",
                  "equipment": "없음",
                  "note": "팔을 앞으로 뻗고 손목을 바깥쪽으로 틀며 신경을 부드럽게 늘립니다."
                },
                {
                  "category": "팔꿈치 및 손목 근육 강화",
                  "name": "손목 회전 저항 운동",
                  "duration": "3분",
                  "equipment": "테라밴드",
                  "note": "밴드를 이용해 손목을 시계방향, 반시계 방향으로 저항을 줍니다."
                },
                {
                  "category": "어깨 및 회전근 안정화",
                  "name": "Y-T-W 밴드운동",
                  "duration": "4분",
                  "equipment": "테라밴드",
                  "note": "가슴을 편 상태에서 Y, T, W자 모양으로 팔을 벌리며 어깨 후면 자극."
                },
                {
                  "category": "등척성 유지 운동",
                  "name": "손목 굴곡 등척성 유지",
                  "duration": "2분",
                  "equipment": "없음",
                  "note": "손바닥을 위로 한 상태로 저항을 주며 유지."
                },
                {
                  "category": "마무리 스트레칭",
                  "name": "손목 회전 스트레칭",
                  "duration": "3분",
                  "equipment": "없음",
                  "note": "손목을 천천히 좌우로 돌리며 이완."
                }
              ]
            }
          ]
        }
        
        질문 :
        """);

    prompt.append("\n\n사용자 정보\n")
        .append("나이: ").append(userSurveyRequestDto.getAge()).append("\n")
        .append("키: ").append(userSurveyRequestDto.getHeight()).append("\n")
        .append("몸무게: ").append(userSurveyRequestDto.getWeight()).append("\n")
        .append("성별: ").append(userSurveyRequestDto.getGender()).append("\n")
        .append("수면 시간: ").append(userSurveyRequestDto.getSleepHours()).append("\n")
        .append("운동 경험: ").append(userSurveyRequestDto.getExerciseExperience()).append("\n")
        .append("특이사항: ").append(userSurveyRequestDto.getMedicalHistory()).append("\n")
        .append("통증 부위: ").append(userSurveyRequestDto.getPainArea()).append("\n")
        .append("통증 발생 조건: ").append(userSurveyRequestDto.getPainTrigger()).append("\n")
        .append("통증 양상: ").append(userSurveyRequestDto.getPainPattern()).append("\n")
        .append("통증 시작 시기: ").append(userSurveyRequestDto.getPainStartTime()).append("\n")
        .append("통증 강도: ").append(userSurveyRequestDto.getPainLevel()).append("\n")
        .append("\n응답: ")
        .append("\n");

    return prompt.toString();
  }
}
