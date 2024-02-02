package himedia.project.highfourm.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.ProductionPlanDTO;
import himedia.project.highfourm.repository.ProductionPlanRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductionPlanService {
	
	private final ProductionPlanRepository productionPlanRepository;
	
	public List<ProductionPlanDTO> findAllProductionPlans() {
		List<ProductionPlanDTO> productionPlans = productionPlanRepository.findAllProductionPlan(Sort.by(Sort.Direction.DESC, "o.orderId"));
		return productionPlans;
	}
}
