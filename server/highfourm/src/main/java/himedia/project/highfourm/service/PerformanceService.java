package himedia.project.highfourm.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.PerformanceDTO;
import himedia.project.highfourm.dto.WorkPerformanceDTO;
import himedia.project.highfourm.repository.ProductionPlanRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PerformanceService {
	private final ProductionPlanRepository productionPlanRepository;
	
	public List<PerformanceDTO> findAllPerformance(){
		List<PerformanceDTO> performances = productionPlanRepository.findAllPerformances(Sort.by(Sort.Direction.DESC,"plan.productionPlanId"));
		performances.stream()
			.forEach(e -> e.setTotalProductionAmount(productionPlanRepository.sumProductionAmount(e.getProductionPlanId())));
		return performances;
	}
	
	public PerformanceDTO findPerformance(String productionPlanId){
		System.out.println(productionPlanId);
		PerformanceDTO performance = productionPlanRepository.findPerformances(productionPlanId);
		System.out.println(performance);
		List<WorkPerformanceDTO> productionList = productionPlanRepository.findProductionList(productionPlanId);
		
//		performance.setTotalProductionAmount(productionList.stream()
//				.mapToLong(e -> e.getAcceptanceAmount())
//				.sum());
		performance.setTotalProductionAmount(productionPlanRepository.sumProductionAmount(productionPlanId));
		performance.setWorkPerformances(productionList);
		
		return performance;
	}
	
}
