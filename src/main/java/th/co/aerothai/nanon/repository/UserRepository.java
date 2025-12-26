package th.co.aerothai.nanon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import th.co.aerothai.nanon.model.User;

// Extends JpaRepository to provide standard CRUD operations.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods (e.g., findByEmail) if needed.
}
