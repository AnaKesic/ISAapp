package ISA.Service.BloodBank;

import ISA.Model.Appointment;
import ISA.Model.Donor;
import ISA.Model.Questionnaire;
import ISA.Repository.KorisnikRepository;
import ISA.enums.AppStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class UserService {
    @Autowired
    private KorisnikRepository _userRepository;



    public void IsDonorExceptable(String email){
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime sixMonthsAgo = currentDate.minusMonths(6);
        Donor donor=(Donor) _userRepository.findByEmail(email);
        Questionnaire q= donor.getQuestionnaire();

        if(q==null ){
            throw  new Error("Please answer questionnaire before sheduling appointment");
        }
        if(q.getAddmited().isBefore(sixMonthsAgo)){
            throw  new Error("Please answer questionnaire before sheduling appointment");
        }


        List<Appointment> appointments= donor.getAppointmentList();
        if(appointments!=null) {
            boolean hasAppointmentBeforeSixMonthsAgo = appointments.stream()
                    .anyMatch(appointment -> appointment.getTime().isAfter(sixMonthsAgo) &&
                            appointment.getStatus()== AppStatus.Finished);

            if (hasAppointmentBeforeSixMonthsAgo) {
                throw new Error("You cannot schedule appointment, because you gave blood less then a six mounts ago");
            }
        }

    }

}
