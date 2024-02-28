package com.example.MuscleTodo.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.MuscleTodo.service.MuscleEntity;
import com.example.MuscleTodo.service.MuscleType;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MuscleForm {
	
	@NotNull (message = "日付を設定してください。")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime doTime;
	
	@NotBlank (message = "トレーニング名を入力してください。")
    @Size(min = 0, max = 50, message = "50文字以内で入力してください。")
	private String traName;
	
	@NotNull (message = "重量を入力してください。")
	@Digits(integer=3, fraction=2, message = "重量は整数部3桁、小数部2桁以下である必要があります。")
	private BigDecimal traWei;
	
	@NotNull (message = "セット数を入力してください。")
	@Min(value = 1, message = "セット数は1以上である必要があります。")
    @Max(value = 999, message = "セット数は３桁以内である必要があります。")
	private Integer traSet;
	
	@NotNull (message = "回数を入力してください。")
	@Min(value = 1, message = "回数は1以上である必要があります。")
    @Max(value = 999, message = "回数は３桁以内である必要があります。")
	private Integer traTimes;
	
	@NotNull (message = "トレーニング部位を選んでください")
    @Pattern(regexp = "胸|背中|腕|肩|背中", message = "胸、背中、腕、肩、背中の中から選んでください。")
	private String traType;
	
	public MuscleEntity toEntity() {
		return new MuscleEntity(
				null,doTime,traName,traWei,traSet,traTimes,MuscleType.valueOf(getTraType()));
	}
	
	public MuscleEntity toEntity(int id) {
		return new MuscleEntity(
				id,doTime,traName,traWei,traSet,traTimes,MuscleType.valueOf(getTraType()));
	}

	public static MuscleForm fromEntity(MuscleEntity muscleEntity) {
		return new MuscleForm(
				muscleEntity.getDoTime(),
				muscleEntity.getTraName(),
				muscleEntity.getTraWei(),
				muscleEntity.getTraSet(),
				muscleEntity.getTraTimes(),
				muscleEntity.getTraType().name()
		     );
	}
}