package himedia.project.highfourm.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.orders.OrderDetailDTO;
import himedia.project.highfourm.dto.orders.OrderDetailFormDTO;
import himedia.project.highfourm.dto.orders.OrdersAndDetailsDTO;
import himedia.project.highfourm.dto.orders.OrdersDTO;
import himedia.project.highfourm.entity.OrderDetail;
import himedia.project.highfourm.entity.Orders;
import himedia.project.highfourm.entity.Product;
import himedia.project.highfourm.repository.OrderDetailRepository;
import himedia.project.highfourm.repository.OrdersRepository;
import himedia.project.highfourm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
	
	private final OrdersRepository ordersRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final ProductRepository productRepository;
	
    public Map<String, Object> findAllOrders() {
        List<Orders> orders = ordersRepository.findAll(Sort.by(Sort.Direction.DESC, "orderId"));
        List<OrderDetail> orderDetails = orderDetailRepository.findAllWithProductName();
        
        
        List<OrdersDTO> ordersDTO = orders.stream()
        		.map(OrdersDTO::fromEntity)
        		.collect(Collectors.toList());
        
        List<OrderDetailFormDTO> orderDetailResponseDTOs = orderDetails.stream()
        		.map(OrderDetailFormDTO::fromEntity)
        		.collect(Collectors.toList());
        
        Map<String,Object> response = new HashMap<>();
        response.put("orders", ordersDTO);
        response.put("ordersDetail", orderDetailResponseDTOs);
        
        return response;
    }
    
   public void saveOrder(OrdersAndDetailsDTO ordersAndDetailsDTO) {
	    LocalDate orderDate = ordersAndDetailsDTO.getOrders().getOrderDate();
	    OrdersDTO ordersDTO = ordersAndDetailsDTO.getOrders();
	    ordersDTO.setOrderId(
	    		orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
	    		+ "-"
	    		+ (ordersRepository.countOrdersWithOrderDate(orderDate) + 1));
	    
	    List<OrderDetailFormDTO> orderDetailsForm = ordersAndDetailsDTO.getOrderDetails();

		List<Product> products = orderDetailsForm.stream()
		    .map(OrderDetailFormDTO::getProductName) 
		    .map(productRepository::findByProductName) 
		    .collect(Collectors.toList()); 

	    List<OrderDetailDTO> orderDetailsDTO = IntStream.range(0, orderDetailsForm.size())
	            .mapToObj(i -> {
	                OrderDetailFormDTO formDTO = orderDetailsForm.get(i);
	                String productId = products.get(i).getProductId();
	                return OrderDetailDTO.fromFormDTO(formDTO, productId);
	            })
	            .collect(Collectors.toList());
	    
	    Orders orders = ordersDTO.toEntity(ordersDTO);
	    List<OrderDetail> orderDetails = orderDetailsDTO.stream()
	            .map(dto -> {
	                Product product = productRepository.findById(dto.getProductId()).orElse(null);
	                return dto.toEntity(dto, orders, product);
	            })
	            .collect(Collectors.toList());

	    ordersRepository.save(orders);
	    orderDetails.stream()
	    	.forEach(e -> orderDetailRepository.save(e));;
   }
}
