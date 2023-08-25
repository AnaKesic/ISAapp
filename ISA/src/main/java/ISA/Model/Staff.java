package ISA.Model;

import ISA.Model.DTO.NewEmpoyeeDTO;
import ISA.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Staff extends User {
    @JsonIgnore
    @ManyToOne
    private BloodBank bloodBank;
    @OneToMany
    private List<Appointment> appointmentListStaff;

    public Staff(NewEmpoyeeDTO e){
        this.setName(e.name);
        this.setSurname(e.surname);
        this.setEmail(e.email);
        this.setPassword(e.password);
        this.setRole(Role.SystemAdmin);
        this.setActivated(true);
    }
}
