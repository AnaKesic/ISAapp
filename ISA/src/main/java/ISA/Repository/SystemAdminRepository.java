package ISA.Repository;

import ISA.Model.Staff;
import ISA.Model.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Long> {
}
