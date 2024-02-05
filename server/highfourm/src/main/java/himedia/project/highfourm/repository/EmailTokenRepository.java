package himedia.project.highfourm.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.project.highfourm.entity.EmailToken;

public interface EmailTokenRepository extends JpaRepository<EmailToken, String> {
	Optional<EmailToken> findByIdAndExpirationDateAfterAndExpired(String emailTokenId, LocalDateTime now, boolean expired);
}
