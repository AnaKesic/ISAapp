package ISA.Model;

import ISA.Model.DTO.SingupRequest;
import ISA.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Donor extends User {
    @OneToMany
    private List<Appointment> AppointmentList = new ArrayList<>();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Questionnaire questionnaire;
    @OneToMany
    public List<Complaint> complaints = new ArrayList<>();

    public Donor(SingupRequest dto) {
        this.setEmail(dto.getEmail());
        this.setPassword(dto.getPassword());
        this.setName(dto.getName());
        this.setSurname(dto.getSurname());
        this.setAddress(new Address(dto.getStreet(), dto.getNumber(), dto.getCity(), dto.getState()));
        this.setPhoneNum(dto.getPhoneNum());
        this.setJob(dto.getJob());
        this.setJobInfo(dto.getJobInfo());
        this.setGender((dto.getGender()));
        this.setJmbg(dto.getJmbg());
        this.setActivated(false);
        this.setJobInfo(dto.getJobInfo());
        this.setRole(Role.Donor);


    }
}