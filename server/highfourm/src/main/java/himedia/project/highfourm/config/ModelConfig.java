//package himedia.project.highfourm.config;
//
//import org.modelmapper.ModelMapper;
//import org.modelmapper.PropertyMap;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import himedia.project.highfourm.dto.OrderDetailDTO;
//import himedia.project.highfourm.entity.OrderDetail;
//
//@Configuration
//public class ModelConfig {
//
//    @Bean
//    protected ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//
//        modelMapper.addMappings(new PropertyMap<OrderDetail, OrderDetailDTO>() {
//            @Override
//            protected void configure() {
//                map().setOrderId(source.getOrderDetailPk().getOrderId());
//                map().setProductId(source.getOrderDetailPk().getProductId());
//            }
//        });
//
//        return modelMapper;
//    }
//}