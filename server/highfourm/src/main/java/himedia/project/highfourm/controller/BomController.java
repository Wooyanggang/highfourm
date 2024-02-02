package himedia.project.highfourm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import himedia.project.highfourm.dto.BomRequestDTO;
import himedia.project.highfourm.dto.ProcessDTO;
import himedia.project.highfourm.dto.ProductDTO;
import himedia.project.highfourm.dto.bom.BomRequiredMaterialDTO;
import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.entity.Process;
import himedia.project.highfourm.service.BomService;
import himedia.project.highfourm.service.ProcessService;
import himedia.project.highfourm.service.ProductService;
import himedia.project.highfourm.service.RequiredMaterialService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BomController {
	
	private final ProductService productService;
	private final ProcessService processService;
	private final RequiredMaterialService requiredMaterialService;
	private final BomService bomService;
	
	@GetMapping("/bom")
	public ResponseEntity<Map<String, Object>> bom() {
	    Map<String, Object> responseMap = new HashMap<>();

	    // product findAll
	    List<Product> productEntityList = productService.findAllProduct();

	    // product Entity to DTO
	    List<ProductDTO> productDTOList = productEntityList.stream()
	            .map(product -> product.toProductDTO())
	            .collect(Collectors.toList());

	    // 보내줄 객체에 담기
	    responseMap.put("product", productDTOList);

	    return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(responseMap);
	}

	@GetMapping("/bom/detail/{productId}")
	public ResponseEntity<Map<String, Object>> bomDetail(@PathVariable("productId") String productId) {
	    Map<String, Object> responseMap = new HashMap<>();
	    
	    // product findByProductProductId
	    Optional<Product> productEntity = productService.findById(productId);
	    if (productEntity.isPresent()) {
	    	// product Entity to DTO
	        ProductDTO productDTO = productEntity.get().toProductDTO();
	        List<ProductDTO> productDTOList =List.of(productDTO);
	        responseMap.put("product", productDTOList);
	    }

	    // process findByProductProductId
	    List<Process> processEntityList = processService.findByProductProductId(productId);

	    // process Entity to DTO
	    List<ProcessDTO> processDTOList = processEntityList.stream()
	            .map(process -> process.toProcessDTO())
	            .collect(Collectors.toList());
	    responseMap.put("process", processDTOList);
	    
	    // bomRequiredMaterialDTO findByProductProductId
	    List<BomRequiredMaterialDTO> bomRequiredMaterialList = requiredMaterialService.findByProductId(productId);
	    responseMap.put("bomRequiredMaterial", bomRequiredMaterialList);
	    
	    return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(responseMap);
	}
	
	@PostMapping("/bom/new")
	public String addBom(@RequestBody BomRequestDTO bomRequestDTO) {
		bomService.saveBom(bomRequestDTO);
		return "redirect:http://localhost:3000/bom";
	}

}
