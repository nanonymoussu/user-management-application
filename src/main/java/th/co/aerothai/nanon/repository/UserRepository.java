package th.co.aerothai.nanon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import th.co.aerothai.nanon.model.User;

// Extends JpaRepository to provide standard CRUD operations.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Finds users where name OR email contains the keyword (case insensitive).
    Page<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email, Pageable pageable);
}
