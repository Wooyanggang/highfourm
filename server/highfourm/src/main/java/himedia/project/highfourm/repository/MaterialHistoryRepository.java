package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.project.highfourm.dto.material.MaterialOrderListDTO;
import himedia.project.highfourm.entity.MaterialHistory;

public interface MaterialHistoryRepository extends JpaRepository<MaterialHistory, Long> {
    @Query("SELECT new himedia.project.highfourm.dto.material.MaterialOrderListDTO("
            + "mh.materialHistoryId, ma.materialId, mh.orderDate, mh.recievingDate, mh.standard,"
            + "mh.supplier, ms.totalStock, mh.inboundAmount, mh.orderAmount, mh.unitPrice, mh.note, ma.materialName, ma.unit) "
            + "FROM MaterialHistory mh "
            + "LEFT JOIN mh.material ma " // fetch에러 나서 수정된 부분
            + "LEFT JOIN ma.materialStock ms "
            + "ORDER BY mh.materialHistoryId DESC")
    List<MaterialOrderListDTO> findAllWithMaterialFields();
    
    
}
