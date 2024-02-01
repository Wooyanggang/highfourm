package himedia.project.highfourm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/mrp")
	public ResponseEntity<Map<String, Object>> mrp() {
		Map<String, Object> responseMap = new HashMap<>();

		List<MrpProductionPlanDTO> productionPlanList = service.findByProductionPlans();

		responseMap.put("plan", productionPlanList);
		return ResponseEntity.ok(responseMap);
	}

	@GetMapping("/mrp/{productionPlanId}")
	public ResponseEntity<Map<String, Object>> mrpDetail(@PathVariable(name = "productionPlanId") String productionPlanId) {

		Map<String, Object> responseMap = new HashMap<>();

		List<MrpProductionPlanDTO> productionPlanList = service.findByProductionPlans();
		responseMap.put("plan", productionPlanList);

		List<MrpRequiredMaterialDTO> requiredMaterialList = service.findByMaterials(productionPlanId);
		responseMap.put("requiredMaterial", requiredMaterialList);

		return ResponseEntity.ok(responseMap);
	}

	@GetMapping("/mrp/search")
	public ResponseEntity<Map<String, Object>> mrpSearch(@RequestParam(value = "searchType") String searchType,
			@RequestParam(value = "search") String search) {
		
		Map<String, Object> responseMap = new HashMap<>();

		List<MrpProductionPlanDTO> result = null;

		if (searchType.equals("생산계획 코드")) {
			result = service.findByProductionPlanID(search);
		} else if (searchType.equals("품번")) {
			result = service.findByProductId(search);
		} else if (searchType.equals("품명")) {
			result = service.findByProductName(search);
		} else if (searchType.equals("납기일")) {
			result = service.findByDueDate(search);
		}
		if (result.isEmpty()) {
			result = new ArrayList<MrpProductionPlanDTO>();
		}
		
		responseMap.put("plan", result);

		return ResponseEntity.ok(responseMap);
	}

}
