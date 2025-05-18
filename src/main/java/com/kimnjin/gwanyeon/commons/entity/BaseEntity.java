package com.kimnjin.gwanyeon.commons.entity;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BaseEntity {
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}