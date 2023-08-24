package ISA.Model;

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
public class Donor extends User{
    @OneToMany
    private List<Appointment> AppointmentList = new ArrayList<>();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Questionnaire questionnaire;
    @OneToMany
    public List<Complaint> complaints= new ArrayList<>();

}
