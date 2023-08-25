package ISA.Service;

import ISA.Model.Donor;
import ISA.Model.Question;
import ISA.Model.QuestionText;
import ISA.Model.Questionnaire;
import ISA.Repository.QuestionRepository;
import ISA.Repository.QuestionTextRepository;
import ISA.Repository.QuestionnaireRepository;
import ISA.Repository.UserRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{

    @Autowired
    public QuestionTextRepository textRepository;
    @Autowired
    public QuestionRepository questionRepository;
    @Autowired
    public QuestionnaireRepository questionnaireRepository;
    @Autowired
    public UserRepository userRepository;
    @Override
    public void submitQuestionnaire(String email, List<Boolean> answers) {
        Sort sort = Sort.by(Sort.Order.asc("id"));
        Questionnaire qq= new Questionnaire();
        qq.setAddmited(LocalDateTime.now());
        Donor donor=(Donor) userRepository.findByEmail(email);
        qq.setDonor(donor);
        List<QuestionText> text= textRepository.findAll(sort);
        List<Question> questions= new ArrayList<>();
        for ( int i=0; i< answers.size();i++) {
             if(answers.get(i)==null){
                 throw new Error("All answer must be checked!");
             }
             Question q = new Question();
             q.text=text.get(i);
             q.answer=answers.get(i);
             questions.add(q);
        }
        qq.setQuestionList(questions);
        Questionnaire alex=donor.getQuestionnaire();
        if(alex!=null) {
            donor.setQuestionnaire(null);
            userRepository.save(donor);
            questionnaireRepository.delete(alex);

        }

        donor.setQuestionnaire(qq);
        userRepository.save(donor);
        return;


    }

    public List<QuestionText> getAllQuestions(){
        Sort sort = Sort.by(Sort.Order.asc("id"));
        return textRepository.findAll(sort);
    }
}
