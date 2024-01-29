package himedia.project.highfourm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import himedia.project.highfourm.vo.MaterialRequestDTO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MaterialController {

	@PostMapping("/materials/stock/new")
	public String addMaterial(@RequestBody MaterialRequestDTO material) {
		
		System.out.println(material.getLeadTime());
		
		return "redirect:http://localhost:3000/materials/stock";
	}
	
}
