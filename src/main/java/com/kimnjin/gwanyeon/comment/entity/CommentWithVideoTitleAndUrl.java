package com.kimnjin.gwanyeon.comment.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentWithVideoTitleAndUrl extends Comment {

  private String exerciseVideoTitle;

  private String youtubeId;

}
