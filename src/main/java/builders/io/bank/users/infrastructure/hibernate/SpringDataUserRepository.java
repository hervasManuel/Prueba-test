package builders.io.bank.users.infrastructure.hibernate;

import builders.io.bank.users.infrastructure.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUserId(String userId);
    Optional<UserEntity> findByUsername(String username);
}
