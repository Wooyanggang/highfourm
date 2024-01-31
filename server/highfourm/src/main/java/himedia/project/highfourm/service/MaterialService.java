package himedia.project.highfourm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.material.MaterialRequestDTO;
import himedia.project.highfourm.dto.material.MaterialResponseDTO;
import himedia.project.highfourm.entity.StockManagement;
import himedia.project.highfourm.repository.MaterialHistoryRepository;
import himedia.project.highfourm.repository.MaterialListRepository;
import himedia.project.highfourm.repository.MaterialRepository;
import himedia.project.highfourm.repository.MaterialStockRepository;
import himedia.project.highfourm.repository.StockManagementRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService {

	private final MaterialRepository materialRepository;
	private final MaterialHistoryRepository historyRepository;
	private final MaterialStockRepository stockRepository;
	private final StockManagementRepository managementRepository;
	private final MaterialListRepository listRepository;
	
	
	public void saveMaterial(MaterialRequestDTO material) {
		materialRepository.save(material.toEntityFirst());
		
		Optional<StockManagement> stockManagement = managementRepository.findById(material.getManagementId());
		if(stockManagement.isPresent())
			stockRepository.save(material.toEntitySecond(stockManagement.get()));
	}
	
	public List<MaterialResponseDTO> MaterialList() {
		return stockRepository.findMaterialList();
		//return listRepository.findMaterialList();
	}
}
