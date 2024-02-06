package himedia.project.highfourm.controller;


import java.util.List;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.material.MaterialListResponseDTO;
import himedia.project.highfourm.dto.material.MaterialOrderEditDTO;
import himedia.project.highfourm.dto.material.MaterialOrderEditFormDTO;
import himedia.project.highfourm.dto.material.MaterialOrderRequestDTO;
import himedia.project.highfourm.dto.material.MaterialOrderResponseDto;
import himedia.project.highfourm.dto.material.MaterialRequestDTO;
import himedia.project.highfourm.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class MaterialController {
	
	private final MaterialService materialService;
	
	/**
	 * 원자재 리스트 조회
	 */
	@GetMapping("/materials/stock")
	public ResponseEntity<List<MaterialListResponseDTO>>  getMaterialList() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setCacheControl(CacheControl.noStore());
	    
		List<MaterialListResponseDTO> materialList = materialService.MaterialList();
		
		return new ResponseEntity<>(materialList, headers, HttpStatus.OK);
	}
	
	/**
	 * 원자재 등록
	 */
	@PostMapping("/materials/stock/new")
	public String addMaterial(@RequestBody MaterialRequestDTO material) {
		materialService.saveMaterial(material);
		return "redirect:/materials/stock";
	}

	/**
	 * 수급내역 리스트 조회
	 */
	
	@GetMapping("/materials/order-history")
	public ResponseEntity<List<MaterialOrderResponseDto>> getdMaterialHistoryList() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setCacheControl(CacheControl.noStore());
	    
		List<MaterialOrderResponseDto> mateiralOderList =materialService.getMaterialOrderList();
		
		return new ResponseEntity<>(mateiralOderList, headers, HttpStatus.OK);
	}

	/**
	 * 수급내역 등록
	 */
	@PostMapping("/materials/order-history/new")
	public String addMaterialHistory(@RequestBody MaterialOrderRequestDTO orderRequestDTO) {
		materialService.saveMaterialhistory(orderRequestDTO);
		return "redirect:http://localhost:3000/materials/order-history";
	}
	
	/**
	 * 입고내역 등록 페이지 조회
	 */
	@GetMapping("/materials/order-history/edit/{orderHistoryId}")
	public ResponseEntity<MaterialOrderEditFormDTO> getdMaterialHistory(@PathVariable(name ="orderHistoryId") Long orderHistoryId) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setCacheControl(CacheControl.noStore());
	    MaterialOrderEditFormDTO editFormDTO = materialService.getMaterialhistoryInfo(orderHistoryId);
		return new ResponseEntity<> (editFormDTO, headers, HttpStatus.OK);
	}
	
	/**
	 * 입고내역 등록 
	 */
	@PostMapping("/materials/order-history/edit/{orderHistoryId}")
	public String editdMaterialHistory(@PathVariable(name ="orderHistoryId") Long orderHistoryId, 
										@RequestBody MaterialOrderEditFormDTO editDTO) {
		log.info("RecievingDate >>>>> {}", editDTO.getRecievingDate());
		log.info("Note >>>>> {}", editDTO.getNote());
		System.out.println(">>>>>>>" + editDTO.getInboundAmount());
		
		materialService.updateMaterialHistory(editDTO);
		
		return "redirect:http://localhost:3000/materials/order-history";
	}
	
	
	
	
}
