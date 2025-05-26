package com.kimnjin.gwanyeon.rehabprogram.dto;

import java.util.List;

public record GptExerciseRoutineRequestDto(
    String startDate,
    List<DayRoutine> weeklyRoutine
) {

}

record DayRoutine(
    String day,
    String date,
    List<Exercise> exercises
) {

}

record Exercise(
    String category,
    String name,
    String duration,
    String equipment,
    String note
) {

}