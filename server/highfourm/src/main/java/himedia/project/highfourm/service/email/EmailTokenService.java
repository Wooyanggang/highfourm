package himedia.project.highfourm.service.email;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.user.UserDTO;
import himedia.project.highfourm.entity.EmailToken;
import himedia.project.highfourm.entity.User;
import himedia.project.highfourm.repository.EmailTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailTokenService {
	
	private final EmailSenderService emailSenderService;
	private final EmailTokenRepository emailTokenRepository;
	
	public String createEmail(UserDTO newUser) {
		User userEntity = newUser.toEntity(newUser.getCompany());

		EmailToken emailToken = EmailToken.createEmailToken(userEntity);
		emailTokenRepository.save(emailToken);
		
		String body = "<div>"
				+ "<h1> 하이포엠 사용자 회원가입 인증 이메일 </h1>"
				+ "<br>"
				+ "<p>아래 링크를 클릭하면 하이포엠 회원가입 화면으로 이동됩니다.</p>"
				+ "<a href='http://localhost:8080/confirm-email?token=\'"+emailToken.getId()
				+"'&userNo=\'"+emailToken.getUser().getUserNo()+"'>인증 링크</a>'"
				+"</div>";
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userEntity.getEmail());
		mailMessage.setSubject("하이포엠 회원가입 사용자 인증 이메일입니다.");
		mailMessage.setText(body);
		emailSenderService.sendEmail(mailMessage);
		
		return emailToken.getId();
	}
	
	// 유효한 토큰 가져오기
	public EmailToken findByUserNoAndExpirationDateAfterAndExpired(String emailTokenId) throws Exception {
		Optional<EmailToken> emailToken = emailTokenRepository
				.findByIdAndExpirationDateAfterAndExpired(emailTokenId, LocalDateTime.now(), false);
		
		return emailToken.orElseThrow(() -> new BadRequestException("토큰을 찾을 수 없습니다."));
				
	}
}
