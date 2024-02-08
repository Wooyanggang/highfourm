package himedia.project.highfourm.service.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

	private final JavaMailSender javaMailSender;
	
	@Async
	public void sendEmail(MimeMessage email) {
		javaMailSender.send(email);
	}
} 
