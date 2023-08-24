package ISA.cofiguration;

import ISA.Model.*;
import ISA.Repository.AppointmentsRepository;
import ISA.Repository.BloodbankRepository;
import ISA.Repository.KorisnikRepository;
import ISA.enums.AppStatus;
import ISA.enums.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class SeedingData {

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedData();
    }

    @Autowired
    private KorisnikRepository _korisnikRepository;
    @Autowired
    private AppointmentsRepository _appointmentRepository;
    @Autowired
    private BloodbankRepository _bloodbankRepository;

    private void seedData() {

        Donor donor= new Donor();
        donor.setEmail("andykesic123@gmail.com");
        donor.setPassword(new BCryptPasswordEncoder().encode("admin123"));
        donor.setActivated(true);
        donor.setRole(Role.Korisnik);
        Questionnaire q= new Questionnaire();
        q.setAddmited(LocalDateTime.of(2023,8,05,10,30));
        donor.setQuestionnaire(q);
        _korisnikRepository.save(donor);



        SystemAdmin admin = new SystemAdmin();
        admin.setName("Nikola");
        admin.setSurname("Antonic");
        admin.setEmail("admin@mail.com");
        admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
        admin.setRole(Role.Admin_sistema);
        admin.setActivated(true);
        _korisnikRepository.save(admin);

        Staff staff1= new Staff();
        staff1.setName("Janko");
        staff1.setSurname("Tipsarevic");
        staff1.setEmail("janko@mail.com");
        staff1.setPassword(new BCryptPasswordEncoder().encode("janko123"));
        staff1.setRole(Role.Admin_centra);
        staff1.setActivated(true);


        Staff staff2= new Staff();
        staff2.setName("Lenka");
        staff2.setSurname("Jaksic");
        staff2.setEmail("lenka@mail.com");
        staff2.setPassword(new BCryptPasswordEncoder().encode("lenka123"));
        staff2.setRole(Role.Admin_centra);
        staff2.setActivated(true);


        Staff staff3= new Staff();
        staff3.setName("Kolja");
        staff3.setSurname("Belic");
        staff3.setEmail("kolja@mail.com");
        staff3.setPassword(new BCryptPasswordEncoder().encode("kolja123"));
        staff3.setRole(Role.Admin_centra);
        staff3.setActivated(true);


        Staff staff4= new Staff();
        staff4.setName("Gala");
        staff4.setSurname("Krunic");
        staff4.setEmail("gala@mail.com");
        staff4.setPassword(new BCryptPasswordEncoder().encode("gala123"));
        staff4.setRole(Role.Admin_centra);
        staff4.setActivated(true);



        Appointment app1 = new Appointment();
        app1.status= AppStatus.Free;
        app1.duration=30;
        app1.time= LocalDateTime.of(2023, 8, 02, 12,40);


        Appointment app2 = new Appointment();
        app2.status= AppStatus.Free;
        app2.duration=30;
        app2.time= LocalDateTime.of(2023, 8, 02,13,30);


        List<Appointment> applist1= new ArrayList<>();
        applist1.add(app1);
        applist1.add(app2);

        Appointment app3 = new Appointment();
        app3.status= AppStatus.Free;
        app3.duration=30;
        app3.time=LocalDateTime.of(2023, 8, 02, 12,40);


        Appointment app4 = new Appointment();
        app4.status= AppStatus.Free;
        app4.duration=30;
        app4.time=LocalDateTime.of(2023, 8, 02,10,30);


        Appointment app5 = new Appointment();
        app5.status= AppStatus.Sheduled;
        app5.duration=30;
        app5.time=LocalDateTime.of(2023, 8, 02,11,30);


        List<Appointment> applist2= new ArrayList<>();
        applist2.add(app3);
        applist2.add(app4);
        applist2.add(app5);

        BloodBank bb1= new BloodBank();
        BloodBank bb2= new BloodBank();
        bb1.setName("Banka krvi Laza Lazarevic");
        bb1.setDescription("Najbolje opremljena banka krvi u regionu. Strucan kadar i visoka tehnologija koju posedujemo u nasem centur ucinice vas pregled ugodnim. Uverite se u nas kvalitet");
        bb1.setRating(4.8);
        bb1.setAddress(new Address("Julija Gagarina", "15a","Novi Sad","Srbija" ));
        List<Staff> list1= new ArrayList<Staff>();
        list1.add(staff1);
        list1.add(staff2);
        staff1.setBloodBank(bb1);
        staff2.setBloodBank(bb1);
        bb1.setStaff(list1);
        bb1.setAdmin(admin);
        List<BloodBank> list2= new ArrayList<>();
        list2.add(bb1);
        admin.setBloodBanks(list2);
        bb1.setAppointments(applist1);
        app1.setBloodBank((bb1));
        app2.setBloodBank(bb1);
        app1.setDoctor(staff1);
        app2.setDoctor(staff2);
        _bloodbankRepository.save(bb1);

        bb2.setName("Banka krvi Bosko Buha");
        bb2.setDescription("Najbolje opremljena banka krvi u regionu. Strucan kadar i visoka tehnologija koju posedujemo u nasem centur ucinice vas pregled ugodnim. Uverite se u nas kvalitet");
        bb2.setRating(4.5);
        bb2.setAddress(new Address("Maksima Gorkog", "20","Novi Sad","Srbija" ));
        List<Staff> list22= new ArrayList<Staff>();
        list22.add(staff3);
        list22.add(staff4);
        staff3.setBloodBank(bb2);
        staff4.setBloodBank(bb2);
        bb2.setStaff(list22);
        bb2.setAdmin(admin);
        list2.add(bb2);
        admin.setBloodBanks(list2);
        bb2.setAppointments(applist2);
        app3.setBloodBank((bb2));
        app3.setDoctor(staff3);
        app4.setBloodBank(bb2);
        app4.setDoctor(staff4);
        app5.setBloodBank(bb2);
        app5.setDoctor(staff4);

        _bloodbankRepository.save(bb2);
        _korisnikRepository.save(admin);



    }

}