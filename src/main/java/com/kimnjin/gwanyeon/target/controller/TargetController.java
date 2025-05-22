package com.kimnjin.gwanyeon.target.controller;

import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.target.dto.CreateTargetRequestDto;
import com.kimnjin.gwanyeon.target.dto.TargetResponseDto;
import com.kimnjin.gwanyeon.target.service.TargetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/targets")
public class TargetController {

  private final TargetService targetService;

  private final String NO_CONTENT = "noContent";
  private final String CREATED = "created";

  @GetMapping
  public ResponseEntity<ApiResult<List<TargetResponseDto>>> getAllTarget(
      @RequestParam(required = false) String sort) {

    List<TargetResponseDto> result = null;
    if (sort != null && sort.equals("popular")) {
      result = targetService.getTargetOrderPopular();
    } else {
      result = targetService.getAllTarget();
    }

    return !result.isEmpty() ? ResponseEntity.ok(ApiResult.success(result))
        : ResponseEntity.ok(ApiResult.success(result, 204, NO_CONTENT));
  }

  @PostMapping
  public ResponseEntity<ApiResult<TargetResponseDto>> createTarget(@RequestBody
  CreateTargetRequestDto createTargetRequestDto) {

    return ResponseEntity.ok(
        ApiResult.success(targetService.saveTarget(createTargetRequestDto), 201, CREATED));

  }

}
