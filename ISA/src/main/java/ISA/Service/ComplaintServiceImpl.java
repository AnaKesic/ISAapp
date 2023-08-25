package ISA.Service;

import ISA.Model.*;
import ISA.Model.DTO.AnswerComplaintDTO;
import ISA.Model.DTO.ComplaintDTO;
import ISA.Model.DTO.ShowComplaintDTO;
import ISA.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintServiceImpl implements ComplaintService{
    @Autowired
    ComplaintRepository _complaintRepository;
    @Autowired
    UserRepository _userRepository;
    @Autowired
    BloodbankRepository _bloodBankRepository;

    public void AddNew(ComplaintDTO dto) {
        Complaint complaint = new Complaint();
        complaint.text = dto.text;
        if (!(dto.bloodBankId==null)) {
            complaint.bloodBank = _bloodBankRepository.findById(dto.bloodBankId).get();
        }
        else if (!dto.staffEmail.isEmpty()) {
            complaint.staff = (Staff) _userRepository.findByEmail(dto.staffEmail);
        }
        else{
            throw new Error("data isnt valid");
        }
        Donor donor =(Donor) _userRepository.findByEmail(dto.donorEmail);
        complaint.donor =donor;
        _complaintRepository.save(complaint);
        donor.getComplaints().add(complaint);
        _userRepository.save(donor);

    }

    public void Answer(AnswerComplaintDTO dto){
         Complaint complaint= _complaintRepository.findById(dto.complaintId).get();
         complaint.answer=dto.answer;
         SystemAdmin admin=(SystemAdmin) _userRepository.findByEmail(dto.adminEmail);
         complaint.admin=admin;
         _complaintRepository.save(complaint);
         admin.getComplaintsAdmin().add(complaint);
         _userRepository.save(admin);

    }

    public List<ShowComplaintDTO> getAllUnanswered(){
        List<Complaint> all= _complaintRepository.findAll();
        List<Complaint> cps=  all.stream()
                .filter(complaint -> complaint.getAnswer()==null)
                .collect(Collectors.toList());
        List<ShowComplaintDTO> response= new ArrayList<>();
        for (Complaint c:cps) {
            ShowComplaintDTO s= new ShowComplaintDTO();
            if(c.bloodBank==null){ s.subject=c.staff.getName()+ " "+c.staff.getSurname(); }
            else{ s.subject=c.bloodBank.getName();}
            s.text=c.text;
            s.answer=c.answer;
            s.id=c.Id;
            response.add(s);
        }
        return response;
    }

   }