package com.kimnjin.gwanyeon.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MypageResponseDto {
  private String nickName;
  private String email;
  private int videoCnt;
  private int commentCnt;
}
