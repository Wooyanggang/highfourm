package himedia.project.highfourm.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.repository.ProductRepository;
import himedia.project.highfourm.dto.BomRequestDTO;
import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.repository.ProcessRepository;
import himedia.project.highfourm.repository.RequiredMaterialRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BomService {
	private final ProductRepository productRepository;
	private final ProcessRepository processRepository;
	private final RequiredMaterialRepository requiredMaterialRepository;
	
	public void saveBom(BomRequestDTO bomRequestDTO) {
		productRepository.save(bomRequestDTO.toProductEntity());
		
		Optional<Product> product = productRepository.findById(bomRequestDTO.getProductId());
	}
}
