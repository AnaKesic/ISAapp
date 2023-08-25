package ISA.Service;

import ISA.Model.DTO.AnswerComplaintDTO;
import ISA.Model.DTO.ComplaintDTO;
import ISA.Model.DTO.ShowComplaintDTO;

import java.util.List;

public interface ComplaintService {
    public void AddNew(ComplaintDTO dto);
    public void Answer(AnswerComplaintDTO dto);
    public List<ShowComplaintDTO> getAllUnanswered();
}
