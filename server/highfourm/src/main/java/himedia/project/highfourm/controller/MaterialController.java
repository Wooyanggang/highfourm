package himedia.project.highfourm.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import himedia.project.highfourm.dto.ResponseDTO;
import himedia.project.highfourm.dto.material.MaterialRequestDTO;
import himedia.project.highfourm.dto.material.MaterialResponseDTO;
import himedia.project.highfourm.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class MaterialController {
	
	
	private final MaterialService materialService;

	@PostMapping("/materials/stock/new")
	public String addMaterial(@RequestBody MaterialRequestDTO material) {
		log.info("material.getLeadTime(): {}",material.getLeadTime());
		log.info("material.getMaterialName(): {}",material.getMaterialName());
		log.info("MaxStock: {}",material.getMaxStock());
		log.info("SafetyStock : {}",material.getSafetyStock());
		log.info("Unit: {}",material.getUnit());
		materialService.saveMaterial(material);
		
		return "redirect:http://localhost:3000/materials/stock";
	}
	
	@GetMapping("/materials/stock")
	public List<MaterialResponseDTO> getMaterialList() {
		List<MaterialResponseDTO> MaterialList = materialService.MaterialList();
		 MaterialList.stream()
				.filter(material -> material.getMaterialName().contains("자재"))
				.forEach(material -> System.out.println("Material: " + material.getManagementName()));
		
		return MaterialList;
	}
	
	@PostMapping("/materials/order-history")
	public String addMaterialHistory() {
		return "redirect:http://localhost:3000/materials/order-history";
	}
	
}
