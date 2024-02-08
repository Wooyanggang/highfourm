package himedia.project.highfourm.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "email_token")
public class EmailToken {

	private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 7L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", length = 36)
	private String id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "user_no", referencedColumnName = "user_no", nullable = false)
	private User user;

	@Column(name = "expiration_date", nullable = false)
	private LocalDateTime expirationDate;

	@Column(name = "expired", nullable = false)
	private boolean expired;

	// 이메일 인증 토큰 생성
	public static EmailToken createEmailToken(User user) {
		EmailToken emailToken = new EmailToken();
		emailToken.expirationDate = LocalDateTime.now().plusDays(EMAIL_TOKEN_EXPIRATION_TIME_VALUE);
		emailToken.expired = false;
		emailToken.user = user;

		return emailToken;
	}

	// 토큰 사용으로 인한 만료
	public void setTokenToUsed() {
		this.expired = true;
	}

}
