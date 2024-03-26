package com.example.MuscleTodo.service;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GrowthEntity {

	private Integer id;
	private LocalDateTime doTime;
	private String imagePath;
	
}
