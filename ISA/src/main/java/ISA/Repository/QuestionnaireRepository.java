package ISA.Repository;

import ISA.Model.Questionnaire;
import ISA.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
}
