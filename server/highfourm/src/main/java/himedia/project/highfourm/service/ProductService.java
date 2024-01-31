package himedia.project.highfourm.service;

import java.util.List;

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
}
