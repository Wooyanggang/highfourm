package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.OrderDetail;
import himedia.project.highfourm.entity.pk.OrderDetailPk;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPk>{
}
