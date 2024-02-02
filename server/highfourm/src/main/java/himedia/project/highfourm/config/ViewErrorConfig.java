package himedia.project.highfourm.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ViewErrorConfig {

//	public ErrorPageRegistrar errorPageResigster() {
//		return new ErrorPageRegistrar() {
//			
//			@Override
//			public void registerErrorPages(ErrorPageRegistry registry) {
//				ErrorPage errorPage = new ErrorPage(HttpStatus.NOT_FOUND, "/");
//				registry.addErrorPages(errorPage);
//			}
//		};
//	}
}
