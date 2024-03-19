package com.example.MuscleTodo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MuscleTodo.repository.WeightRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeightService {
	
	private final WeightRepository weightRepository;
	
	public List<WeightEntity> weiSelect(){
		return weightRepository.weiSelect();
	}
	
	@Transactional
	public void weiInsert(WeightEntity weightEntity) {
		weightRepository.weiInsert(weightEntity);
	}
	
	public Optional<WeightEntity> weiSelectById(int id){
		return weightRepository.weiSelectById(id);
	}
	
	@Transactional
	public void weiUpdate(WeightEntity weightEntity) {
		weightRepository.weiUpdate(weightEntity);
	}
	
	@Transactional
	public void weiDelete(int id) {
		weightRepository.weiDelete(id);
	}
}

