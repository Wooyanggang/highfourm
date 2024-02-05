package himedia.project.highfourm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import himedia.project.highfourm.dto.ProductionPlanDTO;
import himedia.project.highfourm.dto.ProductionStatusDTO;
import himedia.project.highfourm.dto.WorkPerformanceDTO;
import himedia.project.highfourm.service.ProductionPlanService;
import himedia.project.highfourm.service.WorkPerformanceService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductionController {

	private final ProductionPlanService productionPlanService;
	private final WorkPerformanceService workPerformanceService;
	
	@GetMapping("/production-status")
	public ResponseEntity<Map<String, Object>> status() {
		Map<String, Object> responseMap = new HashMap<>();
		
		// productionPlan findStatus
		List<ProductionStatusDTO> productionStatusList = productionPlanService.findStatus();
		responseMap.put("productionPlan", productionStatusList);
		
		// workPerfomance finAll
		List<WorkPerformanceDTO> workPerformanceList = workPerformanceService.findAll();
		responseMap.put("workPerfomance", workPerformanceList);
		
		return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(responseMap);
	}
}
