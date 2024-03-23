package com.example.MuscleTodo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MuscleTodo.controller.GrowthForm;
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
			String filePath = "img/" + fileName;
			
			GrowthEntity growthEntity = new GrowthEntity(null, growthForm.getDoTime(), filePath);
			growthRepository.groInsert(growthEntity);
		}
	}
}