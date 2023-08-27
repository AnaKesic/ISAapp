package ISA.Service;

import ISA.Model.Appointment;
import ISA.Model.DTO.SheduleAppointmentDTO;
import ISA.Model.Donor;
import ISA.Repository.AppointmentsRepository;
import ISA.Repository.UserRepository;
import ISA.enums.AppStatus;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentsRepository _appointmentrepository;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private ISA.Service.QRCodeService _qrcodeService;

    @Autowired
    private EmailSender _emailSender;
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Appointment sheduleAppointment(SheduleAppointmentDTO dto) throws IOException, WriterException {
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
        app.setTimeofSheduling(LocalDateTime.now());
        String qrcodeText="Banka krvi:"+app.getBloodBank().getName()+"-Adresa:"+app.getBloodBank().getAddress().street+", "+app.getBloodBank().getAddress().number+", "+app.getBloodBank().getAddress().city+", "+app.getBloodBank().getAddress().state+"-Doktor:"+app.getDoctor().getName()+" "+ app.getDoctor().getSurname()+"-Status:Sheduled"+"-Datum:" +app.getTime()+"-Datum rezervisanja:" +app.getTimeofSheduling();
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

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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
