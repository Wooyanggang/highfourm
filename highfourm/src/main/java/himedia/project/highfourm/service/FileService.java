package himedia.project.highfourm.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import himedia.project.highfourm.dto.FileDTO;
import himedia.project.highfourm.entity.File;
import himedia.project.highfourm.entity.Orders;
import himedia.project.highfourm.repository.FileRepository;
import himedia.project.highfourm.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileService {
	private final FileRepository fileRepository;
	private final OrdersRepository ordersRepository;
	
	@Transactional
	public void save(FileDTO fileInfo) {
		File file = fileInfo.toEntity();
		Orders orders = ordersRepository.findById(fileInfo.getOrderId()).orElseGet(null);
		file.assignOrders(orders);
		fileRepository.save(file);
	}
	
	public Map<String, Object> extractTableFromPdf(String pdfPath) {
		 String scriptPath = "/home/ec2-user/app/scripts/textConversion.py";
	        ProcessBuilder processBuilder = new ProcessBuilder("python3", scriptPath, pdfPath);
		
        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            System.out.println(System.getProperty("user.dir"));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                String jsonOutput = output.toString();
                ObjectMapper objectMapper = new ObjectMapper();
                
                Map<String, Object> pdfData = objectMapper.readValue(jsonOutput, new TypeReference<Map<String, Object>>() {});
                return pdfData;
            } 
        } catch (IOException | InterruptedException e) {
        }

        return null;
    }
	
}
