package himedia.project.highfourm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping(value = {"", "/error"})
	public String forward() {
		return "forward:/index.html";
	}
	
}
