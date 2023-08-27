package ISA.Service;

import ISA.Model.Appointment;
import ISA.Model.BloodBank;
import ISA.Repository.AddressesRepository;
import ISA.Repository.BloodbankRepository;
import ISA.Repository.UserRepository;
import ISA.enums.AppStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public  class BloodBankServiceImpl  implements BloodBankService {
    @Autowired
    private BloodbankRepository bloodbankrepository;




    public List<BloodBank> getAll(){
        return bloodbankrepository.findAll();
    }


    public List<Appointment> getAllAvailableAppointments(Long id){
        BloodBank bb= bloodbankrepository.findById(id).get();
        List<Appointment> apps=bb.getAppointments().stream()
                .filter(appointment -> appointment.getStatus() == AppStatus.Free)
                .collect(Collectors.toList());

        return apps;
    }





}
