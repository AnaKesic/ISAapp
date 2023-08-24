package ISA.Model.DTO;

import ISA.Model.Address;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class BloodBankDTO {

    private String name;
    private Address address;
    private String description;
    private String admin;
    private List<NewEmpoyeeDTO> employees;

}
