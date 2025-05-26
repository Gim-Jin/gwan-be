package com.kimnjin.gwanyeon.rehabprogram.controller;

import com.kimnjin.gwanyeon.commons.dto.ApiResult;
import com.kimnjin.gwanyeon.commons.security.UserPrincipal;
import com.kimnjin.gwanyeon.rehabprogram.dto.PrescriptionResponseDto;
import com.kimnjin.gwanyeon.rehabprogram.dto.RehabProgramResponseDto;
import com.kimnjin.gwanyeon.rehabprogram.dto.UserSurveyRequestDto;
import com.kimnjin.gwanyeon.rehabprogram.service.RehabProgramService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/rehab-programs")
@RequiredArgsConstructor
public class RehabProgramController {


  private final RehabProgramService rehabProgramService;
  private final String CREATED = "created";
  private final String DELETED = "deleted";
  private final String NO_CONTENT = "noContent";
  private final String UPDATED = "updated";


  @PostMapping
  public ResponseEntity<ApiResult<RehabProgramResponseDto>> createRoutines(
      @RequestBody UserSurveyRequestDto userSurveyRequestDto,
      @AuthenticationPrincipal UserPrincipal userPrincipal) {

    //개발 되면

    RehabProgramResponseDto result = rehabProgramService.saveRehabProgram(userSurveyRequestDto,
        userPrincipal.getUserId());

    return ResponseEntity.ok(ApiResult.success(result, 201, CREATED));
  }

  //
  @GetMapping
  public ResponseEntity<ApiResult<List<PrescriptionResponseDto>>> getAllPrescriptions(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      @RequestParam(required = false) Boolean isMyRoutinePage) {

    List<PrescriptionResponseDto> result = null;
    if (isMyRoutinePage == null || !isMyRoutinePage) {
      result = rehabProgramService.getAllPrescriptionsByUserId(
          userPrincipal.getUserId());
    } else {
      result = rehabProgramService.getLatestPrescriptionsByUserId(userPrincipal.getUserId());
    }

    return !result.isEmpty() ? ResponseEntity.ok(ApiResult.success(result))
        : ResponseEntity.ok(ApiResult.success(result, 204, NO_CONTENT));

  }

  @GetMapping("/{rehabProgramId}")
  public ResponseEntity<ApiResult<RehabProgramResponseDto>> getRehabProgramById(
      @PathVariable Long rehabProgramId
  ) {

    RehabProgramResponseDto result = rehabProgramService.getRehabProgramById(rehabProgramId);

    return ResponseEntity.ok(ApiResult.success(result));

  }

  @PutMapping("/{rehabProgramId}")
  public ResponseEntity<ApiResult<String>> doneRehabProgram(@PathVariable Long rehabProgramId) {

    rehabProgramService.doneRehabProgram(rehabProgramId);

    return ResponseEntity.ok(ApiResult.success(UPDATED));
  }

  @DeleteMapping("/{rehabProgramId}")
  public ResponseEntity<ApiResult<String>> deleteRehabProgram(@PathVariable Long rehabProgramId) {

    rehabProgramService.removeRehabProgram(rehabProgramId);

    return ResponseEntity.ok(ApiResult.success(DELETED));
  }


}
