package ISA.cofiguration;

import ISA.Model.*;
import ISA.Repository.*;
import ISA.Service.QRCodeService;
import ISA.enums.AppStatus;
import ISA.enums.Role;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeedingData {

    @EventListener
    public void seed(ContextRefreshedEvent event) throws IOException, WriterException {
        seedData();
    }

    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private AppointmentsRepository _appointmentRepository;
    @Autowired
    private BloodbankRepository _bloodbankRepository;
    @Autowired
    private QuestionTextRepository qtRepository;
  @Autowired
  private ComplaintRepository _complaintRepository;
  @Autowired
  private QuestionnaireRepository _questionnaireRepository;

  @Autowired
  private QRCodeService qr;
    private void seedData() throws IOException, WriterException {

        String[] qus= new String[]
                { "Da li ste do sada dobrovoljno davali krv ili komponente krvi?",
                   "Da li ste ikada bili odbijeni kao davalac krvi ili komponente krvi?",
                "Da li se trenutno osećate zdravim, sposobnim i odmornim da date krv ili komponente krvi?",
                "Da li ste nešto jeli pre dolaska na davanje krvi ili komponente krvi?",
                "Da li se bavite opasnim zanimanjem ili hobijem?",
                "Da li redovno (svakodnevno) uzimate bilo kakve lekove?",
                "Da li ste poslednja 2-3 dana uzimali bilo kakve lekove (npr. Brufen, Kafetin, Analgin...)?",
                "Da li stalno uzimate Aspirin (Cardiopirin)? Da li ste ga uzimali u poslednjih 5 dana?",
                "Da li ste do sada ispitivani ili lečeni u bolnici ili ste trenutno na ispitivanju ili bolovanju?",
                "Da li ste vadili zub u proteklih 7 dana?",
                "Da li ste u poslednjih 7 do 10 dana imali temperaturu preko 38 C, kijavicu, prehladu ili uzimali antibiotike?",
                "Da li ste primili bilo koju vakcinu ili serum u proteklih 12 meseci?",
                "Da li ste u poslednjih 6 meseci naglo izgubili na težini?",
                "Da li ste imali ubode krpelja u proteklih 12 meseci i da li ste se zbog toga javljali lekaru?",
                "Da li ste ikada lečeni od epilepsije (padavice), šećerne bolesti, astme, tuberkuloze, infarkta, moždanog udara, malignih oboljenja, mentalnih bolesti ili malarije?",
                "Da li bolujete od neke druge hronične bolesti: srca, pluća, bubrega, jetre, želuca i creva, kostiju i zglobova, nervnog sistema, krvi i krvnih sudova?",
                "Da li ste ikada imali problema sa štitastom žlezdom, hipofizom i/ili primali hormone?",
                "Da li imate neke promene na koži ili bolujete od alergije?",
                "Da li dugo krvarite posle povrede ili spontano dobijate modrice?",
                "Da li ste u proteklih 6 meseci imali neku operaciju ili primili krv?",
                "Da li ste u proteklih 6 meseci putovali ili živeli u inostranstvu?",
                "Da li ste u proteklih 6 meseci imali akupunkturu, pirsing ili tetovažu?",
                "Da li ste pili alkohol u poslednjih 6 sati?",
                "Da li ste bolovali ili bolujete od hepatitisa (žutice) A, B ili C?",
                "Da li ste bili u kontaktu ili živite sa osobom obolelom od hepatitisa (žutice)?",
                "Da li mislite da je postojala mogućnost da se zarazite HIV-om?",
                "Da li ste ikada koristili bilo koju vrstu droge?",
                "Da li ste ikada koristili preparate koji se zvanično ne izdaju na recept i/ili preparate za bodi bilding (steroide)?",
                "Da li ste ikada za pružanje seksualnih usluga uzimali novac ili drogu?",
                "Da li znate na koje sve načine ste mogli izložiti sebe riziku od zaraznih, krvlju prenosivih bolesti?",
                "Da li ste imali seksualne odnose tokom proteklih 6 meseci bez zaštite sa osobom koja ima ili je imala hepatitis (žuticu) B ili C?",
                "Da li ste imali seksualne odnose tokom proteklih 6 meseci bez zaštite sa osobom koja je HIV pozitivna?",
                "Da li ste imali seksualne odnose tokom proteklih 6 meseci bez zaštite sa osobom koja je ikada za pružanje seksualnih usluga uzimala novac ili drogu?",
                "Da li ste imali seksualne odnose tokom proteklih 6 meseci bez zaštite sa osobom koja je ikada koristila bilo koju vrstu droge na bilo koji način?",
                "Da li ste imali seksualne odnose tokom proteklih 6 meseci bez zaštite sa osobom čije Vas je dotadašnje seksualno ponašanje moglo dovesti u rizik dobijanja seksualno prenosive bolesti?",
                "Da li ste imali seksualne odnose tokom proteklih 6 meseci bez zaštite da li ste Vi imali analne seksualne odnose tokom proteklih 6 meseci?",
                "Da li ste u drugom stanju?",
                "Da li trenutno imate menstruaciju?",
                "Da li ste u poslednjih 6 meseci imali porođaj ili prekid trudnoće?"};
      for(int i =0; i<qus.length; i++){
          QuestionText q=new QuestionText((long) i,qus[i]);
          qtRepository.save(q);
      }



        Donor donor= new Donor();
        donor.setEmail("andykesic123@gmail.com");
        donor.setPassword(new BCryptPasswordEncoder().encode("admin123"));
        donor.setActivated(true);
        donor.setRole(Role.Donor);
        donor.setAddress(new Address("stefa nem","13","kac","ssasa"));
        Questionnaire q= new Questionnaire();
        q.setAddmited(LocalDateTime.of(2023,8,05,10,30));
        donor.setQuestionnaire(q);
        _userRepository.save(donor);
        q.setDonor(donor);
        _questionnaireRepository.save(q);



        SystemAdmin admin = new SystemAdmin();
        admin.setName("Nikola");
        admin.setSurname("Antonic");
        admin.setEmail("admin@mail.com");
        admin.setAddress(new Address("fdf","dsd","dsad","dasd"));
        admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
        admin.setRole(Role.SystemAdmin);
        admin.setActivated(true);
        _userRepository.save(admin);

        Staff staff1= new Staff();
        staff1.setName("Janko");
        staff1.setSurname("Tipsarevic");
        staff1.setEmail("janko@mail.com");
        staff1.setPassword(new BCryptPasswordEncoder().encode("janko123"));
        staff1.setRole(Role.SystemAdmin);
        staff1.setActivated(true);


        Staff staff2= new Staff();
        staff2.setName("Lenka");
        staff2.setSurname("Jaksic");
        staff2.setEmail("lenka@mail.com");
        staff2.setPassword(new BCryptPasswordEncoder().encode("lenka123"));
        staff2.setRole(Role.SystemAdmin);
        staff2.setActivated(true);


        Staff staff3= new Staff();
        staff3.setName("Kolja");
        staff3.setSurname("Belic");
        staff3.setEmail("kolja@mail.com");
        staff3.setPassword(new BCryptPasswordEncoder().encode("kolja123"));
        staff3.setRole(Role.SystemAdmin);
        staff3.setActivated(true);


        Staff staff4= new Staff();
        staff4.setName("Gala");
        staff4.setSurname("Krunic");
        staff4.setEmail("gala@mail.com");
        staff4.setPassword(new BCryptPasswordEncoder().encode("gala123"));
        staff4.setRole(Role.SystemAdmin);
        staff4.setActivated(true);







        Appointment app1 = new Appointment();
        app1.status= AppStatus.Free;
        app1.duration=30;
        app1.time= LocalDateTime.of(2023, 8, 02, 12,40);


        Appointment app2 = new Appointment();
        app2.status= AppStatus.Free;
        app2.duration=30;
        app2.time= LocalDateTime.of(2023, 8, 02,13,30);

      Appointment appDonor2= new Appointment();
      appDonor2.setTimeofSheduling(LocalDateTime.of(2023,8,16,10,30));

      appDonor2.setTime(LocalDateTime.of(2022,8,22,16,30));
      appDonor2.setStatus(AppStatus.Finished);
      appDonor2.setDuration(30);
      appDonor2.setPrice(3000);


      Appointment appDonor3= new Appointment();
      appDonor3.setTimeofSheduling(LocalDateTime.of(2023,8,23,10,30));

      appDonor3.setTime(LocalDateTime.of(2022,8,30,16,30));
      appDonor3.setStatus(AppStatus.Declined);
      appDonor3.setDuration(35);
      appDonor3.setPrice(4000);

        List<Appointment> applist1= new ArrayList<>();
        applist1.add(app1);
        applist1.add(app2);
        applist1.add(appDonor2);
        applist1.add(appDonor3);

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


      Appointment appDonor1= new Appointment();
      appDonor1.setTimeofSheduling(LocalDateTime.of(2023,8,14,13,30));
      appDonor1.setTime(LocalDateTime.of(2022,8,16,14,30));
      appDonor1.setStatus(AppStatus.Finished);
      appDonor1.setDuration(40);
      appDonor1.setPrice(2000);

        List<Appointment> applist2= new ArrayList<>();
        applist2.add(app3);
        applist2.add(app4);
        applist2.add(app5);
        applist2.add(appDonor1);
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
        appDonor2.setBloodBank(bb1);
        appDonor2.setDoctor(staff2);
        appDonor3.setBloodBank(bb1);
        appDonor3.setDoctor(staff2);
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
        appDonor1.setBloodBank(bb2);
        appDonor1.setDoctor(staff4);
      _bloodbankRepository.save(bb2);

      donor.getAppointmentList().add(appDonor1);
      donor.getAppointmentList().add(appDonor2);
      donor.getAppointmentList().add(appDonor3);


      Complaint c= new Complaint();
      c.Id=1l;
      c.text="complaint1";
      c.bloodBank=bb2;
      c.donor=donor;
      donor.getComplaints().add(c);

      _complaintRepository.save(c);
      _userRepository.save(donor);

        _userRepository.save(admin);
      appDonor1.setPatient(donor);
      appDonor1.setQRCode(qr.generateQRCodeImage("Banka krvi:"+appDonor1.getBloodBank().getName()+"-Adresa:"+appDonor1.getBloodBank().getAddress().street+", "+appDonor1.getBloodBank().getAddress().number+", "+appDonor1.getBloodBank().getAddress().city+", "+appDonor1.getBloodBank().getAddress().state+"-Doktor:"+appDonor1.getDoctor().getName()+" "+ appDonor1.getDoctor().getSurname()+"-Status:"+appDonor1.getStatus().toString()+"-Datum:" +appDonor1.getTime()+"-Datum rezervisanja:" +appDonor1.getTimeofSheduling()));
      appDonor2.setPatient(donor);
      appDonor2.setQRCode(qr.generateQRCodeImage("Banka krvi:"+appDonor2.getBloodBank().getName()+"-Adresa:"+appDonor2.getBloodBank().getAddress().street+", "+appDonor2.getBloodBank().getAddress().number+", "+appDonor2.getBloodBank().getAddress().city+", "+appDonor2.getBloodBank().getAddress().state+"-Doktor:"+appDonor2.getDoctor().getName()+" "+ appDonor2.getDoctor().getSurname()+"-Status:"+appDonor2.getStatus().toString()+"-Datum:" +appDonor2.getTime()+"-Datum rezervisanja:" +appDonor2.getTimeofSheduling()));
      appDonor3.setPatient(donor);
      appDonor3.setQRCode(qr.generateQRCodeImage("Banka krvi:"+appDonor3.getBloodBank().getName()+"-Adresa:"+appDonor3.getBloodBank().getAddress().street+", "+appDonor3.getBloodBank().getAddress().number+", "+appDonor3.getBloodBank().getAddress().city+", "+appDonor3.getBloodBank().getAddress().state+"-Doktor:"+appDonor3.getDoctor().getName()+" "+ appDonor3.getDoctor().getSurname()+"-Status:"+appDonor1.getStatus().toString()+"-Datum:" +appDonor3.getTime()+"-Datum rezervisanja:" +appDonor3.getTimeofSheduling()));

      _appointmentRepository.save(appDonor1);
      _appointmentRepository.save(appDonor2);
      _appointmentRepository.save(appDonor3);



    }

}