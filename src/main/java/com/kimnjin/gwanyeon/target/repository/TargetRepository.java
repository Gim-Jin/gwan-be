package com.kimnjin.gwanyeon.target.repository;

import com.kimnjin.gwanyeon.target.entity.Target;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TargetRepository {

  int insert(Target target);

  List<Target> selectAll();

  List<Target> selectAllOrderByLikesCount();

}
