package himedia.project.highfourm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import himedia.project.highfourm.dto.FileDTO;
import himedia.project.highfourm.dto.orders.OrderFormDTO;
import himedia.project.highfourm.dto.orders.OrdersAndDetailsDTO;
import himedia.project.highfourm.dto.orders.OrdersDTO;
import himedia.project.highfourm.entity.File;
import himedia.project.highfourm.service.FileService;
import himedia.project.highfourm.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileController {

	private final FileService fileService;
	private final OrderService orderService;
	@Value("${file.upload-dir}")
	private String uploadDir;

	@PostMapping("/api/orders/new/upload")
	public void uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			String originalFileName = file.getOriginalFilename();
			String changedFileName = UUID.randomUUID().toString();
			String fileExtension = "";

			if (originalFileName.contains(".")) {
				fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			}

			changedFileName += fileExtension;

			String filePath = uploadDir + java.io.File.separator + changedFileName;
			java.io.File dest = new java.io.File(filePath);
			file.transferTo(dest);

			FileDTO fileInfo = new FileDTO();
			fileInfo.setOriginalName(originalFileName);
			fileInfo.setChangedName(changedFileName);
			fileInfo.setFileType(file.getContentType());
			fileInfo.setFileSize(Long.toString(file.getSize()));
			fileInfo.setFilePath(filePath);

			String pdfPath = uploadDir + changedFileName;

			Map<String, Object> orderInfo = fileService.extractTableFromPdf(pdfPath);
			List<List<String>> orderData = (List<List<String>>) orderInfo.get("order");

			List<String> orderFields = orderData.get(0);
			OrdersDTO ordersDTO = new OrdersDTO(orderFields.get(0), orderFields.get(1), orderFields.get(2),
					orderFields.get(3), false);

			@SuppressWarnings("unchecked")
			List<List<Object>> test = (List<List<Object>>) orderInfo.get("detail");
			List<Object> test2 = test.get(0);

			@SuppressWarnings("unchecked")
			List<List<List<Object>>> details = (List<List<List<Object>>>) orderInfo.get("detail");
			List<OrderFormDTO> orderFormDTOs = new ArrayList<>();
			for (List<List<Object>> detailList : details) {
				for (List<Object> detail : detailList) {
					String productName = detail.get(0).toString();
					Long productionAmount = parseLongWithDefault(detail.get(1).toString(), 0L);
					Long unitPrice = parseLongWithDefault(detail.get(2).toString(), 0L);
					orderFormDTOs.add(new OrderFormDTO(productName, productionAmount, unitPrice));
				}
			}

			OrdersAndDetailsDTO ordersAndDetailsDTO = new OrdersAndDetailsDTO(ordersDTO, orderFormDTOs);
			String orderId = orderService.saveOrder(ordersAndDetailsDTO);
			fileInfo.setOrderId(orderId);
			fileService.save(fileInfo);

		} catch (IOException e) {
		}
	}

	private Long parseLongWithDefault(String value, Long defaultValue) {
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
}