package ISA.Controller;

import ISA.Model.Appointment;
import ISA.Model.User;
import ISA.Service.KorisnikService;
import ISA.enums.FilterApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/korisnik")
public class KorisnikController {

        @Autowired
        private KorisnikService _korisnikservice;

        @PostMapping("/registracija")
        public ResponseEntity<User> registracijaKorisnika(User k){
                   User kk= _korisnikservice.RegisterKorisnik(k);
                    if(k==null){
                            return new ResponseEntity<>(kk, HttpStatusCode.valueOf(404));
                    }
                    return new ResponseEntity<>(kk, HttpStatusCode.valueOf(200));
        }

       @GetMapping("/getAllDonorAppointments")
       public ResponseEntity getAllDonorAppointments(@RequestParam(name="email") String email){
             try{
             return ResponseEntity.ok().body(_korisnikservice.getAllDonorAppointments(email));}
             catch (Exception e){
                 return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(e.getMessage());
             }
        }
    @GetMapping("/getSheduledDonorAppointments")
    public ResponseEntity getSheduledDonorAppointments(@RequestParam(name="email") String email){
        try{
            return ResponseEntity.ok().body(_korisnikservice.getSheduledDonorAppointments(email));}
        catch (Exception e){
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(e.getMessage());
        }
    }

        @PostMapping("/setUpitnik")
         public ResponseEntity<User>  setUpitnik(){
            User u= new User();
            return  ResponseEntity.ok().body(u);
        }

        @GetMapping("/getAllAllowed")
        public ResponseEntity getAllAllowed(@RequestParam(name="email") String email){
           return ResponseEntity.ok( _korisnikservice.getAllAllowed(email));
        }

    @GetMapping("/getDonorComplaints")
        public ResponseEntity getDonorComplaints(@RequestParam(name="email") String email){
        return ResponseEntity.ok( _korisnikservice.getAllDonorComplaints(email));
    }
    @GetMapping("/getAdminComplaints")
    public ResponseEntity getAdminComplaints(@RequestParam(name="email") String email){
        return ResponseEntity.ok( _korisnikservice.getAllAdminComplaints(email));
    }
    @GetMapping("/getHistoryOfDonorAppointments")
    public ResponseEntity getDonorHistory(@RequestParam(name="email") String email){
            return ResponseEntity.ok(_korisnikservice.getHistoryOfDonorAppointments(email));
    }
    @GetMapping("/sortHistoryOfDonorAppointments")
    public ResponseEntity sortDonorHistory( @RequestParam String email,
                                            @RequestParam FilterApp filter){
        return ResponseEntity.ok(_korisnikservice.sortHistoryOfDonorAppointments(email, filter));
    }

}
