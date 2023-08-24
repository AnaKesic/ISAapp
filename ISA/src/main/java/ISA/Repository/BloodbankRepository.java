package ISA.Repository;

import ISA.Model.BloodBank;
import ISA.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BloodbankRepository extends JpaRepository<BloodBank, Long> {

     Collection<User> findEmployeesById(Long Id);



}
