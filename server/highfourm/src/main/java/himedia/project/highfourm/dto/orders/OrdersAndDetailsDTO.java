package himedia.project.highfourm.dto.orders;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrdersAndDetailsDTO {
    private OrdersDTO orders;
    private List<OrderDetailFormDTO> orderDetails;
}