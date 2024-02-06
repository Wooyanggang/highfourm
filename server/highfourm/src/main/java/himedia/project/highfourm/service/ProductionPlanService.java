package himedia.project.highfourm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.MonthlyProductionPlanDTO;
import himedia.project.highfourm.dto.ProductionPlanFormDTO;
import himedia.project.highfourm.dto.ProductionStatusDTO;
import himedia.project.highfourm.dto.WorkPerformanceResponseDTO;
import himedia.project.highfourm.entity.MonthlyProductionPlan;
import himedia.project.highfourm.repository.MonthlyProductionPlanRepository;
import himedia.project.highfourm.repository.ProductionPlanRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductionPlanService {
	
	private final ProductionPlanRepository productionPlanRepository;
	private final MonthlyProductionPlanRepository monthlyProductionPlanRepository;
	
//	public Map<String, Object> findAllProductionPlans() {
	public List<ProductionPlanFormDTO> findAllProductionPlans() {
		List<ProductionPlanFormDTO> productionPlans = productionPlanRepository.findAllProductionPlan(Sort.by(Sort.Direction.DESC, "o.orderId"));
		return productionPlans;
	}
	
	public List<MonthlyProductionPlanDTO> findMonthlyProductionPlan(String productionPlanId){
		List<MonthlyProductionPlan> monthlyProductionPlans = monthlyProductionPlanRepository.findByProductionPlanId(productionPlanId);
		List<MonthlyProductionPlanDTO> monthlyProductionPlanDTOs = monthlyProductionPlans.stream()
				.map(MonthlyProductionPlanDTO::fromEntity)
				.collect(Collectors.toList());
		
		return monthlyProductionPlanDTOs;
	}
	
	public List<WorkPerformanceResponseDTO> findProductionPlanDetails() {
		List<WorkPerformanceResponseDTO> responseList = productionPlanRepository.findProductionPlanDetails();
		return responseList;
	}
	
	public List<ProductionPlanFormDTO> findAll(){
		List<ProductionPlanFormDTO> resultList = productionPlanRepository.findAll()
				.stream().map(productionPlan -> productionPlan.toDTO())
				.collect(Collectors.toList());
		return resultList;
	}
	
	public List<ProductionStatusDTO> findStatus() {
		List<ProductionStatusDTO> statusList = productionPlanRepository.findStatus();
		return statusList;
	}
}
