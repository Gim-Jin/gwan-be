//package com.kimnjin.gwanyeon.gpt.util;
//
//import com.kimnjin.gwanyeon.rehabprogram.dto.UserSurveyRequestDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PromptFormatter {
//
//  public String formatPrompt(UserSurveyRequestDto userSurveyRequestDto) {
//
//    StringBuilder prompt = new StringBuilder();
//
//    prompt.append("""
//        당신은 도수치료 전문 재활운동센터를 운영하는 운동처방사입니다.
//
//         사용자에게 맞춤형 재활운동 프로그램을 제공합니다. 다음 조건을 반드시 지켜야 합니다:
//
//         1. 주 1회 내원 기준, 매주 프로그램을 조정할 수 있도록 구성하세요.
//         2. 운동은 하루 15~20분 이내, 주 5일 기준으로 구성하세요.
//         3. 맨몸, 테라밴드, 마사지볼, 아령(1~3kg), 폼롤러 등 소도구만 사용할 수 있습니다.
//         4. 사용자는 집에 매트를 펼 수 있는 공간만 확보되어 있습니다.
//         5. 수면 시간은 5시간으로 고정되어 있으며, 생활 습관은 바꿀 수 없습니다.
//         6. 난이도는 기초 맨몸운동보다 한 단계 높은 수준으로 구성하세요.
//         7. 통증을 유발하는 동작이나 고중량 웨이트 트레이닝은 금지합니다.
//
//         운동은 다음의 6가지 범주로 구성되어야 합니다:
//         - 준비 운동 / 근막이완
//         - 신경 활주 운동 (nerve gliding)
//         - 통증 부위의 근육 강화
//         - 연관 관절군의 안정화 운동 (예: 어깨, 손목, 척추 등)
//         - 등척성 유지 운동
//         - 마무리 스트레칭
//
//         각 운동은 다음 정보를 포함하는 표 형태로 정리하세요:
//         - 운동 이름
//         - 설명
//         - 세트 수 및 반복 횟수
//
//         사용자의 증상, 신체조건, 통증 발생 조건 등은 아래와 같습니다:
//        """);
//
//    prompt.append("\n\n[증상 정보]\n")
//        .append("-  통증 부위: ").append(userSurveyRequestDto.getPainArea()).append("\n")
//        .append("- 통증 강도: ").append(userSurveyRequestDto.getPainLevel()).append("\n")
//        .append("- 통증 양상: ").append(userSurveyRequestDto.getPainPattern()).append("\n")
//        .append("- 통증 발생 조건: ").append(userSurveyRequestDto.getPainTrigger()).append("\n")
//        .append("_ 질환 및 수술 이력: ").append(userSurveyRequestDto.getMedicalHistory()).append("\n")
//        .append("- 통증 시작 시점: ").append(userSurveyRequestDto.getPainStartTime()).append("\n");
//
//    prompt.append("\n\n[신체 조건 및 생활 습관]\n")
//        .append("- 나이: ").append(userSurveyRequestDto.getAge()).append("\n")
//        .append("- 성별: ").append(userSurveyRequestDto.getGender()).append("\n")
//        .append("- 키: ").append(userSurveyRequestDto.getHeight()).append("\n")
//        .append("- 체중: ").append(userSurveyRequestDto.getWeight()).append("\n")
//        .append("- 수면 시간: ").append(userSurveyRequestDto.getSleepHours()).append("\n")
//        .append("- 일상 활동: ").append(userSurveyRequestDto.getDailyActivity()).append("\n")
//        .append("- 운동 경험: ").append(userSurveyRequestDto.getExerciseExperience()).append("\n")
//        .append("- 운동 선호 여부: ").append(userSurveyRequestDto.getExercisePreference()).append("\n");
//
//    return prompt.toString();
//  }
//}
