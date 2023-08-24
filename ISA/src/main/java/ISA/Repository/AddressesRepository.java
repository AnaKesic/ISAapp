package ISA.Repository;

import ISA.Model.Address;
import ISA.Model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressesRepository extends JpaRepository<Address, Long> {
}
