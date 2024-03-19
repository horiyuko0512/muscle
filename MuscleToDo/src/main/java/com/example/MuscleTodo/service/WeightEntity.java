package com.example.MuscleTodo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeightEntity {
	
	private Integer id;
	private LocalDateTime doTime;
	private BigDecimal weight;
}
