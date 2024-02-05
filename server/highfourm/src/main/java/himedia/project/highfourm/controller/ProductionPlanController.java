package himedia.project.highfourm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.MonthlyProductionPlanDTO;
import himedia.project.highfourm.dto.ProductionPlanFormDTO;
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
	public List<ProductionPlanFormDTO> productionPlan(){
		
		return productionPlanService.findAllProductionPlans();
	}
	
	@GetMapping("/production-plan/{productionPlanId}")
	public List<MonthlyProductionPlanDTO> monthlyProductionPlan(@PathVariable("productionPlanId") String productionPlanId) {
		List<MonthlyProductionPlanDTO> monthlyProductionPlans = productionPlanService.findMonthlyProductionPlan(productionPlanId);
		return monthlyProductionPlans;
	}

}
