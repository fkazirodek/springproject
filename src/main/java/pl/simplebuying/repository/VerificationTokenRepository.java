package pl.simplebuying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.simplebuying.model.User;
import pl.simplebuying.model.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByUser(User user);
	VerificationToken findByToken(String token);

}
