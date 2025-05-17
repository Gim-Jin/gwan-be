package com.kimnjin.gwanyeon.commons.dto;

public class ApiResult<T> {

  private boolean success;
  private int code;
  private String message;
  private T data;

//  public <T> ApiResult<T> success(T data) {
//    return new ApiResult<>(true, 200, "요청 성공", data);
//  }

}
