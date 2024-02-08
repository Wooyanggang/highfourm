package himedia.project.highfourm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import himedia.project.highfourm.dto.orders.OrdersAndDetailsDTO;
import himedia.project.highfourm.service.OrderService;
import himedia.project.highfourm.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	private final ProductService productService;
	
	@GetMapping("/orders")
	public Map<String, Object> orderList() {
		return orderService.findAllOrders();
	}
	
	@GetMapping("/orders/new")
	public List<String> ordersNew(){
		List<String> productNameList = productService.findAllProductName();
		return productNameList;
	}
	
	@PostMapping("/orders/new")
	public void saveOrders(@RequestBody OrdersAndDetailsDTO ordersAndDetailsDTO) {
		orderService.saveOrder(ordersAndDetailsDTO);
	}
}
