package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import himedia.project.highfourm.dto.material.MaterialResponseDTO;
import himedia.project.highfourm.entity.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {
	

//    @Query("SELECT new himedia.project.highfourm.dto.material.MaterialResponseDTO("
//            + "m.material_id, m.material_name, m.unit, sm.management_name, ms.total_stock, ms.safety_stock, ms.max_stock, ms.lead_time) "
//            + "FROM Material m "
//            + "LEFT JOIN m.material_stock ms "
//            + "LEFT JOIN ms.stock_management sm "
//            + "ORDER BY m.material_id")
//    List<MaterialResponseDTO> findAllWithJoin();

}
