package himedia.project.highfourm.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	@PostMapping("/users/new")
	public String userNew(@RequestBody HashMap<String, Object> map) {
		System.out.println(map);
		return "POST 성공";
	}
	
	@GetMapping("/users")
	public String user() {
		return "GET 성공!!!";
	}
	
}
