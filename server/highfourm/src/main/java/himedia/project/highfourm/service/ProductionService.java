package himedia.project.highfourm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.repository.ProductionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductionService {
	private final ProductionRepository productionRepository;
	
	public List<Product> findAllProduct(){
		return productionRepository.findAll();
	}
}
