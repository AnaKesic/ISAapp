package ISA.Controller;

import ISA.Model.Appointment;
import ISA.Model.BloodBank;
import ISA.Model.DTO.BloodBankDTO;
import ISA.Model.User;
import ISA.Service.BloodBank.BloodBankService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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

 /* @PostMapping("/addNew")
  public ResponseEntity<BloodBank> addNew(@RequestBody BloodBankDTO bbb) {
    BloodBank bb = bloodBankService.addNew(bbb);
    return ResponseEntity.ok().body(bb);

  }*/

  @GetMapping("/getAllAppointments")
  public ResponseEntity<List<Appointment>> getAllAppointments(@RequestParam(name="Id") Long e) {
    return ResponseEntity.ok().body(bloodBankService.getAllAppointments(e));
  }
}