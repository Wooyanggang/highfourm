package himedia.project.highfourm.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.project.highfourm.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, String>{
	@Query(value = "SELECT count(o) FROM Orders o where o.orderDate = ?1")
	int countOrdersWithOrderDate(LocalDate orderDate);
}