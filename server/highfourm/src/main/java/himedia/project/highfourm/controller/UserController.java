package himedia.project.highfourm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	
	@PostMapping("/users/new")
	public String userNew() {
//	public Map<String, Object> userNew(@RequestBody HashMap<String, Object> map) {
//		System.out.println(map);
//		oMap<String, Object> result = map;
		log.info("로그 테스트");
		return "POST 성공";
//		return result;
	}
	
	@GetMapping("/users")
	public String user() {
		log.info("로그 테스트 get");
		return "GET 성공!!!";
	}
	
}
