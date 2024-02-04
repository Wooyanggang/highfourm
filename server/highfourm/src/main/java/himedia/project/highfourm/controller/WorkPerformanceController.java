package himedia.project.highfourm.controller;

import java.util.List;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import himedia.project.highfourm.dto.WorkPerformanceDTO;
import himedia.project.highfourm.dto.WorkPerformanceListDTO;
import himedia.project.highfourm.dto.WorkPerformanceResponseDTO;
import himedia.project.highfourm.service.ProductionPlanService;
import himedia.project.highfourm.service.WorkPerformanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WorkPerformanceController {

	private final WorkPerformanceService workPerformanceService;
	private final ProductionPlanService productionPlanService;
	
	@GetMapping("/work-performance")
	public ResponseEntity<List<WorkPerformanceListDTO>> workPerformance() {
		List<WorkPerformanceListDTO> WorkPerformanceList = workPerformanceService.findList();
		return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(WorkPerformanceList);
	}
	
	@GetMapping("/work-performance/new")
	public ResponseEntity<List<WorkPerformanceResponseDTO>> workPerformanceNew() {
		List<WorkPerformanceResponseDTO> reponseList = productionPlanService.findProductionPlanDetails();
		return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(reponseList);
	}
	
	@PostMapping("/work-performance/new")
	public ResponseEntity<String> saveWorkPerformance(@RequestBody WorkPerformanceDTO[] workPerformanceDTOArray) {
		for (WorkPerformanceDTO workPerformanceDTO : workPerformanceDTOArray) {
			workPerformanceService.saveWorkPerformance(workPerformanceDTO);
		}
		log.info(workPerformanceDTOArray.toString());
		return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body("Success");
	}
}
