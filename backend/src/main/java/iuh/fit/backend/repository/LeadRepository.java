package iuh.fit.backend.repository;

import iuh.fit.backend.entity.Lead;
import iuh.fit.backend.entity.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LeadRepository extends JpaRepository<Lead, UUID> {

    Optional<Lead> findByEmail(String email);

    List<Lead> findByStatus(LeadStatus status);

    List<Lead> findByCustomerNameContainingIgnoreCase(String keyword);

}
