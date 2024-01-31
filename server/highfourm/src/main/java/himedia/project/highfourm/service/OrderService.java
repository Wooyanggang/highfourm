package himedia.project.highfourm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.orders.OrderDetailDTO;
import himedia.project.highfourm.dto.orders.OrderDetailResponseDTO;
import himedia.project.highfourm.dto.orders.OrdersDTO;
import himedia.project.highfourm.entity.OrderDetail;
import himedia.project.highfourm.entity.Orders;
import himedia.project.highfourm.repository.OrderDetailRepository;
import himedia.project.highfourm.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
	
	private final OrdersRepository ordersRepository;
	private final OrderDetailRepository orderDetailRepository;
	
    public Map<String, Object> findAllOrders() {
        List<Orders> orders = ordersRepository.findAll(Sort.by(Sort.Direction.DESC, "orderId"));
        List<OrderDetail> orderDetails = orderDetailRepository.findAllWithProductName();
        
        
        List<OrdersDTO> ordersDTO = orders.stream()
        		.map(OrdersDTO::fromEntity)
        		.collect(Collectors.toList());
        
        List<OrderDetailResponseDTO> orderDetailResponseDTOs = orderDetails.stream()
        		.map(OrderDetailResponseDTO::fromEntity)
        		.collect(Collectors.toList());
        
        System.out.println(orderDetails.get(0).getProduct().getProductName());
        
        Map<String,Object> response = new HashMap<>();
        response.put("orders", ordersDTO);
        response.put("ordersDetail", orderDetailResponseDTOs);
        
        return response;
    }
    
   public void saveOrder(OrdersDTO ordersDTO) {
   }
}
