package himedia.project.highfourm.service;

import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.WorkPerformanceDTO;
import himedia.project.highfourm.dto.WorkPerformanceListDTO;
import himedia.project.highfourm.entity.ProductionPlan;
import himedia.project.highfourm.repository.ProductionPlanRepository;
import himedia.project.highfourm.repository.WorkPerformanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkPerformanceService {
	private final WorkPerformanceRepository workPerformanceRepository;
	private final ProductionPlanRepository productionPlanRepository;
	
	public List<WorkPerformanceListDTO> findList() {
		List<WorkPerformanceListDTO> resultList = workPerformanceRepository.findList();
		return resultList;
	}
	public List<WorkPerformanceDTO> findAll() {
		List<WorkPerformanceDTO> resultList = workPerformanceRepository.findAll()
				.stream().map(workPerformance -> 
					workPerformance.toWorkPerformanceDTO()).collect(Collectors.toList());
		return resultList;
	}
	
	public void saveWorkPerformance(WorkPerformanceDTO workPerformanceDTO) {
		Optional<ProductionPlan> production = productionPlanRepository.findById(workPerformanceDTO.getProductionPlanId());
		if (production.isPresent()) {
	        workPerformanceRepository.save(workPerformanceDTO.toEntity(production.get()));
	        log.info(production.get().toString());
	    } else {
	        // ProductionPlan이 존재하지 않을 경우 처리 로직
	    	log.info("생산 계획이 없음");
	    }
	}
}
