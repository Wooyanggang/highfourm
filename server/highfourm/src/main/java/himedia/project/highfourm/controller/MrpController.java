package himedia.project.highfourm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.mrp.MrpProductionPlanDTO;
import himedia.project.highfourm.dto.mrp.MrpRequiredMaterialDTO;
import himedia.project.highfourm.service.MrpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MrpController {
	
	private final MrpService service;
	
//	@GetMapping(value = "/bom")
//	public ResponseEntity<Map<String, List<ProductDTO>>> bom() {
//		Map<String, List<ProductDTO>> responseMap = new HashMap<>();
//		
//		// product findAll
//		List<Product> allEntityList = productService.findAllProduct();
//		
//		// product Entity to DTO
//		List<ProductDTO> productDTOList = allEntityList.stream()
//        .map(product -> product.toProductDTO())
//        .collect(Collectors.toList());
//		
//		// 보내줄 객체에 담기
//	    responseMap.put("product", productDTOList);
//	    
//		return ResponseEntity.ok(responseMap);
//	}

	@GetMapping("/mrp")
	public List<MrpProductionPlanDTO> selectMrpProductionPlanList() {
		return service.findByProductionPlans();
	}

	@GetMapping("/mrp/{productionPlanId}")
	public List<MrpRequiredMaterialDTO> selectMrpRequiredMaterialList(@RequestParam String productionPlanId) {
		log.info(productionPlanId);
		return service.findByMaterials(productionPlanId);
	}
	
	@GetMapping("/mrp/search")
	public List<MrpProductionPlanDTO> mrpSearch(@RequestParam String searchType, @RequestParam String search) {
		List<MrpProductionPlanDTO> result = new ArrayList<MrpProductionPlanDTO>();
		
		log.info("searchType : {}", searchType);
		log.info("search : {}", search);
		
		
		if(searchType.equals("생산계획 코드")) {
			result = service.findByProductionPlanID(search);
		} else if(searchType.equals("품번")) {
			result = service.findByProductId(search);
		} else if(searchType.equals("품명")) {
			result = service.findByProductName(search);
		} else if(searchType.equals("납기일")) {
			result = service.findByDueDate(search);
		}
		
		return result;
	}
	
}
