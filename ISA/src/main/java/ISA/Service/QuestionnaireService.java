package ISA.Service;

import ISA.Model.QuestionText;
import ISA.Repository.QuestionTextRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface QuestionnaireService {


           public void submitQuestionnaire(String email, List<Boolean> answers);
           public List<QuestionText> getAllQuestions();
}
