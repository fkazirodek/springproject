package pl.simplebuying.domain.verification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.simplebuying.domain.user.User;

@Repository
interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByUser(User user);
	VerificationToken findByToken(String token);

}
