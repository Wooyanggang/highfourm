package himedia.project.highfourm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.project.highfourm.dto.user.UserDTO;
import himedia.project.highfourm.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "select * from users where role not like 'ADMIN'", nativeQuery = true)
	List<User> findAll();
	
	@Query(value = "select * from users where emp_no like %?% and role not like 'ADMIN'", nativeQuery = true)
	List<User> findByAllEmpNo(Long empNo);

	@Query(value = "select * from users where user_name like %?% and role not like 'ADMIN'", nativeQuery = true)
	List<User> findByAllUserName(String name);

	@Query(value = "select * from users where email like %?% and role not like 'ADMIN'", nativeQuery = true)
	List<User> findByAllEmail(String email);
	
	User findByEmpNo(Long empNo);
	
	@Query(value = "select * from users where email like ?", nativeQuery = true)
	User findByUserEmail(String email);
	
	/**
	 * @author 신지은
	 * 사용자 아이디로 사용자 존재 여부 확인
	 */
	Boolean existsByUserId(String userId);
	
	/**
	 * @author 신지은
	 * 사용자 아이디로 사용자 조회
	 */
	User findByUserId(String userId);
	
}
