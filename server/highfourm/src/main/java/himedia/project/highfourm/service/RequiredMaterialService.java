package himedia.project.highfourm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.bom.BomRequiredMaterialDTO;
import himedia.project.highfourm.repository.BomRequiredMaterialRepository;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequiredMaterialService {
	private final BomRequiredMaterialRepository bomRequiredMaterialRepository;
	
//	public List<BomRequiredMaterialDTO> findByProductId(String productId){
//		return bomRequiredMaterialRepository.findBomRequiredMaterial(productId);
//	}
	public List<BomRequiredMaterialDTO> findBomRequiredMaterial(String productId) {
        List<Tuple> result = bomRequiredMaterialRepository.findBomRequiredMaterial(productId);
        return mapTupleToBomRequiredMaterialDTO(result);
    }

    private List<BomRequiredMaterialDTO> mapTupleToBomRequiredMaterialDTO(List<Tuple> tuples) {
        List<BomRequiredMaterialDTO> dtos = new ArrayList<>();
        for (Tuple tuple : tuples) {
            BomRequiredMaterialDTO dto = new BomRequiredMaterialDTO();
            dto.setMaterialId(tuple.get("materialId", String.class));
            dto.setInputProcess(tuple.get("inputProcess", String.class));
            dto.setMaterialName(tuple.get("materialName", String.class));
            dto.setInputAmount(tuple.get("inputAmount", Long.class));
            dtos.add(dto);
        }
        return dtos;
    }

}
