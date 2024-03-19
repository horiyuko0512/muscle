package com.example.MuscleTodo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.MuscleTodo.service.WeightEntity;

@Mapper
public interface WeightRepository {
	
	@Select("SELECT id, doTime, weight FROM weight")
	List<WeightEntity> weiSelect();
	
	@Insert("INSERT INTO weight (doTime, weight) VALUES (#{list.doTime}, #{list.weight})")
	void weiInsert(@Param("list") WeightEntity weightEntity);
	
	@Select("SELECT id, doTime, weight FROM weight WHERE id = #{id}")
	Optional<WeightEntity> weiSelectById(@Param("id") int id);
	
	@Update("UPDATE weight SET doTime = #{list.doTime}, weight = #{list.weight} WHERE id = #{list.id}")
	void weiUpdate(@Param("list") WeightEntity weightEntity);
	
	@Delete("DELETE FROM weight WHERE id = #{id}")
	void weiDelete(@Param("id") int id);
}
