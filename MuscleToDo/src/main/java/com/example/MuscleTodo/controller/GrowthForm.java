package com.example.MuscleTodo.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.example.MuscleTodo.service.GrowthEntity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GrowthForm {
	
	private Integer id;
	
	@NotNull(message = "日付を設定してください。")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime doTime;
	
	private MultipartFile imageFile;
	
	private String imagePath;
	
	private String currentImagePath;

	public static GrowthForm fromEntity(GrowthEntity growthEntity) {
		return new GrowthForm(null, growthEntity.getDoTime(), null, growthEntity.getImagePath(), null);
	}
}
