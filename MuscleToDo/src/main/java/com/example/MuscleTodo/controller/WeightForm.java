package com.example.MuscleTodo.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.MuscleTodo.service.WeightEntity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeightForm {
	
	@NotNull (message = "日付を設定してください。")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime doTime;
	
	@NotNull (message = "体重を入力してください。")
	@Digits(integer=3, fraction=2, message = "体重は整数部3桁、小数部2桁以下である必要があります。")
	private BigDecimal weight;
	
	public WeightEntity toEntity() {
		return new WeightEntity(null, doTime, weight);
	}
	
	public WeightEntity toEntity(int id) {
		return new WeightEntity(id, doTime, weight);
	}
	
	public static WeightForm fromEntity(WeightEntity weightEntity) {
		return new WeightForm(weightEntity.getDoTime(), weightEntity.getWeight());
	}
}
