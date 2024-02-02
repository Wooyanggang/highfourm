package himedia.project.highfourm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.mrp.MrpProductionPlanDTO;
import himedia.project.highfourm.dto.mrp.MrpRequiredMaterialDTO;
import himedia.project.highfourm.repository.MrpRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MrpService {

	private final MrpRepository repository;
	
	public List<MrpProductionPlanDTO> findByProductionPlans() {
		return repository.findByProductionPlans();
	}
	
	public List<MrpRequiredMaterialDTO> findByMaterials(String productionPlanId) {
		return repository.findByMaterials(productionPlanId);
	}
	
	public List<MrpProductionPlanDTO> findByProductionPlanID(String productionPlanId) {
		return repository.findByProductionPlanID(productionPlanId);
	}

	public List<MrpProductionPlanDTO> findByProductId(String productId) {
		return repository.findByProductId(productId);
	}
	
	public List<MrpProductionPlanDTO> findByProductName(String productName) {
		return repository.findByProductName(productName);
	}
	
	public List<MrpProductionPlanDTO> findByDueDate(String dueDate) {
		return repository.findByDueDate(dueDate);
	}
}
