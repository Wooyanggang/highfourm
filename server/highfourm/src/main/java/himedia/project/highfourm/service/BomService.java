package himedia.project.highfourm.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.BomRequestDTO;
import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.entity.RequiredMaterial;
import himedia.project.highfourm.entity.Material;
import himedia.project.highfourm.repository.ProcessRepository;
import himedia.project.highfourm.repository.ProductRepository;
import himedia.project.highfourm.repository.RequiredMaterialRepository;
import himedia.project.highfourm.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BomService {
	private final ProductRepository productRepository;
	private final ProcessRepository processRepository;
	private final RequiredMaterialRepository requiredMaterialRepository;
	private final MaterialRepository materialRepository;
	
	public void saveBom(BomRequestDTO bomRequestDTO) {
		productRepository.save(bomRequestDTO.toProductEntity());
		
		Optional<Product> product = productRepository.findById(bomRequestDTO.getProductId());
		if(product.isPresent()) {
			processRepository.save(bomRequestDTO.toProcessEntity(product.get()));
		}
		
		Optional<Material> material = materialRepository.findById(bomRequestDTO.getMaterialId());
		if(material.isPresent()) {
			requiredMaterialRepository.save(bomRequestDTO.toRequiredMaterialEntity(
					product.get(), material.get())
					);
		}
	}
}
