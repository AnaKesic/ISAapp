package ISA.Model;

import ISA.enums.AppStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="APPOINTMENTS")
public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long Id;
    public LocalDateTime time;
    public int duration;

    @ManyToOne
    public Staff doctor;
    @JsonIgnore
    @ManyToOne
    public Donor patient;
    @JsonIgnore
    @ManyToOne
    public BloodBank bloodBank;

    public List<String> blackList = new ArrayList<>();
    public int price;
    public AppStatus status;

    public byte[] QRCode;
}
