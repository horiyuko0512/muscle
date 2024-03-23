package com.example.MuscleTodo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.MuscleTodo.service.GrowthEntity;

@Mapper
public interface GrowthRepository {
	
	@Select("SELECT id, doTime, imagePath FROM growth")
	List<GrowthEntity> groSelect();
	
	@Insert("INSERT INTO growth (doTime, imagePath) VALUES (#{list.doTime}, #{list.imagePath})")
	void groInsert(@Param("list") GrowthEntity growthEntity);
}
