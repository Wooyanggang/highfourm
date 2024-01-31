package himedia.project.highfourm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.MrpDTO;
import himedia.project.highfourm.repository.MrpRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MrpService {

	private final MrpRepository repository;
	
	public List<MrpDTO> findByProductionPlans() {
		return repository.findByProductionPlans();
	}
	
	public List<MrpDTO> findByMaterials(String productionPlanId) {
		return repository.findByMaterials(productionPlanId);
	}
	
	public List<MrpDTO> findByProductionPlanID(String productionPlanId) {
		return repository.findByProductionPlanID(productionPlanId);
	}
	
	public List<MrpDTO> findByProductId(String productId) {
		return repository.findByProductId(productId);
	}
	
	public List<MrpDTO> findByProductName(String productName) {
		return repository.findByProductName(productName);
	}
	
	public List<MrpDTO> findByDueDate(String dueDate) {
		return repository.findByDueDate(dueDate);
	}
}
