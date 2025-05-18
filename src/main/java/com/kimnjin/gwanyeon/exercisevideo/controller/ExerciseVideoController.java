package com.kimnjin.gwanyeon.exercisevideo.controller;


import com.kimnjin.gwanyeon.exercisevideo.entity.ExerciseVideoWithTarget;
import com.kimnjin.gwanyeon.exercisevideo.repository.ExerciseVideoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercise-video")
@RequiredArgsConstructor
public class ExerciseVideoController {

  private final ExerciseVideoRepository exerciseVideoRepository;


}
