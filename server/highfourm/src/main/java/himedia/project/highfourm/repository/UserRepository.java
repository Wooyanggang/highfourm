package himedia.project.highfourm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
