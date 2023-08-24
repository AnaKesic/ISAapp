package ISA.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SystemAdmin extends User{
    @JsonIgnore
    @OneToMany
    public List<BloodBank> bloodBanks;
    @OneToMany
    public List<Complaint> complaintsAdmin= new ArrayList<>();
}
