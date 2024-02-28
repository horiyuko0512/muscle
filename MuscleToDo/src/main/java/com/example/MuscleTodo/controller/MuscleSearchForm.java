package com.example.MuscleTodo.controller;

import java.util.List;
import java.util.Optional;

import com.example.MuscleTodo.service.MuscleSearchEntity;
import com.example.MuscleTodo.service.MuscleType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MuscleSearchForm {
	List<String> muscleTypeList;

	public MuscleSearchEntity toEntity() {
		List<MuscleType> TypeEntityList = Optional.ofNullable(muscleTypeList) 
				   .map(TypeList -> TypeList.stream().map(MuscleType::valueOf).toList())
				   .orElse(List.of());
		return new MuscleSearchEntity(TypeEntityList);
	}
	
	public boolean isChecked(String traType) {
		return Optional.ofNullable(muscleTypeList)
					.map(l -> l.contains(traType))
					.orElse(false);
	}
}
