package ISA.Model.DTO;

import ISA.enums.Gender;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SingupRequest {
    @Email
    private String email;
    @Size(min = 6)
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String street;
    @NotNull
    private String number;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String phoneNum;
    @NotNull
    private Gender gender;
    @NotNull
    private String job;
    @NotNull
    private String jobInfo;
    @NotNull
    private Long jmbg;



}


