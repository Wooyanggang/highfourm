package himedia.project.highfourm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.bom.BomRequiredMaterialDTO;
import himedia.project.highfourm.repository.RequiredMaterialRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequiredMaterial {
	private final RequiredMaterialRepository requiredMaterialRepository;
	
	public List<BomRequiredMaterialDTO> findByMaterialId(String materialId){
		return requiredMaterialRepository.findByMaterialId(materialId);
	}

}
