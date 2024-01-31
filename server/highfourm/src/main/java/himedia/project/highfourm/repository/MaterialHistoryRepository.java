package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import himedia.project.highfourm.entity.MaterialHistory;

@Repository
public interface MaterialHistoryRepository extends JpaRepository<MaterialHistory, Long> {

}
