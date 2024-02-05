package himedia.project.highfourm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	
	public List<Product> findAllProduct(){
		return productRepository.findAll();
	}
	
	public Optional<Product> findById(String productId) {
		return productRepository.findById(productId);
	}
	
	public List<String> findAllProductName(){
		return productRepository.findAllProductNames();
	}
}