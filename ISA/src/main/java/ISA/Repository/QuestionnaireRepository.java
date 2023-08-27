package ISA.Repository;

import ISA.Model.Donor;
import ISA.Model.Questionnaire;
import ISA.Model.Staff;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "0") })
    Questionnaire findByDonorId(Long id);
}
