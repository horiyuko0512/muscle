package com.example.MuscleTodo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.MuscleTodo.service.GrowthEntity;

@Mapper
public interface GrowthRepository {
	
	@Select("SELECT id, doTime, imagePath FROM growth")
	List<GrowthEntity> groSelect();
	
	@Insert("INSERT INTO growth (doTime, imagePath) VALUES (#{list.doTime}, #{list.imagePath})")
	void groInsert(@Param("list") GrowthEntity growthEntity);
	
	@Select("SELECT id, doTime, imagePath FROM growth WHERE id = #{id}")
	Optional<GrowthEntity> groSelectById(@Param("id") int id);
	
	@Update("UPDATE growth SET doTime = #{list.doTime}, imagePath = #{list.imagePath} WHERE id = #{list.id}")
	void groUpdate(@Param("list") GrowthEntity growthEntity);
	
	@Delete("DELETE FROM growth WHERE id = #{id}")
	void groDelete(@Param("id") int id);
}