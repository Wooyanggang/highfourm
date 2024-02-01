package himedia.project.highfourm.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.orders.OrdersAndDetailsDTO;
import himedia.project.highfourm.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
	
	@PostMapping("/orders/new")
	public void ordersNew(@RequestBody OrdersAndDetailsDTO ordersAndDetailsDTO) {
		log.info("orders{}",ordersAndDetailsDTO.getOrders().getOrderDate());
		log.info("orders{}",ordersAndDetailsDTO.getOrderDetails().get(0).getProductName());
		orderService.saveOrder(ordersAndDetailsDTO);
//		}
	}
	
}
