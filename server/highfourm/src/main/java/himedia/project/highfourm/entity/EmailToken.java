//package himedia.project.highfourm.entity;
//
//import java.time.LocalDateTime;
//
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.UuidGenerator;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "email_token")
//public class EmailToken {
//
//	private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 7L;
//	
//	@Id
//	@GeneratedValue(generator = "uuid2")
//	@GenericGenerator(name = "uuid2", type = org.hibernate.id.uuid.UuidGenerator.class)
//	@Column(name = "token_id", length = 36)
//	private String id;
//	
//	@Column(name = "user_no")
//	private Long userNo;
//
//	@Column(name = "expiretion_date")
//	private LocalDateTime expirationDate;
//	
//	@Column(name = "expired")
//	private boolean expired;
//	
//	// 이메일 인증 토큰 생성
//	public static EmailToken createEmailToken(Long userNo) {
//		EmailToken emailToken = new EmailToken();
//		emailToken.expirationDate = LocalDateTime.now().plusDays(EMAIL_TOKEN_EXPIRATION_TIME_VALUE);
//		emailToken.expired = false;
//		emailToken.userNo = userNo;
//		
//		return emailToken;
//	}
//	
//	// 토큰 사용으로 인한 만료
//	public void setTokenToUsed() {
//		this.expired = true;
//	}
//	
//}
