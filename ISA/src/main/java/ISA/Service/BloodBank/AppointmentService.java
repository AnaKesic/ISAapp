package ISA.Service.BloodBank;

import ISA.Model.Appointment;
import ISA.Model.DTO.SheduleAppointmentDTO;
import ISA.Model.Donor;
import ISA.Repository.AppointmentsRepository;
import ISA.Repository.KorisnikRepository;
import ISA.Service.EmailSender;
import ISA.enums.AppStatus;
import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
     @Autowired
     private AppointmentsRepository _appointmentrepository;
     @Autowired
     private KorisnikRepository _userRepository;
     @Autowired
     private QRCodeService _qrcodeService;

     @Autowired
     private EmailSender _emailSender;
    public Appointment sheduleAppointment(SheduleAppointmentDTO dto) throws IOException, WriterException{
        Appointment app=_appointmentrepository.findById(dto.appointmentId).get();
        Donor donor =(Donor) _userRepository.findByEmail(dto.donorEmail);
        List<Appointment> apps=donor.getAppointmentList();
        if(app.status!= AppStatus.Free){
            throw new Error("Sorry this appointment isn't available anymore");
        }
        for (String email : app.getBlackList()) {
            if (email.equals(dto.donorEmail)) {
                throw new Error("You alredy scheduled this appointment and cancel it, can shedule again!");
            }
        }
        app.setStatus(AppStatus.Sheduled);
        String qrcodeText="Vas termin za transfuziju krvi zakazen je u banci krvi "+app.getBloodBank().getName() +", datuma "+
                           app.getTime().toLocalDate() + "sa pocetkom u "+ app.getTime().toLocalTime() +
                "casova i predvidjeno vreme trajanja je "+ app.getDuration() +"minuta.";
         byte[] code= _qrcodeService.generateQRCodeImage(qrcodeText);
        app.setQRCode(code);
        donor.getAppointmentList().add(app);
        _appointmentrepository.save(app);
        _userRepository.save(donor);
        app.setPatient(donor);
        _appointmentrepository.save(app);
        _emailSender.sendQRCodeEmail(dto.donorEmail,"QR code for bloodbank appointment", code);

       return app;
    }

    public void cancelAppointment(SheduleAppointmentDTO dto) {
        Appointment app=_appointmentrepository.findById(dto.appointmentId).get();
        Donor donor =(Donor) _userRepository.findByEmail(dto.donorEmail);
        List<Appointment> apps=donor.getAppointmentList();

        if(app.status!= AppStatus.Sheduled){
            throw new Error("Sorry this appointment isn't sheduled");
        }
        app.setStatus(AppStatus.Free);
        app.setQRCode(null);
        donor.getAppointmentList().remove(app);
        app.getBlackList().add(donor.getEmail());
        _appointmentrepository.save(app);
        _userRepository.save(donor);
        app.setPatient(null);
        _appointmentrepository.save(app);

    }

    public boolean checkTime(Long appointmentId) {
        Appointment app=_appointmentrepository.findById(appointmentId).get();
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime limitTime = currentDate.minusDays(1);
        if(app.getTime().isAfter(limitTime)){
            return false;
        }
        return true;
    }
}
