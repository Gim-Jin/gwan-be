package com.kimnjin.gwanyeon.rehabprogram.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSurveyRequestDto {

  // 통증부위
  private String painArea;
  // 통증 정도
  private String painLevel;
  //
  private String painPattern;
  // 어떤 동작 상황에서 통증이 오는지
  private String painTrigger;
  // 내담자의 질병 및 부상기록 + 특이사항
  private String medicalHistory;
  // 통증 시작시기
  private String painStartTime;

  //인적 사항
  //나이
  private String age;
  // 성별
  private String gender;
  // 키
  private String height;
  // 몸무게
  private String weight;

  // 생활 습관
  // 수면 시간
  private String sleepHours;
  // 주간활동
  private String dailyActivity;
  // 운동 경력
  private String exerciseExperience;
  // 운동 선호
  private String exercisePreference;


}
