package himedia.project.highfourm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.OrderDetailDTO;
import himedia.project.highfourm.dto.OrdersDTO;
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
	
	private final ModelMapper modelMapper;
	private final OrdersRepository ordersRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final ProductRepository productRepository;
	
    public Map<String, Object> findAllOrders() {
        List<Orders> orders = ordersRepository.findAll(Sort.by(Sort.Direction.DESC, "orderId"));
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        List<Product> products = productRepository.findAll();
        
        Map<String, String> productIdToNameMap = products.stream()
                .collect(Collectors.toMap(Product::getProductId, Product::getProductName));
        List<OrdersDTO> ordersDTO = orders.stream()
                                                  .map(order -> modelMapper.map(order, OrdersDTO.class))
                                                  .collect(Collectors.toList());
        
        List<OrderDetailDTO> orderDetailDTO = orderDetails.stream()
                .map(orderDetail -> {
                    OrderDetailDTO dto = modelMapper.map(orderDetail, OrderDetailDTO.class);
                    String productName = productIdToNameMap.get(dto.getProductId());
                    if (productName != null) {
                        dto.setProductId(productName);
                    }
                    return dto;
                })
                .collect(Collectors.toList());
        
        Map<String,Object> response = new HashMap<>();
        response.put("orders",ordersDTO);
        response.put("orderDetail", orderDetailDTO);
        
        return response;
    }
    
   public void saveOrder(OrdersDTO ordersDTO) {
//	   System.out.println("서비스 1" + ordersDTO.getDueDate());
//	   Orders orders = modelMapper.map(ordersDTO, Orders.class);
//	   System.out.println("서비스 2" + orders.getManager());
//	   ordersRepository.save(orders);
   }
}
