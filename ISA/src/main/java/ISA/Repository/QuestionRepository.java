package ISA.Repository;

import ISA.Model.Question;
import ISA.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
