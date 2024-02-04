package himedia.project.highfourm.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.project.highfourm.entity.OrderDetail;
import himedia.project.highfourm.entity.pk.OrderDetailPK;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK>{
	@Query("SELECT o FROM OrderDetail o INNER JOIN o.product p")
	List<OrderDetail> findAllWithProductName();
}