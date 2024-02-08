package himedia.project.highfourm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.material.MaterialListResponseDTO;
import himedia.project.highfourm.dto.material.MaterialOrderEditFormDTO;
import himedia.project.highfourm.dto.material.MaterialOrderListDTO;
import himedia.project.highfourm.dto.material.MaterialOrderRequestDTO;
import himedia.project.highfourm.dto.material.MaterialOrderResponseDto;
import himedia.project.highfourm.dto.material.MaterialRequestDTO;
import himedia.project.highfourm.entity.Material;
import himedia.project.highfourm.entity.MaterialHistory;
import himedia.project.highfourm.entity.MaterialStock;
import himedia.project.highfourm.entity.StockManagement;
import himedia.project.highfourm.repository.MaterialHistoryRepository;
import himedia.project.highfourm.repository.MaterialRepository;
import himedia.project.highfourm.repository.MaterialStockRepository;
import himedia.project.highfourm.repository.StockManagementRepository;
import jakarta.transaction.Transactional;
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
		if(stockManagement.isPresent())
			stockRepository.save(material.toEntitySecond(stockManagement.get()));
	}

	// 원자재 리스트 조회
	public List<MaterialListResponseDTO> MaterialList() {
		return stockRepository.findMaterialList();
	}
	//원자재 materilaId로 검색
	public List<MaterialListResponseDTO>findMaterialByMaterialId(String materialId) {
		return stockRepository.findMaterialByMaterialId(materialId);
	}
	//원자재 materilaId로 검색
	public List<MaterialListResponseDTO> findMaterialByMaterialName(String materialName) {
		return stockRepository.findMaterialByMaterialName(materialName);
	}
	//원자재 materilaId로 검색
	public List<MaterialListResponseDTO> findMaterialByManagement(String managementName) {
		return stockRepository.findMaterialByManagement(managementName);
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

	    // MaterialOrderListDTO를 MaterialOrderResponseDto로 변환
	    List<MaterialOrderResponseDto> materialOrderResponseDtos = materialOrderListDTOs.stream()
	            .map(orderListDto -> MaterialOrderResponseDto.toOrderDTO(orderListDto, materialOrderListDTOs))
	            .collect(Collectors.toList());

	    return materialOrderResponseDtos;
	}
