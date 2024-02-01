package himedia.project.highfourm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.material.MaterialListResponseDTO;
import himedia.project.highfourm.dto.material.MaterialOrderListDTO;
import himedia.project.highfourm.dto.material.MaterialOrderRequestDTO;
import himedia.project.highfourm.dto.material.MaterialOrderResponseDto;
import himedia.project.highfourm.dto.material.MaterialRequestDTO;
import himedia.project.highfourm.entity.Material;
import himedia.project.highfourm.entity.StockManagement;
import himedia.project.highfourm.repository.MaterialHistoryRepository;
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

	// 원자재 등록
	public void saveMaterial(MaterialRequestDTO material) {
		materialRepository.save(material.toEntityFirst());

		Optional<StockManagement> stockManagement = managementRepository.findById(material.getManagementId());
		//if (stockManagement.isPresent())
			stockRepository.save(material.toEntitySecond(stockManagement.get()));
	}

	// 원자재 리스트 조회
	public List<MaterialListResponseDTO> MaterialList() {
		return stockRepository.findMaterialList();
	}

	// 수급내역 등록
	public void saveMaterialhistory(MaterialOrderRequestDTO orderRequestDTO) {
		Optional<Material> material = materialRepository.findById(orderRequestDTO.getMaterialId());

		if (material.isPresent()) {
			historyRepository.save(orderRequestDTO.toEntity(material.get()));
		}
	}

	// 수급내역 조회
	public List<MaterialOrderResponseDto> getMaterialOrderList() {
		
	    List<MaterialOrderListDTO> materialOrderListDTOs = historyRepository.findAllWithMaterialFields();

	    // MaterialOrderListDTO를 MaterialOrderResponseDto로 변환! 
	    List<MaterialOrderResponseDto> materialOrderResponseDtos = materialOrderListDTOs.stream()
	            .map(MaterialOrderResponseDto::toOrderDTO)
	            .collect(Collectors.toList());
		return materialOrderResponseDtos;
	}
}
