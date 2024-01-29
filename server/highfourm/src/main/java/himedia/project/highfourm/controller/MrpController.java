package himedia.project.highfourm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MrpController {

	@GetMapping("/mrp")
	public String mrpSelect() {
		return "";
	}

	@GetMapping("/mrp/search")
	public String mrpSearch() {
		return "";
	}
	
}
