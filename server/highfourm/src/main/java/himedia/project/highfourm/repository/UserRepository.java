package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import himedia.project.highfourm.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
