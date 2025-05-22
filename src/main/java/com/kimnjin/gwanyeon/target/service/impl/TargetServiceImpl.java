package com.kimnjin.gwanyeon.target.service.impl;

import com.kimnjin.gwanyeon.commons.exception.BadRequestException;
import com.kimnjin.gwanyeon.target.dto.CreateTargetRequestDto;
import com.kimnjin.gwanyeon.target.dto.TargetResponseDto;
import com.kimnjin.gwanyeon.target.entity.Target;
import com.kimnjin.gwanyeon.target.repository.TargetRepository;
import com.kimnjin.gwanyeon.target.service.TargetService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TargetServiceImpl implements TargetService {

  private TargetRepository targetRepository;
  private final String BAD_REQUEST = "잘못된 요청입니다.  ";

  @Transactional
  @Override
  public TargetResponseDto saveTarget(CreateTargetRequestDto createTargetRequestDto) {

    Target target = createTargetRequestDto.toEntity();

    int result = targetRepository.insert(target);

    if (result == 0) {
      throw new BadRequestException(BAD_REQUEST);
    }

    return TargetResponseDto.from(target);
  }

  @Override
  public List<TargetResponseDto> getAllTarget() {

    List<Target> targets = targetRepository.selectAll();

    if (targets.isEmpty()) {
      return Collections.emptyList();
    }

    return targets.stream().map(TargetResponseDto::from).collect(Collectors.toList());
  }

  @Override
  public List<TargetResponseDto> getTargetOrderPopular() {

    List<Target> targets = targetRepository.selectAllOrderByLikesCount();

    if (targets.isEmpty()) {
      return Collections.emptyList();
    }

    return targets.stream().map(TargetResponseDto::from).collect(Collectors.toList());
  }
}
