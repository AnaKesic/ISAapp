package ISA.Repository;

import ISA.Model.Appointment;
import ISA.Model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Long> {
}
