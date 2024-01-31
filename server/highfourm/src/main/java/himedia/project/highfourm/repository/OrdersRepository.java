package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, String>{

}
