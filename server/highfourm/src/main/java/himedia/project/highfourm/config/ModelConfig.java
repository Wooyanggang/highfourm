package himedia.project.highfourm.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {

	@Bean
	protected ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
