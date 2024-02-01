package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import himedia.project.highfourm.dto.material.MaterialOrderListDTO;
import himedia.project.highfourm.entity.MaterialHistory;

@Repository
public interface MaterialHistoryRepository extends JpaRepository<MaterialHistory, Long> {
    @Query("SELECT new himedia.project.highfourm.dto.material.MaterialOrderListDTO("
            + "mh.materialHistoryId, ma.materialId, mh.orderDate, mh.recievingDate, mh.standard,"
            + "mh.supplier, mh.materialInventory, mh.inboundAmount, mh.orderAmount, mh.unitPrice, mh.note, ma.materialName, ma.unit) "
            + "FROM MaterialHistory mh "
            + "LEFT JOIN mh.material ma " // 수정된 부분
            + "ORDER BY mh.orderDate")
    List<MaterialOrderListDTO> findAllWithMaterialFields();
}
