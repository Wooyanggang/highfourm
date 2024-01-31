package himedia.project.highfourm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.ProductDTO;
import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.repository.ProductionRepository;
import himedia.project.highfourm.service.ProductionService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductionController {
	
	private final ModelMapper modelMapper;
	private final ProductionRepository productionRepository;
	private final ProductionService productionService;
	
	@GetMapping(value = "/bom")
	public ResponseEntity<List<ProductDTO>> bom() {
		// findAll
		List<Product> allEntityList = productionService.findAllProduct();
		
		// Entity to DTO
		List<ProductDTO> productDTOList = allEntityList.stream()
        .map(product -> modelMapper.map(product, ProductDTO.class))
        .collect(Collectors.toList());
		
		return ResponseEntity.ok(productDTOList);
	}
}
