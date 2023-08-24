package ISA.Controller;

import ISA.Model.AnswerComplaintDTO;
import ISA.Model.Complaint;
import ISA.Model.DTO.ComplaintDTO;
import ISA.Model.DTO.ShowComplaintDTO;
import ISA.Service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {

      @Autowired
      public ComplaintService _complaintService;

    @PostMapping("/addNew")
    public ResponseEntity addNew(@RequestBody ComplaintDTO dto) {
        _complaintService.AddNew(dto);

        return ResponseEntity.ok("successfully complained");
    }

    @GetMapping("/getAllUnanswered")
    public ResponseEntity getAllUnaswered() {
        List<ShowComplaintDTO> response= _complaintService.getAllUnanswered();
        try {
            return ResponseEntity.ok(response);
        }
        catch(Error e){
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(e.getMessage());
        }
    }

    @PostMapping("/answer")
    public ResponseEntity Answer(AnswerComplaintDTO dto){
        _complaintService.Answer(dto);
        return ResponseEntity.ok("answered succ");
    }

}
