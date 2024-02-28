package com.example.MuscleTodo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MuscleEntity {
	
	private Integer id;
	private LocalDateTime doTime;
	private String traName;
	private BigDecimal traWei;
	private int traSet;
	private int traTimes;
	private MuscleType traType;
}
