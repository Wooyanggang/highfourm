package himedia.project.highfourm.controller;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.material.MaterialListResponseDTO;
import himedia.project.highfourm.dto.material.MaterialOrderListDTO;
import himedia.project.highfourm.dto.material.MaterialOrderRequestDTO;
import himedia.project.highfourm.dto.material.MaterialOrderResponseDto;
import himedia.project.highfourm.dto.material.MaterialRequestDTO;
import himedia.project.highfourm.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class MaterialController {
	
	private final MaterialService materialService;
	/**
	 * 원자재 등록
	 */
	@PostMapping("/materials/stock/new")
	public String addMaterial(@RequestBody MaterialRequestDTO material) {
		log.info("material.getLeadTime(): {}",material.getLeadTime());
		log.info("material.getMaterialName(): {}",material.getMaterialName());
		log.info("MaxStock: {}",material.getMaxStock());
		log.info("SafetyStock : {}",material.getSafetyStock());
		log.info("Unit: {}",material.getUnit());
		materialService.saveMaterial(material);
		
		return "redirect:http://localhost:3000/materials/stock";
	}

	/**
	 * 원자재 리스트 조회
	 */
	@GetMapping("/materials/stock")
	public List<MaterialListResponseDTO> getMaterialList() {
		List<MaterialListResponseDTO> MaterialList = materialService.MaterialList();
//		 MaterialList.stream()
//				.filter(material -> material.getMaterialName().contains("자재"))
//				.forEach(material -> System.out.println("Material: " + material.getManagementName()));
		
		return MaterialList;
	}
	/**
	 * 수급내역 리스트 조회
	 */
//	@GetMapping("/materials/order-history")
//	public String getdMaterialHistoryList() {
//		
//		return "redirect:http://localhost:3000/materials/order-history";
//	}
	/**
	 * 
	 */
	@GetMapping("/materials/order-history")
	public List<MaterialOrderResponseDto> getdMaterialHistoryListTest() {
		 List<MaterialOrderResponseDto> m =materialService.getMaterialOrderList();
		return m;
	}
	
	/**
	 * 수급내역 등록
	 */
	@PostMapping("/materials/order-history/new")
	public String addMaterialHistory(@RequestBody MaterialOrderRequestDTO orderRequestDTO) {
		
		log.info("자재코드 >>> {}", orderRequestDTO.getMaterialId());
		materialService.saveMaterialhistory(orderRequestDTO);
		return "redirect:http://localhost:3000/materials/order-history";
	}
	
	/**
	 * 수급내역 수정
	 */
	@GetMapping("/materials/order-history/{orderHistoryId}")
	public String getdMaterialHistory() {
		
		return "redirect:http://localhost:3000/materials/order-history";
	}
	
	
	
	
}
