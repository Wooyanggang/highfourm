package himedia.project.highfourm.service;

import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.WorkPerfomanceDTO;
import himedia.project.highfourm.dto.WorkPerfomanceListDTO;
import himedia.project.highfourm.entity.ProductionPlan;
import himedia.project.highfourm.repository.ProductionPlanRepository;
import himedia.project.highfourm.repository.WorkPerfomanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkPerfomanceService {
	private final WorkPerfomanceRepository workPerfomanceRepository;
	private final ProductionPlanRepository productionPlanRepository;
	
	public List<WorkPerfomanceListDTO> findList() {
		List<WorkPerfomanceListDTO> resultList = workPerfomanceRepository.findList();
		return resultList;
	}
//	public List<WorkPerfomanceDTO> findAll() {
//		List<WorkPerfomanceDTO> resultList = workPerfomanceRepository.findAll()
//				.stream().map(workPerfomance -> 
//					workPerfomance.toWorkPerfomanceDTO()).collect(Collectors.toList());
//		return resultList;
//	}
	
	public void saveWorkPerfomance(WorkPerfomanceDTO workPerfomanceDTO) {
		Optional<ProductionPlan> production = productionPlanRepository.findById(workPerfomanceDTO.getProductionPlanId());
		if (production.isPresent()) {
	        workPerfomanceRepository.save(workPerfomanceDTO.toEntity(production.get()));
	        log.info(production.get().toString());
	    } else {
	        // ProductionPlan이 존재하지 않을 경우 처리 로직
	    	log.info("생산 계획이 없음");
	    }
	}
}
