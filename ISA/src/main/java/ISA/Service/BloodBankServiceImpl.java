package ISA.Service;

import ISA.Model.Appointment;
import ISA.Model.BloodBank;
import ISA.Repository.AddressesRepository;
import ISA.Repository.BloodbankRepository;
import ISA.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class BloodBankServiceImpl  implements BloodBankService {
    @Autowired
    private BloodbankRepository bloodbankrepository;




    public List<BloodBank> getAll(){
        return bloodbankrepository.findAll();
    }


    public List<Appointment> getAllAppointments(Long id){
        BloodBank bb= bloodbankrepository.findById(id).get();
        List<Appointment> apps=bb.getAppointments();

        return bb.getAppointments();
    }





}
