package ISA.Controller;

import ISA.Model.Appointment;
import ISA.Model.BloodBank;
import ISA.Model.DTO.BloodBankDTO;
import ISA.Model.DTO.SheduleAppointmentDTO;
import ISA.Service.BloodBank.AppointmentService;
import ISA.Service.BloodBank.UserService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;
    @Autowired
    UserService userService;

    @PutMapping("/sheduleAppointment")
    public ResponseEntity sheduleAppointment(@RequestBody SheduleAppointmentDTO dto) {
         try{
                  userService.IsDonorExceptable(dto.donorEmail);
                 return ResponseEntity.ok(appointmentService.sheduleAppointment(dto));

         }
         catch (Error e){
              return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(e.getMessage());
         } catch (IOException e) {
             throw new RuntimeException(e);
         } catch (WriterException e) {
             throw new RuntimeException(e);
         }


    }
    @PutMapping("/cancelAppointment")
    public ResponseEntity cancelAppointment(@RequestBody SheduleAppointmentDTO dto) {
        try {
            if (appointmentService.checkTime(dto.appointmentId)) {
                appointmentService.cancelAppointment(dto);
                return ResponseEntity.ok("Uspesno ste otkazali sastanak");
            } else {
                return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Cant cancel appointment 24h before");
            }
        }
        catch(Error e){
                return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(e.getMessage());}

    }

}
