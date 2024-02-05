//package himedia.project.highfourm.service.email;
//
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import himedia.project.highfourm.entity.EmailToken;
//import himedia.project.highfourm.entity.User;
//import himedia.project.highfourm.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class EmailService {
//
//	private final UserRepository repository;
//	private final EmailTokenService emailTokenService;
//	
//	public void confirmEmail(String token) throws Exception {
//		EmailToken findEmailToken =
//		emailTokenService.findByUserNoAndExpirationDateAfterAndExpired(token);
//		
//		Optional<User> findUser = repository.findById(findEmailToken.getUserNo());
//		findEmailToken.setTokenToUsed();
//		
//		if(findUser.isPresent()) {
//			User user = findUser.get();
//			
//		}
//	}
//}
