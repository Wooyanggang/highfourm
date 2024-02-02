package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.project.highfourm.dto.user.UserDTO;
import himedia.project.highfourm.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByEmpNo(Long empNo);
	List<User> findByUserName(String name);
	List<User> findByEmail(String email);
	
	@Query(value = "select * from users where email like ?", nativeQuery = true)
	User findByUserEmail(String email);
	
}
