package com.kimnjin.gwanyeon.target.service;

import com.kimnjin.gwanyeon.target.dto.CreateTargetRequestDto;
import com.kimnjin.gwanyeon.target.dto.TargetResponseDto;
import java.util.List;

public interface TargetService {

  // 타겟 저장 -> 향 후 어드민용으로 이용 가능. 근데 딜리트랑 업데이트는 일단...
  TargetResponseDto saveTarget(CreateTargetRequestDto createTargetRequestDto);

  // 모든 타겟
  List<TargetResponseDto> getAllTarget();

  // 좋아요 많은 타겟 순으로 반환 -> 프론트에서 5개 짤라서 보여주셈..
  List<TargetResponseDto> getTargetOrderPopular();


}
