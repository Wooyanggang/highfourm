package himedia.project.highfourm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.OrdersDTO;
import himedia.project.highfourm.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService; 
	
	@GetMapping("/orders")
	public Map<String, Object> orderList() {
		return orderService.findAllOrders();
	}
	
}
