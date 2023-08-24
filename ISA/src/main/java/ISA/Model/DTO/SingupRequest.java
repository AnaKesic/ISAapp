package ISA.Model.DTO;

import ISA.enums.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingupRequest {

    private String email;
    private String password;
    private String name;
    private String surname;
    private String street;
    private String number;
    private String city;
    private String state;
    private String phoneNum;
    private Gender gender;
    private String job;
    private String jobInfo;
    private Long jmbg;

}
