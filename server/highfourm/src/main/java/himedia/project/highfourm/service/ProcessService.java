package himedia.project.highfourm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.entity.Process;
import himedia.project.highfourm.repository.ProcessRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcessService {
	private final ProcessRepository processRepository;
	
	public List<Process> findByProductProductId(String productId){
		return processRepository.findByProductProductId(productId);
	}
}
