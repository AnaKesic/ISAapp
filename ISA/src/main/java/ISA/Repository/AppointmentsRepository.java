package ISA.Repository;

import ISA.Model.Address;
import ISA.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {
}
