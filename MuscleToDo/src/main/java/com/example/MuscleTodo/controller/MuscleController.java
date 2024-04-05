package com.example.MuscleTodo.controller;

import java.io.IOException;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.MuscleTodo.service.GrowthEntity;
import com.example.MuscleTodo.service.GrowthService;
import com.example.MuscleTodo.service.MuscleEntity;
import com.example.MuscleTodo.service.MuscleService;
import com.example.MuscleTodo.service.WeightEntity;
import com.example.MuscleTodo.service.WeightService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/muscle")
@RequiredArgsConstructor
public class MuscleController {
	
	private final MuscleService muscleService;
	private final WeightService weightService;
	private final GrowthService growthService;
	
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
	public String create(@Validated MuscleForm muscleForm, 
						 BindingResult bindingResult, Model model) {
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
	public String update(@PathVariable("id") int id,
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
	
	@GetMapping("/weight")
	public String weight(Model model) {
		model.addAttribute("weightList", weightService.weiSelect());
		model.addAttribute("title", "体重");
		return "muscle/weight";
	}
	

	@GetMapping("/weight/weiInsert")
	public String weiInsert(@ModelAttribute WeightForm weightForm, Model model) {
		model.addAttribute("mode", "CREATE");
		return "muscle/weiForm";
	}
	

	@GetMapping("/weight/weiView")
	public String weiView(Model model) {
		model.addAttribute("timeList", weightService.doTimeSelect());
		model.addAttribute("weightList", weightService.weightSelect());
		model.addAttribute("title", "グラフ");
		return "muscle/weiGraph";
	}
	
	@PostMapping("/weight")
	public String weiCreate(@Validated WeightForm weightForm, 
							BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return weiInsert(weightForm, model);
		}
		weightService.weiInsert(weightForm.toEntity());
		return "redirect:/muscle/weight";
	}
	
	@GetMapping("/weight/{id}/edit")
	public String weiEdit(@PathVariable("id") int id, Model model) {
		WeightEntity weightEntity = weightService.weiSelectById(id)
						.orElseThrow(MuscleNotFoundException::new);
		WeightForm weightForm = WeightForm.fromEntity(weightEntity);
		model.addAttribute("weightForm", weightForm);
		model.addAttribute("mode", "EDIT");
		return "muscle/weiForm";
	}
	
	@PutMapping("/weight/{id}")
	public String weiUpdate(@PathVariable("id") int id,
			 				@Validated @ModelAttribute WeightForm weightForm,
			 				BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("mode", "EDIT");
			return "muscle/weiForm";
		}
		WeightEntity weightEntity = weightForm.toEntity(id);
		weightService.weiUpdate(weightEntity);
		return "redirect:/muscle/weight";
	}
	
	@DeleteMapping("/weight/{id}")
	public String weiDelete(@PathVariable("id") int id) {
		weightService.weiDelete(id);
		return "redirect:/muscle/weight";
	}
	
	@GetMapping("/growth")
	public String growth(Model model) {
		model.addAttribute("title", "成長");
		model.addAttribute("growthList", growthService.groSelect());
		return "muscle/growth";
	}
	
	@GetMapping("/growth/groInsert")
	public String groInsert(@ModelAttribute GrowthForm growthForm, Model model) {
		model.addAttribute("mode", "CREATE");
		return "/muscle/groForm";	
	}
	
	@PostMapping("/growth")
	public String groCreate(@Validated GrowthForm growthForm,
							BindingResult bindingResult,
							RedirectAttributes redirectAttributes,
							Model model) {
		if (growthForm.getImageFile().isEmpty()) {
	        bindingResult.rejectValue("imageFile", "NotNull", "画像ファイルを選択してください");
	    }
		if(bindingResult.hasErrors()) {
			return groInsert(growthForm, model);
		}
		try {
			growthService.groInsert(growthForm);
			redirectAttributes.addFlashAttribute("successMessage", "画像ファイルを正常に保存しました");
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "画像ファイルの保存に失敗しました");
		}
		return "redirect:/muscle/growth";
	}
	
	@GetMapping("/growth/{id}/edit")
	public String groEdit(@PathVariable("id") int id, Model model) {
		GrowthEntity growthEntity = growthService.groSelectById(id)
						.orElseThrow(MuscleNotFoundException::new);
		GrowthForm growthForm = GrowthForm.fromEntity(growthEntity);
		model.addAttribute("growthForm" , growthForm);
		model.addAttribute("mode", "EDIT");
		return "muscle/groForm";
	}
	
	@PutMapping("/growth/{id}")
	public String groUpdate(@PathVariable("id") int id,
           			        @Validated @ModelAttribute GrowthForm growthForm,
				            BindingResult bindingResult, 
				            RedirectAttributes redirectAttributes,Model model) {
		if(bindingResult.hasErrors()) {
			GrowthEntity growthEntity = growthService.groSelectById(id)
					.orElseThrow(MuscleNotFoundException::new);
			growthForm.setCurrentImagePath(growthEntity.getImagePath());
			model.addAttribute("growthForm", growthForm);
			model.addAttribute("mode", "EDIT");
			return "muscle/groForm";
		}
		try {
			growthService.groUpdate(growthForm, id);
			redirectAttributes.addFlashAttribute("successMessage", "画像ファイルを正常に保存しました");
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "画像ファイルの保存に失敗しました");
		}
		return "redirect:/muscle/growth";
	}
	
	@DeleteMapping("/growth/{id}")
	public String groDelete(@PathVariable("id") int id) {
		growthService.groDelete(id);
		return "redirect:/muscle/growth";
	}
 }