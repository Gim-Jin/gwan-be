package com.kimnjin.gwanyeon.rehabprogram.repository;

import com.kimnjin.gwanyeon.rehabprogram.entity.Prescription;
import com.kimnjin.gwanyeon.rehabprogram.entity.RehabProgram;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RehabProgramRepository {

  int insert(RehabProgram rehabProgram);

  int update(Long programId);

  int delete(Long programId);

  RehabProgram selectRehabProgramByProgramId(Long programId);

  List<RehabProgram> selectAllRehabProgramsByUserId(Long userId);

  List<Prescription> selectProgramDescriptionByUserId(Long userId);

  List<Prescription> selectLatestProgramDescription(Long userId);

}
