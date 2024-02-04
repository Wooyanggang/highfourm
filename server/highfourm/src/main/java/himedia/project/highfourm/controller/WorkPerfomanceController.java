package himedia.project.highfourm.controller;

import java.util.List;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import himedia.project.highfourm.dto.WorkPerfomanceDTO;
import himedia.project.highfourm.dto.WorkPerfomanceListDTO;
import himedia.project.highfourm.dto.WorkPerfomanceResponseDTO;
import himedia.project.highfourm.service.ProductionPlanService;
import himedia.project.highfourm.service.WorkPerfomanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WorkPerfomanceController {

	private final WorkPerfomanceService workPerfomanceService;
	private final ProductionPlanService productionPlanService;
	
	@GetMapping("/work-perfomance")
	public ResponseEntity<List<WorkPerfomanceListDTO>> workPerfomance() {
		List<WorkPerfomanceListDTO> WorkPerfomanceList = workPerfomanceService.findList();
		return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(WorkPerfomanceList);
	}
	
	@GetMapping("/work-perfomance/new")
	public ResponseEntity<List<WorkPerfomanceResponseDTO>> workPerfomanceNew() {
		List<WorkPerfomanceResponseDTO> reponseList = productionPlanService.findProductionPlanDetails();
		return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(reponseList);
	}
	
	@PostMapping("/work-perfomance/new")
	public ResponseEntity<String> saveWorkPerfomance(@RequestBody WorkPerfomanceDTO[] workPerfomanceDTOArray) {
		for (WorkPerfomanceDTO workPerfomanceDTO : workPerfomanceDTOArray) {
			workPerfomanceService.saveWorkPerfomance(workPerfomanceDTO);
		}
		log.info(workPerfomanceDTOArray.toString());
		return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body("Success");
	}
}
