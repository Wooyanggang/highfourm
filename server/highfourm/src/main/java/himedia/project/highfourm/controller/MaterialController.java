package himedia.project.highfourm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import himedia.project.highfourm.vo.MaterialRequestDTO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class MaterialController {

	@PostMapping("/materials/stock/new")
	public String addMaterial(@RequestBody MaterialRequestDTO material) {
		log.info("material.getLeadTime(): {}",material.getLeadTime());
		log.info("material.getMaterialName(): {}",material.getMaterialName());
		log.info("material.getMaxStock(): {}",material.getMaxStock());
		
		return "redirect:http://localhost:3000/materials/stock";
	}
	
}
