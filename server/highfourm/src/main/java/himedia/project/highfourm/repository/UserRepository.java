package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.dto.user.UserDTO;
import himedia.project.highfourm.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<UserDTO> findByEmpNo(Long empNo);
	List<UserDTO> findByUserName(String name);
	List<UserDTO> findByEmail(String email);
	
//	void delete
}
