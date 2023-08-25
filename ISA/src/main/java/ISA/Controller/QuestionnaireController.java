package ISA.Controller;

import ISA.Model.DTO.QuestionDTO;
import ISA.Model.QuestionText;
import ISA.Service.QuestionnaireService;
import jakarta.persistence.PostRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questionnaire")
public class QuestionnaireController {

    @Autowired
    public QuestionnaireService _questionnaireService;

    @PostMapping("/submitQuestionnaire")
    public ResponseEntity submitQuestionnaireController(@RequestBody QuestionDTO requestBody) {
        List<Boolean> answers = requestBody.getAnswers();
        String email = requestBody.getEmail();
       try {
           _questionnaireService.submitQuestionnaire(email, answers);
           return ResponseEntity.ok("Questionnaire successfully submited");
       }
       catch(Error e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getText")
    public ResponseEntity getText(){
        return ResponseEntity.ok(_questionnaireService.getAllQuestions());
    }


}
