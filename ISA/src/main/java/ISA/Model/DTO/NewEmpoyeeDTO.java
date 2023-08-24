package ISA.Model.DTO;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class NewEmpoyeeDTO {
    public String name;
    public String surname;
    public String email;
    public String password;
}
