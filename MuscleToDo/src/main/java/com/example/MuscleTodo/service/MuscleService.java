package com.example.MuscleTodo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MuscleTodo.repository.MuscleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MuscleService {

	private final MuscleRepository muscleRepository;
	
	public List<MuscleEntity> select(MuscleSearchEntity muscleSearchEntity){
		return muscleRepository.select(muscleSearchEntity);
	}
	
	@Transactional
	public void insert(MuscleEntity muscleEntity) {
		muscleRepository.insert(muscleEntity);
	}
	
	public Optional<MuscleEntity> findById(int id){
		return muscleRepository.findById(id);
	}
	
	@Transactional
	public void update(MuscleEntity muscleEntity) {
		muscleRepository.update(muscleEntity);
	}

	@Transactional
	public void delete(int id) {
		muscleRepository.delete(id);
	}

}
