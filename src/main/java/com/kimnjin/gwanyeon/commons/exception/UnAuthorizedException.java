package com.kimnjin.gwanyeon.commons.exception;

public class UnAuthorizedException extends RuntimeException {

  UnAuthorizedException(String message) {
    super(message);
  }
}
