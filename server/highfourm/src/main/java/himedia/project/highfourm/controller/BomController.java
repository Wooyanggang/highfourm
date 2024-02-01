package himedia.project.highfourm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import himedia.project.highfourm.dto.ProcessDTO;
import himedia.project.highfourm.dto.ProductDTO;
import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.entity.Process;
import himedia.project.highfourm.service.ProcessService;
import himedia.project.highfourm.service.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BomController {
	
	private final ProductService productService;
	private final ProcessService processService;
	
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

	    return ResponseEntity.ok(responseMap);
	}

	@GetMapping("/bom/detail/{productId}")
	public ResponseEntity<Map<String, Object>> bomDetail(@PathVariable("productId") String productId) {
	    Map<String, Object> responseMap = new HashMap<>();

	    Optional<Product> productEntity = productService.findById(productId);
	    if (productEntity.isPresent()) {
	        ProductDTO productDTO = productEntity.get().toProductDTO();
	        List<ProductDTO> productDTOList =List.of(productDTO);
	        responseMap.put("product", productDTOList);
	    }

	    // process findByProductProductId
	    List<Process> processEntityList = processService.findByProductProductId(productId);

	    // procss Entity to Dto
	    List<ProcessDTO> processDTOList = processEntityList.stream()
	            .map(process -> process.toProcessDTO())
	            .collect(Collectors.toList());

	    // 보내줄 객체에 담기
	    responseMap.put("process", processDTOList);

	    return ResponseEntity.ok(responseMap);
	}

}
