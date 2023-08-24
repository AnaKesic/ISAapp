package ISA.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Complaint {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long Id;
    public String text;
    public String answer;
    @JsonIgnore
    @ManyToOne
    public BloodBank bloodBank;
    @JsonIgnore
    @ManyToOne
    public Staff staff;
    @JsonIgnore
    @ManyToOne
    public Donor donor;
    @JsonIgnore
    @ManyToOne
    public SystemAdmin admin;

}
