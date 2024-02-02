package himedia.project.highfourm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.ProductionPlanDTO;
import himedia.project.highfourm.service.ProductionPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ProductionPlanController {
	private final ProductionPlanService productionPlanService;
	
	@GetMapping("/production-plan")
	public List<ProductionPlanDTO> productionPlan(){
		return productionPlanService.findAllProductionPlans();
	}

}
