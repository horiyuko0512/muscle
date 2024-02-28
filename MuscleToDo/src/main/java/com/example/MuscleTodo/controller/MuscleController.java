package com.example.MuscleTodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MuscleTodo.service.MuscleEntity;
import com.example.MuscleTodo.service.MuscleService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/muscle")
@RequiredArgsConstructor
public class MuscleController {
	
	private final MuscleService muscleService;
	
	@GetMapping
	public String top(Model model) {		
		model.addAttribute("title", "トップページ");
		return "muscle/top";
	}
	
	@GetMapping("/view")
	public String view(MuscleSearchForm muscleSearchForm, Model model) {
		model.addAttribute("muscleList", muscleService.select(muscleSearchForm.toEntity()));
		model.addAttribute("title", "トレーニング一覧");
		model.addAttribute("muscleSearchForm", muscleSearchForm);
		return "muscle/lists";
	}
	
	@GetMapping("/insert")
	public String insert(@ModelAttribute MuscleForm muscleForm, Model model) {
		model.addAttribute("mode", "CREATE");
		return "muscle/form";
	}
	
	@PostMapping("/view")
	public String create(@Validated MuscleForm muscleForm, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return insert(muscleForm, model);
		}
		muscleService.insert(muscleForm.toEntity());
		return "redirect:/muscle/view";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		MuscleEntity muscleEntity = muscleService.findById(id)
				.orElseThrow(MuscleNotFoundException::new);
		MuscleForm muscleForm = MuscleForm.fromEntity(muscleEntity);
		model.addAttribute("muscleForm", muscleForm);
		model.addAttribute("mode", "EDIT");
		return "muscle/form";
	}
	
	@PutMapping("/view/{id}")
	public String updste(@PathVariable("id") int id,
						 @Validated @ModelAttribute MuscleForm muscleForm,
						 BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("mode", "EDIT");
			return "muscle/form";
		}
		MuscleEntity muscleEntity = muscleForm.toEntity(id);
		muscleService.update(muscleEntity);
		return "redirect:/muscle/view";
	}
	
	@DeleteMapping("/view/{id}")
	public String delete(@PathVariable("id") int id) {
		muscleService.delete(id);
		return "redirect:/muscle/view";
	}
}