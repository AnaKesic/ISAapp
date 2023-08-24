package ISA.Model;

import ISA.Model.DTO.SingupRequest;
import ISA.enums.Gender;

import ISA.enums.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Address address;
    private String phoneNum;
    private Gender gender;
    private String job;
    private String jobInfo;
    private Long jmbg;
    private boolean activated;
    private Role role;
    public User(String email, String password, String name, String surname, Role role) {
        this.email = email;
        this.password=password;
        this.name= name;
        this.surname= surname;
        this.role=role;
    }

    public User(SingupRequest dto){
    this.email=dto.getEmail();
    this.password=dto.getPassword();
    this.name=dto.getName();
    this.surname=dto.getSurname();
    this.address=new Address(dto.getStreet(),dto.getNumber(),dto.getCity(), dto.getState());
    this.phoneNum=dto.getPhoneNum();
    this.job=dto.getJob();
    this.gender=(dto.getGender());
    this.jmbg=dto.getJmbg();
    this.activated=false;
    this.jobInfo=dto.getJobInfo();
    this.role=Role.Korisnik;



}


}
