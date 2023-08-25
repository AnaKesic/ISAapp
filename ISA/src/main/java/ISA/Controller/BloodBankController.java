package ISA.Controller;

import ISA.Model.Appointment;
import ISA.Model.BloodBank;
import ISA.Service.BloodBankService;
import ISA.Service.ComplaintServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloodbank")
public class BloodBankController {
  @Autowired
  private BloodBankService bloodBankService;

  @GetMapping("/getAll")
  public ResponseEntity<List<BloodBank>> getAll() {

    return ResponseEntity.ok().body(bloodBankService.getAll());

  }

  @GetMapping("/getAllAppointments")
  public ResponseEntity<List<Appointment>> getAllAppointments(@RequestParam(name="Id") Long e) {
    return ResponseEntity.ok().body(bloodBankService.getAllAppointments(e));
  }
}