//	// 수급내역검색
//	public List<MaterialOrderResponseDto> searchMaterialHistory(String searchType, String search) {
//		
//		List<MaterialOrderListDTO> materialOrderListDTOs = null;
//		
//		if(searchType.equals("자재코드")) {
//			materialOrderListDTOs = historyRepository.findMaterialHistoryByMaterialId(search);
//		}else if(searchType.equals("자재명")){
//			materialOrderListDTOs =  historyRepository.findMaterialHistoryByMaterialName(search);
//		}else if(searchType.equals("발주일")){
//			materialOrderListDTOs = historyRepository.findMaterialHistoryByOrderDate(search);
//		}else if(searchType.equals("입고일")){
//			materialOrderListDTOs =  historyRepository.findMaterialHistoryByInboundDate(search);
//		}	
//		
//		List<MaterialOrderResponseDto> materialOrderResponseDtos = materialOrderListDTOs.stream()
//				.map(orderListDto -> MaterialOrderResponseDto.toOrderDTO(orderListDto, materialOrderListDTOs))
//				.collect(Collectors.toList());
//		return materialOrderResponseDtos;
//	}
	// 수급내역 materilaId로 검색
	public List<MaterialOrderResponseDto>findMaterialHistoryByMaterialId(String materialId) {
		List<MaterialOrderListDTO> materialOrderListDTOs = historyRepository.findMaterialHistoryByMaterialId(materialId);
		
	    List<MaterialOrderResponseDto> materialOrderResponseDtos = materialOrderListDTOs.stream()
	            .map(orderListDto -> MaterialOrderResponseDto.toOrderDTO(orderListDto, materialOrderListDTOs))
	            .collect(Collectors.toList());
		return materialOrderResponseDtos;
	}
	//수급내역 자재명로 검색
	public List<MaterialOrderResponseDto> findMaterialHistoryByMaterialName(String materialName) {
		List<MaterialOrderListDTO> materialOrderListDTOs =  historyRepository.findMaterialHistoryByMaterialName(materialName);
		
	    List<MaterialOrderResponseDto> materialOrderResponseDtos = materialOrderListDTOs.stream()
	            .map(orderListDto -> MaterialOrderResponseDto.toOrderDTO(orderListDto, materialOrderListDTOs))
	            .collect(Collectors.toList());
	    
		return materialOrderResponseDtos;
	}
	//수급내역 발주일로 검색
	public List<MaterialOrderResponseDto> findMaterialHistoryByOrderDate(String orderDate) {
		List<MaterialOrderListDTO> materialOrderListDTOs = historyRepository.findMaterialHistoryByOrderDate(orderDate);
		
	    List<MaterialOrderResponseDto> materialOrderResponseDtos = materialOrderListDTOs.stream()
	            .map(orderListDto -> MaterialOrderResponseDto.toOrderDTO(orderListDto, materialOrderListDTOs))
	            .collect(Collectors.toList());
	    
		return materialOrderResponseDtos;
	}
	//수급내역 입고일로 검색
	public List<MaterialOrderResponseDto> findMaterialHistoryByInboundDate(String inboundDate) {
		List<MaterialOrderListDTO> materialOrderListDTOs =  historyRepository.findMaterialHistoryByInboundDate(inboundDate);
		
	    List<MaterialOrderResponseDto> materialOrderResponseDtos = materialOrderListDTOs.stream()
	            .map(orderListDto -> MaterialOrderResponseDto.toOrderDTO(orderListDto, materialOrderListDTOs))
	            .collect(Collectors.toList());
		return materialOrderResponseDtos;
	}
	
	// 입고내역 등록화면 조회
	public MaterialOrderEditFormDTO getMaterialhistoryInfo(Long materialHistoryId) {
		Optional<MaterialHistory> materialHistory = historyRepository.findById(materialHistoryId);
		
		//MaterialHistory를 MaterialOrderResponseDTO로 변환
		MaterialHistory material = materialHistory.get();
			
		return MaterialOrderEditFormDTO.builder()
				.materialHistoryId(material.getMaterialHistoryId())
				.orderDate(material.getOrderDate())
				.recievingDate(material.getRecievingDate())
				.materialId(material.getMaterial().getMaterialId())
				.standard(material.getStandard())
				.supplier(material.getSupplier())
				.inboundAmount(material.getInboundAmount())
				.orderAmount(material.getOrderAmount())
				.unitPrice(material.getUnitPrice())
				.note(material.getNote())
				.build();
		
	}
	
	//입고내역 등록
	@Transactional
	public void updateMaterialHistory(MaterialOrderEditFormDTO editDTO) {
		
		MaterialHistory materialHistory = historyRepository.findById(editDTO.getMaterialHistoryId())
		        .orElseThrow();
		
		//materialInventory 계산&저장 로직 필요
		Optional<MaterialStock> materialStock =  stockRepository.findById(editDTO.getMaterialId());
		
		Long materialInventory = 0L;
		
		// material_stock 테이블 total_stock에 입고량 반영 
		if(materialStock.isPresent()) {
			materialInventory = materialStock.get().getTotalStock() + editDTO.getInboundAmount();
			materialStock.get().updateMaterialStock(materialInventory);
			stockRepository.save(materialStock.get());
		}
		
		materialHistory.updateMaterialHistory(editDTO.getInboundAmount(), materialInventory, editDTO.getRecievingDate(), editDTO.getNote());

        // 수정된 엔티티 저장
        historyRepository.save(materialHistory);
	        
	}
	
}
