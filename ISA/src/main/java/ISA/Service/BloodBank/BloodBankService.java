package ISA.Service.BloodBank;

import ISA.Model.*;
import ISA.Model.DTO.BloodBankDTO;
import ISA.Model.DTO.NewEmpoyeeDTO;
import ISA.Repository.AddressesRepository;
import ISA.Repository.AppointmentsRepository;
import ISA.Repository.BloodbankRepository;
import ISA.Repository.KorisnikRepository;
import ISA.enums.Role;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.Callable;



@Service
public class BloodBankService {
    @Autowired
    private BloodbankRepository bloodbankrepository;
    @Autowired
    private AddressesRepository addressesRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;


   /* public BloodBank addNew(BloodBankDTO bbb){
        BloodBank bb= new BloodBank();
        bb.setName(bbb.getName());
        Address a= new Address(bbb.getAddress().street,bbb.getAddress().number,bbb.getAddress().city,bbb.getAddress().state);
        bb.setAddress(a);
        bb.setDescription(bbb.getDescription());
        SystemAdmin admin= (SystemAdmin)korisnikRepository.findByEmail(bbb.getAdmin());

        bb.setAdmin((SystemAdmin)korisnikRepository.findByEmail(bbb.getAdmin()));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        List<Staff> staff= new ArrayList<Staff>();
        for (NewEmpoyeeDTO e: bbb.getEmployees()) {
            Staff existingStaff = (Staff) korisnikRepository.findByEmail(e.email);
            if (existingStaff != null) {
                existingStaff.setBloodBank(bb);
                staff.add(existingStaff);

            } else {
                String endodedPass= encoder.encode(e.getPassword());
                e.setPassword(endodedPass);
                Staff newStaff = new Staff(e);
                newStaff.setBloodBank(bb);
                staff.add(newStaff);

            }
        }
        bb.setStaff(staff);
        bloodbankrepository.save(bb);
        return(bb);
    }
*/
    public List<BloodBank> getAll(){
          return bloodbankrepository.findAll();
    }


    public List<Appointment> getAllAppointments(Long id){
        BloodBank bb= bloodbankrepository.findById(id).get();
        List<Appointment> apps=bb.getAppointments();

        return bb.getAppointments();
    }





}
