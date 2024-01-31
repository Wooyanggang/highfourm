package himedia.project.highfourm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import himedia.project.highfourm.dto.ProductDTO;
import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.service.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BomController {
	
	private final ProductService productService;
	
	@GetMapping(value = "/bom")
	public ResponseEntity<Map<String, List<ProductDTO>>> bom() {
		Map<String, List<ProductDTO>> responseMap = new HashMap<>();
		
		// product findAll
		List<Product> allEntityList = productService.findAllProduct();
		
		// product Entity to DTO
		List<ProductDTO> productDTOList = allEntityList.stream()
        .map(product -> product.toProductDTO())
        .collect(Collectors.toList());
		
		// 보내줄 객체에 담기
	    responseMap.put("product", productDTOList);
	    
		return ResponseEntity.ok(responseMap);
	}
}
