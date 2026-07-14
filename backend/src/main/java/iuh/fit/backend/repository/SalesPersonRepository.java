package iuh.fit.backend.repository;

import iuh.fit.backend.entity.SalesPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalesPersonRepository
        extends JpaRepository<SalesPerson, UUID> {
    Optional<SalesPerson> findByEmail(String email);
}