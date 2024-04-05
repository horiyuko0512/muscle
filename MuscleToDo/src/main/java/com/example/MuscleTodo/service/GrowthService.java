package com.example.MuscleTodo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MuscleTodo.controller.GrowthForm;
import com.example.MuscleTodo.controller.MuscleNotFoundException;
import com.example.MuscleTodo.repository.GrowthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GrowthService {
	
	private final GrowthRepository growthRepository;
	
	@Value("${image.folder}")
	private String imgFolder;
	
	public List<GrowthEntity> groSelect(){
		return growthRepository.groSelect();
	}
	
	@Transactional
	public void groInsert(GrowthForm growthForm) throws IOException{
		if(!growthForm.getImageFile().isEmpty()) {
			String fileName = UUID.randomUUID().toString() + "_" + growthForm.getImageFile().getOriginalFilename();
			Path imgFilePath = Path.of(imgFolder, fileName);
			Files.copy(growthForm.getImageFile().getInputStream(), imgFilePath);
			
			String dbPath = imgFilePath.toString().replace("\\", "/");
			
			GrowthEntity growthEntity = new GrowthEntity(null, growthForm.getDoTime(), dbPath);
			growthRepository.groInsert(growthEntity);
		}
	}
	
	public Optional<GrowthEntity> groSelectById(int id){
		return growthRepository.groSelectById(id);
	}
	
	@Transactional
	public void groUpdate(GrowthForm growthForm, int id) throws IOException{
		GrowthEntity growthEntity = growthRepository.groSelectById(id)
				.orElseThrow(MuscleNotFoundException::new);
		if(!growthForm.getImageFile().isEmpty()) {
			String fileName = UUID.randomUUID().toString() + "_" + growthForm.getImageFile().getOriginalFilename();
			Path imgFilePath = Path.of(imgFolder, fileName);
			Files.copy(growthForm.getImageFile().getInputStream(), imgFilePath);
			
			String dbPath = imgFilePath.toString().replace("\\", "/");
			growthEntity.setImagePath(dbPath);	
		} else if(growthForm.getCurrentImagePath() != null && !growthForm.getCurrentImagePath().isEmpty()) {
			growthEntity.setImagePath(growthForm.getImagePath());
		}
		growthEntity.setDoTime(growthForm.getDoTime());
		growthRepository.groUpdate(growthEntity);
	}
	
	@Transactional
	public void groDelete(int id) {
		growthRepository.groDelete(id);
	}
}