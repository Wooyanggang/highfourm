package himedia.project.highfourm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.PerformanceDTO;
import himedia.project.highfourm.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class PerformanceController {
	
	private final PerformanceService performanceService; 
	
	@GetMapping("/production-performance")
	public List<PerformanceDTO> performance(){
		List<PerformanceDTO> performances = performanceService.findAllPerformance();
		return performances;
	}
	
	@GetMapping("/production-performance/{productionPlanId}/chart")
	public PerformanceDTO performanceChart(@PathVariable("productionPlanId") String productionPlanId) {
		PerformanceDTO performance = performanceService.findPerformance(productionPlanId);
		System.out.println(performance.getOrderId());
		return performance;
	}
}
