package ISA.Service;

import ISA.Model.*;
import ISA.Model.DTO.*;
import ISA.Repository.BloodbankRepository;
import ISA.Repository.QuestionnaireRepository;
import ISA.Repository.UserRepository;
import ISA.enums.AppStatus;
import ISA.enums.FilterApp;
import ISA.enums.FilterQR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository _korisnkRepository;

    @Autowired
    private EmailSender _emailSender;

    @Autowired
    private BloodbankRepository _bbRepository;
    @Autowired
    private QuestionnaireRepository _qRepository;
    @Override
    public Donor RegisterKorisnik(Donor k) {
         Donor kk=(Donor) _korisnkRepository.findByEmail(k.getEmail());
         if(kk==null){

             _korisnkRepository.save(k);
             ActivateEmail(_korisnkRepository.findByEmail(k.getEmail()));
             return k;

         }

        return  kk;
    }

    @Override
    public List<AppointmentDTO> getAllDonorAppointments(String email) {
        Donor d=(Donor) _korisnkRepository.findByEmail(email);
        List<Appointment> apps=d.getAppointmentList();
        List<AppointmentDTO> appsdto=new ArrayList<>();
        for (Appointment a : apps) {
            AppointmentDTO ap= new AppointmentDTO(a);
            appsdto.add(ap);
        }
        return appsdto;
    }

    @Override
    public List<AppointmentDTO> getSheduledDonorAppointments(String email) {
        Donor d = (Donor) _korisnkRepository.findByEmail(email);
        List<Appointment> apps = d.getAppointmentList().stream()
                .filter(appointment -> appointment.getStatus() == AppStatus.Sheduled)
                .collect(Collectors.toList());
        List<AppointmentDTO> appsdto=new ArrayList<>();
        for (Appointment a : apps) {
            AppointmentDTO ap= new AppointmentDTO(a);
            appsdto.add(ap);
        }
        return appsdto;
    }
        @Override
        public List<AppointmentDTO> getHistoryOfDonorAppointments(String email) {
            Donor d=(Donor) _korisnkRepository.findByEmail(email);
            List<Appointment> apps=  d.getAppointmentList().stream()
                    .filter(appointment -> appointment.getStatus() == AppStatus.Finished||
                            appointment.getStatus()==AppStatus.Declined)
                    .collect(Collectors.toList());
            List<AppointmentDTO> appsdto=new ArrayList<>();
            for (Appointment a : apps) {
                AppointmentDTO ap= new AppointmentDTO(a);
                appsdto.add(ap);
            }
            return appsdto;
    }

    @Override
    public List<AppointmentDTO> sortHistoryOfDonorAppointments(String email, FilterApp filter) {
        List<AppointmentDTO> unsorted= getHistoryOfDonorAppointments(email);
        switch(filter){
            case date_b_l :
            {
                Collections.sort(unsorted, new Comparator<AppointmentDTO>() {
                    @Override
                    public int compare(AppointmentDTO event1, AppointmentDTO event2) {
                        return event2.time.compareTo(event1.time);
                    }
                });
                return unsorted;
            }
            case date_l_b :
            {
                Collections.sort(unsorted, new Comparator<AppointmentDTO>() {
                    @Override
                    public int compare(AppointmentDTO event1, AppointmentDTO event2) {
                        return event1.time.compareTo(event2.time);
                    }
                });
                return unsorted;
            }
            case price_b_l: {
                Collections.sort(unsorted, new Comparator<AppointmentDTO>() {
                    @Override
                    public int compare(AppointmentDTO product1, AppointmentDTO product2) {
                        return Double.compare(product2.price, product1.price);
                    }

                });
                return unsorted;
            }

            case price_l_b: {
                Collections.sort(unsorted, new Comparator<AppointmentDTO>() {
                    @Override
                    public int compare(AppointmentDTO product1, AppointmentDTO product2) {
                        return Double.compare(product1.price, product2.price);
                    }
                });
                return unsorted;
            }
            case duration_b_l: {
                Collections.sort(unsorted, new Comparator<AppointmentDTO>() {
                    @Override
                    public int compare(AppointmentDTO product1, AppointmentDTO product2) {
                        return Double.compare(product2.duration, product1.duration);
                    }
                });
                return unsorted;
            }
            case duration_l_b: {
                Collections.sort(unsorted, new Comparator<AppointmentDTO>() {
                    @Override
                    public int compare(AppointmentDTO product1, AppointmentDTO product2) {
                        return Double.compare(product1.duration, product2.duration);
                    }
                });
                return unsorted;
            }
            case bb_z_a: {
                Collections.sort(unsorted, new Comparator<AppointmentDTO>() {
                    @Override
                    public int compare(AppointmentDTO person1, AppointmentDTO person2) {
                        return person2.bloodBank.compareTo(person1.bloodBank);
                    }
                });
                return unsorted;
            }
            case bb_a_z: {
                Collections.sort(unsorted, new Comparator<AppointmentDTO>() {
                    @Override
                    public int compare(AppointmentDTO person1, AppointmentDTO person2) {
                        return person1.bloodBank.compareTo(person2.bloodBank);
                    }
                });
                return unsorted;
            }
            case doctor_a_z: {
                Collections.sort(unsorted, new Comparator<AppointmentDTO>() {
                    @Override
                    public int compare(AppointmentDTO person1, AppointmentDTO person2) {
                        return person1.doctor.compareTo(person2.doctor);
                    }
                });
                return unsorted;
            }
            case doctor_z_a: {
                Collections.sort(unsorted, new Comparator<AppointmentDTO>() {
                    @Override
                    public int compare(AppointmentDTO person1, AppointmentDTO person2) {
                        return person2.doctor.compareTo(person1.doctor);
                    }
                });
                return unsorted;
            }
            default:
                System.out.println("Invalid FILTER.");
                return unsorted;
        }


    }

    @Override
    public List<AppointmentDTO> sortQROfDonorAppointments(String email, FilterQR filter) {
        List<AppointmentDTO> apps = getAllDonorAppointments(email);
        switch (filter) {
            case date_b_l: {
                Collections.sort(apps, new Comparator<AppointmentDTO>() {
                    @Override
                    public int compare(AppointmentDTO event1, AppointmentDTO event2) {
                        return event2.sheduledTime.compareTo(event1.sheduledTime);
                    }
                });
                return apps;
            }
            case date_l_b: {
                Collections.sort(apps, new Comparator<AppointmentDTO>() {
                    @Override
                    public int compare(AppointmentDTO event1, AppointmentDTO event2) {
                        return event1.sheduledTime.compareTo(event2.sheduledTime);
                    }
                });
                return apps;
            }
            case n:
                return apps.stream()
                        .filter(appointment -> appointment.status == AppStatus.Sheduled)
                        .collect(Collectors.toList());
            case c:
                return apps.stream()
                        .filter(appointment -> appointment.status == AppStatus.Declined)
                        .collect(Collectors.toList());
            case f:
                return apps.stream()
                        .filter(appointment -> appointment.status == AppStatus.Finished)
                        .collect(Collectors.toList());
            default:
                return apps;
        }
    }
    @Override
    public DataForComplaintDTO getAllAllowed(String email) {
        DataForComplaintDTO data= new DataForComplaintDTO();
        Donor d=(Donor) _korisnkRepository.findByEmail(email);
        List<Appointment> apps=  d.getAppointmentList().stream()
                .filter(appointment -> appointment.getStatus() == AppStatus.Finished)
                .collect(Collectors.toList());

        for (Appointment app: apps) {
                 BloodBank bb = _bbRepository.findById(app.bloodBank.getId()).get();
                 List<Staff> bbs =bb.getStaff().stream().toList();
            for (Staff s: bbs) {
                StaffDTO staff= new StaffDTO();
                staff.setFullName(app.doctor.getName()+" "+app.doctor.getSurname());
                staff.setEmail(app.doctor.getEmail());
                data.getStaff().add(staff);
            }
            List<StaffDTO> uniqueStaff = data.getStaff().stream()
                    .collect(Collectors.toMap(StaffDTO::getEmail, person -> person, (existing, replacement) -> existing))
                    .values()
                    .stream()
                    .collect(Collectors.toList());
            data.setStaff(uniqueStaff);
            BBDTO bbdto= new BBDTO();
                 bbdto.setId(bb.getId());
                 bbdto.setName(bb.getName());
                 data.getBloodBank().add(bbdto);
        }

        return data;
    }

    @Override
    public List<ShowComplaintDTO> getAllAdminComplaints(String email) {
        SystemAdmin admin=(SystemAdmin) _korisnkRepository.findByEmail(email);
        List<Complaint> comp= admin.getComplaintsAdmin();
        List<ShowComplaintDTO> response= new ArrayList<>();
        for (Complaint c:comp) {
            ShowComplaintDTO s= new ShowComplaintDTO();
            if(c.bloodBank==null){ s.subject=c.staff.getName()+ " "+c.staff.getSurname(); }
            else{ s.subject=c.bloodBank.getName();}
            s.text=c.text;
            s.answer=c.answer;
            response.add(s);
        }
       return response;
    }
    @Override
    public List<ShowComplaintDTO> getAllDonorComplaints(String email) {
        Donor donor=(Donor) _korisnkRepository.findByEmail(email);
        List<Complaint> comp= donor.getComplaints();
        List<ShowComplaintDTO> response= new ArrayList<>();
        for (Complaint c:comp) {
            ShowComplaintDTO s= new ShowComplaintDTO();
            if(c.bloodBank==null){ s.subject=c.staff.getName()+ " "+c.staff.getSurname(); }
            else{ s.subject=c.bloodBank.getName();}
            s.text=c.text;
            s.answer=c.answer;
            response.add(s);
        }
        return response;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void IsDonorExceptable(String email){
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime sixMonthsAgo = currentDate.minusMonths(6);
        Donor donor=(Donor) _korisnkRepository.findByEmail(email);
        Questionnaire q= _qRepository.findByDonorId(donor.getId());

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



    public void ActivateEmail(User k) {
     _emailSender.sendActivationEmail(k.getEmail(),"Aktivacija naloga", "Posetite link da aktivirate profil u nasoj banci:" + "http://localhost:3000/activate/"+k.getId());

    }

}
