package himedia.project.highfourm.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.PerformanceDTO;
import himedia.project.highfourm.repository.ProductionPlanRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PerformanceService {
	private final ProductionPlanRepository productionPlanRepository;
	
	public List<PerformanceDTO> findPerformance(){
		List<PerformanceDTO> performances = productionPlanRepository.findAllPerformances(Sort.by(Sort.Direction.DESC,"plan.productionPlanId"));
		performances.stream()
			.forEach(e -> {
				e.setTotalProductionAmount(productionPlanRepository.sumProductionAmount(e.getProductionPlanId()));
			});
		return performances;
	}
	
}
