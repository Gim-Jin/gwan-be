package com.kimnjin.gwanyeon.commons.entity;


import java.time.LocalDateTime;
import lombok.Builder;


public class BaseEntity {
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}