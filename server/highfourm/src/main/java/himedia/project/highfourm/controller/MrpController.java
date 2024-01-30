package himedia.project.highfourm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.MrpDTO;
import himedia.project.highfourm.service.MrpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MrpController {
	
	private final MrpService service;

	@GetMapping("/mrp")
	public List<MrpDTO> selectMrpProductionPlanList() {
		List<MrpDTO> totalList = new ArrayList<>();
		totalList = service.findByProductionPlans();
		
		return totalList;
	}

	@GetMapping("/mrp/{productionPlanId}")
	public List<MrpDTO> selectMrpMaterialList(@RequestParam String productionPlanId) {
		return service.findByMaterials(productionPlanId);
	}
	
//	@GetMapping("/mrp/search?searchType={searchType}&search={search}")
	@GetMapping("/mrp/search")
	public List<MrpDTO> mrpSearch(@RequestParam String searchType, @RequestParam String search) {
		List<MrpDTO> result = new ArrayList<MrpDTO>();
		
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
